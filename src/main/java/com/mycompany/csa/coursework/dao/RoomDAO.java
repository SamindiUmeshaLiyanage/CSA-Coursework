/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.dao;

/**
 *
 * @author umesh
 */
import com.mycompany.csa.coursework.dao.MockDatabase;
import com.mycompany.csa.coursework.model.Room;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class RoomDAO {

    private static final Logger LOGGER = Logger.getLogger(RoomDAO.class.getName());
    private static List<Room> rooms = MockDatabase.rooms;

    public List<Room> getAllRooms() {
        LOGGER.info("Getting all rooms. Count: " + rooms.size());
        return rooms;
    }

    public Room getRoomById(String id) {
        LOGGER.info("Getting room by ID: " + id);
        for (Room room : rooms) {
            if (room.getId().equals(id)) {
                return room;
            }
        }
        LOGGER.warning("Room not found with ID: " + id);
        return null;
    }

    public void addRoom(Room room) {
        LOGGER.info("Adding room: " + room.getId());
        rooms.add(room);
    }

    public void deleteRoom(String id) {
        LOGGER.info("Deleting room: " + id);
        rooms.removeIf(room -> room.getId().equals(id));
    }

    public void updateRoom(Room updatedRoom) {
        LOGGER.info("Updating room: " + updatedRoom.getId());
        for (int i = 0; i < rooms.size(); i++) {
            if (rooms.get(i).getId().equals(updatedRoom.getId())) {
                rooms.set(i, updatedRoom);
                return;
            }
        }
        LOGGER.warning("Room not found for update: " + updatedRoom.getId());
    }

    public boolean roomExists(String id) {
        return getRoomById(id) != null;
    }
}

