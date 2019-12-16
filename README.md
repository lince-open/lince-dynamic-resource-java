# lince-dynamic-resource-java

Microservice responsavel pela parametrização de resources dinamicos.
Possui um dicionario de dados com as entidades dinamicas, faz eventuais consistencias e persiste.
O dicionario de dados fica em um banco relacional local, já os dados das entidade são persistidos no serviço lince-dynamic-resource. 

Solução

* Persistencia Spring Data e JPA
* Autenticação com Spring Security e uso de Header
* Testes Unitários com JUnit/Spock/Groove
* Testes Funcionais com JUnit/Spock/Groove
* Swagger2
* Docker

![](https://github.com/lince-open/lince-dynamic-resource-java/workflows/Java%20CI/badge.svg)
[![Known Vulnerabilities](https://snyk.io/test/github/lince-open/lince-dynamic-resource-java/badge.svg)](https://snyk.io/test/github/pedrozatta/lince-dynamic-resource-java)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-dynamic-resource-java&metric=coverage)](https://sonarcloud.io/dashboard?id=lince-open_lince-dynamic-resource-java)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=lince-open_lince-dynamic-resource-java&metric=alert_status)](https://sonarcloud.io/dashboard?id=lince-open_lince-dynamic-resource-java)

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-dynamic-resource

mvn clean package dockerfile:build

docker run  --name lince-dynamic-resource -p 8080:8080 -t lince-open/lince-dynamic-resource:latest

docker tag lince-open/lince-dynamic-resource:latest linceopen/lince-dynamic-resource:latest

docker push linceopen/lince-dynamic-resource:latest

## Execução

mvn spring-boot:run

