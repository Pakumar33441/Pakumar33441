# Import contacts asynchronously

This application uses pre-packaged tools to connect with Salesforce. Based on a simple use case, the application takes a CSV file of contacts and uploads the contact information to an active Salesforce user account. The processing is done asynchronously and is triggered by an HTTP POST request. It uses Anypoint DataWeave to transform data, thereby facilitating quick integration with this Software-as-a-Service (SaaS) provider.

At times, you may find that you need to connect one or more of your organization's on-premises systems with a SaaS such as Salesforce. Ideally, these independent systems would talk to each other and share data to enable automation of end-to-end business processes. Use Mule applications to facilitate communication between your on-prem system(s) and Salesforce. (Though this use case does not extend as far, you can also use Mule to facilitate communication between SaaS providers).

### Assumptions ###

This document assumes that you are familiar with Mule and the [Anypoint™ Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture), [Mule Global Elements](http://www.mulesoft.org/documentation/display/current/Global+Elements), and [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).

This document describes the details of the example within the context of Anypoint Studio, Mule ESB’s graphical user interface, and includes configuration details for XML Editor where relevant. 

### Example Use Case ###

Though a simple example, this application nonetheless employs complex functionality to demonstrate a basic use case. The application accepts CSV files that contain contact information – name, phone number, email – and uploads them into a Salesforce account, automatically inserting the correct data into each Salesforce field. 

#### Set Up and Run the Example ####

Complete the following procedure to create and then run this example in your own instance of Anypoint Studio. Skip ahead to the next section if you prefer to simply examine this example via code snippets.

1. Open the Import Contacts Asynchronously project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange). *Do not run the application*.
1. Log in to your Salesforce account. From your account menu (your account is labeled with your name), select **Setup**.
1. In the left navigation bar, under the **Personal Setup** heading, click to expand the **My Personal Information** folder. 
1. Click **Reset My Security Token**. Salesforce resets the token and emails you the new one.
1. Access the email that Salesforce sent you and find the security token in it.
1. In the **Package Explorer** click the `src/main/resources` folder to expand it and open the file `mule-artifact.properties`. 
1. Put your Salesforce account-specific values into the properties `sfdc.user`, `sfdc.password`, and `sfdc.securityToken`.
1. In the same configuration file, set the HTTP port and API domain information in the properties `http.port` and `api.domain`.
1. In your application in Studio, click the **Global Elements** tab. 
1. In the **Package Explorer**, click the `src/main/resources` folder to expand it, and then find the `contacts.csv` file inside this folder. 
1. Double-click the **File config** tab in the **Global Elements** tab and set the **Working Directory** field to point to `src/main/resources` folder.
1. In the **Package Explorer**, right-click the `import-contacts-asynchronously` project name, then select **Run As > Mule Application**. Studio runs the application on the embedded server.  
1. In a REST client (e.g., Postman), trigger the following request: ```POST localhost:{http.port}/import/trigger```
1. In the HTTP response, find the Location header. It contains the URL of the ```GET /import/status``` endpoint.
1. Send a GET request to the URL that is in the Location header and check the status of the processing. When the status is ```IN PROGRESS```, the asynchronous processing isn't finished yet and you should try it again later. When the status is ```COMPLETED```, the asynchronous job is done and the data has been imported into Salesforce.
1. In your browser, access your Salesforce account, and then navigate to the **Contacts** tab.
1. Use the drop-down menu to display **All Contacts**, and then scan your contacts for two new entries:  
	- John Doe
	- Jane Doe
1. Stop the Mule application by clicking the red, square terminate button in the **Console**.
1. Delete the three sample contacts from your Salesforce account.

#### How it Works ####

The application consists of two separate flows that are triggered by HTTP POST and GET requests. The first flow starts the asynchronous processing and returns the status to the status endpoint in the Location header. By calling the status endpoint, you can check whether the asynchronous job is done or not. 

When the HTTP Listener receives a `POST /import/trigger` request, it generates the process ID and triggers the import job in the asynchronous scope. Then, it sets the Location header to the status endpoint for this particular process and sends the response back to the user.  

The import job first sets the status to ```IN PROGRESS```. Then, it reads the input CSV file and transforms the content to a format accepted by the **Salesforce Connector** by using the **DataWeave** transformation language. In the next step, the application uses the [Salesforce Connector](http://www.mulesoft.org/documentation/display/current/Salesforce+Connector) to push data into your Salesforce account. The connector's configuration specifes the **operation** – *Create* – and the **sObject type** – *Contact* – which dictate exactly how the data is uploaded to Salesforce to create the new contacts. 

To highlight that the processing is done in an asynchronous manner, this project uses a **Script Component** that calls the *Groovy's* ```sleep()``` command. This command causes the flow to sleep for 30 seconds, so you have a chance to call the ```GET /import/status``` endpoint and notice that the processing is in the ```IN PROGRESS``` state. In a real world scenario,  this component should be omitted. As the last step, the job changes the status to ```COMPLETED``` and the processing ends.

### Documentation ###

Studio includes a feature that enables you to easily export all the documentation you have recorded for your project. Whenever you want to share your project with others outside the Studio environment, you can export the project's documentation to print, email or share online. Studio's auto-generated documentation includes:

- A visual diagram of the flows in your application
- The XML configuration which corresponds to each flow in your application
- The text you entered in the Notes tab of any building block in your flow

Follow [the procedure](http://www.mulesoft.org/documentation/display/current/Importing+and+Exporting+in+Studio#ImportingandExportinginStudio-ExportingStudioDocumentation) to export auto-generated Studio documentation.

### Go Further ###

- Learn more about [Connection Testing](http://www.mulesoft.org/documentation/display/current/Testing+Connections) and [DataWeave](http://www.mulesoft.org/documentation/display/current/DataWeave).
- Learn more about [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).
