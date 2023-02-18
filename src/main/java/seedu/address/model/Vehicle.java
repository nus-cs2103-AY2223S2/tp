package seedu.address.model;

import seedu.address.model.entity.person.Technician;
import seedu.address.model.service.Service;

import java.util.ArrayList;

public class Vehicle {
    private static int incrementalID = 0;
    private int id;
    private String plateNumber;
    private String originalColor;
    private String brand;
    private VEHICLETYPE type;
    private ArrayList<Service> service;
    private Technician assignedTo;
    public enum VEHICLETYPE{
        MOTORBIKE, CAR
    }
    public Vehicle(String plateNumber, String originalColor, String brand, VEHICLETYPE type) {
        id = ++incrementalID;
        this.plateNumber = plateNumber;
        this.originalColor = originalColor;
        this.brand = brand;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getPlateNumber() {
        return plateNumber;
    }

    public void setPlateNumber(String plateNumber) {
        this.plateNumber = plateNumber;
    }

    public String getOriginalColor() {
        return originalColor;
    }

    public void setOriginalColor(String originalColor) {
        this.originalColor = originalColor;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public VEHICLETYPE getType() {
        return type;
    }

    public void setType(VEHICLETYPE type) {
        this.type = type;
    }

    public ArrayList<Service> getService() {
        return service;
    }

    public void setService(ArrayList<Service> service) {
        this.service = service;
    }

    public Technician getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(Technician assignedTo) {
        this.assignedTo = assignedTo;
    }
}
