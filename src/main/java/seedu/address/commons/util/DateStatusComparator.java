package seedu.address.commons.util;

import seedu.address.model.service.appointment.Appointment;

import java.util.Comparator;

public class DateStatusComparator implements Comparator<Appointment> {
    public int compare(Appointment a1, Appointment a2)
    {
        return a1.getDateStatus().ordinal() - a2.getDateStatus().ordinal();
    }
}
