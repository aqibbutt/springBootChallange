# Springboot challenge 

Hey there! Welcome to the Spring boot backend challenge. 

## The project

This project is just a POC for spring boot API Exercise

### Business case

This task is based on speed home system. 

## The process

NOTE: use Postman to test the API. Base URL for these API is : http://localhost:8091/api/property
1. In order to access all the endpoint user need to login. For login user need to hit following endpoint
   ```json 
   POST http://localhost:8091/api/user/login?user=user&password=qwe
   ```
2. For moving on please copy the token and past it in header with name like this 
   ```json
   Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJqdGkiOiJzb2Z0dGVrSldUIiwic3ViIjoic2ViYXMiLCJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwiaWF0IjoxNjE3OTIwNzQzLCJleHAiOjE2MTc5MjEzNDN9.XlRqSBOewBKVDeognAobQGfpFgPJTMnbSv_72P71ZUJsagulPRhtBfswKWMqvEJNuj5z2r715avusNeO6nJecQ
   ```
3. Add the property by using following DTO. Please use POST method.
   ```json
   {
    "propertyName": "ABC",
    "size": "ABC",
    "location": "ABC",
    "status": true
    }
4. Edit the property by using same DTO as of adding the DTO. please use PATCH method
5. Get the Property by ID using API 
   ```json
      GET : http://localhost:8091/api/property/{id}
   ```
6. Get the list of Property by calling 
   ```json
   GET : http://localhost:8091/api/property 
   ```
   This endpointis capable of filtering and searching. it also includes pagination.
for globle search use parameter "search" and write any property LIKE 'prpertyName' ,'size' or 'location'. FOrexample if you hit API http://localhost:8091/api/property/?search=AB you will get response like 
   ```json
   {
    "totalPages": 1,
    "totalResults": 1,
    "data": [
                {
                    "id": 1,
                    "propertyName": "CBD",
                    "location": "ABC",
                    "size": "ABC",
                    "status": true,
                    "created": "2021-04-08T22:37:38.304",
                    "lastModified": "2021-04-08T22:38:07.806"
                }
        ]
    }
5. For approving a property API : PATCH :  http://localhost:8091/api/property/{id}/approve/{check} is called. Where id is ID of property and check is boolean.
## How to start

- Run the project. and use port 8091
