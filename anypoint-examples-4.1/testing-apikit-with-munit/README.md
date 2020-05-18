# Using MUnit to Test Flows Created in APIkit #

This example project shows how to use [MUnit](https://docs.mulesoft.com/munit/v/2.1/) to test [flows](https://docs.mulesoft.com/mule4-user-guide/v/4.1/about-flows) that were created with [APIkit](https://docs.mulesoft.com/apikit/v/4.x/), Anypoint Studio's tooling for building REST APIs with RAML interfaces. The application in this project illustrates an API implementation using APIkit. The application takes a [RAML](http://raml.org/) file and maps it to an implementation of an API in Mule. This example implementation routes the request according the method which was used (GET, POST, PUT, DELETE) and generates a dummy message.

APIkit is an open-source, declarative toolkit specially created to facilitate REST API implementation with RAML definitions. As a simple framework that caters to API-first development, it enforces good API implementation practices. Anypoint Studio allows you to import a RAML spec and automatically generate an APIkit project with a main flow, back-end flows for each allowed method for each resource, and exception-strategy mappings.

### Example Use Case ###

This application, which is based on the RAML API specification that you can find in the `src/main/resources/api` folder of the project, uses the APIKit Router component to route requests that end with `/munit` to the proper flow. There are four different flows, one each for GET, POST, PUT, and DELETE requests. The flow then generates a dummy return message.

For testing the application, the project includes an MUnit test suite. There is a test for each request type. Each test checks whether the right status code and message are generated for its corresponding request type.

### Set Up and Run the Example Application ###

1. Open the *Using MUnit to Test Flows Created in APIkit* project in Anypoint Studio from Anypoint Exchange.   
  1. Click the **Open Exchange** icon in the top-left corner of Anypoint Studio.
  2. Log into Anypoint Exchange.
  3. Search on the name of the project.
  4. In the top-right corner of Anypoint Exchange, click **Open**.  

  **Result:**

  A project folder named `testing-apikit-with-munit` appears in the Project Explorer view. The file `src/main/mule/api.xml` is open in the editor. The main flow is standard for an APIkit project. It contains an inbound endpoint and an APIkit Router.

  Below the main flow are several global exception strategy mappings that the Main flow references to send error responses in HTTP-status-code-friendly format. Defined at a global level within the project's XML configuration file, this standard set of exception strategy mappings ensures that, any time a backend flow throws an exception, the API responds to the caller with an HTTP-status code and corresponding plain-language message. Anypoint Studio automatically generates these mappings.
2. Click the **Global Elements** tab in the editor and then double-click the HTTP Listener config global element to open its **Global Element Properties** dialog.
3. Change the contents of the **Port** field to required HTTP port: 8080. Then, click **OK**.
3. In the Project Explorer, right-click the project name and then select **Run As > Mule Application**.
4. In the **APIKit Consoles** view, click **Open Console**.

	**Result:**

  The API Console opens in your default browser. The API console provides interactive documentation for your API. When you publish an API, you can share this console with users by sending them to your API's baseURI with `/console` appended to the end.

  For instance, the base URI in this RAML specification is currently [http://localhost:8080/api](http://localhost:8080/api,), so you can access the console for this API at http://localhost:8080/api/console. To deploy this API, you would replace the baseURI in the RAML file with the deployed baseURI, so the API console could then be accessed at http://myapibaseURI.com/console.
3. On the left side of the API console, click **GET**.

  **Result:**

  The console displays the request format for GET requests and the expected responses.
4. Click **Try It**, then click **Send**.

  **Result:**

  The API returns the status code 200.
5. Click **Display All** to see the dummy message `GET RESPONSE`.
6. Click through the other methods to test out the other API calls.
1. Open the `api.raml` file in the `src/main/resources/api` folder to compare the resource and methods defined in this file with what you see in the API console in Studio.

### Test the Example Application ###

Now, you can run MUnit tests.
1. In the Project Explorer, double-click the file `src/test/munit/test-suite.xml`. This is the file in which the tests are located.
2. Run the tests by right-clicking in the **Message Flow** working area and selecting **Run MUnit suite**. Click **Yes** if you see this message: `A Mule Runtime instance is already running. Do you want to stop it and run testing with testing-apikit-with-munit#test-suite.xml?`  

  **Result:**

  The MUnit view opens and the tests are run.
4. View the results of each test in the MUnit view.

### How Testing Works ###

We can illustrate how testing is performed by examining the flow and MUnit test for the GET request. When you run the application and send a GET request from the API Console, the application routes the request to this flow:

```
<flow name="get:\munit:api-config">
    <set-payload value="GET RESPONSE" doc:name="Set Payload" mimeType="text/plain"/>
</flow>
```

That flow is tested by MUnit through this code:

```
<munit:test name="test-suite-get" description="Test" doc:id="e3448a78-f22d-4490-8db5-5585e37031d7" >
  <munit:enable-flow-sources>	        
        <munit:enable-flow-source value="api-main"/>
        <munit:enable-flow-source value="get:\munit:api-config"/>
  </munit:enable-flow-sources>
  <munit:execution >
    <http:request method="GET" doc:name="Request" doc:id="ddf62d27-4de0-4f11-9f94-d71845a17388" config-ref="HTTP_Request_configuration" path="/munit"/>
  </munit:execution>
  <munit:validation >
    <ee:transform doc:name="Stream -&gt; String" doc:id="b053f0c8-fa82-49d7-a399-932f51002979" >
      <ee:message>
        <ee:set-payload ><![CDATA[%dw 2.0 output application/java --- payload]]>
        </ee:set-payload>
      </ee:message>
    </ee:transform>
    <munit-tools:assert-that doc:name="Assert that" doc:id="76c72835-5d19-4019-adfc-5cf16778b322" expression="#[attributes.statusCode]" is="#[MunitTools::equalTo(200)]" message="The HTTP Status code is not correct!"/>
    <munit-tools:assert-that doc:name="Assert that" doc:id="62934e5a-e527-4fe0-a76c-496b562d4fbd" expression="#[payload]" is="#[MunitTools::equalTo('GET RESPONSE')]" message="The response payload is not correct!"/>
  </munit:validation>
</munit:test>
```

The steps in this test are as follow:
1. The test sends a GET request that has `/mule` at the end of the URL to the HTTP endpoint.
1. The returned payload from HTTP connector is an InputStream. The test transforms the payload to String.
1. The two assertions are checked. The first assertion validates the HTTP status code, and the second validates the returned payload.
