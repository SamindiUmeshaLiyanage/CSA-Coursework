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
import java.util.logging.Logger;

@Provider
public class LinkedResourceNotFoundExceptionMapper 
        implements ExceptionMapper<LinkedResourceNotFoundException> {

    private static final Logger LOGGER = Logger.getLogger(
        LinkedResourceNotFoundExceptionMapper.class.getName()
    );

    @Override
    public Response toResponse(LinkedResourceNotFoundException exception) {
        LOGGER.warning("LinkedResourceNotFoundException: " + exception.getMessage());

        ErrorMessage error = new ErrorMessage(
            exception.getMessage(),
            422,
            "http://localhost:8080/CSA-Coursework/api/v1"
        );

        return Response.status(422)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}