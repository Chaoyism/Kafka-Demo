# Kafka-Demo

# How to use

## Environment
```shell
# Java 1.8
$ sudo apt install openjdk-8-jdk
$ java -version

# Kafka 3.2.0
$ curl "https://dlcdn.apache.org/kafka/3.2.0/kafka_2.13-3.2.0.tgz" -o ~/Downloads/kafka.tgz
$ mkdir ~/kafka && cd ~/kafka
$ tar -xvzf ~/Downloads/kafka.tgz --strip 1 
```

## Installation
```shell
$ git clone https://github.com/Chaoyism/Kafka-Demo.git
```

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
Open the folder as a project in Intellij IDEA, build and run locally.
![Run the Application Successfully](./pic/Run_the_Application_Successfully.png)

Access Port 8080 and begin sending the messages.
```shell
http://localhost:8080/kafka/send
```
![Send the Messages](./pic/Send_the_Messages.png)

See the Throughput (for 0~99999).
![See the Throughput](./pic/See_the_Throughput.png)

## Details
This part is to respond requirements in the PRD.
### Write to Kafka
#### How to ensure that each number exists only once?


#### How to ensure that partitions have balanced loads?
The kafkaTemplate.send() function can specify the target partition id. In this application, number n will be sent to partition (n-1) % 3, so the balanced loads can be achieved.

#### How to ensure that numbers are ordered within each partition?
Because the numbers are sent in ascending order. The latter number will be sent only when the former one has been received and stored (ACK).

### Read from Kafka
#### How to guarantee the exactly-once semantic?



