# Import leads into Salesforce #

This application uses pre-packaged tools to intelligently connect with Salesforce. Based on a simple use case, the application takes a CSV file of leads and uploads the lead information to an active Salesforce user account. It uses Anypoint DataWeave to map and transform data, thereby facilitating quick integration with this Software as a Service (SaaS) provider.

### Connect with Salesforce ###

At times, you may find that you need to connect one or more of your organization's on-premises systems with a SaaS such as Salesforce. Ideally, these independent systems would talk to each other and share data to enable automation of end-to-end business processes. Use Mule applications to facilitate communication between your on-prem system(s) and Salesforce. (Though this use case does not extend as far, you can also use Mule to facilitate communication between SaaS providers).

### Assumptions ###

This document assumes that you are familiar with Mule Runtime and the Anypoint Studio interface. This example also assumes you are familiar with XML coding and that you have a basic understanding of [Mule flows](https://docs.mulesoft.com/mule4-user-guide/v/4.1/about-flows) and [DataWeave](https://docs.mulesoft.com/mule-runtime/4.1/dataweave). 

### Example Use Case ###

The application accepts a CSV file which contain lead information – company, first name, last name, birthday, email – and uploads them into a Salesforce account, automatically inserting the correct data into each Salesforce field. 

### Set Up and Run the Example ###

Complete the following procedure to create, then run this example in your own instance of Anypoint Studio.

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](https://www.mulesoft.com/exchange/). *Do not run the application*.
1. Log in to your Salesforce account. From your account menu (your account is labeled with your name), select **Setup**.
1. In the left navigation bar, under the **Personal Setup** heading, click to expand the **My Personal Information** folder. 
1. Click **Reset My Security Token**. Salesforce resets the token and emails you the new one.
1. Access the email that Salesforce sent and copy the new token onto your local clipboard.
1. In your application in Studio, click the **Global Elements** tab. 
1. Double-click the Salesforce global element to open its **Global Element Properties** panel. In the **Security Token** field, paste the new Salesforce token you copied from the email. Alternatively, configure the global element in the XML Editor.
1. Change the contents of the **Username** and **Password** fields to your account-specific values, then click **OK** to save your changes. 
1. In the **Package Explorer**, right-click the connect-with-salesforce project name, then select **Run As > Mule Application**. Studio runs the application on the embedded server.  
1. This project includes a sample CSV file, called *leads.csv*, that you can use to witness end-to-end functionality of the application. In the **Package Explorer**, click the *src/main/resources/input* folder to expand it, then find the *leads.csv* file inside this folder.
1. Double-click the File config tab in the Global Elements tab and set the Working Directory field to point to *src/main/resources/input* folder.
1. The File connector in the application polls the input folder every ten seconds. It picks up the CSV file, processes it, then deposits it into the output folder in the same directory. (Hit **F5** to refresh the contents of the input and output folders.)
1. In your browser, access your Salesforce account, then navigate to the **Leads** tab.
1. Use the drop-down menu to display **All Open Leads**, then scan your leads for the new entries:  
	- Ishmael Alexander
	- Cole Burks	
1. Stop the Mule application by clicking the square, red terminate button in the **Console**.
1. Delete the two sample leads from your Salesforce account.

### How it Works ###

This application accepts CSV files which contain lead information, then uploads the leads to Salesforce. 

The [File connector](https://docs.mulesoft.com/connectors/file/file-connector) polls the input folder for new files every ten seconds. When it spots a new file, it reads it and passes the content to the [DataWeave](https://docs.mulesoft.com/mule-runtime/4.1/dataweave). This component not only converts the format of the data from CSV to a collection, it automatically maps the input fields from the CSV file – FirstName, LastName, etc. – to output fields that Salesforce uses in a collection. Each mapping earns an arrow which helps you to visualize the activity that occurs within the DataWeave component. When it has converted all the leads in the file to a collection of Salesforce-friendly data, the application uses a [Salesforce Connector](https://docs.mulesoft.com/connectors/salesforce/salesforce-connector) to query for existing leads. If not present in Salesforce, the connector pushes data into your account. The connector's configurations specify the **operation** – *Create* – and the **sObject type** – *Lead* – which dictate exactly how the data uploads to Salesforce; in this case, it creates new leads. 

While the application's functionality is relatively straightforward, the beauty of this project is illustrated through its use of [DataWeave](https://docs.mulesoft.com/mule-runtime/4.1/dataweave). Rather than building the application serially – adding, then configuring each of the elements manually according to the order in which they appear in the flow. 