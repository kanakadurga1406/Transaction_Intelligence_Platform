# Transaction Intelligence Platform

An AI-powered financial transaction monitoring and investigation platform that helps identify potentially suspicious transactions using rule-based fraud detection, Model Context Protocol (MCP), and Google Gemini AI.

The platform combines traditional transaction analysis with AI-powered natural-language investigations, allowing users to investigate customer accounts, retrieve transaction histories, identify suspicious activity, and understand potential fraud patterns.

## 🚀 Key Features

- **Transaction Monitoring:** Retrieve and analyze financial transactions associated with customer accounts.
- **Rule-Based Fraud Detection:** Identify potentially suspicious activity using configurable transaction monitoring rules.
- **High-Value Transaction Detection:** Flag transactions involving amounts of ₹50,000 or above.
- **Failed Transaction Monitoring:** Identify accounts with multiple failed transactions within a 24-hour period.
- **MCP Integration:** Implement custom MCP tools to expose transaction and investigation capabilities to AI clients.
- **AI-Powered Investigation:** Use Google Gemini to interpret natural-language investigation requests and generate contextual responses.
- **REST API Integration:** Expose backend functionality through Spring Boot REST endpoints.
- **MySQL Database:** Store and retrieve account and transaction information.

## 🛠️ Technology Stack

| Category | Technologies |
|---|---|
| Backend | Java, Spring Boot |
| API Development | REST APIs |
| AI Integration | Google Gemini API |
| AI Tool Integration | Model Context Protocol (MCP) |
| Database | MySQL |
| Database Access | Spring Data JPA / Hibernate |
| Build Tool | Maven |
| API Testing | Postman / PowerShell |

## 🏗️ System Architecture

The platform uses a modular architecture consisting of an MCP server and an AI client.

```text
              User
                |
                v
       Natural Language Query
                |
                v
        AI Client Application
          (Spring Boot)
                |
                v
          Google Gemini
                |
                v
          MCP Client
                |
                v
          MCP Server
                |
                v
       Custom MCP Tools
                |
                v
       Transaction Services
                |
                v
          MySQL Database
```

### Main Components

**1. MCP Server**
- Implements custom MCP tools.
- Exposes transaction-related operations to AI clients.
- Connects tool execution with backend transaction services.

**2. AI Client**
- Accepts natural-language investigation requests.
- Integrates with Google Gemini.
- Uses MCP tools to retrieve relevant transaction information.
- Processes investigation results into contextual responses.

**3. Transaction Management**
- Retrieves account information and transaction histories.
- Applies transaction monitoring rules.
- Identifies suspicious transaction patterns.

**4. Database Layer**
- Uses MySQL for persistent data storage.
- Uses Hibernate/JPA for database operations.

## 🔍 Fraud Detection Logic

The platform currently demonstrates rule-based suspicious transaction detection using the following conditions:

| Detection Rule | Condition |
|---|---|
| High-Value Transaction | Transaction amount ≥ ₹50,000 |
| Multiple Failed Transactions | At least 2 failed transactions within 24 hours |

These rules help identify potentially suspicious activity for further investigation. They are monitoring indicators, not definitive proof of fraud.

## 💡 Example Investigation

**User Query:**

"Investigate account ACC1001. Show its transactions and explain why it may be suspicious."

**Sample Transaction Data:**

| Transaction ID | Amount | Status |
|---|---:|---|
| TXN10001 | ₹7,500 | SUCCESS |
| TXN10002 | ₹65,000 | FAILED |
| TXN10007 | ₹72,000 | FAILED |

**Investigation Findings:**

- Three transactions are associated with account ACC1001.
- Two transactions have failed.
- Both failed transactions meet the high-value threshold.
- The account meets the configured multiple-failure monitoring condition, assuming both failures occurred within the same 24-hour window.

The AI investigation layer can use these findings to explain why the account requires further review.

## 📁 Project Structure

```text
transaction-intelligence-platform/
│
├── src/
│   └── main/
│       ├── java/
│       │   └── ...
│       └── resources/
│           └── application.properties
│
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

*The directory structure above is a simplified overview.*

## ⚙️ Prerequisites

Make sure the following are installed:

- Java JDK 17 or compatible project-required version
- Maven
- MySQL Server
- Google Gemini API key
- Git

## 🔧 Installation and Setup

### 1. Clone the Repository

```bash
git clone <YOUR_GITHUB_REPOSITORY_URL>
cd transaction-intelligence-platform
```

### 2. Configure MySQL

Create a MySQL database:

```sql
CREATE DATABASE transaction_intelligence;
```

Configure your database connection in `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/transaction_intelligence
spring.datasource.username=YOUR_DB_USERNAME
spring.datasource.password=YOUR_DB_PASSWORD

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

Adjust the database name and configuration keys to match your application.

### 3. Configure Gemini API

Generate a Google Gemini API key and configure it as an environment variable.

**Windows PowerShell:**

```powershell
$env:GEMINI_API_KEY="YOUR_GEMINI_API_KEY"
```

Do not commit API keys or database credentials to GitHub.

### 4. Build the Project

```bash
.\mvnw.cmd clean install
```

### 5. Run the Applications

Start the MCP server and AI client using their respective Spring Boot application entry points and configurations.

The current local development setup uses:

| Component | Port |
|---|---:|
| MCP Server | 8080 |
| AI Client / REST API | 8081 |

The AI client exposes the chat endpoint:

```http
POST http://localhost:8081/api/chat
```

Example request:

```json
{
  "message": "Investigate account ACC1001. Show its transactions and explain why it may be suspicious."
}
```

## 🧪 Testing

The backend functionality has been tested through local API requests and database verification.

Testing includes:

- MySQL connectivity and transaction retrieval.
- Account-specific transaction investigation.
- MCP server and client connectivity.
- Custom MCP tool registration.
- Rule-based suspicious transaction detection.
- Gemini API connectivity and AI investigation workflow.

## 🔮 Future Enhancements

- React-based financial monitoring dashboard.
- Secure authentication and role-based access control.
- Real-time transaction monitoring and fraud alerts.
- Transaction analytics and visualization.
- Investigation history and audit logging.
- Docker-based deployment.
- Automated unit and integration testing.

## 🎯 Project Objective

The objective of this project is to explore how AI agents and Model Context Protocol can be integrated into financial applications to simplify transaction investigations, automate information retrieval, and improve the identification of potentially suspicious financial activity.

## 👩‍💻 Author

**Kanaka Durga**

Java | Spring Boot | AI/ML | MCP | Backend Development
