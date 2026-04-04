# Software Flow: Embedding Matching and ServiceNow Request Automation

https://docs.cloud.google.com/docs/authentication/set-up-adc-local-dev-environment


## Overview
This software module performs the following tasks:

1. Receives a user request in general English.
2. Converts the request into an embedding.
3. Searches for matching embeddings in the database.
4. Filters results with a matching score greater than 7.
5. Automatically raises a ServiceNow request using a REST API call with the best match information.

---

## Project Setup and Running Instructions

## Running the Applications

### 1. Start the Python Embedding Service

Make sure you have the Python app (e.g., FastAPI with Uvicorn) ready. Run the following command from the Python app directory:

```bash
pip install fastapi uvicorn sentence-transformers

uvicorn app:app --host 0.0.0.0 --port 8000
````

* This will start the embedding service on port **8000**.
* The Java app expects this service running at `http://localhost:8000/embed`.

---

### 2. Start the Java Spring Boot Application

Run your Spring Boot app (e.g., via your IDE or with `mvn spring-boot:run` or `./gradlew bootRun`).

Once running, access the Swagger UI for API documentation and testing at:

```
http://localhost:8080/swagger-ui/
```

* This UI lets you explore and test the API endpoints interactively.



## Flow Steps

### 1. Receive User Request
- The system accepts a user input (in general English).
- Example input:  
  *"I need to report a network outage in building 3."*

### 2. Generate Embedding
- The input text is converted into an embedding vector using a suitable embedding model or API (e.g., OpenAI embeddings).

### 3. Search Matching Embeddings
- The system queries the embedding database for similar entries.
- Each result comes with a similarity score.

### 4. Filter Results
- Filter the matching results to only include those with a similarity score > 7.
- Select the best match (highest score).

### 5. Raise ServiceNow Request
- Using the best match details, prepare the payload for ServiceNow.
- Call the ServiceNow REST API endpoint to create a new request.
- Include relevant information from the match and user input in the request.

---

### Notes

* Ensure the Python embedding service is running before starting the Java app, otherwise embedding requests will fail.
* If you change the Python app port or host, update the URL in the Java `PythonEmbeddingService` accordingly.
* Java app runs on port **8080** by default (can be configured in `application.properties`).

```
