# Service Orchestration and Choice Routing

This application illustrates the orchestration of Web service calls and message queue submissions in order to fulfill an HTTP request for order fulfillment. Routing messages according to the content of their payload, the application efficiently coordinates order fulfillment with different vendors and initiates auditing of orders.

#### Foreach Processing 

An iterative processor, Foreach splits collections into elements, then processes them iteratively without losing any of the message payload. After Foreach splits a message collection and processes the individual elements, it returns a reference to the original message thus resulting in processing.

#### Content-Based Routing 

Mule has the ability to intelligently route a message through different processing pathways according to its content. Using a Choice Router, Mule uses an expression to evaluate a message's properties, or part of its payload, then routes it to a specific pathway (i.e. series of message processors). Content-based routing, as this activity is called, dynamically applies routing criteria to a message at runtime.

#### Service Orchestration 
This term applies to the activity of coordinating calls to several different Web services in order to process a single Web service request. As the name implies, an application, such as this example, can orchestrate the sequence of calls to services.  Like the conductor of an orchestra, a single Mule flow signals when to submit calls to services, ensuring that all the moving pieces work together to produce a single response.

### Assumption

This document assumes that you are familiar with Mule runtime and [About Anypoint Studio](https://docs.mulesoft.com/anypoint-studio/v/7.2/). Further, this example assumes you are familiar with XML coding, and that you have a basic understanding of [Mule flows](https://docs.mulesoft.com/mule4-user-guide/v/4.1/about-flows), SOAP as a Web service paradigm, and the practice of WSDL-first Web service development. 

This document describes the details of the example within the context of Anypoint Studio, Mule ESB graphical user interface (GUI), and includes configuration details for both the visual and XML editors. 

### Example Use Case

This example application simulates a electronics store which classifies these orders, then processes them according to their classification.

The application acquires the price for each order, sets a status for the order's result (success or failure), then audits the order and amalgamates the results into a summary reply to be sent back to the customer.

### Set Up and Run the Example 

As with the other example templates, you can create a fully-functioning applications straight out of the box in Anypoint Studio. You can tweak the configurations of these use case-based examples to create your own customized applications in Mule.
The approach to running this example is using raw SOAP that bypasses the ordering process via the GUI. To achieve this, follow the procedure bellow:

1. Open the Example project in Anypoint Studio. *Do not run the application*. 
2. Now, run order_audits.sql and orders.sql which is placed under src/main/resources to create a DB tables and set properties for DB in *mule.artifact.properties* file [Properties to be configured](#propertiestobeconfigured).
3. In the Package Explorer panel in Studio, right-click the project name, then select Run As > Mule Application. Studio runs the application and Mule is up and kicking!
4. In Postman send request to the following URL: [http://localhost:8081/IProcessOrderService/IProcessOrderPort) with this body:  

		<soapenv:Envelope 
			xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" 
			xmlns:ord="http://orders.se.mulesoft.com/">
		   <soapenv:Header/>
		   <soapenv:Body>
		      <ord:processOrder>
		            <order>
		            <orderId>12</orderId>
		            <customer>
		               <address>Main Street 123</address>
		               <firstName>John</firstName>
		               <lastName>Doe</lastName>
		            </customer>
	               <item>
	                  <manufacturer>Samsung</manufacturer>
	                  <name>s-1</name>
	                  <productId>AX02</productId>
	                  <quantity>5</quantity>
	               </item>          
	               <item>
	                  <manufacturer>Manufacture2</manufacturer>
	                  <name>s-4</name>
	                  <productId>AX06</productId>
	                  <quantity>2</quantity>
	               </item>              
		         </order>
		      </ord:processOrder>
		   </soapenv:Body>
		</soapenv:Envelope>
		
4. The result of successfully processing the order represent a message in Postman:  
	
		<soap:Envelope xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
		    <soap:Body>
		        <soap:Response xmlns:soap="http://schemas.xmlsoap.org/soap/envelope">
		            <message>Order 12 was processed successfully</message>
		        </soap:Response>
		    </soap:Body>
		</soap:Envelope>
	
### How It Works

The Service Orchestration and Choice Routing application exposes a SOAP-based Web service, which accepts order requests and processes each item inside the order. If a requested item is manufactured by Samsung, then the application routes that particular item request to an external SOAP-based Web service hosted by Samsung. The application sends all other item requests to an in-house order processor, defined in the [inhouseOrder] flow.

Regardless of who processes the request, the application stores the result for the request (success or failure) with the price, which it retrieves from an in-house RESTful Web service. Finally, the application audits the order and amalgamates the results of item requests into a summary reply to be sent back to the client. 

#### order-api-main flow

The main flow of the application uses SOAP Apikit router which accepts orders via a SOAP Web service, routes the order to the appropriate order queue according to the content of the message, and dispatches a request to initiate an audit of the order. This flow is also responsible for returning a response to the caller to confirm that the order was processed.  

#### processOrder flow
The first building block in processOrder flow receives orders entered by the user in the Web page served by the application from SOAP Apikit router.The [Choice Router](https://docs.mulesoft.com/mule4-user-guide/v/4.1/choice-router-concept) in the flow parses the message payload; if the payload defines the manufacturer as Samsung, the Choice Strategy routes the message to a [VM connector](https://docs.mulesoft.com/connectors/vm-connector) which calls the samsungOrder flow. Otherwise, the Choice Strategy routes the message to another VM connector which calls the inhouseOrder flow. When either the samsungOrder flow or the inhouseOrder flow replies, the orderService flow enriches the item with the purchase receipt provided by the replying flow. When, the processOrder flow uses another VM connector to dispatch the enriched message to the auditService flow.
	
#### samsungOrder Flow 
The samsungOrder flow delegates processing of Samsung order item requests to an external, SOAP-based Web service at Samsung.

#### inhouseOrder Flow 

The inhouseOrder flow processes requests for all non-Samsung items which are stored to DB.

#### priceService Flow 
The inhouse RESTful priceService flow returns the price of non-Samsung products.

#### auditService Flow 
The auditService flow, which is invoked asynchronously by the processOrder flow, audits the item requests, which have been enriched with the responses from the inhouseOrder flow and the samsungOrder flow. Result from audit is stored to DB.

### Properties to be configured (With examples) <a name="propertiestobeconfigured"/>
In order to use this Mule Anypoint Example you need to configure properties (Credentials, configurations, etc.) either in properties file or in CloudHub as Environment Variables. Detail list with examples:

#### Application configuration
+ db.host `localhost`
+ db.port `3306`
+ db.name `dbnameA`
+ db.user `user-nameA`
+ db.password `user-passwordA`

### Go Further 

- Learn more about about the [SOAP Apikit ](https://docs.mulesoft.com/apikit/v/4.x/apikit-4-for-soap).
- Learn more about the [Choice Router](https://docs.mulesoft.com/mule4-user-guide/v/4.1/choice-router-concept).
- Learn more about the [VM connector](https://docs.mulesoft.com/connectors/vm-connector). 
- Learn more about the [Database Connector](https://docs.mulesoft.com/connectors/db-connector-index).
- Learn more about the [Foreach Scope](https://docs.mulesoft.com/mule4-user-guide/v/4.1/for-each-scope-concept).
