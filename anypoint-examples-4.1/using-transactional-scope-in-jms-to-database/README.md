# Using transactional scope in JMS to Database
This example illustrates the concept of transactional scope and a rollback-error handling strategy in a use case where data is sent from JMS to a MySQL database.

Transactions are operations in a Mule app for which the result cannot remain indeterminate. When a series of steps in a flow must succeed or fail as one unit, Mule uses a transaction to demarcate that a unit. For example, you might use a transaction to encapsulate several steps in a flow that result in committing information to a database. In this type of scenario, the commit (or transaction) is either entirely complete and succeeds, or is incomplete and fails. Even if partially complete, the commit fails. When a transaction fails, Mule rolls back the operations within the transaction so that no one part results in partial completion.

## Example Use Case
In this example the JMS endpoint listens for XML messages sent to the "in" queue. This message is logged and then inserted to a MySQL database. After the order has been inserted an error is thrown using a Script component with Groovy body. This error is handled using the On Error Propagate component which redelivers the message for a set number of times before it logs an error message. As the database connector is within the try scope, the action that inserted the order is rescinded and also next processors defined in the scope are not executed.

## Prerequisites

1. Install Apache AdminMQ and MySQL on the system where you are running Anypoint Studio.

2. Start Apache ActiveMQ server.

3. Start MySQL server, create a database, and establish a connection to the database.

## Set up and run the example

1. Open the _Using transactional scope in JMS to Database_ project in Anypoint Studio from Anypoint Exchange.

3. In the Project Explorer in Anypoint Studio, Run the `import.sql` script that is in `scr/main/resources` to create a MySQL table named `orders`.

4. Also in the Project Explorer, run the `using-transactional-scope-in-jms-to-database` project as a Mule application.

5. Go to the Apache Activemq web admin console at `http://localhost:8161/admin/send.jsp` and send the following message to the queue named `in`:
```
<order>
<itemId>1</itemId>
<itemUnits>2</itemUnits>
<customerId>1</customerId>
</order>
```

**Result:** The error occurs after SQL Insert. However, if you look at the content of `orders` table in your database by using MySQL workbench, you'll see that no record was inserted. This is because the Database and ActiveMQ connectors are within the transactional scope (Try Scope).

## Go Further
* Read about the transactional scope [here](https://docs.mulesoft.com/mule4-user-guide/v/4.1/transaction-management).
