# Blockchain Transaction Management System

A Spring Boot application demonstrating blockchain transaction management using Ethereum smart contracts with Web3j integration.

## Features

- **Multiple Entry Types**: Payment, Vote, System, Audit, and Generic entries
- **Buffer Pattern**: Batch entries before blockchain writes for efficiency
- **Validation**: Type-specific validation rules with custom exception handling
- **Polymorphism**: Factory pattern for different entry types
- **Blockchain Integration**: Web3j for Ethereum smart contract interaction
- **REST API**: Clean endpoints for transaction management

## Prerequisites

- Java 21+
- Maven 3.6+
- Node.js 16+
- Hardhat (for local blockchain)

## Quick Start

### 1. Clone and Setup
```bash
git clone <your-repo>
cd demo-Blockchain-Transaction
npm install
```

### 2. Environment Configuration
Create a `.env` file with your configuration:
```env
# Blockchain Configuration
BLOCKCHAIN_RPC_URL=http://127.0.0.1:8545
PRIVATE_KEY=0xac0974bec39a17e36ba4a6b4d238ff944bacb478cbed5efcae784d7bf4f2ff80
CONTRACT_ADDRESS=0x0000000000000000000000000000000000000000

# Server Configuration
SERVER_PORT=8080
SPRING_PROFILES_ACTIVE=dev

# Logging
LOGGING_LEVEL_ROOT=INFO
LOGGING_LEVEL_COM_EXAMPLE_CHAIN=DEBUG
```

### 3. Start Local Blockchain
```bash
npx hardhat node
```

### 4. Deploy Smart Contract (Optional)
```bash
cd txlog-chain
node scripts/deploy.js
# Update CONTRACT_ADDRESS in .env with deployed address
```

### 5. Run Application
```bash
mvn spring-boot:run
```

## API Endpoints

### Buffer Entries
- **POST** `/api/log/{type}?ref={ref}&payload={payload}`
  - Types: `payment`, `vote`, `system`, `audit`, `generic`
  - Returns: JSON with buffered entry details

### View Buffer
- **GET** `/api/log/buffer`
  - Returns: Array of all buffered entries

### Flush to Blockchain
- **POST** `/api/log/flushOne` - Flush single entry
- **POST** `/api/log/flushAll` - Flush all entries

## Entry Types & Validation Rules

| Type | Reference Format | Payload Requirements |
|------|-----------------|---------------------|
| `PAYMENT` | Must start with `INV-` | Must contain `"amount"` |
| `VOTE` | Must start with `ELECT-` | Max 2048 characters |
| `SYSTEM` | Must start with `SYS-` | No restrictions |
| `AUDIT` | No restrictions | No restrictions |
| `GENERIC` | No restrictions | No restrictions |

## Demo Commands (PowerShell)

```powershell
# Add different entry types
Invoke-RestMethod -Uri "http://localhost:8080/api/log/payment?ref=INV-001&payload={`"amount`":250}" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/log/vote?ref=ELECT-001&payload=VoteForCandidate" -Method POST  
Invoke-RestMethod -Uri "http://localhost:8080/api/log/system?ref=SYS-456&payload=ServerRestart" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/log/generic?ref=GEN001&payload=GenericData" -Method POST

# View buffered entries
Invoke-RestMethod -Uri "http://localhost:8080/api/log/buffer" -Method GET

# Flush to blockchain
Invoke-RestMethod -Uri "http://localhost:8080/api/log/flushOne" -Method POST
Invoke-RestMethod -Uri "http://localhost:8080/api/log/flushAll" -Method POST
```

## Architecture

```
┌─────────────────┐    ┌──────────────┐    ┌─────────────────┐
│  REST Controller │────│ Log Service  │────│  Blockchain     │
│  (Spring Boot)   │    │ (Buffer)     │    │  (Ethereum)     │
└─────────────────┘    └──────────────┘    └─────────────────┘
         │                      │                      │
         │              ┌───────────────┐              │
         └──────────────│ Entry Factory │──────────────┘
                        │ (Polymorphism)│
                        └───────────────┘
```

## Key Classes

- **LogController**: REST API endpoints
- **LogService**: Business logic with buffer management
- **EntryFactory**: Creates different entry types (Factory Pattern)
- **LedgerEntry**: Abstract base class for all entries
- **Web3Config**: Blockchain connection configuration
- **PublicTxLog**: Smart contract wrapper (Web3j generated)

## Error Handling

The application includes comprehensive error handling:
- **ValidationException**: Custom business rule violations
- **IllegalStateException**: System state errors
- **GlobalExceptionHandler**: Centralized error responses

## Development

### Project Structure
```
src/main/java/com/example/chain/
├── config/          # Configuration classes
├── controller/      # REST controllers
├── domain/          # Domain models and factories
└── service/         # Business logic

txlog-chain/         # Smart contract project
├── contracts/       # Solidity contracts
├── scripts/         # Deployment scripts
└── test/           # Contract tests
```

### Building
```bash
mvn clean compile
mvn test
mvn package
```

## Troubleshooting

**Common Issues:**
1. **Connection refused**: Ensure Hardhat node is running on port 8545
2. **Invalid private key**: Check PRIVATE_KEY in .env file
3. **Contract not found**: Deploy contract and update CONTRACT_ADDRESS
4. **400 Bad Request**: Check entry validation rules match your input

## License
GPL 3.0 License
Educational purposes demonstrating blockchain integration with Spring Boot.