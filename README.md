# spring-boot-contact-manager
Contact CRUD Operations

POST:
http://localhost:8080/contacts/

{
        "firstName": "Nikhila",
        "lastName": "Nervetla",
        "emailAddress": "nikhila.nervetla@gmail.com"
}

GET ALL
======
http://localhost:8080/contacts/

GET by EMAIL
==============
http://localhost:8080/contacts/nikhila.nervetla@gmail.com

UPDATE [PUT]
==============
http://localhost:8080/contacts/nikhila.nervetla@gmail.com
{
        "firstName": "NikhilaReddy",
        "lastName": "Nervetla",
        "emailAddress": "nikhila.nervetla@gmail.com"
}
    
    
DELETE 
======
http://localhost:8080/contacts/nikhila.nervetla@gmail.com



{
    "AWSTemplateFormatVersion": "2010-09-09",
    "Description": "creates SFTP Server",
    "Resources": {
        "MyTransferServer": {
            "Type": "AWS::Transfer::Server",
            "Properties": {
                "EndpointDetails": {
                    "AddressAllocationIds": [
                        "AddressAllocationId-1",
                        "AddressAllocationId-2"
                    ],
                    "SubnetIds": [
                        "SubnetId-1",
                        "SubnetId-2"
                    ],
                    "VpcId": "VpcId"
                },
                "EndpointType": "VPC",
                "LoggingRole": "Logging-Role-ARN",
                "Protocols": [
                    "SFTP"
                ],
                "SecurityPolicyName": "Security-Policy-Name",
                "IdentityProviderDetails": {
                    "InvocationRole": "Invocation-Role-ARN",
                    "Url": "API_GATEWAY-Invocation-URL"
                },
