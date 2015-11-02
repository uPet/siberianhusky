# SiberianHusky
SiberianHusky is a simple file manager for Amazon Glacier that works by follow a simple set of policies specified in a json file.

SiberianHusky carry out a simple file inventory in a MongoDB database in order to be able to manage the stored files.

Configuration file example:

{
   "filePath":"/var/backup/images.tar",
   "fileDescription":"Weekly backup",
   "vaultName":"my_glacier_vault",
   "daysToDeleteBk":14,
   "mongoHost":"localhost",
   "mongoPort":27017,
   "dataStore":"glacier_inventory",
   "awsAccessKey":"AKIA665YSSQUXCRJTYXQ",
   "awsSecretKey":"8p3gDIQJk7CIe66y66FTDHj2lyqklUcB2XyEC9R",
   "glacierEndPoint":"https://glacier.us-east-1.amazonaws.com/"
}

The above snippet specifies that files in the inventory that are older than 14 days should by deleted from Glacier and that the  "/var/backup/images.tar" file, should be uploaded to Amazon Glacier.

In order to run SiberianHusky to perform the tasks specified in the configuration file you must execute the following command:

java -jar SiberianHusky.jar /rute/to/conf.json


## Build
In order to build SiberianHusky you must have Java 8 and Maven > 3. And Run:

mvn install


