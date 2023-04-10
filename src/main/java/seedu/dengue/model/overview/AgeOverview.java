package seedu.dengue.model.overview;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.logic.analyst.AgeAnalyst;
import seedu.dengue.model.person.Person;

/**
 * An {@code Overview} that runs analysis by {@code Age}.
 *
 * @see AgeAnalyst
 */
public class AgeOverview extends Overview {
    private static final String AGE_TITLE = "Overview by Age";

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
}
