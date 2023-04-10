package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_COMPANY_NAME_DEUTSCHE_BANK;
import static seedu.address.logic.commands.CommandTestUtil.VALID_JOB_TITLE_NETWORK_ENGINEER;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalInternships.LINKEDIN;
import static seedu.address.testutil.TypicalInternships.META;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.contact.Contact;
import seedu.address.model.documents.Documents;
import seedu.address.testutil.ContactBuilder;
import seedu.address.testutil.DocumentsBuilder;
import seedu.address.testutil.InternshipBuilder;

public class InternshipApplicationTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        InternshipApplication internship = new InternshipBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> internship.getQualifications().remove(0));
    }

    @Test
    public void isSameInternship() {
        // same object -> returns true
        assertTrue(LINKEDIN.isSameApplication(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.isSameApplication(null));

        // same company name and job title, some other attributes different -> returns true
        InternshipApplication editedLinkedIn = new InternshipBuilder(LINKEDIN).withQualifications("Dancing").build();
        assertTrue(LINKEDIN.isSameApplication(editedLinkedIn));

        // different name, all other attributes same -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withCompanyName(VALID_COMPANY_NAME_DEUTSCHE_BANK).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));

        // name differs in case, all other attributes same -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN)
                            .withCompanyName(LINKEDIN.getCompanyName().toString().toLowerCase()).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = LINKEDIN.getCompanyName().toString() + " ";
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withCompanyName(nameWithTrailingSpaces).build();
        assertFalse(LINKEDIN.isSameApplication(editedLinkedIn));
    }

    @Test
    public void equals() {
        // same values -> returns true
        InternshipApplication linkedInCopy = new InternshipBuilder(LINKEDIN).build();
        assertTrue(LINKEDIN.equals(linkedInCopy));
        assertTrue(LINKEDIN.hashCode() == linkedInCopy.hashCode());
        assertTrue(LINKEDIN.compareTo(linkedInCopy) == 0);

        // same object -> returns true
        assertTrue(LINKEDIN.equals(LINKEDIN));

        // null -> returns false
        assertFalse(LINKEDIN.equals(null));

        // different type -> returns false
        assertFalse(LINKEDIN.equals(5));

        // different person -> returns false
        assertFalse(LINKEDIN.equals(META));

        // different name -> returns false
        InternshipApplication editedLinkedIn = new InternshipBuilder(LINKEDIN)
            .withCompanyName(VALID_COMPANY_NAME_DEUTSCHE_BANK).build();
        assertFalse(LINKEDIN.equals(editedLinkedIn));

        // different job title -> returns false
        editedLinkedIn = new InternshipBuilder(LINKEDIN).withJobTitle(VALID_JOB_TITLE_NETWORK_ENGINEER).build();
        assertFalse(LINKEDIN.equals(editedLinkedIn));
    }

    @Test
    public void testInternshipApplicationConstructor() {
        // Arrange
        CompanyName companyName = new CompanyName("ABC Company");
        JobTitle jobTitle = new JobTitle("Software Engineer Intern");
        Set<Review> reviews = new HashSet<>();
        Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();
        Set<Qualification> qualifications = new HashSet<>();
        Location location = new Location("New York");
        Salary salary = new Salary("5000 SGD");
        Set<Note> notes = new HashSet<>();
        Rating rating = new Rating("4.5");
        Set<Reflection> reflections = new HashSet<>();

        // Act
        InternshipApplication application = new InternshipApplication(
                companyName, jobTitle, reviews, programmingLanguages, qualifications,
                location, salary, notes, rating, reflections
        );

        // Assert
        assertEquals(companyName, application.getCompanyName());
        assertEquals(jobTitle, application.getJobTitle());
        assertEquals(reviews, application.getReviews());
        assertEquals(programmingLanguages, application.getProgrammingLanguages());
        assertEquals(qualifications, application.getQualifications());
        assertEquals(location, application.getLocation());
        assertEquals(salary, application.getSalary());
        assertEquals(notes, application.getNotes());
        assertEquals(rating, application.getRating());
        assertEquals(reflections, application.getReflections());
        assertNull(application.getContact());
        assertEquals(InternshipStatus.PENDING, application.getStatus());
        assertFalse(application.isArchived());
        assertNull(application.getDocuments());
        assertNull(application.getInterviewDate());
    }

    @Test
    public void testInternshipApplicationConstructorWithContact() {
        // Arrange
        CompanyName companyName = new CompanyName("ABC Company");
        JobTitle jobTitle = new JobTitle("Software Engineer Intern");
        Set<Review> reviews = new HashSet<>();
        Contact contact = new ContactBuilder().build();
        InternshipStatus status = InternshipStatus.ACCEPTED;
        boolean isArchived = false;
        Documents documents = new DocumentsBuilder().build();

        // Act
        InternshipApplication application = new InternshipApplication(
                companyName, jobTitle, reviews, contact, status, isArchived, documents
        );

        // Assert
        assertEquals(companyName, application.getCompanyName());
        assertEquals(jobTitle, application.getJobTitle());
        assertEquals(reviews, application.getReviews());
        assertEquals(contact, application.getContact());
        assertEquals(status, application.getStatus());
        assertNull(application.getInterviewDate());
        assertNull(application.getLocation());
        assertNull(application.getSalary());
        assertNull(application.getRating());
        assertEquals(documents, application.getDocuments());
        assertEquals(isArchived, application.isArchived());
    }

    @Test
    public void testToString() {
        // Create an instance of InternshipApplication with sample data
        CompanyName companyName = new CompanyName("Acme Inc");
        JobTitle jobTitle = new JobTitle("Software Developer Intern");
        Set<Review> reviews = new HashSet<>();
        reviews.add(new Review("Great company to work for!"));
        Set<ProgrammingLanguage> programmingLanguages = new HashSet<>();
        programmingLanguages.add(new ProgrammingLanguage("Java"));
        Set<Qualification> qualifications = new HashSet<>();
        qualifications.add(new Qualification("Bachelor's degree in Computer Science"));
        Location location = new Location("New York City");
        Salary salary = new Salary("25 SGD");
        Set<Note> notes = new HashSet<>();
        notes.add(new Note("Need to submit transcripts"));
        Rating rating = new Rating("4 stars");
        Set<Reflection> reflections = new HashSet<>();
        reflections.add(new Reflection("Learned a lot about web development"));

        // Set contact and status fields
        Contact contact = new ContactBuilder().build();
        InternshipStatus status = InternshipStatus.ACCEPTED;
        boolean isArchived = false;
        Documents documents = null;

        // Set interview date field
        InterviewDate interviewDate = new InterviewDate("2023-02-27 08:00 AM");

        InternshipApplication application = new InternshipApplication(companyName, jobTitle, reviews,
            programmingLanguages, qualifications, location, salary, notes, rating, reflections, contact, status,
            isArchived, interviewDate, documents);

        // Define expected output
        String expected = "Acme Inc; Job Title: Software Developer Intern; Status: ACCEPTED; Archived: false; "
            + "Review: Great company to work for!; Programming Language: Java; "
            + "Qualification: Bachelor's degree in Computer Science; Location: New York City; Salary: 25 SGD; "
            + "Note: Need to submit transcripts; Rating: 4 stars; Reflection: Learned a lot about web development; "
            + "Company Phone: 55555555; Company Email: meta@example.com; Interview Date: 2023-02-27 08:00 AM";

        // Verify that toString() method returns expected output
        assertEquals(expected, application.toString());
    }


}
