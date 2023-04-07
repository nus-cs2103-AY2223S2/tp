package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Elister;
import seedu.address.model.ReadOnlyElister;
import seedu.address.model.person.Person;

/**
 * An Immutable Elister that is serializable to CSV format.
 */
class CsvSerializableElister {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    public static final String CSV_HEADERS = "Name,Phone,Email,Address,Income,Tags\n";

    private final List<CsvAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code CsvSerializableElister} with the given persons.
     */
    public CsvSerializableElister(List<List<String>> listOfTokens) throws DataConversionException {
        for (List<String> tokens: listOfTokens) {
            persons.add(new CsvAdaptedPerson(tokens));
        }
    }

    /**
     * Converts a given {@code ReadOnlyElister} into this class for Csv use.
     *
     * @param source future changes to this will not affect the created {@code CsvSerializableElister}.
     */
    public CsvSerializableElister(ReadOnlyElister source) {
        persons.addAll(source.getPersonList().stream().map(CsvAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts a given {@code ObservableList} into this class for Csv use.
     *
     * @param source future changes to this will not affect the created {@code CsvSerializableElister}.
     */
    public CsvSerializableElister(ObservableList<Person> source) {
        persons.addAll(source.stream().map(CsvAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this E-Lister into the model's {@code Elister} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Elister toModelType() throws IllegalValueException {
        Elister elister = new Elister();
        for (CsvAdaptedPerson csvAdaptedPerson : persons) {
            Person person = csvAdaptedPerson.toModelType();
            if (elister.hasPerson(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            elister.addPerson(person);
        }
        return elister;
    }

    @Override
    public String toString() {
        return CSV_HEADERS + String.join("\n", persons.stream()
                .<String>map(person -> person.toString()).collect(Collectors.toList()));
    }

}
