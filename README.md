# TCP - Trabalho de Conclusão da Disciplina - FIAP

Projeto de conclusão da disciplina de microservices FIAP

<h2>Configurações:</h2>
Baixar imagem docker do mysql 5.6

```
docker pull mysql/mysql-server:5.6
```
Criar container com mysql e criar database e senha para usuário root
```
docker run --name mysql-tcp -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=databasetcp -d mysql:5.6
```

Construir imagem com os serviços(Observação este comando é necessário dentro do path do projeto, já que, ele procura o arquivo DockerFile)
```
docker build -f DockerFile -t tcp-fiap .
```

Rodar container com os serviços e criar link para container do mysql
```
docker run -p 8080:8080 --name tcp-fiap --link mysql-tcp:mysql -d tcp-fiap
```
