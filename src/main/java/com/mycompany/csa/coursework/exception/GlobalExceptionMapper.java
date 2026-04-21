/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.exception;

/**
 *
 * @author umesh
 */
import com.mycompany.csa.coursework.model.ErrorMessage;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Level;
import java.util.logging.Logger;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOGGER = Logger.getLogger(
        GlobalExceptionMapper.class.getName()
    );

    @Override
    public Response toResponse(Throwable exception) {
        // use Level.SEVERE for unexpected errors
        LOGGER.log(Level.SEVERE, "Unexpected error occurred: " + exception.getMessage(), exception);

        ErrorMessage error = new ErrorMessage(
            "An unexpected internal server error occurred.",
            500,
            "http://localhost:8080/CSA-Coursework/api/v1"
        );

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}