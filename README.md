# Optimal Spring Boilerplate

**Optimal Spring Boilerplate** is a simple boilerplate to create secured **Spring Boot** apps with **MongoDB**.

It contains Spring Security configuration settings, with JWT user authentication, user entities and Mongo repositories.
This boilerplate enable multi-user application with secured authentication.

## Packages 

### Appuser
Contains everything about your user, with credentials. The user entity implements **UserDetails** so that it can be used in Spring Security for account activation.

### Config
Contains all the configuration files :
+ Bcrypt encryption bean configuration
+ JWT token generation and claims extration
+ Authentication filters with JWT authentication
+ Security configuration

### Authentication
Contains all the authentication services and controllers. Authentication request and response DTOs.
Register and Login endpoins are here !

### Image
Contains a simple multipart image handler.
