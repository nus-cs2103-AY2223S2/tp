package seedu.address.model.event;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.Person;

public class LabTest {

    private static final Lab lab1 = new Lab("lab1");
    private static final Lab lab2 = new Lab("lab2", new ArrayList<>());

    private static final Person testPerson = new Person();
    private static final List<Person> testPersons = new ArrayList<>(Arrays.asList(testPerson));
    private static final Lab lab3 = new Lab("lab3", testPersons);

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
        assertNotEquals(newLab, lab3);
        assertEquals(newLab.getStudents(), lab3.getStudents());
    }

    @Test
    public void lab_validLab_getDate() {
        assertNotEquals(LocalDateTime.now(), new Lab("now").getDate());
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
    public void lab_validLab_countAttachments() {
        assertEquals(0, lab1.countAttachments());
        assertEquals(0, lab2.countAttachments());
        assertEquals(0, lab3.countAttachments());
    }
}
