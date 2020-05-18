# Filtering a Message #

This example shows how to use validation components within Anypoint Studio to validate an incoming message.  

### Assumptions ###

This document describes the details of the example within the context of **Anypoint™ Studio**, Mule ESB’s graphical user interface (GUI). Where appropriate, the XML configuration is provided.

This document assumes that you are familiar with Mule ESB and the [Anypoint Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Mule Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture).

### Example Use Case ###

This example application receives the list of users in JSON format. Every record in the list should contains id, email in correct format, object with information about user connection. Connection info should contains IP address. If all of the mentioned fields are presented and validation process is successful as well, we will see: *User records are valid!* in response of the HTTP call.

### Set Up and Run the Example ###

Complete the following procedure to create, then run this example in your own instance of Anypoint Studio. Skip ahead to the next section if you prefer to simply examine this example.

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).
2. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **port** field to required HTTP port e.g. 8081
3. In the Package Explorer pane in Studio, right-click the project name, then select Run As > Mule Application. Studio runs the application and Mule is up and kicking!
4. Send a POST request with the following JSON in the body to *http:/localhost:8081*.

		[
        	{
        		"id": "8fa8435c-fca4-4b42-9b5a-e81f9bd9aa16",
        		"username": "bob",
        		"email": "bob@mulesoft",
        		"connectionInfo": {
        			"IPAddress": "212.141.190.171",
        			"MACAddress": "2A-7A-6F-D3-64-54"
        		}
        	},{
        		"id": "f125a585-df12-404a-8c80-711d117dd353",
        		"username": "greg",
        		"email": "greg@mulesoft.com",
        		"connectionInfo": {
        			"IPAddress": "128.211.42.181",
        			"MACAddress": "8D-BD-C3-C4-D8-A4"
        		}
        	},{
        		"id": "6d4747ee-eb00-4e81-b7dc-2b01585e6d99",
        		"username": "anna",
        		"email": "anna@mulesoft.com",
        		"connectionInfo": {
        			"IPAddress": "40.125.118.175",
        			"MACAddress": "9E-05-9B-68-8E-80"
        		}
        	}
        ]

5. Examine the response body to see that validation has been not successful:

        Email in record: {
          "id": "8fa8435c-fca4-4b42-9b5a-e81f9bd9aa16",
          "username": "bob",
          "email": "bob@mulesoft",
          "connectionInfo": {
            "IPAddress": "212.141.190.171",
            "MACAddress": "2A-7A-6F-D3-64-54"
          }
        } is not valid!
        
6. As you can see email is not valid. Try to fix it with *.com* suffix.

7. Examine the response body to see that validation has been not successful once again: 
        
        IP address of record: {
          "id": "6d4747ee-eb00-4e81-b7dc-2b01585e6d99",
          "username": "anna",
          "email": "anna@mulesoft.com",
          "connectionInfo": {
            "IPAddress": "40.125.118.175",
            "MACAddress": "9E-05-9B-68-8E-80"
          }
        } is on blacklist!
        
8. Looks like email address of that user is blacklisted.

9. In case that you will send valid message, you will see: *User records are valid!* in the response of HTTP call.

### How it Works ###

The following steps outline the process to build an application for validation of messages.

1. Create a new Mule Project by going to **File > New... > Mule Project** and name it **Filtering a Message**.
2. Drop an HTTP Listener to the flow. 
3. Create configuration for HTTP listener with the port you want.
4. Add Validation module to the Anypoint Studio palette. 
5. Validation module contains a lot of components - drop some of them you want to try to your flow with the HTTP listener in the beginning.
6. Run the example!

### Go Further ###

- For more information on validation module, please visit the [Validation module](https://docs.mulesoft.com/connectors/validation-documentation) documentation.  