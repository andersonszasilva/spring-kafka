# TCP - Trabalho de Conclusão da Disciplina - FIAP

Projeto de conclusão da disciplina de microservices FIAP

<h2>Configurações - Criando com docker</h2>

Criar network
```
docker network create --driver bridge production-network
```
Baixar imagem docker do mysql 5.6

```
docker pull mysql/mysql-server:5.6
```
Criar container com mysql e criar database e senha para usuário root
```
docker run --name mysql56 -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=databasetcp --network production-network -d mysql:5.6
```

Construir imagem com os serviços (Observação: este comando é necessário dentro do path do projeto, já que, ele procura o arquivo DockerFile)
```
docker build -f DockerFile -t app .
```

Rodar container com os serviços e criar link para container do mysql
```
docker run it -p 8080:8080 --name app --network production-network -d app
```

Baixar Apache kafka

Depois de extraido rode o comando abaixo:

Once you download Kafka, you can issue a command to start ZooKeeper which is used by Kafka to store metadata.

```
.\zookeeper-server-start.bat ..\..\config\zookeeper.properties
```

Next, we need to start the Kafka cluster locally by issuing the below command.

```
.\kafka-server-start.bat ..\..\config\server.properties

```

<h2>Configurações - Criando com Docker composer</h2>

Construindo pelo arquivo docker-composer.yaml

```
docker-compose build
```

Subindo containers
```
docker-compose up -d
```