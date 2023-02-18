package seedu.address.model.service;

import seedu.address.model.Vehicle;
import seedu.address.model.entity.person.Technician;

import java.time.LocalDate;
import java.util.ArrayList;

public class Service {
    private static int incrementalID = 0;
    private int id;
    private LocalDate entryDate;
    private ArrayList<Part> parts;
    private Vehicle vehicle;
    public String description;
    public LocalDate estimatedFinishDate;
    public ArrayList<Technician> assignedTo;
    public boolean isComplete = false;

    /**
     *  This method is the constructor for a Service.
     *
     * @param vehicle The vehicle that requires servicing.
     * @param estimatedDaysRequired The amount of time estimated to be needed for repairs.
     */
    public Service(Vehicle vehicle, int estimatedDaysRequired) {
        this.id = ++incrementalID;
        this.vehicle = vehicle;
        entryDate = LocalDate.now();
        estimatedFinishDate = entryDate.plusDays(estimatedDaysRequired);
        parts = new ArrayList<Part>();
    }

    /**
     * This method is the constructor for a Service.
     * By default, this method estimates the amount of time needed to be 7 whole days (not working days).
     *
     * @param vehicle The vehicle that requires servicing.
     */
    public Service(Vehicle vehicle) {
        this(vehicle, 7);
    }

    /**
     * This method returns the id of this service.
     *
     * @return the id of this service.
     */
    public int getId() {
        return id;
    }

    /**
     * This method returns the estimated finish date.
     *
     * @return The estimated finish date.
     */
    public LocalDate getEstimatedFinishDate() {
        return estimatedFinishDate;
    }

    /**
     * This method sets the estimated finish date.
     *
     * @return The estimated finish date.
     */
    public void setEstimatedFinishDate(LocalDate estimatedFinishDate) {
        this.estimatedFinishDate = estimatedFinishDate;
    }

    /**
     * This method returns the list of parts needed to perform this Service.
     *
     * @return a list of parts needed to repair this.
     */
    public ArrayList<Part> getParts() {
        return parts;
    }

    /**
     * This method adds a part to this service.
     *
     * @param part The part to be added.
     */
    public void addPart(Part part) {
        parts.add(part);
    }

    /**
     * This method removes a part from this service.
     *
     * @param part The part to be removed.
     */
    public void removePart(Part part){
        parts.remove(part);
    }

    /**
     * This method returns the vehicle that requires this service.
     * @return returns the Vehicle that this Service is linked to.
     */
    public Vehicle getVehicle() {
        return vehicle;
    }

    /**
     * This method returns the bill of this service.
     * Currently only returns the parts cost. Assumes that only use one part.
     * Does not charge Technician cost etc.
     * @return The cost of this service.
     */
    public int bill() {
        return parts.stream().mapToInt( i -> i.getCost()).sum();
    }
}
