```shell
### Ubuntu Update
sudo apt update

### Java
sudo apt install openjdk-11-jdk
java -version


### Kafka
# https://www.apache.org/dyn/closer.cgi?path=/kafka/3.2.0/kafka_2.13-3.2.0.tgz
# sudo apt install curl

# https://www.digitalocean.com/community/tutorials/how-to-install-apache-kafka-on-ubuntu-20-04
curl "https://dlcdn.apache.org/kafka/3.2.0/kafka_2.13-3.2.0.tgz" -o ~/Downloads/kafka.tgz
mkdir ~/kafka && cd ~/kafka
tar -xvzf ~/Downloads/kafka.tgz --strip 1

# https://kafka.apache.org/quickstart
# Step 2
```
