# Implementing an Exception Strategy

This example illustrates the concept of error handling in Mule. This particular example deals with exception strategy.

### Example use case

The Mule application reads a CSV file and returns exception messages for two types of error: 

* The request specifies an invalid path.
* The request specifies a file that read permissions are not granted on.

### Set up and run the example

1. Open the Implementing a Exception Strategy example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).

2. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **port** field to required HTTP port (e.g., 8081).

3. Run the example project as a Mule application

4. In your browser, access the URL `http://localhost:8081/?filePath=invalidPath`. This message specifies an invalid path, and the application returns this error message:
		
		{
		  "message": "Your path is invalid"
		}

5. In your browser, access the URL `http://localhost:8081/?filePath=path`, substituing the path to the file `input.csv` for the `path` variable. The application returns the following payload:

		[
		  {
		    "orderId": "1",
		    "name": "T-shirt",
		    "pricePerUnit": "25.0",
		    "units": "2"
		  },
		  {
		    "orderId": "2",
		    "name": "Jacket",
		    "pricePerUnit": "40.5",
		    "units": "3"
		  }
		]

6. In your browser, access the URL `http://localhost:8081/?filePath=path`, replacing the `path` variable this time with the path to a file that does not allow read access. The application returns the following error message:
       
		{
		  "message": "Access to file denied"
		}

### Go further
       
* Read the documentation about exception strategy [here](http://www.mulesoft.org/documentation/display/current/Choice+Exception+Strategy).
   
   

