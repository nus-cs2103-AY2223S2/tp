package seedu.dengue.model.overview;

import java.util.List;
import java.util.stream.Collectors;

import seedu.dengue.logic.analyst.Analyst;
import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.model.person.Person;

/**
 * The API of the Overview list.
 */
public abstract class Overview {
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
        return String.format("Total : %d",
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
    public abstract String makeBinFormat(DataBin bin);

    /**
     * Returns a {@code String} of all the bins recorded in the overview.
     * Meant for display to the user.
     *
     * @return Multi-line string representation of all present bins.
     */
    public String getOverviewContent() {
        return getAnalyst().getSortedBins().stream()
                .map(this::makeBinFormat)
                .collect(Collectors.joining("\n"));
    }
}
