package nl.ramonpeek.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {


    @RequestMapping(value = "/error")
    public ResponseEntity<String> handleUnspecifiedEndpoint(HttpServletRequest request) {
        return new ResponseEntity<String>("The requested endpoint '" + request.getRequestURI() + "' does not exist, or is not configured to process " + request.getMethod() + "-requests." , HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}
