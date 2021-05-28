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
  return new AWS.DynamoDB();
}

const https = require('https');

exports.handler = function (event, context, callback) {
  const nodeUrl = "https://k0qls1ffad.execute-api.us-east-2.amazonaws.com/Test/calc?from=";
  //Maximum Range Of Logs That a Mining Node Can search
  const nodesAmount = 200
  // Create the DynamoDB Client with the region you want
  const dynamoDbClient = createDynamoDbClient(region);
  dynamoDbClient.describeTable({TableName:"logs"}, function (err, data) {
    if (err) {
      console.error("Error:", JSON.stringify(err, null, 2));
    } else {
      const logsAmount = data.Table.ItemCount;
      const rangePerNode = logsAmount/nodesAmount;
      const result = {};
      const isRunning = true;
      for (let i = 0; i < logsAmount; i+=rangePerNode) {
        let dataString = '';
        const req = https.get(nodeUrl+i+"&until="+(i+rangePerNode), res => {
          res.on('data', chunk => {
            dataString += chunk;
          });
          res.on('end', () => {
            const response = JSON.parse(dataString);
            const data = response.body;
            for (var key in data) {
              if(result[key]===undefined){
                result[key]=data[key];
              } else {
                const oldData = result[key];
                const newData = data[key];
                for(var event in newData){
                  if(oldData[event]===undefined){
                    oldData[event] = newData[event];
                  } else {
                    oldData[event] = oldData[event] + newData[event];
                  }
                }
              }
            }
            if(i+rangePerNode>=logsAmount){
              console.log("Result:",result);
              callback(null, {
                statusCode: 200,
                body: result,
              });
            }
          });
        });
        req.on('error', (e) => {
          console.error(e);
        });
      }
    }});
}
