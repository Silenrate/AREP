// ------------ NodeJS runtime ---------------
// Add aws-sdk in package.json as a dependency
// Example:
// {
//     "dependencies": {
//         "aws-sdk": "^2.0.9",
//     }
// }
// Create your credentials file at ~/.aws/credentials (C:\Users\USER_NAME\.aws\credentials for Windows users)
// Format of the above file should be:
//  [default]
//  aws_access_key_id = YOUR_ACCESS_KEY_ID
//  aws_secret_access_key = YOUR_SECRET_ACCESS_KEY

const AWS = require('aws-sdk');

// The region you of DynamoDb
const region = 'us-east-2';


function createDynamoDbClient(regionName) {
  // Set the region
  AWS.config.update({region: regionName});
  // Use the following config instead when using DynamoDB Local
  // AWS.config.update({region: 'localhost', endpoint: 'http://localhost:8000', accessKeyId: 'access_key_id', secretAccessKey: 'secret_access_key'});
  return new AWS.DynamoDB.DocumentClient({region: regionName});
}

function createPutItemInput(event,username , id) {
  var date = new Date(Date.now()).toLocaleString();
  var dateF = date.substring(0,date.length-2);
  return {
    "TableName": "logs",
    "Item": {
      "id" : id+1,
      "event": event,
      "timestamp": dateF,
      "username": username,
    }
  }
}

function createScanInput(){
  return {
    "TableName" : "logs",
    ScanIndexForward: false
  };
}


async function executePutItem(dynamoDbClient, putItemInput) {
  // Call DynamoDB's putItem API
  try {
    console.info(putItemInput);
    const putItemOutput = await dynamoDbClient.put(putItemInput).promise();
    console.info('Successfully put item.');
    // Handle putItemOutput
  } catch (err) {
    console.info(err);
    handlePutItemError(err);
  }
}

async function getMaxId(dynamoDbClient, putItemInput) {
  // Call DynamoDB's putItem API
  try {
    console.info(putItemInput);
    const putItemOutput = await dynamoDbClient.put(putItemInput).promise();
    console.info('Item obtained');
  } catch (err) {
    handleCommonErrors(err);
  }
}

// Handles errors during PutItem execution. Use recommendations in error messages below to 
// add error handling specific to your application use-case. 
function handlePutItemError(err) {
  if (!err) {
    console.error('Encountered error object was empty');
    return;
  }
  if (!err.code) {
    console.error(`An exception occurred, investigate and configure retry strategy. Error: ${JSON.stringify(err)}`);
    return;
  }
  switch (err.code) {
    case 'ConditionalCheckFailedException':
      console.error(`Condition check specified in the operation failed, review and update the condition check before retrying. Error: ${err.message}`);
      return;
    case 'TransactionConflictException':
      console.error(`Operation was rejected because there is an ongoing transaction for the item, generally safe to retry ' +
       'with exponential back-off. Error: ${err.message}`);
       return;
    case 'ItemCollectionSizeLimitExceededException':
      console.error(`An item collection is too large, you're using Local Secondary Index and exceeded size limit of` + 
        `items per partition key. Consider using Global Secondary Index instead. Error: ${err.message}`);
      return;
    default:
      break;
    // Common DynamoDB API errors are handled below
  }
  handleCommonErrors(err);
}

exports.handler = function (event, context, callback) {
  // Create the input for putItem call
	var putItemInput;
	// Create the DynamoDB Client with the region you want
  const dynamoDbClient = createDynamoDbClient(region);
   // Call DynamoDB's putItem API
  const scanInput = createScanInput();
  var id = -1;
  dynamoDbClient.scan(scanInput, function (err, data) {
    if (err) {
        console.error("Unable to query. Error:", JSON.stringify(err, null, 2));
    } else {
        data.Items.forEach((item) =>{ 
        if(item.id>id){
          id = item.id
        }}
        );
        putItemInput = createPutItemInput(event.event,event.username,id);
        executePutItem(dynamoDbClient, putItemInput).then(() => {
        });
        const res = {
        statusCode: 201,
        body: "Query succeeded."
        };
         callback(null, res);
    }
});
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
