package com.cg.tests;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import com.cg.exceptions.EmailAlreadyExistsException;
import com.cg.exceptions.ErrorDetails;
import com.cg.exceptions.GlobalExceptionHandler;
import com.cg.exceptions.ResourceNotFoundException;

class GlobalExceptionHandlerTest {

    @Mock
    private ResourceNotFoundException resourceNotFoundException;

    @Mock
    private Exception exception;

    @Mock
    private EmailAlreadyExistsException emailAlreadyExistsException;

    @Mock
    private WebRequest webRequest;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testResourceNotFoundExceptionHandler() {
        // Mock the necessary objects and setup the test case
        String message = "Resource not found";
        when(resourceNotFoundException.getMessage()).thenReturn(message);
        String description = "Error occurred while processing the request";
        when(webRequest.getDescription(false)).thenReturn(description);

        // Invoke the exception handler method
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.resourceNotFoundException(resourceNotFoundException, webRequest);

        // Verify that the necessary methods were invoked
        verify(resourceNotFoundException, times(1)).getMessage();
        verify(webRequest, times(1)).getDescription(false);

        // Assert the response entity and its contents
        assertErrorResponse(HttpStatus.NOT_FOUND, message, description, responseEntity);
    }

    @Test
    void testGlobleExcpetionHandler() {
        // Mock the necessary objects and setup the test case
        String message = "Internal server error";
        when(exception.getMessage()).thenReturn(message);
        String description = "Error occurred while processing the request";
        when(webRequest.getDescription(false)).thenReturn(description);

        // Invoke the exception handler method
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.globleExcpetionHandler(exception, webRequest);

        // Verify that the necessary methods were invoked
        verify(exception, times(1)).getMessage();
        verify(webRequest, times(1)).getDescription(false);

        // Assert the response entity and its contents
        assertErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, message, description, responseEntity);
    }

    @Test
    void testEmailNotFoundExceptionHandler() {
        // Mock the necessary objects and setup the test case
        String message = "Email not found";
        when(emailAlreadyExistsException.getMessage()).thenReturn(message);
        String description = "Error occurred while processing the request";
        when(webRequest.getDescription(false)).thenReturn(description);

        // Invoke the exception handler method
        ResponseEntity<ErrorDetails> responseEntity = globalExceptionHandler.globleExcpetionHandler(emailAlreadyExistsException, webRequest);

        // Verify that the necessary methods were invoked
        verify(emailAlreadyExistsException, times(1)).getMessage();
        verify(webRequest, times(1)).getDescription(false);

        // Assert the response entity and its contents
       
    }

    private void assertErrorResponse(HttpStatus expectedStatus, String expectedMessage, String expectedDescription, ResponseEntity<ErrorDetails> responseEntity) {
        assertEquals(expectedStatus, responseEntity.getStatusCode());
        ErrorDetails errorDetails = responseEntity.getBody();
        assertEquals(expectedMessage, errorDetails.getMessage());
        
    }
}
