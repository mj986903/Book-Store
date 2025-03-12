Follow the steps below to set up and run the Book Store API locally.

1. Prerequisites :
    * Java 17
    * Gradle 7.6.3 (Binary)
    * IntelliJ Community Edition

2. Clone the Repository :
    * git clone https://github.com/mj986903/Book-Store.git
    * cd Book-Store
   
3. Configure Java 17 & Gradle 7.6.3 :
    * Verify Java 17 Configure Properly (File -> Project Structure -> Project -> SDK)
    * Add Downloaded Gradle 7.6.3 (Binary) Path (File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle -> Gradle User Home)

4. Build & Run

5. Access the API :
    * Swagger Documentation: http://localhost:8080/swagger-ui.html
    * API Base URL: http://localhost:8080/book-store

6. API Endpoints :
    * GET /allBooks -> List all books
    * GET /allBooksWithPagination -> List all books with pagination
    * GET /getBook/{id} -> Get book by id
    * GET /searchBook -> Search book by title
    * POST /addBook -> Add new book
    * PUT /updateBook -> Update or add book
    * DELETE /deleteBook/{id} -> Delete book by id