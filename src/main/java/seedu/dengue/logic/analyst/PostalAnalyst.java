package seedu.dengue.logic.analyst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import seedu.dengue.model.person.Location;
import seedu.dengue.model.person.Person;
import seedu.dengue.model.person.PostalLocationMapping;

/**
 * An {@code Analyst} that groups {@code Person} instances by {@code Postal}, i.e. neighbourhood/location.
 */
public class PostalAnalyst extends Analyst {
    private final int total;
    private final EnumMap<Location, DataBin> bins;

    /**
     * Constructs a {@code PostalAnalyst} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public PostalAnalyst(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.total = persons.size();
        this.bins = new EnumMap<>(Location.class);

        Arrays.stream(Location.values())
                .forEach(location -> bins.put(location, new DataBin(location.toString())));

        persons.forEach(person -> {
            Location location = PostalLocationMapping.getLocation(person.getPostal());
            bins.get(location).addPerson(person);
        });
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
