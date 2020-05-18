# Upload to FTP after converting JSON to XML

This example application illustrates the concept of datamapping to convert JSON data to XML. It also shows you how to configure and use the FTP connector to upload a file to a FTP server.

### Assumptions ###

This document assumes that you are familiar with Mule and [Anypoint Studio](https://docs.mulesoft.com/anypoint-studio/v/7.2/). This example also assumes that you have a basic understanding of [Mule flows](https://docs.mulesoft.com/mule4-user-guide/v/4.1/about-flows), [Mule Global Elements](https://docs.mulesoft.com/mule4-user-guide/v/4.1/global-elements), and [DataWeave](https://docs.mulesoft.com/mule4-user-guide/v/4.1/dataweave).

### Example Use Case
In this example JSON data is sent to the mule application through an HTTP end point. This data is then converted to the XML format using the DataWeave transformer after which the message payload is uploaded to the FTP folder.

### Set Up and Run the Example
1. Open the Example project in Anypoint Studio from Anypoint Exchange.
2. In the Package Explorer, click the `src/main/resources` folder, double-click **mule-artifact.properties**, and set properties to the following values:
```
http.port 8081
ftp.host <your_ftp_host>
ftp.port <your_ftp_port>
ftp.username <your_user>
ftp.password <your_user_password>
```

3. In the Package Explorer pane, right-click the project name and  select **Run As > Mule Application**.

4. Make a POST request using Postman to `http://localhost:<your_HTTP_port>` with following JSON message body, and setting the `Content-Type` header to `application/json`:
```
	    {
	    "employees": {
	    "employee": [
	      {
	        "name": "John",
	        "lastName": "Doe",
	        "addresses": {
	          "address": [
	            {
	              "street": "123 Main Street",
	              "zipCode": "111"
	            },
	            {
	              "street": "987 Cypress Avenue",
	              "zipCode": "222"
	            }
	          ]
	        }
	      },
	      {
	        "name": "Jane",
	        "lastName": "Doe",
	        "addresses": {
	          "address": [
	            {
	              "street": "345 Main Street",
	              "zipCode": "111"
	            },
	            {
	              "street": "654 Sunset Boulevard",
	              "zipCode": "333"
	            }
	          ]
	        }
	      }
	    ]
	    }
	    }
```
5. Verify that the file `muleExample.xml` was uploaded to the `upload` folder on your FTP server.

### Documentation ###

Studio includes a feature that enables you to easily export all the documentation you have recorded for your project. Whenever you want to share your project with others outside the Studio environment, you can export the project's documentation to print, email or share online. Studio's auto-generated documentation includes:

- A visual diagram of the flows in your application
- The XML configuration which corresponds to each flow in your application
- The text you entered in the Notes tab of any building block in your flow

Select **File > Export Studio Documentation**, select a project, specify a target directory, and then click **Generate Studio Documentation**.
