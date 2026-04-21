/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.exception;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 *
 * @author umesh
 */
@Provider
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {
    private static final Logger LOGGER = Logger.getLogger(RoomNotEmptyExceptionMapper.class.getName());

    @Override
    public Response toResponse(RoomNotEmptyException e) {
        LOGGER.log(Level.INFO, e.getMessage());  
        Map<String, String> error = new HashMap<>();
        error.put("status", "409");
        error.put("message", e.getMessage());
        return Response.status(409).entity(error).type(MediaType.APPLICATION_JSON).build();
    }
}
