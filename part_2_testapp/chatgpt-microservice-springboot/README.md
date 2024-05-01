## Build and run docker image

In the Terminal, go to the project folder "/chatgpt-microservice-springboot"

Run commands:

```
  docker build -t chatgpt-app .
```

```
  docker run -d -p 8500:8500 chatgpt-app
```


## Credentials for login page http://localhost:8500/:

Username: admin

Password: password

## Get auth token via POSTMAN:
```
POST http://localhost:8500/api/v1/auth
```

Request body:
```
{
    "username": "admin",
    "password": "password"
}
```

Response:
```
{
    "token": "token",
    "expiresIn": 86400000
}
```

Use this token for other endpoints with Header:
```
Autorization: "Bearer {token}"
```