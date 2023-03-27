package seedu.dengue.logic.analyst;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import seedu.dengue.model.person.Person;

/**
 * An {@code Analyst} that groups {@code Person} instances by {@code Age}.
 */
public class AgeAnalyst extends Analyst {
    private static final int NUM_AGE_GROUPS = 20; // number of age groups, 0-9 to 190-199

    private final int total;
    private final ArrayList<DataBin> bins;

    /**
     * Constructs an {@code AgeAnalyst} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public AgeAnalyst(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.total = persons.size();
        this.bins = new ArrayList<>();

        IntStream.iterate(0, i -> i + 1).limit(NUM_AGE_GROUPS)
                        .forEach(i -> bins.add(new DataBin(
                                String.format("%d - %d",
                                        i * 10, i * 10 + 9))));

        persons.forEach(person -> {
            int decade = Integer.parseInt(person.getAge().value) / 10;
            bins.get(decade).addPerson(person);
        });
    }

    @Override
    public int getTotal() {
        return this.total;
    }

    @Override
    List<DataBin> getBins() {
        return this.bins;
    }
}
