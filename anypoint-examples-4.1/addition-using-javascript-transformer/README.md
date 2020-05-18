#Addition using JavaScript Transformer

This example shows how to parse an incoming JSON message and process it using the Scripting module with JavaScript code.

###Assumption

This document describes the details of the example within the context of Anypoint Studio, Mule ESBs graphical user interface (GUI). 
This document assumes that you are familiar with Mule ESB and the  [Anypoint Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). 
To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). 
Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture)

### Example Use Case

In this example, we send a JSON message containing two numbers to an HTTP endpoint. The JavaScript transformer then processes the data and adds up the numbers. The sum is then returned to the HTTP endpoint.

### Set up and Run the Example


1. Open this example project in studio

2. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **port** field to required HTTP port e.g. 8081

3. Run the example

4. Use Postman, curl or REST console to make a **POST** request using JSON. The message body should contain the following data:

	{ 
    "a": 3, "b": 4
    }

5. If the project is running successfully you should get the following message on your studio console.

		INFO  2018-08-07 10:21:26,440 [[MuleRuntime].cpuLight.06: [addition-using-javascript-transformer].addition-using-javascript-transformerFlow.CPU_LITE @33118e0b] [event: 0-df3bb720-9a1a-11e8-96d6-60d819b7426d] org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Sum is: 7.0

### How it Works ###

The [HTTP endpoint](http://www.mulesoft.org/documentation/display/current/HTTP+Connector) receives requests the end user submits to the Web service. Because it has a request-response message exchange pattern, this HTTP endpoint is responsible for both receiving and returning messages. The request body is converted from Json format to Java Object and the evaluated using execute component (Scripting module) with JavaScript body . The formatted output is returned back to the client. 

The following steps outline the process to build this application.
    
1. Drop an HTTP Connector to the flow. 
2. Click on the plus sign next to the Connector Configuration field. A dialogue for creating HTTP Listener Configuration will be displayed. Fill in:

		Host 	localhost
		Port 	8081 
   
   Click Ok button.
   	
2. Set *Path* attribute to */* value and *Allowed methods* to *POST* value.
2. Add a transformer message and convert input to Java Object.
3. Add a Execute component from Scripting module, set *Engine* like Nashorn (JavaScript) and *Code* with the following script:

		var list = eval(payload);
		var sum = 0;
		for (var i in list)
		 sum += list[i];
		payload = sum;
		
4. Add a Logger component with Message property set to:

		Sum is: #[payload]
		
5. Finish the flow by dropping a Set Payload processor with Value property set to:

		#["Sum is: " ++ payload]

###Go Further

* Read more about the Scripting module [here](https://docs.mulesoft.com/connectors/scripting-reference)
