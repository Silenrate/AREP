const AWS = require('aws-sdk');

const region = 'us-east-2';

function createDynamoDbClient(regionName) {
  // Set the region
  AWS.config.update({region: regionName});
  // Use the following config instead when using DynamoDB Local
  // AWS.config.update({region: 'localhost', endpoint: 'http://localhost:8000', accessKeyId: 'access_key_id', secretAccessKey: 'secret_access_key'});
  return new AWS.DynamoDB.DocumentClient({region: regionName});
}

function createScanInput(initialIndex,finalIndex) {
  return {
        TableName: "logs",
        FilterExpression: "id >= :i and id < :f",
        ExpressionAttributeValues: {
          ":i": initialIndex,
          ":f": finalIndex
        }
    }
  /* {
    "TableName": "logs",
    "ConsistentRead": false,
    "FilterExpression": "id > :i and id < :f",
    "ExpressionAttributeValues": {
      ":i": {
        "N": initialIndex
      },
      ":f": {
        "N": finalIndex
      }
    }
  }*/
}

function readEvents(res){
  const map = {};
  res.map(log =>{
    let date = new Date(log.timestamp).toLocaleDateString("en-US");
    let event = log.event;
    if(map[date]===undefined){
      let data = {};
      data[event] = 1;
      map[date]=data;
    } else {
      let data = map[date];
      if (data[event]===undefined){
        data[event] = 1;
      } else {
        data[event] = data[event]+1;
      }
    }
  })
  return map;
}

function executeScan(dynamoDbClient, scanInput, callback) {
  // Call DynamoDB's scan API
  try {
    const scanOutput = dynamoDbClient.scan(scanInput,callback);
    // Handle scanOutput
  } catch (err) {
    handleScanError(err);
  }
}

function handleScanError(err) {
  if (!err) {
    console.error('Encountered error object was empty');
    return;
  }
  if (!err.code) {
    console.error(`An exception occurred, investigate and configure retry strategy. Error: ${JSON.stringify(err)}`);
    return;
  }
  // here are no API specific errors to handle for Scan, common DynamoDB API errors are handled below
  handleCommonErrors(err);
}

function handleCommonErrors(err) {
  switch (err.code) {
    case 'InternalServerError':
      console.error(`Internal Server Error, generally safe to retry with exponential back-off. Error: ${err.message}`);
      return;
    case 'ProvisionedThroughputExceededException':
      console.error(`Request rate is too high. If you're using a custom retry strategy make sure to retry with exponential back-off. `
        + `Otherwise consider reducing frequency of requests or increasing provisioned capacity for your table or secondary index. Error: ${err.message}`);
      return;
    case 'ResourceNotFoundException':
      console.error(`One of the tables was not found, verify table exists before retrying. Error: ${err.message}`);
      return;
    case 'ServiceUnavailable':
      console.error(`Had trouble reaching DynamoDB. generally safe to retry with exponential back-off. Error: ${err.message}`);
      return;
    case 'ThrottlingException':
      console.error(`Request denied due to throttling, generally safe to retry with exponential back-off. Error: ${err.message}`);
      return;
    case 'UnrecognizedClientException':
      console.error(`The request signature is incorrect most likely due to an invalid AWS access key ID or secret key, fix before retrying. `
        + `Error: ${err.message}`);
      return;
    case 'ValidationException':
      console.error(`The input fails to satisfy the constraints specified by DynamoDB, `
        + `fix input before retrying. Error: ${err.message}`);
      return;
    case 'RequestLimitExceeded':
      console.error(`Throughput exceeds the current throughput limit for your account, `
        + `increase account level throughput before retrying. Error: ${err.message}`);
      return;
    default:
      console.error(`An exception occurred, investigate and configure retry strategy. Error: ${err.message}`);
      return;
  }
}

exports.handler = function (event, context, callback) {
	const scanInput = createScanInput(event.a,event.b);
	const dynamoDbClient = createDynamoDbClient(region);
	executeScan(dynamoDbClient, scanInput, function (err, data) {
	  if (err) {
      console.log("Error", err);
	  } else {
	    const resMessage = data.Items;
	    console.log(readEvents(resMessage));
	    const res = {
        status: 200,
        body: readEvents(resMessage)
      };
      callback(null, res);
	  }
	});
	
	/*).then(result => {
		  console.info('Scan API call has been executed.');
		  const responseMessage = { 
        response: "Searching between "+event.a+" and "+event.b,
        data: result
		  };
		  resMessage = responseMessage;
		  console.info(responseMessage)
		  return responseMessage;
  });
  
  while (resMessage === undefined) {
    
  }*/
}