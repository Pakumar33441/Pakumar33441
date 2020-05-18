# Track a Custom Business Event

This example shows how to track data by using the Custom Business Event component.

### Example use case

Data about an order is sent in JSON through an HTTP endpoint to a DataWeave transformer. A custom DataWeave transformation is used to calculate the discount that is offered for a certain product type. The Custom Business Event keeps track of the item name, number of units, and price per unit for the order data.

### Set up and run the example

1. Import the _Track a Custom Business Event_ project into your workspace

1. In your application in Studio, click the **Global Elements** tab.

1. Double-click the **HTTP Listener config** global element to open its **Global Element Properties** panel. Change the contents of the **Port** field to required HTTP port (e.g., 8081).

1. Right-click the project folder and select **Anypoint Platform > Deploy to CloudHub**.

1. In the Deploying Application window that opens, click **Insight** and then select **Metadata and Replay**.

1. Click **Deploy Application**.

1. Use Postman to make a POST request using JSON to `http://(your domain).cloudhub.io/customBusinessEvents`:

		{
		"email": "aaa@abc.sk",
		"item name": "shoes",
		"item units": 2,
		"item price per unit": 10,
		"membership": "free"
		}

    The response body should contain price per unit with the applied discount (-15% for shoes):

1. Go to your Cloudhub account and click the **Insight** tab in the left panel to see that your custom business event was logged.

### Go further

* Check out the documentation on [Business Events](http://www.mulesoft.org/documentation/display/current/Business+Events) in Mule
