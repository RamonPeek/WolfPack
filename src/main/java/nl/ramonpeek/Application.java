package nl.ramonpeek;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <h1>WolfPackAPI Application</h1>
 *
 * The WolfPackAPI application is a Spring-framework based API with
 * which you can access, create, update and delete wolves and packs.
 *
 * @author Ramon Peek
 * @version 1.0
 * @since 2020-04-18
 */
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        System.out.println("Swagger UI is available at: http://localhost:8080/swagger-ui.html#/");
    }

}
