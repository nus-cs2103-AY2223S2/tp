package seedu.dengue.model.overview;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.dengue.logic.analyst.Analyst;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.model.person.Person;

/**
 * The API of the Overview list.
 */
public abstract class Overview {
    protected static final int MAX_INDEX_LEN = 4; // Length of " 28."
    protected static final int MAX_BIN_NAME_LENGTH = 17; // Hong Leong Garden
    protected static final String GAP = "     ";

    /**
     * Returns the title {@code String} of the overview.
     *
     * @return The title of the overview.
     */
    public abstract String getOverviewTitle();

    /**
     * Returns the subtitle {@code String} of the overview.
     *
     * @return The subtitle of the overview.
     */
    public String getOverviewSubtitle() {
        return String.format("Total Cases: %d",
                getAnalyst().getTotal());
    }

    /**
     * Returns the {@code Analayst} that runs the data analysis for the overview.
     *
     * @return The analyst associated with the overview.
     */
    public abstract Analyst getAnalyst();

    /**
     * Updates the analyst with the given {@code Person} list.
     *
     * @param personsList The new list of persons to analyse.
     */
    public abstract void update(List<Person> personsList);

    /**
     * Returns a standardized {@code String} representation of a given bin,
     * including information like its name and size,
     * such that the format matches other bins in the same overview.
     *
     * @param bin The input bin to format into a {@code String}.
     * @return The string representation of the bin.
     */
    public String makeBinFormat(DataBin bin) {
        int maxSizeLen = String.valueOf(getAnalyst().getTotal()).length();
        return String.format("%s" + GAP + "%" + maxSizeLen + "d",
                padBinName(bin.getName()), bin.getSize());
    }

    protected static String makeWhitespace(int length) {
        if (length <= 0) {
            return "";
        }

        return Stream.iterate(" ", x -> x).limit(length).collect(Collectors.joining());
    }

    protected static String padBinName(String binName) {
        int paddingNeeded = MAX_BIN_NAME_LENGTH - binName.length();
        String whitespace = makeWhitespace(paddingNeeded / 2);
        return whitespace + binName + whitespace;
    }

    private String makeBinPrefix(int x) {
        String index = x + ".";
        int paddingNeeded = MAX_INDEX_LEN - index.length();
        return makeWhitespace(paddingNeeded) + index;
    }

    /**
     * Returns a {@code String} of all the bins recorded in the overview.
     * Meant for display to the user.
     *
     * @return Multi-line string representation of all present bins.
     */
    public String getOverviewContent() {
        List<DataBin> sortedBins = getAnalyst().getSortedBins();
        return Stream.iterate(1, i -> i + 1).limit(sortedBins.size())
                .map(i -> makeBinPrefix(i) + GAP + makeBinFormat(sortedBins.get(i - 1)))
                .collect(Collectors.joining("\n\n"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Overview)) {
            return false;
        }

        Overview other = (Overview) obj;
        return getAnalyst().equals(other.getAnalyst());
    }
}
