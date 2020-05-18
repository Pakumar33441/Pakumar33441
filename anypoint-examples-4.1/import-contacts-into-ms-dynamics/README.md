# Import contacts into Microsoft Dynamics CRM

This application uses pre-packaged tools to intelligently connect with Microsoft Dynamics CRM. Based on a simple use case, the application takes a CSV file of contacts and uploads the contact information to an active Dynamics CRM user account. It uses the Anypoint DataWeave Transformer to map and transform data, thereby facilitating quick integration with this Software-as-a-Service (SaaS) provider.

At times, you might find that you need to connect one or more of your organization's on-premises systems with a SaaS such as Microsoft Dynamics. Ideally, these independent systems would talk to each other and share data to enable automation of end-to-end business processes. Use Mule applications to facilitate communication between your on-prem system(s) and Microsoft Dynamics. (Though this use case does not extend as far, you can also use Mule to facilitate communication between SaaS providers).

**Note:** You need to install Java Cryptography Extensions to be able to connect to MS Dynamics. Please [choose](http://www.oracle.com/technetwork/java/javase/downloads/index.html) a relevant version, according to your Java installation.

### Assumptions ###

This document assumes that you are familiar with Mule and the [Anypoin Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture), [Mule Global Elements](http://www.mulesoft.org/documentation/display/current/Global+Elements), and [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).

This document describes the details of the example within the context of Anypoint Studio, Mule ESB graphical user interface.

**Note:** Be sure to have the Microsoft Dynamics CRM connector installed in your Anypoint Studio.

### Example Use Case ###

The application accepts a CSV file by polling a local folder at frequent intervals that are set with a Scheduler component. The columns of the CSV file contain this contact information: first name, last name, phone number, and email. These columns are mapped to each of the respective fields in a specific Dynamics CRM account and the rows are uploaded.

#### Set Up and Run the Example ####

Complete the following procedure to create and then run this example in your own instance of Anypoint Studio. 

1. Create your free trial MS Dynamics account [here](http://www.microsoft.com/en-us/dynamics/crm-free-trial-overview.aspx). Remember your registration data (username, password, and company name) because you will need it to connect to Dynamics.
1. Open the Import Contacts into Microsoft Dynamics project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange). *Do not run the application*.
1. In your application in Studio, open the `mule.artifact.properties` file
1. Set the contents of the `Username`, `Password`, and `Organization Service URL` fields to your account-specific values as follows:

		Username					<USERNAME>@<COMPANY_NAME>.onmicrosoft.com
		Password					<PASSWORD>
		Organization Service Url	https://<COMPANY_NAME>.api.crm4.dynamics.com/XRMServices/2011/Organization.svc
 		
1 Set the number of authentication retries, the path to the `contacts.csv` working directory, and the scheduler parameters, as in this example:

		working.directory 'C:/import-contacts-into-ms-dynamics/src/main/resources/input'
		authenticationRetries '3'
		scheduler.frequency '10000'
		scheduler.startDelay '0' 		
 
**Note:** The URL might differ, depending on your location. Choose an appropriate one from [For Microsoft Dynamics CRM Online](https://msdn.microsoft.com/en-us/library/gg309401.aspx).

1. Click **OK** to save your changes. 
1. In the **Package Explorer**, right-click the `connect-with-ms-dynamics` project name, and then select **Run As > Mule Application**. Studio runs the application on the embedded server.  
1. In the **Package Explorer**, click the `src/main/resources` folder to expand it, and then find the `contacts.csv` file inside this folder. 
1. Click and drag the `contacts.csv` file into an `input` folder in the same directory. The application, at the intervals set in the Scheduler component, checks the input folder for new files. On the next check, it recognizes the CSV file and processes it.
1. In your browser, access your Dynamics account and check your contacts for two new entries:  
	- John Doe
	- Jane Doe
1. Stop the Mule application by clicking the red, square terminate button in the **Console**.
1. Delete the two sample contacts from your MS Dynamics account.

### How it Works ###

Using a single flow with four elements, this application accepts CSV files that contain contact information, and then uploads the contacts to MS Dynamics. 

The application, at the intervals set in the Scheduler component, checks the input folder for new files. When it spots a new file, it reads the file and passes the content to the [Anypoint DataWeave transformer](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation). This transformer not only converts the format of the data from CSV to a collection, but it automatically maps the input fields from the CSV file to output fields that MS Dynamics uses in a collection. When it has converted all the contacts in the file to a collection of MS Dynamics-friendly data, the application uses a [MS Dynamics Connector](https://www.mulesoft.com/resources/esb/ms-dynamics-integration) to push data into your MS Dynamics account. The connector's configuration specifies exactly how the data is uploaded to MS Dynamics; in this case, it specifies to create new contacts. 

### Go Further ###

- Learn more about [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).	
- Learn more about [File connector](http://www.mulesoft.org/documentation/display/current/File+Connector).
