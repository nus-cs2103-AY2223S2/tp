package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;
import seedu.address.testutil.PersonBuilder;

public class LabTest {

    private static final Lab lab1 = new Lab("lab1");
    private static final Lab lab2 = new Lab("lab2", new ArrayList<>());

    private static final Person testPerson = new Person();
    private static final List<Person> testPersons = new ArrayList<>(Arrays.asList(testPerson));
    private static final Lab lab3 = new Lab("lab3", testPersons);

    private static final Lab lab4 = new Lab("lab4", LocalDateTime.now(), new ArrayList<>());

    private static final Lab lab5 = new Lab("lab5", LocalDateTime.now(), new ArrayList<>(), new ArrayList<>());

    private static final Lab lab6 = new Lab("lab6", LocalDateTime.now(), new ArrayList<>(), new ArrayList<>(),
            new ArrayList<>());

    @Test
    public void name_editName_getName() {
        lab1.setName("notLab1");
        assertEquals("notLab1", lab1.getName());

        lab2.setName("notLab2");
        assertEquals("notLab2", lab2.getName());

        lab3.setName("notLab3");
        assertEquals("notLab3", lab3.getName());
    }

    @Test
    public void student_validCount_countStudents() {
        assertEquals(0, lab1.countStudents());
        assertEquals(0, lab2.countStudents());
        assertEquals(1, lab3.countStudents());
    }

    @Test
    public void student_validStudents_getStudents() {
        assertNotEquals(testPersons, lab1.getStudents());
        assertNotEquals(testPersons, lab2.getStudents());
        assertEquals(testPersons, lab3.getStudents());
    }

    @Test
    public void student_invalidStudent_removeStudent() {
        lab1.removeStudent(testPerson);
        assertEquals(lab1.getStudents(), new Lab("lab1").getStudents());
    }

    @Test
    public void student_validStudent_removeStudent() {
        Lab newLab = new Lab("new Lab", testPersons);
        newLab.removeStudent(testPerson);
        assertNotEquals(newLab, lab3);
    }

    @Test
    public void student_validStudent_removeIndexStudent() {
        Lab newLab = new Lab("lab2", testPersons);
        newLab.removeIndexStudent(0);
        assertEquals(newLab.getStudents(), lab2.getStudents());
    }

    @Test
    public void student_validStudentProfiles_getStudentProfiles() {
        assertNotNull(lab1.getStudentProfiles());
    }

    @Test
    public void student_validAttachments_getAttachments() {
        assertNotNull(lab1.getAttachments());
    }

    @Test
    public void student_validAttachments_addAttachment() {
        lab6.addAttachment(new File(""));
        assertEquals(1, lab6.countAttachments());
    }

    @Test
    public void student_validAttachmentToCurrentAttachment_addAttachment() {
        lab6.addAttachment(new File(""));
        assertEquals(1, lab6.countAttachments());
    }

    @Test
    public void student_validAttachment_removeAttachment() {
        lab6.removeAttachment(new File(""));
        assertEquals(0, lab6.countAttachments());
    }

    @Test
    public void student_validStudent_addStudent() {
        Lab newLab = new Lab("lab3");
        newLab.addStudent(testPerson);
        assertNotEquals(newLab, lab3);
        assertEquals(newLab.getStudents(), lab3.getStudents());
    }

    @Test
    public void student_validStudent_replaceStudent() {
        Lab newLab = new Lab("lab3");
        newLab.addStudent(testPerson);
        newLab.replaceStudent(testPerson, testPerson);
        lab3.addStudent(testPerson);
        assertNotEquals(newLab, lab3);
        assertEquals(newLab.getStudents(), lab3.getStudents());
    }

    @Test
    public void lab_validLab_getDate() {
        assertNotNull(lab5.getDate());
    }

    @Test
    public void lab_validLab_changeDate() {
        Lab newLab = new Lab("now");
        newLab.changeDate(LocalDateTime.now());
        assertNotEquals(LocalDateTime.now(), newLab);
    }

    @Test
    public void lab_validLab_sameLab() {
        Lab newLab = new Lab("now");
        newLab.changeDate(LocalDateTime.now());
        assertFalse(lab1.isSameLab(newLab));
    }

    @Test
    public void lab_validLab_countNotes() {
        assertEquals(0, lab1.countNotes());
        assertEquals(0, lab2.countNotes());
        assertEquals(0, lab3.countNotes());
    }

    @Test
    public void lab_validLab_getNotes() {
        assertEquals(1, lab6.getNotes().size());
    }

