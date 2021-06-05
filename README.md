# queston-and-answer-spring-boot-mysql-jpa


## Create Question POST /questions
POST http://localhost:8000/questions
{
    "title":"how to use MySql with Spring Boot and JPA",
    "description":"I want to use MySql with Spring Boot and JPA and develop RESTful APIs. Let's Go!"
}


{
    "title":"What are the @RequestMapping  and @RestController annotation in Spring Boot used for?",
    "description":"Learn about @RequestMapping  and @RestController "
}

{
    "title":"What are the advantages of Spring Boot?",
    "description":"Learn Spring Boot advantages"
}



## Get paginated Questions
GET http://localhost:8000/questions?page=0&size=2&sort=createdAt,desc


## Create Answer 
POST /questions/{questionId}/answers
http://localhost:8000/questions/1000/answers

{
    "text":"It is very easy easy to configure MySql in a Spring Boot app."
}
{
    "text":"To connect the MySQL database, we need to add the MySQL dependency into our build configuration file."
}
{
    "text":"Spring Boot provides a very good support to create a DataSource for Database. We need not write any extra code to create a DataSource in Spring Boot."
}

## Get all answers of a Question GET /questions/{questionId}/answers
GET http://localhost:8000/questions/1000/answers
