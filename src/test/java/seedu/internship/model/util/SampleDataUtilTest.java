package seedu.internship.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.internship.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.internship.model.InternBuddy;
import seedu.internship.model.internship.Comment;
import seedu.internship.model.internship.CompanyName;
import seedu.internship.model.internship.Date;
import seedu.internship.model.internship.Internship;
import seedu.internship.model.internship.Role;
import seedu.internship.model.internship.Status;

public class SampleDataUtilTest {

    @Test
    public void getSampleInternships_success() {
        Internship[] sampleInternships = SampleDataUtil.getSampleInternships();

        //Check first internship
        assertEquals(sampleInternships[0].getCompanyName().toString(), "Apple");

        //Check second internship
        assertEquals(sampleInternships[1].getCompanyName().toString(), "Amazon");

        //Check third internship
        assertEquals(sampleInternships[2].getCompanyName().toString(), "Google");

        //Check fourth internship
        assertEquals(sampleInternships[3].getCompanyName().toString(), "Samsung");

        //Check fifth internship
        assertEquals(sampleInternships[4].getCompanyName().toString(), "Grab");

        //Check sixth internship
        assertEquals(sampleInternships[5].getCompanyName().toString(), "Paypal");

        //Check seventh internship
        assertEquals(sampleInternships[6].getCompanyName().toString(), "Facebook");
    }

    @Test
    public void getSampleInternBuddy_success() {
        InternBuddy ib = (InternBuddy) SampleDataUtil.getSampleInternBuddy();
        Internship internshipToCheck = new Internship(new CompanyName("Apple"), new Role("iOS Developer"),
                new Status("applied"), new Date("2023-03-20"), new Comment("My dream company!"),
                getTagSet("iOS"));
        assertTrue(ib.hasInternship(internshipToCheck));
    }

}
