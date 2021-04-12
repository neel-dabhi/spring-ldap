# spring-ldap

A local MySQL DB needs to be setup, in order to run.

## :information_source: How To Use

Clone and Run application.
Here are the Postman Sample Requests


```bash
# POST : Submit API (Requires authentication and CIRCUS_CHOREOGRAPH / CIRCUS_ADMIN authorization)
# Sends the original data with point of collision (if no collision -1) and the message.
Endpoint: http://localhost:8080/submit
Username: bob
Password: bob
Body :
{
    "x1": 0,
    "v1": 3,
    "x2": 4,
    "v2": 2
}

# GET : Get API (Requires authentication and CIRCUS_ADMIN authorization)
# Sends all the privious request where collison occured.
Endpoint: http://localhost:8080/get-collisions
Username: ben
Password: ben

```
