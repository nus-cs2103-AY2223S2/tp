package seedu.address.model.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Staff;

/**
 * The appointment class containing a meeting with a customer at a particular date.
 */
public class Appointment {
    private Customer customer;
    private LocalDateTime timeDate;
    private List<Staff> staffs;

    /**
     * This method is the constructor for Appointment.
     *
     * @param customer The customer to meet.
     * @param timeDate The date time which this appointment occurs.
     */
    public Appointment(Customer customer, LocalDateTime timeDate) {
        this.customer = customer;
        this.timeDate = timeDate;
        this.staffs = new ArrayList<>();
    }

    /**
     * This method returns the customer who we are meeting.
     *
     * @return The customer.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * This method returns the time which we are meeting this customer.
     *
     * @return the date time of this appointment.
     */
    public LocalDateTime getTimeDate() {
        return timeDate;
    }

    /**
     * This method returns the list of staff members who will be meeting this customer.
     *
     * @return a list of staff members.
     */
    public List<Staff> getStaffs() {
        return staffs;
    }

    /**
     * This method sets the list of staff members who will be meeting this customer.
     *
     * @param staffs a list of staff members.
     */
    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    /**
     * Adds staff to the staff list
     *
     * @param staff Staff to be added
     */
    public void addStaff(Staff staff) {
        this.getStaffs().add(staff);
    }
}
