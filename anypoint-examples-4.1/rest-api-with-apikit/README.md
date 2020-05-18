# REST API with APIkit #

This application illustrates an API implementation using Anypoint™ Studio's tooling for building REST APIs with RAML interfaces: [APIkit](http://www.mulesoft.org/documentation/display/current/APIkit). The application takes a [RAML](http://raml.org/) file and maps it to an implementation of an API in Mule. This example implementation mocks a Spring backend, but you can replace these placeholders with a full implementation of your choice.

#### APIkit ####

APIkit is an open-source, declarative toolkit specially created to facilitate REST API implementation with RAML definitions. As a simple framework that caters to API-first development, it enforces good API implementation practices.

### Assumptions ###

This document describes the details of the example within the context of Anypoint Studio, Mule ESB’s graphical user interface (GUI), and includes configuration details for both the visual and XML editors. It assumes that you are familiar with Mule ESB and the [Anypoint™ Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes you are familiar with REST APIs.

In addition, this example requires that you have Maven installed. If you don't already have it (for example, Mac OSX Mavericks does not include Maven), follow the [instructions](http://maven.apache.org/download.cgi) to download and install it before attempting to create the example project.

### Example Use Case ###

This API mocks endpoints for customer entity.

### Set Up and Run the Example ###

Follow the procedure below to run and test the functionality of this example application in Anypoint Studio.

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).
2. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **port** field to required HTTP port e.g. 8080
3. In the Package Explorer pane in Studio, right-click the project name, then select Run As > Mule Application. Studio runs the application and Mule is up and kicking!
4. Anypoint Studio starts the application and provides link to API console.
5. Click through the resources and methods to test out API calls.

### How It Works ###

This application is based on a RAML specification file, which you can find in the **src/main/api** folder in the package explorer. Anypoint Studio allows you to import a RAML spec and automatically generate an APIkit project with a main flow, backend flows for each allowed method for each resource, and exception strategy mappings. The following sections walk through the RAML file and each of the auto-generated aspects of the APIkit project based on it.

#### RAML File ####

Open the RAML file in the src/main/api folder to review the details of this API implementation. Note that the RAML definition makes use of numerous !includes that reference other files within the same directory. You can see all the files referenced also reside in your src/main/api folder.

Compare the resource and methods defined in the RAML file with what you see in the API console in Studio. The API console provides interactive documentation for your API. When you publish your finished API, you can share this console with users by sending them to your API's baseURI with /console appended to the end. For instance, the base URI in this RAML definition is currently [http://localhost:8080/api](http://localhost:8080/api,), so you can access the console for this API at http://localhost:8080/api/console. To deploy this API, you would replace the baseURI in the RAML file with the deployed baseURI, so the console would then be accessed at http://myapibaseURI.com/console.

#### Documentation ####

Anypoint Studio includes a feature that enables you to easily export all the documentation you have recorded for your project. Whenever you want to easily share your project with others outside the Studio environment, you can export the project's documentation to print, email, or share online. Studio's auto-generated documentation includes:

- a visual diagram of the flows in your application
- the XML configuration which corresponds to each flow in your application
- the text you entered in the Notes tab of any building block in your flow

Follow the [procedure](http://www.mulesoft.org/documentation/display/current/Importing+and+Exporting+in+Studio#ImportingandExportinginStudio-ExportingStudioDocumentation) to export auto-generated Studio documentation.


Go Further

- Learn more about [APIkit](http://www.mulesoft.org/documentation/display/current/APIkit) by following the [APIkit Tutorial](http://www.mulesoft.org/documentation/display/current/APIkit+Tutorial).
- Start designing your own RAML-based APIs in [API Designer](http://api-portal.anypoint.mulesoft.com/raml/api-designer).
