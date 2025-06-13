#!/bin/bash

# Build the application
echo "Building the application..."
mvn clean package -DskipTests

# Build and start the containers
echo "Starting the containers..."
docker-compose up --build 