/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.resources;

/**
 *
 * @author umesh
 */
import com.mycompany.csa.coursework.dao.RoomDAO;
import com.mycompany.csa.coursework.dao.SensorDAO;
import com.mycompany.csa.coursework.exception.DataNotFoundException;
import com.mycompany.csa.coursework.exception.LinkedResourceNotFoundException;
import com.mycompany.csa.coursework.model.Sensor;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/sensors")
public class SensorResource {

    private static final Logger LOGGER = Logger.getLogger(SensorResource.class.getName());
    SensorDAO sensorDAO = new SensorDAO();
    RoomDAO roomDAO = new RoomDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensors(@QueryParam("type") String type) {
        LOGGER.info("Request to get sensors, type filter: " + type);

        List<Sensor> sensors;

        if (type != null && !type.isEmpty()) {
            // filter by type if query param provided
            sensors = sensorDAO.getSensorsByType(type);
            LOGGER.info("Filtered sensors by type: " + type + ", count: " + sensors.size());
        } else {
            // return all sensors if no filter
            sensors = sensorDAO.getAllSensors();
            LOGGER.info("Returning all sensors, count: " + sensors.size());
        }

        return Response.ok(sensors).build();
    }

    @GET
    @Path("/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getSensorById(@PathParam("sensorId") String sensorId) {
        LOGGER.info("Request to get sensor: " + sensorId);

        Sensor sensor = sensorDAO.getSensorById(sensorId);

        if (sensor == null) {
            throw new DataNotFoundException("Sensor not found with ID: " + sensorId);
        }

        return Response.ok(sensor).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addSensor(Sensor sensor) {
        LOGGER.info("Request to add sensor: " + sensor.getId());

        if (sensor.getRoomId() == null || !roomDAO.roomExists(sensor.getRoomId())) {
            throw new LinkedResourceNotFoundException(
                "Cannot add sensor - room with ID: " + sensor.getRoomId() + " does not exist."
            );
        }

        sensorDAO.addSensor(sensor);

        roomDAO.getRoomById(sensor.getRoomId()).getSensorIds().add(sensor.getId());

        return Response.status(Response.Status.CREATED)
                .entity("Sensor created successfully with ID: " + sensor.getId())
                .build();
    }


    @PUT
    @Path("/{sensorId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSensor(@PathParam("sensorId") String sensorId, Sensor sensor) {
        LOGGER.info("Request to update sensor: " + sensorId);

        Sensor existing = sensorDAO.getSensorById(sensorId);
        if (existing == null) {
            throw new DataNotFoundException("Sensor not found with ID: " + sensorId);
        }

        sensor.setId(sensorId);
        sensorDAO.updateSensor(sensor);
        return Response.ok("Sensor updated successfully").build();
    }

    @DELETE
    @Path("/{sensorId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteSensor(@PathParam("sensorId") String sensorId) {
        LOGGER.info("Request to delete sensor: " + sensorId);

        Sensor sensor = sensorDAO.getSensorById(sensorId);
        if (sensor == null) {
            throw new DataNotFoundException("Sensor not found with ID: " + sensorId);
        }

        if (sensor.getRoomId() != null) {
            roomDAO.getRoomById(sensor.getRoomId()).getSensorIds().remove(sensorId);
        }

        sensorDAO.deleteSensor(sensorId);
        return Response.noContent().build();  
    }


    @Path("/{sensorId}/readings")
   
    public SensorReadingResource getSensorReadingResource(@PathParam("sensorId") String id) {
        return new SensorReadingResource(id);
    }

}