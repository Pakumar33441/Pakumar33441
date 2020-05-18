# Retry mechanism in Mule applications

### Enterprise, CloudHub ####

This application shows on a simple use case how the retry mechanism could be implemented in the Mule applications. It receives the HTTP request and simulates the failure in the flow. Once the flow fails the retry mechanism proceed with series of retries based on the configuration.

### Assumptions ###

This document assumes that you are familiar with Mule and the [Anypoint™ Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture), [Mule Global Elements](http://www.mulesoft.org/documentation/display/current/Global+Elements), and [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).

This document describes the details of the example within the context of Anypoint Studio, Mule ESB’s graphical user interface, and includes configuration details for XML Editor where relevant. 

### Example Use Case ###

The application consumes HTTP requests and based on the query parameter ```fail``` either successfully completes the flow or throw an exception inside the **Until Successful** scope causing the retry mechanism to repeat the flow part inside **Until Successful** scope.

### Set Up and Run the Example ###

Complete the following procedure to create, then run this example in your own instance of Anypoint Studio. Skip ahead to the next section if you prefer to simply examine this example via code snippets.

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).
1. In your application in Studio, click the Global Elements tab. Double-click the HTTP Listener global element to open its Global Element Properties panel. Change the contents of the port field to required HTTP port e.g. 8081
1. Double-click to **Until Successful** scope and set the *Max Retries* and *Milliseconds Between Retries* to values of your choice.
1. In the **Package Explorer**, right-click the retry-mechanism-example project name, then select **Run As > Mule Application**. Studio runs the application on the embedded server.
1. In the REST client e.g. Postman send the following request: ```GET localhost:{http.port}/trigger?fail=false```
1. In the HTTP response you can see the message *Processing successfully completed.* and the application log contains the following log entries:

    ```
    INFO org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Processing successfully completed.```

1. The flow did not fail, no retry was needed and the processing was successful.
1. Now, let's send another request: ```GET localhost:{http.port}/trigger?fail=true`
1. Setting the query parameter ```fail=true``` causes the flow to fail by throwing the following exception:
    
    ```
    throw new Exception("This is the reason why the processing has failed.");```
    
1. The HTTP status is 500 (Internal Server Error) now and the response body contains the error message indicating the flow fails even after predefined number of retries:

    ```
    'until-successful' retries exhausted. Last exception message was: java.lang.Exception: This is the reason why the processing has failed.```

1. The application log now contains the following entries:

    ```
    INFO  2017-11-30 15:29:35,177 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO  2017-11-30 15:29:36,604 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO  2017-11-30 15:29:37,611 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO  2017-11-30 15:29:38,617 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO  2017-11-30 15:29:39,624 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    INFO  2017-11-30 15:29:40,631 org.mule.runtime.core.internal.processor.LoggerMessageProcessor: Attempt to trigger processing
    ERROR 2017-11-30 15:29:40,642 org.mule.runtime.core.internal.exception.OnErrorPropagateHandler: 
    ***************************************************************************************************************************************
    Message : 'until-successful' retries exhausted. Last exception message was: java.lang.Exception: This is the reason why the processing has failed.```

1. As you see in the logs the application has triggered processing which failed, then proceed with 5 retries each with 1000ms latency (depends on configuration) and in the end it propagated the error.

### Documentation ###

Studio includes a feature that enables you to easily export all the documentation you have recorded for your project. Whenever you want to share your project with others outside the Studio environment, you can export the project's documentation to print, email or share online. Studio's auto-generated documentation includes:

- A visual diagram of the flows in your application
- The XML configuration which corresponds to each flow in your application
- The text you entered in the Notes tab of any building block in your flow

Follow [the procedure](http://www.mulesoft.org/documentation/display/current/Importing+and+Exporting+in+Studio#ImportingandExportinginStudio-ExportingStudioDocumentation) to export auto-generated Studio documentation.

### Go Further ###

- Learn more about [Connection Testing](http://www.mulesoft.org/documentation/display/current/Testing+Connections) and [DataWeave](http://www.mulesoft.org/documentation/display/current/DataWeave).
- Learn more about [Anypoint DataWeave](https://developer.mulesoft.com/docs/display/current/DataWeave+Reference+Documentation).
