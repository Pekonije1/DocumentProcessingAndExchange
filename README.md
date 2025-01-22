## Document Processing And Exchange

### Overview

Sample project that is used for specific data extraction from files.

Project contains two microservices.

1. `DocumentService` - receives files and stores data
2. `ProcessingService` - processes files received from DocumentService and sends information back.

Communication between services is done via RabbitMq.

### Endpoints:

1. GET `/documents/{documentId}` -> get single document
2. POST: `/documents/upload` -> upload document

### How it works?

1. Document service collects files via POST endpoint, checks validity and stores locally
files, saves path to file in database(along with status of processing) and sends a message with file path to the processing queue.
2. Processing service listens for the messages on processing queue. When message arrives,
Processing Service will check the file path from the message, collect the file and process it (extract data like: price, tax, item id, etc, word is of dummy data)
3. Processing service sends successfully processed data to the completed queue. In case of process fail, message will be sent to failed queue.
In case of inability to read a message, processing service will forward it to dead queue.
4. Document Service listens to the completed and failed queue. Based from which queue message appears, Document Service will update existing document in database.


### Supported file formats and types

Example of supported files and files that contain valid data, can be found in this [directory](https://github.com/Pekonije1/DocumentProcessingAndExchange/tree/main/ProcessingService/src/test/resources/files). 

### How to run?
1. Download [Docker](https://www.docker.com/get-started/)
2. Check out the repository
3. Navigate to the project directory and execute following command:
```
docker compose up
```

