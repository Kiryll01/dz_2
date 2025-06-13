# JSONPlaceholder API Clone

This is a Spring Boot implementation of the JSONPlaceholder API with JWT authentication and PostgreSQL database.

## Features

- Full CRUD operations for users
- JWT-based authentication
- PostgreSQL database
- Docker support
- Initial data seeding

## Prerequisites

- Java 17 or higher
- Maven
- Docker and Docker Compose

## Setup

1. Clone the repository
2. Start the PostgreSQL database:
   ```bash
   docker-compose up -d
   ```
3. Build and run the application:
   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

The application will be available at `http://localhost:8080`

## API Endpoints

### Users

- `GET /users` - Get all users
- `GET /users/{id}` - Get user by ID
- `POST /users` - Create a new user
- `PUT /users/{id}` - Update a user
- `DELETE /users/{id}` - Delete a user

## Database

The application uses PostgreSQL. The database configuration can be found in `application.properties`.

## Security

JWT authentication is implemented. The JWT secret and expiration time can be configured in `application.properties`.

## Development

To run the application in development mode:

```bash
mvn spring-boot:run
```

## Testing

To run the tests:

```bash
mvn test
``` 