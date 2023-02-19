package seedu.address.model.service;

import java.time.LocalDate;
import java.util.ArrayList;

import seedu.address.model.Vehicle;
import seedu.address.model.entity.person.Technician;

/**
 * The Service class contains information about what task to be performed on the vehicle.
 */
public class Service {
    private static int incrementalID = 0;
    private final int id;
    private final LocalDate entryDate;
    private ArrayList<Part> parts;
    private Vehicle vehicle;
    private String description;
    private LocalDate estimatedFinishDate;
    private ArrayList<Technician> assignedTo;
    private boolean isComplete = false;
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
     * @param estimatedFinishDate The estimated finish date.
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
    public void removePart(Part part) {
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
        return parts.stream().mapToInt(i -> i.getCost()).sum();
    }

    /**
     * This method adds a part needed for this service.
     *
     * @param part The part needed to be added.
     */
    public void addParts(Part part) {
        parts.add(part);
    }

    /**
     * This method removes a part that was added to this service.
     * @param part The part to be removed.
     */
    public void removeParts(Part part) {
        parts.remove(part);
    }

    /**
     * This method assigns a particular vehicle to this service.
     * @param vehicle The vehicle needed for this service.
     */
    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * This method returns the description of this service.
     *
     * @return the description of this service.
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method sets the description of this service.
     *
     * @param description the new description of this service.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method returns the list of technicians assigned to this task.
     *
     * @return the list of technicians assigned to this task.
     */
    public ArrayList<Technician> isAssignedTo() {
        return assignedTo;
    }

    /**
     * This method adds a technician to this service.
     *
     * @param technician The technician assigned to this service.
     */
    public void assignTechnician(Technician technician) {
        assignedTo.add(technician);
    }

    /**
     * This method removes a technician from this service.
     *
     * @param technician The technician to be removed from this service.
     */
    public void removeTechnician(Technician technician) {
        assignedTo.remove(technician);
    }

    /**
     * This method returns whether this task is completed or not.
     *
     * @return whether this task is completed or not.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * This method sets whether this task is completed or not.
     *
     * @param complete the value to set this task to.
     */
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}