    @Test
    public void lab_validLab_addNote() {
        lab6.addNote(new Note());
        assertEquals(1, lab6.countNotes());
    }

    @Test
    public void lab_validLab_removeNote() {
        lab6.removeNote(new Note());
        assertEquals(0, lab6.countNotes());
    }

    @Test
    public void lab_validLab_countAttachments() {
        assertEquals(0, lab1.countAttachments());
        assertEquals(0, lab2.countAttachments());
        assertEquals(0, lab3.countAttachments());
    }

    @Test
    public void lab_differentLab_notEquals() {
        Lab newLab = new Lab("lab6");
        newLab.changeDate(LocalDateTime.of(2060, 10, 10, 10, 10));
        assertFalse(lab6.isSameLab(newLab));
    }

    @Test
    public void lab_nullLab_notEquals() {
        assertFalse(lab6.isSameLab(null));
    }

    @Test
    public void lab_differentNameSameDateLab_notEquals() {
        Lab newLab = new Lab("new Lab");
        newLab.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));

        Lab newLabB = new Lab("new Lab B");
        newLabB.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));

        assertFalse(newLab.isSameLab(newLabB));
    }

    @Test
    public void lab_hashLab_notNull() {
        assertNotNull(lab4.hashCode());
    }

    @Test
    public void lab_copyLab_sameLab() {
        assertEquals(lab6, lab6.copy());
    }

    @Test
    public void lab_differentLabEquality_notSameLab() {
        Person person = new PersonBuilder().build();

        Lab newLabA = new Lab("lab6");
        newLabA.addStudent(person);
        newLabA.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabA.addAttachment(new File("new file"));
        newLabA.addNote(new Note("new note"));

        Lab newLabB = new Lab("lab6");
        newLabB.addStudent(person);
        newLabB.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabB.addAttachment(new File("new file"));
        newLabB.addNote(new Note("not new note"));

        assertFalse(newLabA.equals(newLabB));
    }

    @Test
    public void lab_differentLabEqualityStudent_notSameLab() {
        Person person = new PersonBuilder().build();

        Lab newLabA = new Lab("lab6");
        newLabA.addStudent(person);
        newLabA.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabA.addAttachment(new File("new file"));
        newLabA.addNote(new Note("new note"));

        Lab newLabB = new Lab("lab6");
        newLabB.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabB.addAttachment(new File("new file"));
        newLabB.addNote(new Note("new note"));

        assertFalse(newLabA.equals(newLabB));
    }

    @Test
    public void lab_differentLabEqualityDate_notSameLab() {
        Person person = new PersonBuilder().build();

        Lab newLabA = new Lab("lab6");
        newLabA.addStudent(person);
        newLabA.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabA.addAttachment(new File("new file"));
        newLabA.addNote(new Note("new note"));

        Lab newLabB = new Lab("lab6");
        newLabB.addStudent(person);
        newLabB.changeDate(LocalDateTime.of(2021, 10, 10, 10, 10));
        newLabB.addAttachment(new File("new file"));
        newLabB.addNote(new Note("new note"));

        assertFalse(newLabA.equals(newLabB));
    }

    @Test
    public void lab_differentLabEqualityAttachment_notSameLab() {
        Person person = new PersonBuilder().build();

        Lab newLabA = new Lab("lab6");
        newLabA.addStudent(person);
        newLabA.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabA.addAttachment(new File("new file"));
        newLabA.addNote(new Note("new note"));

        Lab newLabB = new Lab("lab6");
        newLabB.addStudent(person);
        newLabB.changeDate(LocalDateTime.of(2021, 10, 10, 10, 10));
        newLabB.addNote(new Note("new note"));

        assertFalse(newLabA.equals(newLabB));
    }

    @Test
    public void lab_sameLabEqualityDetails_sameLab() {
        Person person = new PersonBuilder().build();

        Lab newLabA = new Lab("lab6");
        newLabA.addStudent(person);
        newLabA.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabA.addAttachment(new File("new file"));
        newLabA.addNote(new Note("new note"));

        Lab newLabB = new Lab("lab6");
        newLabB.addStudent(person);
        newLabB.changeDate(LocalDateTime.of(2020, 10, 10, 10, 10));
        newLabB.addAttachment(new File("new file"));
        newLabB.addNote(new Note("new note"));

        assertTrue(newLabA.equals(newLabB));
    }

    @Test
    public void lab_notLabObject_notSame() {
        assertFalse(lab6.equals(new Object()));
    }

    @Test
    public void lab_stringBuilder_notNull() {
        assertNotNull(lab6.toString());
    }

}
