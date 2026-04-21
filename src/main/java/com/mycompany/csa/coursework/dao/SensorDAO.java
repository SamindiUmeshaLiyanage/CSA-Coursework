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
import com.mycompany.csa.coursework.model.Sensor;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class SensorDAO {

    private static final Logger LOGGER = Logger.getLogger(SensorDAO.class.getName());
    private static List<Sensor> sensors = MockDatabase.sensors;

    public List<Sensor> getAllSensors() {
        LOGGER.info("Getting all sensors. Count: " + sensors.size());
        return sensors;
    }

    public List<Sensor> getSensorsByType(String type) {
        LOGGER.info("Getting sensors by type: " + type);
        List<Sensor> filtered = new ArrayList<>();
        for (Sensor sensor : sensors) {
            if (sensor.getType().equalsIgnoreCase(type)) {
                filtered.add(sensor);
            }
        }
        return filtered;
    }

    public Sensor getSensorById(String id) {
        LOGGER.info("Getting sensor by ID: " + id);
        for (Sensor sensor : sensors) {
            if (sensor.getId().equals(id)) {
                return sensor;
            }
        }
        LOGGER.warning("Sensor not found with ID: " + id);
        return null;
    }

    public void addSensor(Sensor sensor) {
        LOGGER.info("Adding sensor: " + sensor.getId());
        sensors.add(sensor);
    }

    public void deleteSensor(String id) {
        LOGGER.info("Deleting sensor: " + id);
        sensors.removeIf(sensor -> sensor.getId().equals(id));
    }

    public void updateSensor(Sensor updatedSensor) {
        LOGGER.info("Updating sensor: " + updatedSensor.getId());
        for (int i = 0; i < sensors.size(); i++) {
            if (sensors.get(i).getId().equals(updatedSensor.getId())) {
                sensors.set(i, updatedSensor);
                return;
            }
        }
        LOGGER.warning("Sensor not found for update: " + updatedSensor.getId());
    }

    public List<Sensor> getSensorsByRoomId(String roomId) {
        LOGGER.info("Getting sensors for room: " + roomId);
        List<Sensor> result = new ArrayList<>();
        for (Sensor sensor : sensors) {
            if (sensor.getRoomId().equals(roomId)) {
                result.add(sensor);
            }
        }
        return result;
    }

    public boolean sensorExists(String id) {
        return getSensorById(id) != null;
    }

    public void updateSensorValue(String sensorId, double newValue) {
        LOGGER.info("Updating current value for sensor: " + sensorId + " to " + newValue);
        for (Sensor sensor : sensors) {
            if (sensor.getId().equals(sensorId)) {
                sensor.setCurrentValue(newValue);
                return;
            }
        }
        LOGGER.warning("Sensor not found for value update: " + sensorId);
    }
}
