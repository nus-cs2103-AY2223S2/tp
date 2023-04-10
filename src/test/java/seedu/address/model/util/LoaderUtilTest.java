package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.doctor.Doctor;
import seedu.address.model.person.doctor.Specialty;
import seedu.address.model.person.doctor.Yoe;
import seedu.address.model.person.patient.Diagnosis;
import seedu.address.model.person.patient.Height;
import seedu.address.model.person.patient.Patient;
import seedu.address.model.person.patient.Remark;
import seedu.address.model.person.patient.Status;
import seedu.address.model.person.patient.Weight;
import seedu.address.model.tag.Tag;



public class LoaderUtilTest {
    @Test
    public void getTagSet_validTags_success() {
        Set<Tag> tags = new HashSet<>();
        tags.add(new Tag("tag1"));
        tags.add(new Tag("tag2"));
        tags.add(new Tag("tag3"));

        assertEquals(tags, LoaderUtil.getTagSet("tag1", "tag2", "tag3"));
    }

    @Test
    public void getTagSet_emptyTags_success() {
        Set<Tag> tags = new HashSet<>();
        assertEquals(tags, LoaderUtil.getTagSet());
    }

    @Test
    public void getDoctorSet_validDoctors_success() {
        Set<Doctor> doctors = new HashSet<>();
        doctors.add(new Doctor(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Specialty("Cardiology"), new Yoe("5"), LoaderUtil.getTagSet("intern"))
        );
        doctors.add(new Doctor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Specialty("Neurology"), new Yoe("10"), LoaderUtil.getTagSet("resident")));

        assertEquals(doctors, LoaderUtil.getDoctorSet(new Doctor(new Name("Alex Yeoh"), new Phone("87438807"),
                new Email("alexyeoh@example.com"),
                new Specialty("Cardiology"), new Yoe("5"), LoaderUtil.getTagSet("intern")),
                new Doctor(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Specialty("Neurology"), new Yoe("10"), LoaderUtil.getTagSet("resident"))));
    }

    @Test
    public void getPatientSet_validPatients_success() {
        Set<Patient> patients = new HashSet<>();
        patients.add(new Patient(new Name("Bob Choo"), new Phone("94351253"), new Email("bchoo@gmail.com"),
                new Height("1.70"),
                new Weight("70.0"), new Diagnosis("Cancer"), new Status("Intensive Care Unit"),
                new Remark("Prescribed chemo"), LoaderUtil.getTagSet("pendingReview")));

        assertEquals(patients, LoaderUtil.getPatientSet(new Patient(new Name("Bob Choo"), new Phone("94351253"),
                new Email("bchoo@gmail.com"),
                new Height("1.70"),
                new Weight("70.0"), new Diagnosis("Cancer"), new Status("Intensive Care Unit"),
                new Remark("Prescribed chemo"), LoaderUtil.getTagSet("pendingReview"))));
    }
}
