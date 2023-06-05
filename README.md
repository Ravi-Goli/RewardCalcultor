# RewardsCalculator

## Pre-requisites:

- Java version 8
- MySQL Server (Username: root, Password: rewards)

## How to run the project:

- Run the sql queries in the MySQL server to load the data.
	If MySQL Workbench or any such applications are available to use, run the file queries.sql as is.

- Open a terminal, go to the project home directory and execute the following command:

	./mvnw spring-boot:run

- This should start-up the application and the API end point will be ready to use.

## Using the application:

- Open any standard browser and hit the below url format by replacing the customerID field.

	http://localhost:7071/customers/{customerID}/rewards

- Available customerID's are 1, 2, 3.
- Example: http://localhost:7071/customers/1001/rewards