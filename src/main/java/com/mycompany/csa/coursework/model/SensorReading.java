/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.csa.coursework.model;

/**
 *
 * @author umesh
 */
public class SensorReading implements BaseModel{
    private String id; // Unique reading ID
    private long timestamp; // Epoch time
    private double value;
    private String sensorId;


    public SensorReading() {}

    public SensorReading(String id, long timestamp, double value, String sensorId) {
        this.id = id;
        this.timestamp = timestamp;
        this.value = value;
        this.sensorId = sensorId;
    }

    @Override
    public String getId() { 
        return id; }
    @Override
    public void setId(String id)
    { this.id = id; }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setSensorId(String sensorId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Object getSensorId() {
        throw new UnsupportedOperationException("Not supported yet."); 
    }
}