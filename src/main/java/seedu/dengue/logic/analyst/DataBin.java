package seedu.dengue.logic.analyst;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;

import seedu.dengue.model.person.Person;

/**
 * Simulates the bins of a histogram.
 * This is meant to streamline the process of bulk data analysis.
 *
 * @see Analyst
 */
public class DataBin implements Comparable<DataBin> {
    private final String binName;
    private int binSize;

    /**
     * Constructs a {@code DataBin} with the given name.
     *
     * @param binName Name of the data bin.
     */
    public DataBin(String binName) {
        this.binName = binName;
        this.binSize = 0;
    }

    /**
     * Adds the {@code person} to the data bin.
     */
    public void addPerson(Person person) {
        requireNonNull(person);
        this.binSize += 1;
    }

    /**
     * Returns the name of the data bin.
     *
     * @return The name of the data bin.
     */
    public String getName() {
        return this.binName;
    }

    /**
     * Returns the size of the data bin.
     *
     * @return The size of the data bin.
     */
    public int getSize() {
        return this.binSize;
    }

    /**
     * Returns {@code true} if there is no {@code Person} counted in the data bin.
     *
     * @return {@code true} if the data bin has is empty.
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof DataBin)) {
            return false;
        }

        return compareTo((DataBin) other) == 0;
    }

    @Override
    public int compareTo(DataBin other) {
        return getSize() - other.getSize();
    }

    @Override
    public String toString() {
        return String.format("Bin '%s': %s",
                getName(), getSize());
    }

    /**
     * Returns an array of Strings representing the fields of this DataBin object formatted as a CSV string.
     * The string array is generated in the following order: binName, binSize.
     * @return A String[] representing the fields of this Person formatted as a CSV string
     */
    public String[] toCsvString() {
        ArrayList<String> result = new ArrayList<>();
        result.add(this.binName);
        result.add(String.valueOf(this.binSize));
        String[] csvString = new String[2];
        return result.toArray(csvString);
    }
}
