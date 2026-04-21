## API Design Overview

The Smart Campus API is designed using RESTful principles to manage rooms, sensors, and their associated readings. The architecture follows a layered approach consisting of resource classes, data access objects (DAOs), and model classes.

The API exposes three primary resources:

* **Rooms**: Represent physical spaces on campus. Each room maintains a list of associated sensor IDs.
* **Sensors**: Represent IoT devices deployed within rooms. Each sensor is linked to a specific room and maintains a current value reflecting its latest reading.
* **Sensor Readings**: Represent historical data collected from sensors over time.

The API supports standard HTTP methods such as GET, POST, and DELETE to perform CRUD-like operations. Relationships between resources are maintained using identifiers (e.g., `roomId` in Sensor).

A key design feature is the use of the **Sub-Resource Locator pattern** to manage nested resources. For example, sensor readings are accessed via `/sensors/{id}/readings`, allowing clean separation of concerns and improved scalability.

Data is stored in memory using collections such as ArrayList and HashMap, as per coursework requirements. No external database is used.

The API also implements proper HTTP status codes and exception handling to ensure robust and predictable client interactions.

## Setup and Execution Instructions

### Prerequisites

* Java JDK 8+
* NetBeans IDE
* Apache Tomcat 9
* Maven

### Steps

1. **Clone repo**

```bash
git clone https://github.com/SamindiUmeshaLiyanage/CSA-Coursework.git
```

2. **Open in NetBeans**

* File → Open Project → Select folder

3. **Build project**

* Right-click → *Clean and Build*

4. **Configure Tomcat (first time)**

* Tools → Servers → Add Tomcat

5. **Run project**

* Right-click → *Run*

### Base URL

```bash
http://localhost:8080/CSA-Coursework/api/v1
```

Test using Postman or curl.
## Curl 
### 1. Discovery

```bash
curl http://localhost:8080/CSA-Coursework/api/v1
```

### 2. Create Room

```bash
curl -X POST http://localhost:8080/CSA-Coursework/api/v1/rooms -H "Content-Type: application/json" -d "{\"id\":\"HALL-001\",\"name\":\"Main Hall\",\"capacity\":200}"
```

### 3. Get Rooms

```bash
curl http://localhost:8080/CSA-Coursework/api/v1/rooms
```

### 4. Filter Sensors

```bash
curl "http://localhost:8080/CSA-Coursework/api/v1/sensors?type=CO2"
```

### 5. Add Reading

```bash
curl -X POST http://localhost:8080/CSA-Coursework/api/v1/sensors/TEMP-001/readings -H "Content-Type: application/json" -d "{\"id\":\"READ-003\",\"value\":25.5,\"timestamp\":1234567890}"
```

### 6. Error Example (409)

```bash
curl -X DELETE http://localhost:8080/CSA-Coursework/api/v1/rooms/LAB-101
```

### 7. Error Example (422)

```bash
curl -X POST http://localhost:8080/CSA-Coursework/api/v1/sensors -H "Content-Type: application/json" -d "{\"id\":\"TEMP-999\",\"type\":\"Temperature\",\"status\":\"ACTIVE\",\"currentValue\":0.0,\"roomId\":\"FAKE-999\"}"
```
