# Spring Boot 3 REST 1v1 Game Lab

This GitHub Classroom lab uses Spring Boot 3 REST, JSON, and a browser-based web client.

Spring Boot version: 3.5.14

## Modules

| Module | Purpose |
|---|---|
| `game-server` | Spring Boot 3 REST API server |
| `game-client` | Spring Boot 3 static web client |

## Run

```bash
mvn clean install
cd game-server
mvn spring-boot:run
```

In a second terminal:

```bash
cd game-client
mvn spring-boot:run
```

Open:

```text
http://localhost:9091
```

## REST API

| Method | Endpoint | Purpose |
|---|---|---|
| `POST` | `/api/matches` | Join a match |
| `POST` | `/api/matches/{matchId}/play` | Server randomly chooses winner |
| `GET` | `/api/matches/history?playerName=NAME` | Load sample match history |

## JSON Join Request

```json
{
  "playerName": "Ada",
  "difficulty": "Hard",
  "ranked": true
}
```

## Reflection Questions

1. What does the REST controller do?
2. What does the service layer do?
3. What data is sent in the JSON join request?
4. What data is returned in the JSON play response?
5. Why was HP removed from the client?
6. Why were player roles removed?
7. How does the web client call the server?
8. What did your peer reviewer suggest?
9. What did you change after peer review?
