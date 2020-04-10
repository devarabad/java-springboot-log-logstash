# Springboot Logstash #
Springboot Logging to Logstash

## Getting Started ##

### Requirements ###
  - JDK 8
  - Gradle
  - ELK up and running

### Running Springboot App with Docker ELK Stack ###
1. Run Docker ELK Stack
  - see https://github.com/devarabad/docker-elk-7.6

2. Update Springboot App configuration and apply the ELK host and port if necessary
  - update src/main/resources/application.properties
```
elk.logstash.host = localhost
elk.logstash.port = 9563
```

3. Run the application
```
$ ./gradlew bootRun
```

### Send Application Logs to Logstash ###
1. Call an enpoint
```
$ curl http://localhost:8081/api/books/1
```

### View Logs in Kibana ###
1. Open Kibana in a browser
  - http://localhost:5601

2. Go to Logs

3. Go to Settings and add a Log Index
  - Add the following:
    - logstash*
    - nprod*
    - prod*
    - unknown*
```
Eg. 
  filebeat-*,kibana_sample_data_logs*,logstash*,nprod*,prod*, unknown*
```

4. Apply the settings

5. Go to Stream and reload the Logs