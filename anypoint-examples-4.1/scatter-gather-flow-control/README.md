# Scatter Gather flow control to email


This example shows the usage of the scatter-gather control flow to aggregate data in parallel and return the result in JSON. The example uses prepared data as input for two resources that should be aggregated. The data represents contacts information with the following structure:

Example data structure(contacts-1.csv, contacts-2.csv) in:
		firstname;surname;phone;email
		John;Doe;096548763;john.doe@texasComp.com
		Jane;Doe;091558780;jane.doe@texasComp.com
		...		

[DataWeave](https://docs.mulesoft.com/mule-user-guide/v/current/dataweave) is used to aggregate the data.

Contacts are aggregated to a JSON structure that represents data from both sources (`contacts-1.csv` and `contacts-2.csv`).

	
### Assumptions

This document describes the details of the example within the context of Anypoint™ Studio, Mule ESB’s graphical user interface (GUI). This document assumes that you are familiar with Mule ESB and the [Anypoint Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture), and [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).

### Example Use Case

In this example, sample data is prepared with the Expression component, and the prepared data serves as input for aggregation in the Scatter-Gather component. Then, the report is transformed to the CSV format and sent to an email address via the SMTP connector. This example has been configured for gmail.

#### Set Up and Run this Example

1. Open the Scatter Gather Flow Control project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).

2. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **Port** field to required HTTP port e.g. 8081

3. Navigate to `src/main/app/scatter-gather-flow-control.xml` and **check "Read contacts-1.csv" and "Read contacts-2.csv" file components** and in File Path field point to the attributable files located in src/main/resources folder. 
    
4. **Run** the project as a Mule application

5. Open your Web browser.

6. In the address bar, type the following URL: http://localhost:8081/scatterGather 

7. Press enter to elicit a response from the Scatter-Gather flow control example.


### Go Further

* Read more about the [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation)

* Read more about the [Scatter Gather Flow Control](http://www.mulesoft.org/documentation/display/current/Scatter-Gather)

* Check out this [blog](http://blogs.mulesoft.org/parallel-multicasting-simplified/) on Parallel Processing in Mule.



   
