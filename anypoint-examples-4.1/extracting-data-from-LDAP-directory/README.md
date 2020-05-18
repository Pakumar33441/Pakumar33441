# Importing data from an LDAP Directory

This example illustrates how to connect to an LDAP directory in Mule and retrieve a list of users that is stored in that directory. 

### LDAP

The Lightweight Directory Access Protocol (LDAP) is a public standard that facilitates distributed directories (such as network user privilege information) over the Internet Protocol (IP). The many available LDAP servers include free-use open source projects.

### Assumption

This document describes the details of the example within the context of **Anypoint™ Studio**, Mule ESB’s graphical user interface (GUI). Where appropriate, the XML configuration is provided.

This document assumes that you are familiar with Mule ESB and the [Anypoint Studio interface](http://www.mulesoft.org/documentation/display/current/Anypoint+Studio+Essentials). To increase your familiarity with Mule Studio, consider completing one or more [Anypoint Studio Tutorials](http://www.mulesoft.org/documentation/display/current/Basic+Studio+Tutorial). Further, this example assumes that you have a basic understanding of [Mule flows](http://www.mulesoft.org/documentation/display/current/Mule+Application+Architecture) and [Mule Global Elements](http://www.mulesoft.org/documentation/display/current/Global+Elements).

### Example Use Case

The example application connects to an LDAP directory and retrieves a list of LDAP users. 

### Set Up and Run the Example 

1. Set up LDAP on your machine
   * For Windows: Install OpenLDAP from [http://www.openldap.org](http://www.openldap.org/).
   * For MacOS: OpenLDAP comes bundled with MacOS, so you do not need to download an LDAP server.
   
1. Install Apache Directory Studio from [http://directory.apache.org/studio/downloads.html](http://directory.apache.org/studio/downloads.html).
 
1. Navigate to `etc\openldap\slapd.conf` in your OpenLDAP installation directory and set `rootpw` in the **BDB database definitions** section to `root`. You might also need to encrypt the password by using the following command before adding it to the `slapd.conf` file:
		
		slappasswd -h {SHA} -s <password>
   
1. Start the LDAP server
   * For Windows: Enter `libexec\StartLDAP.cmd` at a command line.
   * For MacOS: Enter `sudo /usr/libexec/slapd -d 255`.
    
1. Start Apache Directory Studio and create a new connection by selecting **File > New ... > LDAP Connection**. Use these settings:

		name		local LDAP
		hostname	localhost
		port		389

1. Click **Check network parameter**. If the test is not succesful, your LDAP server is not running. If the test is successfule, click **Next**.
	
1. Set **Bind dn or user** to `cn=Manager,dc=my-domain,dc=com` and **Bind password** to `root`. Click **Check Authentication** to verify the connection. Click **Finish**.

1. Click **File > Import... > LDIF into LDAP** and then click **Next**. 

1. Set the path to `ldap.ldif`, which is located in the `scr/main/resources` directory of this project. Set **Import into** to `local LDAP`. Click **Finish** to finish the import process. If you click **ROOT DSE** in the panel **LDAP browser**, you should see the imported data structure.

1. Open the Example project in Anypoint Studio from [Anypoint Exchange](http://www.mulesoft.org/documentation/display/current/Anypoint+Exchange).

1. In your application in Studio, click the **Global Elements** tab. Double-click the HTTP Listener global element to open its **Global Element Properties** panel. Change the contents of the **port** field to required HTTP port (e.g., 8081).

1. Double-click `ldap.xml` under `src/main/app directory` and click the **Global Elements** tab. Open **LDAP configuration** and specify these values:

		Principal DN	cn=Manager,dc=my-domain,dc=com
		Password		root
		URL 			ldap://localhost:389/dc=my-domain,dc=com

1. Run the Mule Application.

1. Open your browser and type `http://localhost:8081/` in the address bar.

You should see three user records in response:
```
[
    {
        "dn": "cn=admin,ou=people",
        "sn": "admin",
        "cn": "admin",
        "objectclass": [
            "top",
            "person"
        ],
        "userpassword": "mmcadmin"
    },
    {
        "dn": "cn=testuser1,ou=people",
        "sn": "testuser1",
        "cn": "testuser1",
        "objectclass": [
            "top",
            "person"
        ],
        "userpassword": "testuser1123"
    },
    {
        "dn": "cn=mmc,ou=people",
        "sn": "mmc",
        "cn": "mmc",
        "objectclass": [
            "top",
            "person"
        ],
        "userpassword": "mmc123"
    }
]
```
### How it Works 

An [HTTP endpoint](http://www.mulesoft.org/documentation/display/current/HTTP+Connector) is responsible for listening to incoming HTTP requests and sending the response. The [LDAP Connector](http://www.mulesoft.org/connectors/ldap-connector) performs the search operation with the given search parameters.

The following steps outline the process to build this application:

1. Create a new Mule Project by going to **File > New... > Mule Project** and name it `Importing data from an LDAP Directory`.
2. Drag an **HTTP listener** into a new flow. This is an inbound endpoint for your proxy application and receives all the requests that reference its address. 
3. Click on the plus sign next to the Connector Configuration field. A dialogue for creating HTTP Listener Configuration will be displayed. Specify these values:

		Host 	localhost
		Port 	8081 
4. Click **Ok**.	
5. Set the **Path** attribute to `/`.		
6. Drag **Search operation of LDAP connector** into the flow. Add an LDAP Configuration. Configure it as follows:

		Principal DN	cn=Manager,dc=my-domain,dc=com
		Password		root
		Url 			ldap://localhost:389/dc=my-domain,dc=com
		 
7. Test the connection to see if the connection parameters are correct.
	
8. Configure **Search operation**, added in the previous step, as follows:

		Base DN 	ou=people
		Filter 		(objectClass=*)
		 
5. Build a JSON response using the **Set Payload** message processor.

Having completed the flow, you can hit the HTTP endpoint to see all the users stored in the LDAP system in the given domain.
