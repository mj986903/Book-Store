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
   * GET    /allBooks?currentPage={page}&rowsPerPage={rows} -> List all books with pagination  
   * POST   /addBook                                       -> Add a new book  
   * GET    /getBook/{bookId}                              -> Get book by ID  
   * GET    /searchBook?title={title}                      -> Search book by title  
   * PUT    /updateBook/{bookId}                           -> Update book by ID  
   * DELETE /deleteBook/{bookId}                           -> Delete book by ID  