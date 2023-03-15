package seedu.wife.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.wife.model.ReadOnlyWife;
import seedu.wife.model.Wife;
import seedu.wife.model.food.ExpiryDate;
import seedu.wife.model.food.Food;
import seedu.wife.model.food.Name;
import seedu.wife.model.food.Quantity;
import seedu.wife.model.food.Unit;
import seedu.wife.model.tag.Tag;

/**
 * Contains utility methods for populating {@code WIFE} with sample data.
 */
public class SampleDataUtil {
    public static Food[] getSampleFood() {
        return new Food[] {
            new Food(new Name("Meiji Milk"), new Unit("Carton"), new Quantity("2"),
                new ExpiryDate("13-11-2024"),
                getTagSet("dairy")),
        };
    }

    public static ReadOnlyWife getSampleWife() {
        Wife sampleWife = new Wife();
        for (Food sampleFood : getSampleFood()) {
            sampleWife.addFood(sampleFood);
        }
        return sampleWife;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
