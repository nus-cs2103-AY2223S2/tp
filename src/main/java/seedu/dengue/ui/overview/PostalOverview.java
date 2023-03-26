package seedu.dengue.ui.overview;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.analyst.PostalAnalyst;
import seedu.dengue.model.person.Person;

/**
 * An {@code Overview} that runs analysis by {@code Postal}, i.e. neighbourhood/location.
 *
 * @see PostalAnalyst
 */
public class PostalOverview extends Overview {
    private static final String POSTAL_TITLE = "Overview by Location";

    private final PostalAnalyst analyst;

    /**
     * Constructs a {@code PostalOverview} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public PostalOverview(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new PostalAnalyst(persons);
    }

    @Override
    public String getOverviewTitle() {
        return POSTAL_TITLE;
    }

    @Override
    public PostalAnalyst getAnalyst() {
        return this.analyst;
    }

    @Override
    public String makeBinFormat(DataBin bin) {
        return " ";
    }
}
