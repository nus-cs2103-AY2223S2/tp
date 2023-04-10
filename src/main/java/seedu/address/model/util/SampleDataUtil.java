package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.AppointmentList;
import seedu.address.model.ReadOnlyAppointmentList;
import seedu.address.model.ReadOnlyPatientList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.Description;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.Timeslot;
import seedu.address.model.patient.Address;
import seedu.address.model.patient.Email;
import seedu.address.model.patient.Name;
import seedu.address.model.patient.Patient;
import seedu.address.model.patient.Phone;
import seedu.address.model.patient.Remark;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static final Remark EMPTY_REMARK = new Remark("");
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Name("Alex Yeoh"),
                        new Phone("87438807"), new Email("alexyeoh@example.com"),
                        new Address("Blk 30 Geylang Street 29, #06-40"), EMPTY_REMARK,
                        getTagSet("returningPatient")),
            new Patient(new Name("Bernice Yu"),
                        new Phone("99272758"), new Email("berniceyu@example.com"),
                        new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), new Remark("Follow-up required"),
                        getTagSet("returningPatient", "outstandingPayment")),
            new Patient(new Name("Charlotte Oliveiro"),
                        new Phone("93210283"), new Email("charlotte@example.com"),
                        new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), EMPTY_REMARK,
                        getTagSet("returningPatient")),
            new Patient(new Name("David Li"),
                        new Phone("91031282"), new Email("lidavid@example.com"),
                        new Address("Blk 436 Serangoon Gardens Street 26, #16-43"), EMPTY_REMARK,
                        getTagSet("newPatient")),
            new Patient(new Name("Irfan Ibrahim"),
                        new Phone("92492021"), new Email("irfan@example.com"),
                        new Address("Blk 47 Tampines Street 20, #17-35"), EMPTY_REMARK,
                        getTagSet("newPatient")),
            new Patient(new Name("Roy Balakrishnan"),
                        new Phone("92624417"), new Email("royb@example.com"),
                        new Address("Blk 45 Aljunied Street 85, #11-31"), EMPTY_REMARK,
                        getTagSet("returningPatient"))
        };
    }

    public static ReadOnlyPatientList getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Patient samplePatient : getSamplePatients()) {
            sampleAb.addPatient(samplePatient);
        }
        return sampleAb;
    }

    public static Appointment[] getSampleAppointments() {
        return new Appointment[] {
            new Appointment(new Name("Alex Yeoh"),
                        new Timeslot("19032023 08:00,19032023 09:00"), new Description("Regular checkup"),
                        new Doctor("Xiao Lu")),
            new Appointment(new Name("Bernice Yu"),
                        new Timeslot("26032023 11:00,26032023 13:00"), new Description("Consultation"),
                        new Doctor("Sidharth Rajesh")),
        };
    }

    public static ReadOnlyAppointmentList getSampleAppointmentList() {
        AppointmentList sampleAl = new AppointmentList();
        for (Appointment sampleAppointment : getSampleAppointments()) {
            sampleAl.addAppointment(sampleAppointment);
        }
        return sampleAl;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
