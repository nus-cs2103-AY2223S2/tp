package seedu.fitbook.model.util;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static seedu.fitbook.model.util.SampleDataUtil.getAppointmentSet;
import static seedu.fitbook.model.util.SampleDataUtil.getTagSet;
import static seedu.fitbook.testutil.Assert.assertArrayNotEquals;

import org.junit.jupiter.api.Test;

import seedu.fitbook.model.client.Address;
import seedu.fitbook.model.client.Calorie;
import seedu.fitbook.model.client.Client;
import seedu.fitbook.model.client.Email;
import seedu.fitbook.model.client.Gender;
import seedu.fitbook.model.client.Goal;
import seedu.fitbook.model.client.Name;
import seedu.fitbook.model.client.Phone;
import seedu.fitbook.model.client.Weight;

public class SampleDataUtilTest {

    @Test
    public void getSampleClients() {
        Client[] testClient = new Client[] {
            new Client(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new Address("Blk 30 Geylang Street 29, #06-40"), getAppointmentSet("12-12-2019 18:30"),
                new Weight("20"), new Gender("M"), new Calorie("1200"), new Goal("lose weight"),
                    getTagSet("friends")),
            new Client(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new Address("Blk 30 Lorong 3 Serangoon Gardens, #07-18"), getAppointmentSet("12-12-2019 18:30"),
                new Weight("20"), new Gender("M"), new Calorie("1200"), new Goal("lose weight"),
                    getTagSet("colleagues", "friends")),
            new Client(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new Address("Blk 11 Ang Mo Kio Street 74, #11-04"), getAppointmentSet("12-12-2019 18:30"),
                new Weight("30"), new Gender("M"), new Calorie("1100"), new Goal("lose weight"),
                    getTagSet("neighbours")),
            new Client(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new Address("Blk 436 Serangoon Gardens Street 26, #16-43"),
                    getAppointmentSet("12-12-2019 18:30"), new Weight("40"), new Gender("M"),
                    new Calorie("1200"), new Goal("lose weight"), getTagSet("family")),
            new Client(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new Address("Blk 47 Tampines Street 20, #17-35"), getAppointmentSet("12-12-2019 18:30"),
                new Weight("50"), new Gender("M"), new Calorie("1202"), new Goal("lose weight"),
                    getTagSet("classmates")),
            new Client(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new Address("Blk 45 Aljunied Street 85, #11-31"), getAppointmentSet("12-12-2019 18:30"),
                new Weight("60"), new Gender("M"), new Calorie("2356"), new Goal("lose weight"),
                    getTagSet("colleagues"))
        };

        int clientNumber = SampleDataUtil.getSampleClients().length;
        Client[] nullTestClient = new Client[clientNumber];

        // Sample Data is the equal to the sample data of test.
        assertArrayEquals(SampleDataUtil.getSampleClients(), testClient);

        // Sample Data is non null.
        assertArrayNotEquals(nullTestClient, SampleDataUtil.getSampleClients());
    }
}
