package seedu.dengue.model.overview;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.logic.analyst.DataBin;
import seedu.dengue.logic.analyst.VariantAnalyst;
import seedu.dengue.model.person.Person;

/**
 * An {@code Overview} that runs analysis by {@code Variant}.
 *
 * @see VariantAnalyst
 */
public class VariantOverview extends Overview {
    private static final String VARIANT_TITLE = "Overview by Variant";
    private static final int MAX_VARIANT_NAME_LEN = 5; // length of "DENV1", since all the same

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

    @Override
    public String makeBinFormat(DataBin bin) {
        int maxSizeLen = String.valueOf(getAnalyst().getTotal()).length();
        return String.format("%" + MAX_VARIANT_NAME_LEN + "s: %" + maxSizeLen + "d",
                bin.getName(), bin.getSize());
    }
}
