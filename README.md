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

## Docker Hub

https://hub.docker.com/repository/docker/linceopen/lince-dynamic-resource

mvn clean package dockerfile:build

docker run  --name lince-dynamic-resource -p 8080:8080 -t lince-open/lince-dynamic-resource:latest

docker tag lince-open/lince-dynamic-resource:latest linceopen/lince-dynamic-resource:latest

docker push linceopen/lince-dynamic-resource:latest

## Execução
mvn spring-boot:run

