package seedu.dengue.model.overview;

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
    private static final String POSTAL_DELIMITER = ", ";
    private static final String POSTAL_TITLE = "Overview by Location";

    private PostalAnalyst analyst;

    /**
     * Constructs a {@code PostalOverview} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public PostalOverview(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new PostalAnalyst(persons);
    }

    /**
     * Constructs a {@code PostalOverview} instance with an empty list of {@code Person}s.
     */
    public PostalOverview() {
        this(new ArrayList<>());
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
    public void update(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new PostalAnalyst(persons);
    }

    private static String[] splitLocationName(String locationName) {
        return locationName.split(POSTAL_DELIMITER);
    }

    @Override
    public String makeBinFormat(DataBin bin) {
        String[] locationNames = splitLocationName(bin.getName());
        String paddingWhitespace = makeWhitespace(MAX_BIN_NAME_LENGTH - locationNames[0].length());

        int maxSizeLen = String.valueOf(getAnalyst().getTotal()).length();
        locationNames[0] = String.format("%s" + paddingWhitespace + GAP + "%" + maxSizeLen + "d",
                locationNames[0], bin.getSize());

        String rowPrefix = "\n" + makeWhitespace(MAX_INDEX_LEN) + GAP;
        return String.join(rowPrefix, locationNames);
    }
}
