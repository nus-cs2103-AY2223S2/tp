package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.SampleDataUtil.getTagSet;

import org.junit.jupiter.api.Test;

import seedu.address.model.InternBuddy;
import seedu.address.model.internship.Comment;
import seedu.address.model.internship.CompanyName;
import seedu.address.model.internship.Date;
import seedu.address.model.internship.Internship;
import seedu.address.model.internship.Role;
import seedu.address.model.internship.Status;

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
                new Status("applied"), new Date("2023-02-01"), new Comment("My dream company!"),
                getTagSet("front"));
        assertTrue(ib.hasInternship(internshipToCheck));
    }

}
