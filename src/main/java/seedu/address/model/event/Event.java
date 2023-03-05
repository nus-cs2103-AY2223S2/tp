package seedu.address.model.event;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import seedu.address.model.person.Person;

public abstract class Event {

    private final List<Person> students;
    //Add time in v 1.2
    private LocalDate eventDate;
    //Add notes class in v 1.2
    private String notes = "";
    //Add status tags in v 1.2
    private String status = "upcoming";

    public Event(List<Person> students) {
        this.students = students;
        this.eventDate = LocalDate.now();
    }

    public Event(List<Person> students, LocalDate eventDate) {
        this.students = new ArrayList<>();
        this.eventDate = eventDate;
    }

    public List<Person> getStudents() {
        return students;
    }

    public LocalDate getDate() {
        return eventDate;
    }

    public void addStudent(Person student) {
        students.add(student);
    }

    public void removeStudent(Person student) {
        students.remove(student);
    }

    public void removeIndexStudent(int index) {
        students.remove(index);
    }

    public void changeDate(LocalDate date) {
        eventDate = date;
    }

    public int countStudents() {
        return students.size();
    }
}
