/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.dao;

import com.mycompany.csa.coursework.model.Room;
import com.mycompany.csa.coursework.model.Sensor;
import com.mycompany.csa.coursework.model.SensorReading;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author umesh
 */
public class MockDatabase {
    public static final List<Room> rooms = new ArrayList<>();
    public static final List<Sensor> sensors = new ArrayList<>();
    public static final List<SensorReading> readings = new ArrayList<>();
    static {
        // Initialise Rooms
        rooms.add(new Room("LIB-301", "Library Quiet Study", 50));
        rooms.add(new Room("LAB-101", "Computer Lab", 30));
        rooms.add(new Room("LEC-201", "Main Lecture Hall", 150));
        
        // Intialise Sensors
        sensors.add(new Sensor("TEMP-001", "Temperature", "ACTIVE", 22.5, "LIB-301"));
        sensors.add(new Sensor("CO2-001", "CO2", "ACTIVE", 400.0, "LAB-101"));
        sensors.add(new Sensor("OCC-001", "Occupancy", "MAINTENANCE", 0.0, "LEC-201"));
        
        readings.add( new SensorReading("READ-001", System.currentTimeMillis(), 22.5, "TEMP-001"));
        readings.add(new SensorReading("READ-002", System.currentTimeMillis(), 400.0, "CO2-001"));
    }
    
}
