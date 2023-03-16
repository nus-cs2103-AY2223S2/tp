package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.ReadOnlyUltron;
import seedu.address.model.Ultron;
import seedu.address.model.opening.Company;
import seedu.address.model.opening.Date;
import seedu.address.model.opening.Email;
import seedu.address.model.opening.Opening;
import seedu.address.model.opening.Position;
import seedu.address.model.opening.Remark;
import seedu.address.model.opening.Status;


/**
 * Contains utility methods for populating {@code Ultron} with sample data.
 */
public class SampleDataUtilNew {
    public static Opening[] getSampleOpenings() {
        return new Opening[] {
            new Opening(new Position("Software Engineer"), new Company("Google"), new Email("johnDoe@google.com"),
                    new Status("FOUND"), null, getDateSet(new Date("Interview", "2023-11-21"))),
            new Opening(new Position("Software Developer"), new Company("Shopee"), new Email("alice@shopee.com"),
                    new Status("APPLIED"), new Remark("Dream Company"),
                    getDateSet(new Date("Interview", "2023-10-15"))),
            new Opening(new Position("UX Designer"), new Company("Grab"), new Email("tenz@grab.com"),
                    new Status("FOUND"), null, getDateSet(new Date("Interview", "2023-04-12"),
                        new Date("Practical Assessment", "2023-05-11"))),
            new Opening(new Position("Software Engineer"), new Company("Gojek"), new Email("sleepy@gojek.com"),
                    new Status("FOUND"), null, getDateSet())
        };
    }

    public static ReadOnlyUltron getSampleUltron() {
        Ultron sampleUltron = new Ultron();
        for (Opening sampleOpening : getSampleOpenings()) {
            sampleUltron.addOpening(sampleOpening);
        }
        return sampleUltron;
    }


    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Date> getDateSet(Date... dates) {
        return Arrays.stream(dates)
                .collect(Collectors.toSet());
    }
}
