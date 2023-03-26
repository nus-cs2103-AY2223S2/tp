package seedu.address.model.person;

import java.util.ArrayList;
import java.util.Set;

import seedu.address.model.appointment.Appointment;
import seedu.address.model.tag.Tag;

/**
 * Represents a Doctor in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Doctor extends Person {
    public Doctor(Name name, Phone phone, Email email, Nric nric, Address address, Set<Tag> tags,
                  ArrayList<Appointment> patientAppointments, Role role) {
        super(name, phone, email, nric, address, tags, patientAppointments, role);
    }

    /**
     * Returns true if both Doctors have the same NRIC.
     * This defines a weaker notion of equality between two doctors.
     */
    public boolean isSameDoctor(Doctor otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getNric().equals(getNric());
    }
}

