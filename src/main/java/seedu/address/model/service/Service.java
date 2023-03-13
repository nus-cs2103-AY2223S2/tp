package seedu.address.model.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.entity.person.Technician;

/**
 * The Service class contains information about what task to be performed on the vehicle.
 */
public class Service {
    private final int id;
    private final LocalDate entryDate;
    private List<Part> requiredParts;
    private String description;
    private LocalDate estimatedFinishDate;
    private final Set<Integer> assignedToIds = new HashSet<>();
    private boolean isComplete = false;

    /**
     *  This method is the constructor for a Service.
     *
     * @param estimatedDaysRequired The amount of time estimated to be needed for repairs.
     */
    public Service(int estimatedDaysRequired) {
        this.id = id;
        entryDate = LocalDate.now();
        estimatedFinishDate = entryDate.plusDays(estimatedDaysRequired);
        requiredParts = new ArrayList<Part>();
    }

    /**
     *  This method is the constructor for a Service.
     *
     * @param vehicle The vehicle that requires servicing.
     * @param estimatedDaysRequired The amount of time estimated to be needed for repairs.
     * @param assignedToIds The list of staffs ids that this service is assigned to.
     */
    public Service(int id, Vehicle vehicle, int estimatedDaysRequired, Set<Integer> assignedToIds) {
        this.id = id;
        this.vehicle = vehicle;
        entryDate = LocalDate.now();
        estimatedFinishDate = entryDate.plusDays(estimatedDaysRequired);
        requiredParts = new ArrayList<Part>();
        this.assignedToIds.addAll(assignedToIds);
    }

    /**
     * This method is the constructor for a Service.
     * By default, this method estimates the amount of time needed to be 7 whole days (not working days).
     */
    public Service(int id, Vehicle vehicle) {
        this(id, vehicle, 7);
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
     * @return Entry date of service
     */
    public LocalDate getEntryDate() {
        return this.entryDate;
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
     * This method returns the list of requiredParts needed to perform this Service.
     *
     * @return a list of requiredParts needed to repair this.
     */
    public List<Part> getRequiredParts() {
        return requiredParts;
    }

    /**
     * This method adds a part to this service.
     *
     * @param part The part to be added.
     */
    public void addPart(Part part) {
        requiredParts.add(part);
    }

    /**
     * This method removes a part from this service.
     *
     * @param part The part to be removed.
     */
    public void removePart(Part part) {
        requiredParts.remove(part);
    }


    /**
     * This method returns the bill of this service.
     * Currently only returns the requiredParts cost. Assumes that only use one part.
     * Does not charge Technician cost etc.
     * @return The cost of this service.
     */
    public int bill() {
        return requiredParts.stream().mapToInt(i -> i.getCost()).sum();
    }

    /**
     * This method adds some parts needed for this service.
     *
     * @param parts The part needed to be added.
     */
    public void addParts(List<Part> parts) {
        requiredParts.addAll(parts);
    }

    /**
     * This method removes some parts that was added to this service.
     * @param parts The part to be removed.
     */
    public void removeParts(List<Part> parts) {
        requiredParts.removeAll(parts);
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
     * This method returns the list of technician ids assigned to this task.
     *
     * @return the list of technician ids assigned to this task.
     */
    public List<Integer> getAssignedToIds() {
        return new ArrayList<>(this.assignedToIds);
    }

    /**
     * This method adds a technician to this service.
     *
     * @param technician The technician assigned to this service.
     */
    public void assignTechnician(Technician technician) {
        this.assignedToIds.add(technician.getId());
    }

    /**
     * This method removes a technician from this service.
     *
     * @param technician The technician to be removed from this service.
     */
    public void removeTechnician(Technician technician) {
        this.assignedToIds.remove(technician.getId());
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

    @Override
    public String toString() {
        String newline = System.lineSeparator();
        String parts = this.getRequiredParts().stream()
                .map(Object::toString)
                .reduce("", (a, b) -> a + "\n"  + b);
        if (parts.length() > 0) {
            parts = parts.substring(1);
        }
        String technicians = this.getAssignedTechnicians().stream()
                .map(Objects::toString)
                .reduce("", (a, b) -> a + "\n" + b);
        if (technicians.length() > 0) {
            technicians = technicians.substring(1);
        }
        String status = this.isComplete() ? "COMPLETE" : "INCOMPLETE";
        String formatter = "<<Service>>" + newline
                + "ID: %d" + newline
                + "Desc: %s" + newline
                + "Entry Date: %s" + newline
                + "Est Finish Date: %s" + newline
                + "Parts Required: %n %s" + newline
                + "Assigned Technicians: %n %s" + newline
                + "%s";

        return String.format(formatter,
                this.getId(),
                this.getDescription(),
                this.getEntryDate(),
                this.getEstimatedFinishDate(),
                StringUtil.indent(parts, 2),
                StringUtil.indent(technicians, 2),
                status);
    }
}
