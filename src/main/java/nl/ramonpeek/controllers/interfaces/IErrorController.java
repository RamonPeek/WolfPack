package nl.ramonpeek.controllers.interfaces;

import org.springframework.http.ResponseEntity;

public interface IErrorController {

    ResponseEntity<String> handleUnspecifiedEndpoint();

}
