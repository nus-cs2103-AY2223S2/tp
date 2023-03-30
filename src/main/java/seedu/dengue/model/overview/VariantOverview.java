package seedu.dengue.model.overview;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.logic.analyst.VariantAnalyst;
import seedu.dengue.model.person.Person;

/**
 * An {@code Overview} that runs analysis by {@code Variant}.
 *
 * @see VariantAnalyst
 */
public class VariantOverview extends Overview {
    private static final String VARIANT_TITLE = "Overview by Variant";

    private VariantAnalyst analyst;

    /**
     * Constructs a {@code VariantOverview} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public VariantOverview(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new VariantAnalyst(persons);
    }

    /**
     * Constructs a {@code VariantOverview} instance with an empty list of {@code Person}s.
     */
    public VariantOverview() {
        this(new ArrayList<>());
    }

    @Override
    public String getOverviewTitle() {
        return VARIANT_TITLE;
    }

    @Override
    public VariantAnalyst getAnalyst() {
        return this.analyst;
    }

    @Override
    public void update(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.analyst = new VariantAnalyst(persons);
    }
}
