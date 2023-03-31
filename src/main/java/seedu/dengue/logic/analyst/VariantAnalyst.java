package seedu.dengue.logic.analyst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import seedu.dengue.model.person.Person;
import seedu.dengue.model.variant.DengueVariant;

/**
 * An {@code Analyst} that groups {@code Person} instances by {@code Variant}.
 * Note that the same {@code Person} may be counted under multiple groups.
 */
public class VariantAnalyst extends Analyst {
    private final int total;
    private final EnumMap<DengueVariant, DataBin> bins;

    /**
     * Constructs a {@code VariantAnalyst} instance with the given list of {@code Person}s.
     *
     * @param personList The list of {Person} instances to analyze.
     */
    public VariantAnalyst(List<Person> personList) {
        List<Person> persons = new ArrayList<>(personList);
        this.total = persons.size();
        this.bins = new EnumMap<>(DengueVariant.class);

        Arrays.stream(DengueVariant.values())
                .forEach(variantName -> bins.put(variantName, new DataBin(variantName.toString())));

        persons.forEach(person -> person.getVariants()
                .forEach(variant -> {
                    DengueVariant variantName = variant.variantName;
                    bins.get(variantName).addPerson(person);
                }));
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
