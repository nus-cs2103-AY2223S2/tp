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
                    new Status("found"), null, getDateSet(new Date("Interview", "20231121"))),
            new Opening(new Position("Software Developer"), new Company("Shopee"), new Email("alice@shopee.com"),
                    new Status("applied"), new Remark("Dream Company"),
                    getDateSet(new Date("Interview", "20231015"))),
            new Opening(new Position("UI/UX Designer"), new Company("Grab"), new Email("tenz@grab.com"),
                    new Status("found"), null, getDateSet(new Date("Interview", "20230412"),
                        new Date("Practical Assessment", "20230511"))),
            new Opening(new Position("Software Engineer"), new Company("Gojek"), new Email("sleepy@gojek.com"),
                    new Status("found"), null, getDateSet())

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
