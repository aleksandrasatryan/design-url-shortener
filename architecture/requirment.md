### Functional Requirements

- URL owner requests for long URL shortening
- The generated short URL should be returned to client
- Short URL should be as short as possible
- Short URL should contain only [a-z], [A-Z] and [0-9] characters 
- Short URL can not be deleted or updated
- In average, 10 million URLs will be generated per day
- Short URL should redirect to long URL
- If long URL is already generated, return existing short URL

### Nonfunctional Requirements

- High availability
- Scalability
- Fault tolerance
- High write and read throughput 
- Low latency
- Microservice Architecture
- Domain Driven Design (inspired by https://en.wikipedia.org/wiki/Domain-driven_design)

### Back of the envelope estimation

- write operation
  - 10 million URLs are generated per day 
  - 10 million / 24 / 3600 = 120 per second
- read operation
  - 10:1 ratio to write, 120 * 10 = 1200 per second
- storage
  - assuming the service will run for 10 years, 10 million * 365 * 10 = 36,5 billion records
  - assume average URL length is 100
  - 36,5 billion * 100 bytes = 3.65 TB

### Tech Stack

- Java 21
- Soring Boot
- AWS API Gateway
  - Expose APIs
  - Rate limiting
  - Authorization 
- AWS DynamoDB:
  - Store URL details
  - Query URL details
  - High data volume 
  - Low latency
- AWS MemoryDB/Redis
  - Distributed caching
  - Low latency retrieval
  - TTL of 1 hour
- CRC32 for long URL hashing
