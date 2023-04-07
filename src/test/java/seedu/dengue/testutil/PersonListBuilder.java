package seedu.dengue.testutil;

import java.util.ArrayList;
import java.util.List;

import seedu.dengue.model.person.Person;

/**
 * A utility class to help with building Lists of Person objects.
 */
public class PersonListBuilder {
    private final PersonBuilder personBuilder;
    private final List<Person> personList;

    private PersonListBuilder(Person defaultPerson) {
        this.personBuilder = new PersonBuilder(defaultPerson);
        this.personList = new ArrayList<>();
    }

    private PersonListBuilder(Person defaultPerson, List<Person> personList) {
        this.personBuilder = new PersonBuilder(defaultPerson);
        this.personList = new ArrayList<>(personList);
    }

    /**
     * Creates a {@code PersonListBuilder} with an empty list.
     */
    public PersonListBuilder() {
        this.personBuilder = new PersonBuilder();
        this.personList = new ArrayList<>();
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds using the given default {@code Person} instance.
     * Building this immediately will only return a singleton containing this default person.
     *
     * @param defaultPerson The new base person to build the list from.
     * @return A new list builder.
     */
    public PersonListBuilder withDefaultPerson(Person defaultPerson) {
        return new PersonListBuilder(defaultPerson);
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds a {@code List} of {@code Person} instances with
     * names following the list provided, but are otherwise identical to the default {@code Person}.
     *
     * @param names The desired names of the people in the output list.
     * @return A new list builder.
     */
    public PersonListBuilder withNames(List<String> names) {
        List<Person> personList = new ArrayList<>();
        Person defaultPerson = this.personBuilder.build();

        names.forEach(name -> personList.add(this.personBuilder.withName(name).build()));

        return new PersonListBuilder(defaultPerson, personList);
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds a {@code List} of {@code Person} instances with
     * ages following the list provided, but are otherwise identical to the default {@code Person}.
     *
     * @param ages The desired ages of the people in the output list.
     * @return A new list builder.
     */
    public PersonListBuilder withAges(List<String> ages) {
        List<Person> personList = new ArrayList<>();
        Person defaultPerson = this.personBuilder.build();

        ages.forEach(age -> personList.add(this.personBuilder.withAge(age).build()));

        return new PersonListBuilder(defaultPerson, personList);
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds a {@code List} of {@code Person} instances with
     * postal codes following the list provided, but are otherwise identical to the default {@code Person}.
     *
     * @param postals The desired postals of the people in the output list.
     * @return A new list builder.
     */
    public PersonListBuilder withPostals(List<String> postals) {
        List<Person> personList = new ArrayList<>();
        Person defaultPerson = this.personBuilder.build();

        postals.forEach(postal -> personList.add(this.personBuilder.withPostal(postal).build()));

        return new PersonListBuilder(defaultPerson, personList);
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds a {@code List} of {@code Person} instances with
     * case dates following the list provided, but are otherwise identical to the default {@code Person}.
     *
     * @param dates The desired dates of the cases in the output list.
     * @return A new list builder.
     */
    public PersonListBuilder withDates(List<String> dates) {
        List<Person> personList = new ArrayList<>();
        Person defaultPerson = this.personBuilder.build();

        dates.forEach(date -> personList.add(this.personBuilder.withDate(date).build()));

        return new PersonListBuilder(defaultPerson, personList);
    }

    /**
     * Returns a new {@code PersonListBuilder} which builds a {@code List} of {@code Person} instances with
     * dengue variants following the list provided, but are otherwise identical to the default {@code Person}.
     *
     * @param variants The desired list of dengue variants (possibly empty) contracted by the people in the output list.
     * @return A new list builder.
     */
    public PersonListBuilder withVariants(List<String[]> variants) {
        List<Person> personList = new ArrayList<>();
        Person defaultPerson = this.personBuilder.build();

        variants.forEach(variantArr -> personList.add(this.personBuilder.withVariants(variantArr).build()));

        return new PersonListBuilder(defaultPerson, personList);
    }

    /**
     * Returns a {@code List} of {@code Person} instances, according to the list of people stored.
     *
     * @return The desired list of people.
     */
    public List<Person> build() {
        return new ArrayList<>(this.personList);
    }

    /**
     * Returns a {@code List} containing only one default {@code Person} instance.
     *
     * @return A singleton containing the default person.
     */
    public List<Person> buildSingle() {
        return List.of(this.personBuilder.build());
    }

    /**
     * Returns a {@code List} containing no {@code Person} instances.
     *
     * @return An empty list.
     */
    public List<Person> buildEmpty() {
        return new ArrayList<>();
    }
}
