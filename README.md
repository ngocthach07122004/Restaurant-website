# 🍔 Food Website

An easy-to-use, fast food ordering platform 🚀

---

## ✨ Features

- 🔐 Security (User authentication, authorization)
- 🍽️ Order food
- 💳 Pay online
- 🧑‍💼 Manage user information
- 📋 View food list
- 🛒 Manage shopping cart
- 📦 View order history
- 📝 Send feedback
- 💬 Comment on each dish

---

## 🛠️ Technology Stack

| Layer       | Tech Stack                              |
| ----------- | --------------------------------------- |
| 🔙 Backend  | Java, Spring Framework                  |
| 🌐 Frontend | HTML, CSS, JavaScript, Bootstrap, React |
| 🗃️ Database | MySQL                                   |
| ⚙️ Others   | Apache Kafka, Docker, WebSocket         |

# How to start

## To start project

To start the project here are the things to do

1. Clone project
2. Start a mysql server instance
3. Start kafka instance

### 1) To clone project

$ git clone https://github.com/ngocthach07122004/Restaurant-website.git

### 2) To Start a mysql server instance

How to use this image
Start a mysql server instance
Starting a MySQL instance is simple:

$ docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag
... where some-mysql is the name you want to assign to your container, my-secret-pw is the password to be set for the MySQL root user and tag is the tag specifying the MySQL version you want. See the list above for relevant tags.

Connect to MySQL from the MySQL command line client
The following command starts another mysql container instance and runs the mysql command line client against your original mysql container, allowing you to execute SQL statements against your database instance:

$ docker run -it --network some-network --rm mysql mysql -hsome-mysql -uexample-user -p
... where some-mysql is the name of your original mysql container (connected to the some-network Docker network).

This image can also be used as a client for non-Docker or remote instances:

$ docker run -it --rm mysql mysql -hsome.mysql.host -usome-mysql-user -p
More information about the MySQL command line client can be found in the MySQL documentation⁠

... via docker-compose⁠ or docker stack deploy⁠
Example docker-compose.yml for mysql:

# Use root/example as user/password credentials

version: '3.1'

services:

db:
image: mysql
restart: always
environment:
MYSQL_ROOT_PASSWORD: example # (this is just an example, not intended to be a production configuration)
Try in PWD

Run docker stack deploy -c stack.yml mysql (or docker compose -f stack.yml up), wait for it to initialize completely, and visit http://swarm-ip:8080, http://localhost:8080, or http://host-ip:8080 (as appropriate).

Container shell access and viewing MySQL logs
The docker exec command allows you to run commands inside a Docker container. The following command line will give you a bash shell inside your mysql container:

$ docker exec -it some-mysql bash
The log is available through Docker's container log:

$ docker logs some-mysql

### 3) To start kafka instance

Bitnami package for Apache Kafka
What is Apache Kafka?
Apache Kafka is a distributed streaming platform designed to build real-time pipelines and can be used as a message broker or as a replacement for a log aggregation solution for big data applications.

Overview of Apache Kafka⁠ Trademarks: This software listing is packaged by Bitnami. The respective trademarks mentioned in the offering are owned by the respective companies, and use of them does not imply any affiliation or endorsement.

TL;DR
docker run --name kafka bitnami/kafka:latest
Why use Bitnami Images?
Bitnami closely tracks upstream source changes and promptly publishes new versions of this image using our automated systems.
With Bitnami images the latest bug fixes and features are available as soon as possible.
Bitnami containers, virtual machines and cloud images use the same components and configuration approach - making it easy to switch between formats based on your project needs.
All our images are based on minideb⁠ -a minimalist Debian based container image that gives you a small base container image and the familiarity of a leading Linux distribution- or scratch -an explicitly empty image-.
All Bitnami images available in Docker Hub are signed with Notation⁠. Check this post⁠ to know how to verify the integrity of the images.
Bitnami container images are released on a regular basis with the latest distribution packages available.
Looking to use Apache Kafka in production? Try VMware Tanzu Application Catalog⁠, the commercial edition of the Bitnami catalog.

How to deploy Apache Kafka in Kubernetes?
Deploying Bitnami applications as Helm Charts is the easiest way to get started with our applications on Kubernetes. Read more about the installation in the Bitnami Apache Kafka Chart GitHub repository⁠.

Bitnami containers can be used with Kubeapps⁠ for deployment and management of Helm Charts in clusters.

Why use a non-root container?
Non-root container images add an extra layer of security and are generally recommended for production environments. However, because they run as a non-root user, privileged tasks are typically off-limits. Learn more about non-root containers in our docs⁠.

