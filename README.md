# Kafka-Demo

# How to use

## Environment
jdk 1.8
kafka 

## Installation
git clone https://github.com/Chaoyism/Kafka-Demo.git

## Running Kafka
```shell
# Start the ZooKeeper service in a terminal session.
# Note: Soon, ZooKeeper will no longer be required by Apache Kafka.
$ bin/zookeeper-server-start.sh config/zookeeper.properties

# Start the Kafka broker service in another terminal session.
$ bin/kafka-server-start.sh config/server.properties

# You need a Kafka topic with three or more partitions running.
# If you don't have one, create one using the following command:
# topicDemo is the name of the topic, remember to modify the corresponding name in application.yaml if you name the topic in another way.
$ bin/kafka-topics.sh --create --topic topicDemo --partitions 3 --bootstrap-server localhost:9092
# Create topic topicDemo
```

## Build and Run the Project
Open the folder as a project in Intellij IDEA, build and run

