/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.resources;

import com.mycompany.csa.coursework.dao.MockDatabase;
import com.mycompany.csa.coursework.dao.RoomDAO;
import com.mycompany.csa.coursework.dao.SensorDAO;
import com.mycompany.csa.coursework.exception.DataNotFoundException;
import com.mycompany.csa.coursework.exception.RoomNotEmptyException;
import com.mycompany.csa.coursework.model.Room;
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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author umesh
 */
@Path("/rooms")
public class SensorRoomResource {
    private static final Logger LOGGER = Logger.getLogger(SensorRoomResource.class.getName());
   
    RoomDAO roomDAO = new RoomDAO();
    SensorDAO sensorDAO = new SensorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Room> getAllRooms() { 
        LOGGER.info("Get request for all rooms");
        return roomDAO.getAllRooms();           
           }  

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createRoom(Room room) {
        LOGGER.info("Request to create room: " + room.getId());
        roomDAO.addRoom(room);
        return Response.status(Response.Status.CREATED)
                .entity("Room created successfully with ID: " + room.getId())
                .build();
    }


    @GET
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Room getRoom(@PathParam("roomId") String roomId) { 
    LOGGER.info("Request to get room: " + roomId);
        Room room = roomDAO.getRoomById(roomId);

        if (room == null) {
            throw new DataNotFoundException("Room not found with ID: " + roomId);
        }

        return room;
    }  

    @PUT
    @Path("/{roomId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateRoom(@PathParam("roomId") String roomId, Room room) {
        LOGGER.info("Request to update room: " + roomId);
        Room existing = roomDAO.getRoomById(roomId);

        if (existing == null) {
            throw new DataNotFoundException("Room not found with ID: " + roomId);
        }

        room.setId(roomId);
        roomDAO.updateRoom(room);
        return Response.ok("Room updated successfully").build();  
    }

    @DELETE
    @Path("/{roomId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteRoom(@PathParam("roomId") String roomId) {
        LOGGER.info("Request to delete room: " + roomId);

        // check room exists first
        Room room = roomDAO.getRoomById(roomId);
        if (room == null) {
            throw new DataNotFoundException("Room not found with ID: " + roomId);
        }

        if (!sensorDAO.getSensorsByRoomId(roomId).isEmpty()) {
            throw new RoomNotEmptyException(
                "Cannot delete room " + roomId + " - it still has active sensors assigned to it."
            );
        }

        roomDAO.deleteRoom(roomId);
        return Response.noContent().build();  
    }
}