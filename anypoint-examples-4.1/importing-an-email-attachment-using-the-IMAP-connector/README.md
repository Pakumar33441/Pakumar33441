# Importing an Attached CSV File Using the IMAP connector

This example shows how to use the IMAP connector to facilitate information transfers through email. It also illustrates how you can use DataWeave to transform a CSV file to XML.

### Prerequisites

This document describes the details of the example within the context of Anypoint Studio. This document assumes that you are familiar with Mule runtime, [Anypoint DataWeave](https://docs.mulesoft.com/anypoint-studio/v/7.2/transform-message-component-concept-studio). 

### Example Use Case

In this example, a CSV file containing sample sales data has been received as an attachment in an email inbox. This file is imported using the IMAP connector. The DataWeave transformer converts the CSV file to the XML format. The Logger logs this data on the Studio console. The output is stored in an XML file using the write component.

### Set Up and Run this Example

1.  Open the Example project in Anypoint Studio from Anypoint Exchange.

2. Navigate to *src/main/resources/mule-artifact.properties* and edit its properties:

	**Email Connector configuration**			
	imap.host 'imap.googlemail.com'
	imap.port '993'
	imap.user 'receiveremailaddress@gmail.com'
	imap.password 'receiver_password'
	
    **Full path to your output folder where xml file will be created**       
    working.directory 'C:/User/src/main/resource/output'
    
3. **Run** the project as a Mule application

4. Navigate to src/main/resources to find the input.csv file. Use any email address to send the input.csv file as an attachement  to receiveremailaddress@gmail.com

5. If you have configured and run this example correctly, the CSV file should appear in XML format in the Studio console. The log message should be similar to:

         INFO  2018-05-09 12:42:07,251 [[MuleRuntime].cpuLight.03:org.mule.runtime.core.internal.processor.LoggerMessageProcessor: 
         <?xml version='1.0' encoding='windows-1252'?>
			<orders>
			  <order>
			    <orderId>1</orderId>
			    <name>aaa</name>
			    <units>2.0</units>
			    <pricePerUnit>10</pricePerUnit>
			  </order>
			  <order>
			    <orderId>2</orderId>
			    <name>bbb</name>
			    <units>4.15</units>
			    <pricePerUnit>5</pricePerUnit>
			  </order>
			</orders>

6. After the attachment is processed, it's saved as an XML file.

### See Also

* Read more about the [Email Connector](https://docs.mulesoft.com/connectors/email-connector)

