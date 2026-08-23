# FinanceMe

A Spring Boot Maven microservice for the FinanceMe banking POC.

## Requirements

- Java 17+
- Maven 3.9+

## Run with the in-memory H2 database

```powershell
cd C:\Users\raksh\Downloads\code\FinanceMe
mvn spring-boot:run
```

The API starts at `http://localhost:8080`.

## Run tests

```powershell
mvn clean test
```

## API examples

Create an account:

```powershell
Invoke-RestMethod -Method Post -Uri http://localhost:8080/createAccount -ContentType 'application/json' -Body '{"accountNo":3001,"customerName":"Test User","policy":"Credit Card","balance":5000}'
```

View a policy:

```powershell
Invoke-RestMethod http://localhost:8080/viewPolicy/1001
```

Update an account:

```powershell
Invoke-RestMethod -Method Put -Uri http://localhost:8080/updateAccount/1001 -ContentType 'application/json' -Body '{"accountNo":1001,"customerName":"Rakshith Updated","policy":"Savings Plus","balance":28000}'
```

Delete a policy:

```powershell
Invoke-RestMethod -Method Delete http://localhost:8080/deletePolicy/1001
```

Seed records are loaded from `src/main/resources/data.sql`.

## TestNG HTML report

Run the JUnit tests and the TestNG compatibility suite:

```powershell
mvn clean test -Ptestng-report
```

The TestNG report is generated under `target/surefire-reports` or `target/test-output`, depending on the Maven/TestNG provider version.

## Delivery files

- `Dockerfile` and `docker-compose.yml` build and run the service.
- `Jenkinsfile` checks out, tests, packages, builds, and runs the container on the test environment.
- `terraform/` provisions test and production EC2 instances.
- `ansible/` installs Java and Docker on those servers.
- `selenium/` contains a TestNG browser smoke test.
- `monitoring/` contains Prometheus targets and a Grafana dashboard for CPU, memory, and disk.

Run the infrastructure only after replacing placeholders such as `TEST_SERVER_IP`, `PROD_SERVER_IP`, `ubuntu_ami`, and `key_name`.

## AWS RDS MySQL

Set these environment variables and activate the MySQL profile:

```powershell
$env:DB_URL='jdbc:mysql://RDS_HOST:3306/financeme'
$env:DB_USERNAME='financeme_user'
$env:DB_PASSWORD='change-me'
mvn spring-boot:run -Dspring-boot.run.profiles=mysql
```
