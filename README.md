## Requisito:
- Para qualquer modalidade de execução do projeto, criar a seguinte estrutura de pastas:
 - data 
 	- input
 	- output

## Pré-requisitos
- Utilizar o openjdk version "11.0.*".
- Importar o projeto na IDE utilizando encoding UTF-8.


## Executar a aplicação via maven
```
mvn spring-boot:run
```

# Executar via docker
```
docker-compose up
```

## Criar um JAR executável
```
mvn package
```

## Executar a aplicação
```
java -jar SpringApp-0.0.1-SNAPSHOT.jar
```

## Executar os testes unitários
```
mvn test
```

## Funcionamento

- Após executar o projeto, ao ser inserido qualquer arquivo na pasta input, com a extensão *.data,
 será executado o Processo de Transmissão de dados (fake).
- Caso haja mais de um arquivo, todos serão processados em lotes.
- Para cada novo arquvo inserido, todos serão processados novamente.
- Após ser concluído o processamento dos arquivos da pasta input, será gerado um arquivo na pasta output com o resultado do
 processamento de todos os arquivos contidos na pasta input.
- Caso seja identificada inconsistência de dados em uma linha, a mesma não será transmitida, sendo informada no
 prompt de execução do projeto.
