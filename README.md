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

# API Documentation and Testing

## Available Endpoints

---

### 1. Get All Employees

- **URL**: `/api/employees`
- **HTTP Method**: `GET`
- **Description**: Returns a page of employees based on the provided filters.

**Query Parameters**:

- `name` (optional): Name employee's admission,
- `startAdmissionPeriodDate` (optional): Start date for the admission period filter.
- `endAdmissionPeriodDate` (optional): End date for the admission period filter.
- `grossSalaryGreaterThan` (optional): Minimum gross salary for filtering.
- `grossSalaryLessThan` (optional): Maximum gross salary for filtering.
- `page` (optional): Page number. Default: `0`.
- `size` (optional): Page size. Default: `20`.

**Curl Request Example**:

```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/employees?name=John%20Doe&startAdmissionPeriodDate=2023-01-01&endAdmissionPeriodDate=2023-12-31&grossSalaryGreaterThan=3000&grossSalaryLessThan=10000&page=0&size=1' \
  -H 'accept: application/json'
```  

**Expected Response (JSON)**:

```json
{
  "content": [
    {
      "id": "123",
      "name": "John Doe",
      "yearsPassed": 1,
      "monthsPassed": 2,
      "daysPassed": 15,
      "grossSalary": 5000.00,
      "calculatedPercentage": 1750.00
    }
  ],
  "totalPages": 1,
  "currentPageNumber": 0,
  "numberOfElements": 10,
  "totalElements": 10
}
```  

---

### 2. Get Employee by ID

- **URL**: `/api/v1/employees/{id}`
- **HTTP Method**: `GET`
- **Description**: Returns the details of an employee based on the provided ID.

**Path Parameter**:

- `id` (required): The ID of the employee to fetch.

**Curl Request Example**:

```bash
curl -X GET "http://localhost:8080/api/v1/employees/123" \  
-H "Accept: application/json"
```  

**Expected Response (JSON)**:

```json
{
  "id": "123",
  "name": "John Doe",
  "yearsPassed": 1,
  "monthsPassed": 2,
  "daysPassed": 15,
  "grossSalary": 5000.00,
  "calculatedPercentage": 1750.00
}
```  

**Possible Errors**:

- If the ID does not exist, the response will be:
    - **Status**: `404 Not Found`
    - **Body**:
      ```json
        {
          "code": 404,
          "status": "Not Found",
          "message": "Employee Not Found",
          "path": "/api/v1/employees",
          "errors": []
        }
      ```  

---

### 3. Register a New Employee

- **URL**: `/api/v1/employees`
- **HTTP Method**: `POST`
- **Description**: Registers a new employee in the system.

**Request Body (JSON)**:

```json
{
  "name": "John Doe",
  "admissionDate": "2023-01-15",
  "grossSalary": 5000.00
}
```  

**Curl Request Example**:

```bash
curl -X POST "http://localhost:8080/api/v1/employees" \  
-H "Content-Type: application/json" \  
-H "Accept: application/json" \  
-d '{
  "name": "John Doe",
  "admissionDate": "2023-01-15",
  "grossSalary": 5000.00
}'
```  

**Expected Response**:

- **Status**: `201 Created`
- **Body**: *(empty)*

**Validations**:

- If the request body contains invalid fields, the response will be:
    - **Status**: `400 Bad Request`
    - **Body**:
      ```json
        {
          "code": 400,
          "status": "Bad Request",
          "message": "Invalid request parameters.",
          "path": "/api/v1/employees",
          "errors": [
            {
              "fieldName": "admissionDate",
              "message": "Admission date must be today or in the past."
            }
          ]
        }
      ```
---

## Technologies Used

- **Backend**: Spring Boot
- **Database**: MongoDB
- **Documentation**: Springdoc OpenAPI (Swagger UI)
- **Build Tool**: Gradle
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

