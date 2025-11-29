Here is the **updated README.md** including **Spring Cloud Stream** for Kafka.
You can copy/paste this directly into your GitHub repository.

---

# **Microservices Quiz Application**

A distributed **Microservices-based Quiz Application** built with **Spring Boot**, featuring automated question generation, secure APIs, asynchronous communication, fault-tolerance patterns, and centralized configuration. This project demonstrates modern microservices architecture using **Spring Cloud**, **Spring Cloud Stream (Kafka Binder)**, **Keycloak**, **Circuit Breaker**, **API Gateway**, **Eureka**, and more.

---

## 🚀 **Project Overview**

This application allows users to create quizzes.

When a quiz is created:

1. The **Quiz Service** publishes a Kafka event using **Spring Cloud Stream**.
2. The **Question Generator Service** listens to that event via Spring Cloud Stream consumer bindings.
3. It automatically generates **10 questions** and associates them with the quiz.
4. All services are authenticated with **Keycloak** and monitored via **Actuator**.

The system uses modern communication, security, service discovery, and fault tolerance patterns.

---

## 🧩 **Microservices Included**

### **1. Quiz Service**

* Handles quiz creation.
* Publishes quiz-created events to Kafka via **Spring Cloud Stream**.
* Uses:

  * RestTemplate
  * Feign Client
  * WebClient
* Secured through Keycloak.

### **2. Question Generator Service**

* Consumes Kafka messages using **Spring Cloud Stream Binder for Kafka**.
* Automatically generates 10 questions.
* Saves generated data or responds back through APIs.

### **3. API Gateway**

* Secures and routes incoming requests.
* Validates Keycloak tokens.

### **4. Eureka Server**

* Registers all microservices.
* Enables service discovery.

### **5. Config Server**

* Manages central configurations for all microservices.

### **6. Keycloak Auth Server**

* Manages users, roles, and realms.

---

## 🛠 **Technologies & Features**

### 🔐 **Authentication & Authorization**

* Keycloak with realm-level access control.
* API Gateway secured using Bearer Token validation.

### 📡 **Inter-Service Communication**

* **Feign Client** for declarative REST calls
* **RestTemplate** for synchronous operations
* **WebClient** for reactive communication

---

## 💬 **Asynchronous Messaging with Kafka**

Implemented using **Spring Cloud Stream Kafka Binder**.

### ✔ Features:

* Decoupled microservices via messaging
* Easy event publisher and consumer configuration
* Payload-based communication using JSON
* Topic creation/management from configuration

### 📌 Example:

* Quiz Service → *Message Producer* (quiz-created event)
* Question Generator Service → *Message Consumer*

Spring Cloud Stream simplifies message handling with functional bindings such as:

```java
@Bean
public Supplier<Message<QuizEvent>> quizEventPublisher() { ... }

@Bean
public Consumer<QuizEvent> quizEventConsumer() { ... }
```

---

## ⚙️ **Fault Tolerance (Resilience4j)**

* **Circuit Breaker Pattern**
  Prevents cascading failures when dependent services are unavailable.

* **Retry Pattern**
  Limits retries and adds delays to prevent infinite calling loops.

---

## 🎛 **Service Discovery**

* **Netflix Eureka Server**
  Auto-registers each microservice for easy discovery and load balancing.

---

## 🛡 **API Gateway**

* Central entry point for all service calls.
* Integrates with Keycloak for authentication.
* Applies security filters and routing rules.

---

## 🗂 **Centralized Configuration**

* Spring Cloud Config Server stores:

  * Profiles (dev, test, prod)
  * Kafka properties
  * Eureka properties
  * Keycloak configuration
  * Logging and management settings

---

## 📊 **Logging & Monitoring**

* **Spring Boot Actuator** for health metrics.
* Centralized logging.
* Exposed endpoints for monitoring each microservice.

---

## 📡 **Spring Cloud Stream Kafka Binder Setup**

### **Producer (Quiz Service)**

Publishes quiz-created events.

```yaml
spring:
  cloud:
    stream:
      bindings:
        quizCreated-out-0:
          destination: quiz-created-topic
      kafka:
        binder:
          brokers: localhost:9092
```

### **Consumer (Question Generator Service)**

Consumes quiz-created events.

```yaml
spring:
  cloud:
    stream:
      bindings:
        quizCreated-in-0:
          destination: quiz-created-topic
          group: question-generator-group
```

This abstraction removes the boilerplate Kafka template and listener code.

---

## 🧪 **How the Application Works**

1. User logs in via Keycloak.
2. Sends a request to create a quiz → API Gateway → Quiz Service.
3. Quiz Service:

   * Saves quiz
   * Sends Kafka event using Spring Cloud Stream
4. Question Generator Service:

   * Consumes event
   * Generates 10 questions
5. User retrieves quiz along with generated questions.

---

## 📁 **Folder Structure Example**

```
/quiz-service
/question-generator-service
/api-gateway
/eureka-server
/config-server
/keycloak
/common-libs
```

---

## 📚 **Future Enhancements**

* Add React/Angular UI
* Add distributed tracing with Zipkin or Jaeger
* Convert services to fully reactive architecture
* Add Redis caching layer

---

## 🤝 **Contributing**

Contributions are welcome—feel free to open a pull request or raise an issue!
