# Employee Management API

This project is a robust Employee Management API developed using **Spring Boot** and **MongoDB** as the database. It follows **SOLID principles** and **Clean Code** practices, ensuring maintainability, scalability, and clear separation of concerns.

## Features

- **CRUD Operations**: Full support for creating, reading, updating, and deleting employee records.
- **Pagination and Sorting**: Efficient retrieval of employee records with pagination.
- **Error Handling**: Comprehensive error handling with detailed responses.
- **Custom Calculations**:
    - Calculate days, months, and years since an employee's admission.
    - Calculate 35% of the gross salary, ensuring precise financial calculations.
- **Swagger Integration**: Interactive API documentation using **Springdoc OpenAPI**.

---

## Endpoints

### **Employee Endpoints**

#### **Retrieve All Employees (Paginated)**
**GET** `/api/v1/employees`

- **Query Parameters**:
    - `page` (optional, default: 0): Page number.
    - `size` (optional, default: 10): Number of records per page.
    - `sort` (optional): Sorting field and direction, e.g., `admissionDate,desc`.

- **Responses**:
    - **200 OK**: Returns a paginated list of employees.
    - **400 Bad Request**: Invalid parameters with an `ErrorResponse` body.

#### **Create Employee**
**POST** `/api/v1/employees`

- **Request Body**:
  ```json
  {
    "admissionDate": "2023-01-15",
    "grossSalary": 5000.00
  }
  ```
- **Responses**:
    - **201 Created**: Employee successfully created.
    - **400 Bad Request**: Invalid input data.

---

### **Error Responses**
**Model: `ErrorResponse`**

- **Fields**:
    - `code`: HTTP status code.
    - `status`: Status text.
    - `message`: Detailed error description.
    - `path`: API path that caused the error.
    - `errors`: List of field-specific validation errors.

Example:
```json
{
  "code": 400,
  "status": "Bad Request",
  "message": "Invalid request parameters.",
  "path": "/api/v1/employees",
  "errors": [
    {
      "fieldName": "grossSalary",
      "message": "must be greater than 0"
    }
  ]
}
```

---

## Technologies Used

- **Backend**: Spring Boot
- **Database**: MongoDB
- **Documentation**: Springdoc OpenAPI (Swagger UI)
- **Build Tool**: Maven
- **Java Version**: 17
- **Containerization**: Docker, Docker Compose

---

## Development Setup

1. **Clone the Repository**:
   ```bash
   git clone git@github.com:Severianofsp/salary-time.git
   cd salary-time
   ```

2. **Run the Application with Docker Compose**:
    - Ensure Docker and Docker Compose are installed.
    - Start the application:
      ```bash
      docker-compose up --build
      ```

3. **Access Swagger UI**:
    - URL: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

