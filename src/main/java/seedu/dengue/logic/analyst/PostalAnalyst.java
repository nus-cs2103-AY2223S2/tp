package seedu.dengue.logic.analyst;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.variant.DengueVariant;

/**
 * An {@code Analyst} that groups {@code Person} instances by {@code Postal}, i.e. neighbourhood/location.
 */
public class PostalAnalyst extends Analyst {
    private final int total;
    private final EnumMap<DengueVariant, DataBin> bins; // TODO change to Location

    /**
     * Constructs a {@code PostalAnalyst} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public PostalAnalyst(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.total = persons.size();
        this.bins = null; // TODO change to whatever

    }

    @Override
    public int getTotal() {
        return this.total;
    }

    @Override
    List<DataBin> getBins() {
        return new ArrayList<>(this.bins.values());
    }
}