Only latest stable branch maintained in the free Bitnami catalog
Starting December 10th 2024, only the latest stable branch of any container will receive updates in the free Bitnami catalog. To access up-to-date releases for all upstream-supported branches, consider upgrading to Bitnami Premium. Previous versions already released will not be deleted. They are still available to pull from DockerHub.

Please check the Bitnami Premium page in our partner Arrow Electronics⁠ for more information.

Supported tags and respective Dockerfile links
Learn more about the Bitnami tagging policy and the difference between rolling tags and immutable tags in our documentation page⁠.

You can see the equivalence between the different tags by taking a look at the tags-info.yaml file present in the branch folder, i.e bitnami/ASSET/BRANCH/DISTRO/tags-info.yaml.

Subscribe to project updates by watching the bitnami/containers GitHub repo⁠.

Get this image
The recommended way to get the Bitnami Apache Kafka Docker Image is to pull the prebuilt image from the Docker Hub Registry.

docker pull bitnami/kafka:latest
To use a specific version, you can pull a versioned tag. You can view the list of available versions in the Docker Hub Registry.

docker pull bitnami/kafka:[TAG]
If you wish, you can also build the image yourself by cloning the repository, changing to the directory containing the Dockerfile and executing the docker build command. Remember to replace the APP, VERSION and OPERATING-SYSTEM path placeholders in the example command below with the correct values.

git clone https://github.com/bitnami/containers.git
cd bitnami/APP/VERSION/OPERATING-SYSTEM
docker build -t bitnami/APP:latest .
Persisting your data
If you remove the container all your data and configurations will be lost, and the next time you run the image the database will be reinitialized. To avoid this loss of data, you should mount a volume that will persist even after the container is removed.

Note: If you have already started using your database, follow the steps on backing up and restoring to pull the data from your running container down to your host.

The image exposes a volume at /bitnami/kafka for the Apache Kafka data. For persistence you can mount a directory at this location from your host. If the mounted directory is empty, it will be initialized on the first run.

Using Docker Compose:

This requires a minor change to the docker-compose.yml⁠ file present in this repository:

kafka:
...
volumes: - /path/to/kafka-persistence:/bitnami/kafka
...
NOTE: As this is a non-root container, the mounted files and directories must have the proper permissions for the UID 1001.

Connecting to other containers
Using Docker container networking⁠, an Apache Kafka server running inside a container can easily be accessed by your application containers.

Containers attached to the same network can communicate with each other using the container name as the hostname.

Using the Command Line
In this example, we will create an Apache Kafka client instance that will connect to the server instance that is running on the same docker network as the client.

Step 1: Create a network

docker network create app-tier --driver bridge
Step 2: Launch the Apache Kafka server instance

Use the --network app-tier argument to the docker run command to attach the Apache Kafka container to the app-tier network.

docker run -d --name kafka-server --hostname kafka-server \
 --network app-tier \
 -e KAFKA_CFG_NODE_ID=0 \
 -e KAFKA_CFG_PROCESS_ROLES=controller,broker \
 -e KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 \
 -e KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT \
 -e KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka-server:9093 \
 -e KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER \
 bitnami/kafka:latest
Step 3: Launch your Apache Kafka client instance

Finally we create a new container instance to launch the Apache Kafka client and connect to the server created in the previous step:

docker run -it --rm \
 --network app-tier \
 bitnami/kafka:latest kafka-topics.sh --list --bootstrap-server kafka-server:9092
Using a Docker Compose file
When not specified, Docker Compose automatically sets up a new network and attaches all deployed services to that network. However, we will explicitly define a new bridge network named app-tier. In this example we assume that you want to connect to the Apache Kafka server from your own custom application image which is identified in the following snippet by the service name myapp.

version: '2'

networks:
app-tier:
driver: bridge

services:
kafka:
image: 'bitnami/kafka:latest'
networks: - app-tier
environment: - KAFKA_CFG_NODE_ID=0 - KAFKA_CFG_PROCESS_ROLES=controller,broker - KAFKA_CFG_LISTENERS=PLAINTEXT://:9092,CONTROLLER://:9093 - KAFKA_CFG_LISTENER_SECURITY_PROTOCOL_MAP=CONTROLLER:PLAINTEXT,PLAINTEXT:PLAINTEXT - KAFKA_CFG_CONTROLLER_QUORUM_VOTERS=0@kafka:9093 - KAFKA_CFG_CONTROLLER_LISTENER_NAMES=CONTROLLER
myapp:
image: 'YOUR_APPLICATION_IMAGE'
networks: - app-tier
IMPORTANT:

Please update the YOUR_APPLICATION_IMAGE placeholder in the above snippet with your application image
In your application container, use the hostname kafka to connect to the Apache Kafka server
