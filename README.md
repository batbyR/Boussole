# Boussole

## Introduction

This project builds an API REST allowing users to get the fastest path between two Paris subway stations.

## REST

The restful webservice was built thanks to [Spring framewok](https://spring.io/guides/gs/rest-service/).

````
API Server: localhost://path?start=stop_id&end=stop_id
````

Users can retrieve the JSON formatted shortest path thanks to HTTP clients specifying starting station and ending station (see above).

## Data

Data used in this project comes from [RATP Data](http://dataratp.download.opendatasoft.com/).

## Graph

Subway metwork is modelized as an undirected graph. The shortest path between two vertices is found unsing Dijkstra Algorithm.
