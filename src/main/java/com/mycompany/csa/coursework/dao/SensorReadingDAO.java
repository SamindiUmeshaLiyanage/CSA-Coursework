/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.dao;

/**
 *
 * @author umesh
 */

import com.mycompany.csa.coursework.model.SensorReading;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SensorReadingDAO {

    private static final Logger LOGGER = Logger.getLogger(SensorReadingDAO.class.getName());
    private static List<SensorReading> readings = MockDatabase.readings;

    public List<SensorReading> getReadingsBySensorId(String sensorId) {
        LOGGER.info("Getting readings for sensor: " + sensorId);
        List<SensorReading> result = new ArrayList<>();
        for (SensorReading reading : readings) {
            if (reading.getId().equals(sensorId)) {
                result.add(reading);
            }
        }
        return result;
    }

    public SensorReading getReadingById(String id) {
        LOGGER.info("Getting reading by ID: " + id);
        for (SensorReading reading : readings) {
            if (reading.getId().equals(id)) {
                return reading;
            }
        }
        LOGGER.warning("Reading not found with ID: " + id);
        return null;
    }

    public void addReading(SensorReading reading) {
        LOGGER.info("Adding reading: " + reading.getId() + " for sensor: " + reading.getSensorId());
        readings.add(reading);
    }

    public void deleteReading(String id) {
        LOGGER.info("Deleting reading: " + id);
        readings.removeIf(reading -> reading.getId().equals(id));
    }
}