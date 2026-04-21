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
public class DataNotFoundExceptionMapper 
        implements ExceptionMapper<DataNotFoundException> {

    private static final Logger LOGGER = Logger.getLogger(
        DataNotFoundExceptionMapper.class.getName()
    );

    @Override
    public Response toResponse(DataNotFoundException exception) {
        LOGGER.warning("DataNotFoundException: " + exception.getMessage());

        ErrorMessage error = new ErrorMessage(
            exception.getMessage(),
            404,
            "http://localhost:8080/CSA-Coursework/api/v1"
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(error)
                .type(MediaType.APPLICATION_JSON)
                .build();
    }
}