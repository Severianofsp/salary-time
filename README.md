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

# Documentação e Testes da API

## Endpoints Disponíveis

### 1. Buscar Todos os Funcionários

- **URL**: `/api/employees`
- **Método HTTP**: `GET`
- **Descrição**: Retorna uma página de funcionários com base nos filtros fornecidos.

**Parâmetros de Query**:

- `page` (opcional): Número da página. Default: `0`.
- `size` (opcional): Tamanho da página. Default: `20`.

**Exemplo de Requisição com Curl**:

```bash
curl -X GET "http://localhost:8080/api/employees?page=0&size=10" \
-H "Accept: application/json"
```

**Resposta Esperada (JSON)**:

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

### 2. Buscar Funcionário por ID

- **URL**: `/api/employees/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Retorna os detalhes de um funcionário com base no ID fornecido.

**Parâmetro de Path**:

- `id` (obrigatório): ID do funcionário a ser buscado.

**Exemplo de Requisição com Curl**:

```bash
curl -X GET "http://localhost:8080/api/employees/123" \
-H "Accept: application/json"
```

**Resposta Esperada (JSON)**:

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

**Possíveis Erros**:

- Se o ID não existir, retorna:
    - **Status**: `404 Not Found`
    - **Body**:
      ```json
      {
        "error": "Employee Not Found"
      }
      ```

---

### 3. Registrar Novo Funcionário

- **URL**: `/api/employees`
- **Método HTTP**: `POST`
- **Descrição**: Registra um novo funcionário no sistema.

**Body da Requisição (JSON)**:

```json
{
  "name": "John Doe",
  "admissionDate": "2023-01-15",
  "grossSalary": 5000.00
}
```

**Exemplo de Requisição com Curl**:

```bash
curl -X POST "http://localhost:8080/api/employees" \
-H "Content-Type: application/json" \
-H "Accept: application/json" \
-d '{
  "name": "John Doe",
  "admissionDate": "2023-01-15",
  "grossSalary": 5000.00
}'
```

**Resposta Esperada**:

- **Status**: `201 Created`
- **Body**: (vazio)

**Validações**:

- Caso o body contenha campos inválidos, retorna:
    - **Status**: `400 Bad Request`
    - **Body**:
      ```json
      {
        "errors": [
          {
            "field": "name",
            "message": "Name is required"
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

