package seedu.patientist.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.patientist.model.Patientist;
import seedu.patientist.model.ReadOnlyPatientist;
import seedu.patientist.model.person.Address;
import seedu.patientist.model.person.Email;
import seedu.patientist.model.person.IdNumber;
import seedu.patientist.model.person.Name;
import seedu.patientist.model.person.Phone;
import seedu.patientist.model.person.patient.Patient;
import seedu.patientist.model.person.staff.Staff;
import seedu.patientist.model.tag.Tag;
import seedu.patientist.model.ward.Ward;

/**
 * Contains utility methods for populating {@code Patientist} with sample data.
 * TODO: refactor this to create a valid sample Patientist
 */
public class SampleDataUtil {
    public static Patient[] getSamplePatients() {
        return new Patient[] {
            new Patient(new Email("alexyeoh@example.com"), new Name("Alex Yeoh"), new Phone("87438807"),
                    new IdNumber("A12345A"), new Address("Blk 30 Geylang Street 29, #06-40"),
                getTagSet("Patient")),
            new Patient(new Email("berniceyu@example.com"), new Name("Bernice Yu"), new Phone("99272758"),
                    new IdNumber("A12345B"), new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"),
                getTagSet("Patient")),
            new Patient(new Email("charlotte@example.com"), new Name("Charlotte Oliveiro"), new Phone("93210283"),
                    new IdNumber("A12345C"), new Address("Blk 11 Ang Mo Kio Street 74, #11-04"),
                getTagSet("Patient")),
            new Patient(new Email("lidavid@example.com"), new Name("David Li"), new Phone("91031282"),
                    new IdNumber("A12345D"), new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                getTagSet("Patient")),
            new Patient(new Email("irfan@example.com"), new Name("Irfan Ibrahim"), new Phone("92492021"),
                    new IdNumber("A12345E"), new Address("Blk 47 Tampines Street 20, #17-35"),
                getTagSet("Patient")),
            new Patient(new Email("royb@example.com"), new Name("Roy Balakrishnan"), new Phone("92624417"),
                    new IdNumber("A12345F"), new Address("Blk 45 Aljunied Street 85, #11-31"),
                getTagSet("Patient"))
        };
    }

    private static Staff[] getSampleStaffs() {
        return new Staff[] {
            new Staff(new Name("Amy Bee"), new Phone("11111111"), new Email("amy@example.com"),
                    new IdNumber("A12345678B"), new Address("Block 312, Amy Street 1"), getTagSet("Staff")),
            new Staff(new Name("Bob Choo"), new Phone("222222222"), new Email("bob@example.com"),
                    new IdNumber("Y78932734N"), new Address("Block 123, Bobby Street 3"), getTagSet("Staff")),
            new Staff(new Name("Charles Lee"), new Phone("2136784"), new Email("leecharles@example.com"),
                    new IdNumber("G487659645D"), new Address("123, ABC, #08-111"), getTagSet("Staff")),
            new Staff(new Name("Dacia Chin"), new Phone("96128393"), new Email("daciachin@example.com"),
                    new IdNumber("L73825263J"), new Address("970, Hindhede St, #07-27"), getTagSet("Staff")),
        };
    }

    public static Ward[] getSampleWards() {
        return new Ward[] {
            new Ward("Block A Ward 1"),
            new Ward("Block B Ward 2")
        };
    }

    public static ReadOnlyPatientist getSamplePatientist() {
        Patientist sampleAb = new Patientist();
        Patient[] patients = getSamplePatients();
        Staff[] staffs = getSampleStaffs();
        Ward[] wards = getSampleWards();

        for (int i = 0; i < wards.length; i++) {
            for (int j = i; j < patients.length; j += 2) {
                wards[i].addPatient(patients[j]);
            }
            for (int j = i; j < staffs.length; j += 2) {
                wards[i].addStaff(staffs[j]);
            }
            sampleAb.addWard(wards[i]);
        }
        return sampleAb;
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
