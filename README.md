# TCP - Trabalho de Conclusão da Disciplina - FIAP

Projeto de conclusão da disciplina de microservices FIAP

<h2>Configurações:</h2>
Baixa imagem docker do mysql 5.6

```
docker pull mysql/mysql-server:5.6
```
Cria container com mysql e configura senha root
```
docker run --name mysql-tcp -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=databasetcp -d mysql:5.6
```

Construir imagem aplicativo
```
docker build -f DockerFile -t tcp-fiap .
```

Rodar container do aplicativo
```
docker run -p 8080:8080 --name tcp-fiap --link mysql-tcp:mysql -d tcp-fiap
```
