/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.resources;

/**
 *
 * @author umesh
 */

import com.mycompany.csa.coursework.dao.MockDatabase;
import com.mycompany.csa.coursework.dao.SensorDAO;
import com.mycompany.csa.coursework.dao.SensorReadingDAO;
import com.mycompany.csa.coursework.exception.DataNotFoundException;
import com.mycompany.csa.coursework.exception.SensorUnavailableException;
import com.mycompany.csa.coursework.model.Sensor;
import com.mycompany.csa.coursework.model.SensorReading;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class SensorReadingResource {

    private static final Logger LOGGER = Logger.getLogger(SensorReadingResource.class.getName());
    private String sensorId;
    SensorDAO sensorDAO = new SensorDAO();
    SensorReadingDAO readingDAO = new SensorReadingDAO();
    private static List<SensorReading> readings = MockDatabase.readings;

    public SensorReadingResource(String sensorId) {
        this.sensorId = sensorId;
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getReadings() {
        LOGGER.info("Request to get readings for sensor: " + sensorId);

        Sensor sensor = sensorDAO.getSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor not found with ID: " + sensorId);
        }

        List<SensorReading> readings = readingDAO.getReadingsBySensorId(sensorId);
        return Response.ok(readings).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addReading(SensorReading reading) {
        LOGGER.info("Request to add reading for sensor: " + sensorId);

        Sensor sensor = sensorDAO.getSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor not found with ID: " + sensorId);
        }

        if (sensor.getStatus().equalsIgnoreCase("MAINTENANCE")) {
            throw new SensorUnavailableException(
                "Sensor " + sensorId + " is currently under MAINTENANCE and cannot accept readings."
            );
        }

        reading.setSensorId(sensorId);

        readingDAO.addReading(reading);
        
        sensorDAO.updateSensorValue(sensorId, reading.getValue());
        LOGGER.info("Updated currentValue of sensor " + sensorId + " to " + reading.getValue());

        return Response.status(Response.Status.CREATED)
                .entity("Reading added successfully for sensor: " + sensorId)
                .build();
    }
}