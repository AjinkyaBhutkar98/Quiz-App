# **SpringAi Quiz Application**

A lightweight microservices-based Quiz Application built with **Spring Boot**, using **Kafka**, **Spring Cloud Stream**, **Keycloak**, **Eureka**, **API Gateway**, **Config Server**, and **Spring AI** for AI-powered question generation.

---

## 🚀 **Overview**

* Users create quizzes through secured APIs.
* **Quiz Service** publishes a *quiz-created* event using **Spring Cloud Stream (Kafka Binder)**.
* **Question Generator Service** consumes the event and automatically generates **10 questions using Spring AI**.
* Services communicate using **Feign Client**, **RestTemplate**, and **WebClient**.
* **Keycloak** handles authentication.
* **Eureka** manages service discovery.
* **API Gateway** secures and routes external traffic.
* **Config Server** provides centralized configuration.
* **Resilience4j** adds circuit breaker & retry patterns.
* **Actuator** provides monitoring endpoints.

---

## 🧩 **Services**

* **Quiz Service** – Creates quizzes, publishes Kafka events
* **Question Generator Service** – Uses **Spring AI** to generate 10 questions from quiz metadata
* **API Gateway** – Routing & security
* **Eureka Server** – Service registry
* **Config Server** – Centralized configs
* **Keycloak** – Authentication & authorization

---

## 🛠 **Tech Stack**

* Spring Boot
* **Spring AI** (for LLM-based question generation)
* Spring Cloud Stream (Kafka Binder)
* Kafka
* Eureka
* API Gateway
* Keycloak
* Resilience4j
* Actuator

---

## 📡 **Event Flow**

1. Quiz created
2. Quiz Service → publishes Kafka event (`quiz-created-topic`)
3. Question Generator Service → consumes event
4. Uses **Spring AI** to generate 10 intelligent questions

---

## ▶️ **Requirements**

* Java 17+
* Kafka & Zookeeper
* Keycloak
* Eureka Server
* Config Server
* Spring AI provider (OpenAI / HuggingFace / Local Model)

---

## 🤝 **Contributions**

Issues and pull requests are welcome.
