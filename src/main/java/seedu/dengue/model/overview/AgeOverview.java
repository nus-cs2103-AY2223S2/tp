package seedu.dengue.model.overview;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.logic.analyst.AgeAnalyst;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.model.person.Person;

/**
 * An {@code Overview} that runs analysis by {@code Age}.
 *
 * @see AgeAnalyst
 */
public class AgeOverview extends Overview {
    private static final String AGE_TITLE = "Overview by Age";
    private static final int MAX_AGE_LENGTH = 9; // Length of the longest bin name, "190 - 199"

    private AgeAnalyst analyst;

    /**
     * Constructs an {@code AgeOverview} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public AgeOverview(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new AgeAnalyst(persons);
    }

    /**
     * Constructs an {@code AgeOverview} instance with an empty list of {@code Person}s.
     */
    public AgeOverview() {
        this(new ArrayList<>());
    }

    @Override
    public String getOverviewTitle() {
        return AGE_TITLE;
    }

    @Override
    public AgeAnalyst getAnalyst() {
        return this.analyst;
    }

    @Override
    public void update(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new AgeAnalyst(persons);
    }

    private static String padBinName(String binName) {
        int paddingNeeded = MAX_AGE_LENGTH - binName.length();
        String whitespace = makeWhitespace(paddingNeeded / 2);
        return whitespace + binName + whitespace;
    }

    @Override
    public String makeBinFormat(DataBin bin) {
        int maxSizeLen = String.valueOf(getAnalyst().getTotal()).length();
        return String.format("%s" + GAP + "%" + maxSizeLen + "d",
                padBinName(bin.getName()), bin.getSize());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof AgeOverview)) {
            return false;
        }

        AgeOverview other = (AgeOverview) obj;
        return this.analyst.equals(other.analyst);
    }
}
