package seedu.dengue.model.overview;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
     * Returns a {@code String} of all the bins recorded in the overview.
     * Meant for display to the user.
     *
     * @return Multi-line string representation of all present bins.
     */
    public ObservableList<DataBin> getOverviewContent() {
        ObservableList<DataBin> tempBin = FXCollections.observableArrayList();
        tempBin.setAll(getAnalyst().getSortedBins());
        return tempBin;
    }

    /**
     * Enumerates the elements of a List of String arrays. Each element of the resulting
     * array will contain the original index, followed by the elements of the original array.
     * The first element of each row will start at 1.
     *
     * @param inputList the List of String arrays to enumerate
     * @return a List of String arrays with each element enumerated
     */
    public static List<String[]> enumList(List<String[]> inputList) {
        AtomicInteger index = new AtomicInteger(1);
        return inputList.stream()
                .map(array -> {
                    String[] newArray = new String[array.length + 1];
                    newArray[0] = String.valueOf(index.getAndIncrement());
                    System.arraycopy(array, 0, newArray, 1, array.length);
                    return newArray;
                })
                .collect(Collectors.toList());
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

    /**
     * Returns an array of the string headers for CSV storage
     * @return A String[] representing the Headers for Overview class formatted for CSV storage.
     */
    public static String[] getHeaders() {
        return new String[]{"id", "binName", "binSize"};
    }
}
