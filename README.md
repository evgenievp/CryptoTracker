CryptoTracker

Description:
CryptoTracker is a Spring Boot application for tracking cryptocurrency prices.  
- Fetches data from public APIs (CoinGecko)  
- Stores price history in a database (MS SQL / H2)  
- Provides REST endpoints to access all or specific currencies:
  - `/prices/findAll/` – all records  
  - `/prices/findAll/{name}` – records for a specific currency  
  - `/prices/update` – updates prices from the API  
- Sends email notifications if a price crosses a defined threshold (currently using `MockMailSender`, which prints messages to the console instead of sending real emails)

Technologies
- Java 21  
- Spring Boot  
- JPA / Hibernate  
- MS SQL
- Lombok  
- REST API  

Usage
1. Run the Spring Boot application  
2. Use the REST endpoints to get price data or update prices manually:  
   - Example: `GET http://localhost:8080/prices/update`  
   - Example: `GET http://localhost:8080/prices/findAll/filecoin`  
3. Mock email notifications will appear in the console if thresholds are exceeded.

Notes
- For development, email sending is mocked to avoid sending real emails.  
- Real email integration can be added later using Spring Boot Mail and an app-specific password.  

