package seedu.address.model.service;

import seedu.address.model.entity.person.Customer;
import seedu.address.model.entity.person.Staff;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Appointment {
    public Customer customer;
    public ArrayList<Staff> staffs;
    public LocalDateTime meeting;

    public Appointment(Customer customer, ArrayList<Staff> staffs, LocalDateTime meeting) {
        this.customer = customer;
        this.staffs = staffs;
        this.meeting = meeting;
    }
}
