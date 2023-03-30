package seedu.dengue.logic.analyst;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The API of the data analyzer.
 */
public abstract class Analyst {
    /**
     * Returns the total number of {@code Person} data points.
     *
     * @return The total number of data points.
     */
    public abstract int getTotal();

    /**
     * Returns a list of all the {@code DataBin} instances involved.
     *
     * @return The list of relevant data bins.
     */
    abstract List<DataBin> getBins();

    /**
     * Returns a list of non-empty {@code DataBin} instances involved.
     * This does not modify the original list.
     *
     * @return The list of non-empty data bins.
     */
    private List<DataBin> cullEmptyBins() {
        return getBins().stream()
                .filter(bin -> !bin.isEmpty())
                .collect(Collectors.toList());
    }

    /**
     * Returns a list of non-empty {@code DataBin} instances involved,
     * in descending order.
     * This does not modify the original list.
     *
     * @return A list of non-empty data bins, in descending order.
     */
    public List<DataBin> getSortedBins() {
        return cullEmptyBins().stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Analyst)) {
            return false;
        }

        Analyst other = (Analyst) obj;
        return getBins().equals(other.getBins())
                && getTotal() == other.getTotal();
    }
}
