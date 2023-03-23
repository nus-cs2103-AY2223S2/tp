package seedu.address.model.util;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import seedu.address.model.internship.Internship;

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
        assertEquals(sampleInternships[5].getCompanyName().toString(), "Facebook");
    }
}
