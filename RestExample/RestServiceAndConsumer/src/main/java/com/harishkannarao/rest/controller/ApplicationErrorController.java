package com.harishkannarao.rest.controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.AbstractErrorController;
import org.springframework.boot.autoconfigure.web.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class ApplicationErrorController extends AbstractErrorController {
    private static final boolean INCLUDE_STACK_TRACE = true;
    private static final String ERROR_PATH = "/error";
    private static final String GENERAL_ERROR_VIEW = "general_error";
    private static final String STATUS_KEY = "status";
    private static final String ERROR_KEY = "error";

    private final Logger logger = LoggerFactory.getLogger(ApplicationErrorController.class);

    @Autowired
    public ApplicationErrorController(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = {ERROR_PATH}, produces = {MediaType.TEXT_HTML_VALUE})
    public ModelAndView errorHtml(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> errorAttributes = getErrorAttributes(request, INCLUDE_STACK_TRACE);
        if (HttpStatus.NOT_FOUND.equals(status)) {
            logger.debug(errorAttributes.get(ERROR_KEY).toString());
        } else {
            logger.error(errorAttributes.get(ERROR_KEY).toString());
        }
        return new ModelAndView(GENERAL_ERROR_VIEW, errorAttributes, status);
    }

    @RequestMapping(value = {ERROR_PATH})
    public ResponseEntity<ErrorDetails> errorObject(HttpServletRequest request) {
        HttpStatus status = getStatus(request);
        Map<String, Object> errorAttributes = getErrorAttributes(request, INCLUDE_STACK_TRACE);
        ErrorDetails errorDetails = new ErrorDetails(
                (Integer) errorAttributes.get(STATUS_KEY),
                (String) errorAttributes.get(ERROR_KEY)
        );
        if (HttpStatus.NOT_FOUND.equals(status)) {
            logger.debug(errorAttributes.get(ERROR_KEY).toString());
        } else {
            logger.error(errorAttributes.get(ERROR_KEY).toString());
        }
        return new ResponseEntity<>(errorDetails, status);
    }

    public static class ErrorDetails {
        @JsonProperty("status")
        private final Integer status;
        @JsonProperty("error")
        private final String error;

        @JsonCreator
        public ErrorDetails(
                @JsonProperty("status") Integer status,
                @JsonProperty("error") String error
        ) {
            this.status = status;
            this.error = error;
        }

        public int getStatus() {
            return status;
        }

        public String getError() {
            return error;
        }
    }

}
