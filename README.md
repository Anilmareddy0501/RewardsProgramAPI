  

# Rewards Program API

## Overview
This Spring Boot application calculates reward points based on customer purchases:
- 2 points for every dollar spent over $100.
- 1 point for every dollar spent between $50 and $100.
  
### Example:
A $120 purchase earns 90 points.

---

## API Endpoints

### 1. **Access All Rewards**
Fetch total reward points for all customers.

- **URL**: `http://localhost:8080/api/rewards`
- **Method**: `GET`
- **Description**: This endpoint retrieves reward points summaries for all customers.

#### Example Request:
```bash
GET http://localhost:8080/api/rewards
```

#### Example Response:
```json
[
  {
    "customerId": "Jack",
    "totalPoints": 270,
    "transactions": [
      { "date": "2024-01-15", "points": 120 },
      { "date": "2024-02-10", "points": 150 }
    ]
  },
  {
    "customerId": "Becky",
    "totalPoints": 170,
    "transactions": [
      { "date": "2024-02-17", "points": 75 },
      { "date": "2024-03-15", "points": 95 }
    ]
  }
]
```

### 2. **Access Rewards for a Specific Customer**
Fetch reward points for a specific customer using their `customerId`.

- **URL**: `http://localhost:8080/api/rewards/{customerId}`
- **Method**: `GET`
- **Description**: This endpoint retrieves the reward points summary for a specific customer based on their `customerId`.

#### Example Request:
```bash
GET http://localhost:8080/api/rewards/Jack
```

#### Example Response:
```json
{
  "customerId": "Jack",
  "totalPoints": 270,
  "transactions": [
    { "date": "2024-01-15", "points": 120 },
    { "date": "2024-02-10", "points": 150 }
  ]
}
```

### 3. **Access Rewards for a Customer within a Date Range**
Fetch reward points for a specific customer within a given date range.

- **URL**: `http://localhost:8080/api/rewards/{customerId}/dateRange?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`
- **Method**: `GET`
- **Description**: This endpoint retrieves the reward points for a specific customer within the specified date range.

#### Example Request:
```bash
GET http://localhost:8080/api/rewards/Jack/dateRange?startDate=2024-01-01&endDate=2024-12-31
```

#### Example Response:
```json
{
  "customerId": "Jack",
  "totalPoints": 270,
  "transactions": [
    { "date": "2024-01-15", "points": 120 },
    { "date": "2024-02-10", "points": 150 }
  ]
}
```

---

## Setup

### 1. Clone the Repository:
```bash
git clone https://github.com/YourUsername/rewardAPI.git
cd rewardAPI
```

### 2. Build and Run the Application:
```bash
mvn clean install
mvn spring-boot:run
```

### 3. Access the Application:
Once the application is running, access it via the following URLs:
- **All Rewards**: `http://localhost:8080/api/rewards`
- **Specific Customer Rewards**: `http://localhost:8080/api/rewards/{customerId}`
- **Customer Rewards with Date Range**: `http://localhost:8080/api/rewards/{customerId}/dateRange?startDate=YYYY-MM-DD&endDate=YYYY-MM-DD`

---

## Example Use Cases

### 1. Get All Customer Rewards:
- **URL**: `http://localhost:8080/api/rewards`
- Returns a list of all customers with their total reward points and transaction history.

### 2. Get Rewards for a Specific Customer:
- **URL**: `http://localhost:8080/api/rewards/Jack`
- Returns the reward points and transaction history for the customer with `customerId` "Jack".

### 3. Get Rewards for a Specific Customer within a Date Range:
- **URL**: `http://localhost:8080/api/rewards/Jack/dateRange?startDate=2024-01-01&endDate=2024-12-31`
- Returns the reward points and transaction history for "Jack" within the given date range.

---

## Exception Handling

The API handles various exceptions and returns appropriate HTTP status codes:

- **404 Not Found**: Returned when a customer is not found.
- **400 Bad Request**: Returned for invalid input or parameters.
- **500 Internal Server Error**: Returned for unexpected errors.

 

