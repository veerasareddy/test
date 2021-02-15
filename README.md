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
