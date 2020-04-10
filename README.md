# Springboot Logstash
Springboot Logging to Logstash

# README #

## Getting Started ##

### Prerequisites ###
  - JDK 8
  - Gradle

### Running Springboot App with Docker ELK Stack ###
1. Run Docker ELK Stack
  - see https://github.com/devarabad/docker-elk-7.6

2. Update Springboot App configuration for ELK host and port if necessary
  - update src/main/resources/application.properties
```
elk.logstash.host = localhost
elk.logstash.port = 9563
```

3. Run the application
```
$ ./gradlew bootRun
```

### View Logs in Kibana ###
1. Open Kibana
  - http://localhost:5601

2. Go to Logs

3. Go to Settings and add a Log Index
  - Add the ff:
    - logstash*
    - nprod*
    - prod*
    - unknown*
```
Eg. 
  filebeat-*,kibana_sample_data_logs*,logstash*,nprod*,prod*, unknown*
```

4. Apply the settings

5. Go to Stream and Reload the Logs

### Send Application Logs to ELK ###
1. Call an enpoint
```
$ curl http://localhost:8081/api/books/1
```

2. Go to Kibana -> Logs -> Stream and reload the logs