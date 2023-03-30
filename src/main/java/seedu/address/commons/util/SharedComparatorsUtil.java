package seedu.address.commons.util;

import seedu.address.model.service.appointment.Appointment;

import java.util.Comparator;

public class SharedComparatorsUtil {
    public static Comparator<Appointment> getDefaultAppointmentSort() {
        return Comparator.comparing(Appointment::getDateStatus)
                .thenComparing(Appointment::getTimeDate);
    }
}
