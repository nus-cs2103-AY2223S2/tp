package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.person.information.AvailableDate;
import seedu.address.model.tag.MedicalQualificationTag;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code FriendlyLink} with sample data.
 */
public class SampleDataUtil {
    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

    public static Set<MedicalQualificationTag> getMedicalTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(s -> {
                    String[] parts = s.split(" ");
                    return new MedicalQualificationTag(parts[0], parts[1]);
                })
                .collect(Collectors.toSet());
    }

    public static Set<AvailableDate> getAvailableDateSet(String... strings) {
        return Arrays.stream(strings)
                .map(s -> {
                    String[] parts = s.split(",");
                    return new AvailableDate(parts[0], parts[1]);
                })
                .collect(Collectors.toSet());
    }

    public static AvailableDate getAvailableDate(String startDate, String endDate) {
        return new AvailableDate(startDate, endDate);
    }

}
