# Microwarehouse Java Spring Boot with Hadoop and Spark

## Environment:

IntelliJ Idea
Docker
Maven
Java 8

## Step 1: Deploy the system to the Docker

In the Terminal, go to the project folder : /microwarehouse-java-springboot

To deploy all parts, run:
```
  docker-compose up
```

## Step 2: Move the dataset file to HDFS storage

Copy dataset.csv to the namenode.
```
  docker cp dataset.csv namenode:dataset.csv
```

Go to the bash shell of namenode.
```
  docker exec -it namenode bash
```

Create a HDFS directory /data/warehouse/cuisine.

```
  hdfs dfs -mkdir -p /data/warehouse/cuisine
```

Copy dataset.csv.csv to HDFS:
```
  hdfs dfs -put dataset.csv /data/warehouse/cuisine/dataset.csv
```

## Step 3: Spring Boot app and get 10 Top cuisine types along with the number of restaurants serving them

Go to the Spring Boot app: 
http://localhost:8500 to open spring boot app page.

Click button "Process Dataset File", after a few seconds you will see the result at the same page.


Also, here is Spark master host: http://localhost:8080