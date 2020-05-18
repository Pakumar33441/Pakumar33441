# Importing an attached csv file using the POP3 connector


This example shows you how to use the POP3 connector to facilitate information transfer through email. It also illustrates how you can use DataWeave to transform a CSV to XML format.

### Assumptions

This document describes the details of the example within the context of Anypoint™ Studio, Mule ESB’s graphical user interface (GUI). This document assumes that you are familiar with Mule ESB, [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation) and the [Anypoint Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). 

### Example Use Case

In this example a CSV file containing sample sales data which has been received as an attachment in a mail inbox is imported using the POP3 connector. The DataWeave transformer then converts the CSV file to the XML format. The logger then logs this data on the studio console and the output is stored to xml file by write component.

### Set Up and Run this Example

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).

2. Open the mule-artifact.properties file and edit its properties as follows:

           working.directory=/your/path
           pop3.host=pop.gmail.com
           pop3.port=995
           pop3.user=receiveremailaddress%40gmail.com
           pop3.password=receiver_password
    
3. **Run** the project as a Mule application

4. Navigate to src/main/resources and use any email address to **send the 'input.csv' file as an attachement**  to receiveremailaddress@gmail.com

5. If you have configured and run this example correctly, the csv file should appear in XML format in the studio console. The log message should be similar to what is shown below:

        INFO  2018-05-09 14:31:55,446 [[MuleRuntime].cpuLight.06: [importing-an-email-attachment-using-the-pop3-connector].importing-an-email-attachment-using-the-pop3-connectorFlow.CPU_LITE @24841f38] [event: 0-f3cc6f90-5384-11e8-9a97-f85971bd4f61] org.mule.runtime.core.internal.processor.LoggerMessageProcessor: <?xml version='1.0' encoding='windows-1252'?>
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
 
6. After processing the attachment, it will be saved in path defined in property working.directory in the output folder with XML format.

### Go Further

* Read more about the [POP3 Connector](http://www.mulesoft.org/documentation/connectors/email-documentation)
* Read more about the [POP3 Listing Emails](http://www.mulesoft.org/documentation/connectors/email-list)
