package seedu.address.logic.parser;

import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.IntStream;

import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

/**
 * Assigns a valid contact index for a newly-added contact to EduMate.
 */
public class IndexHandler {
    private Model model;

    public IndexHandler(Model model) {
        this.model = model;
    }

    /**
     * Assigns a contact index to a new contact.
     * @return new Contact Index for new contact.
     */
    public ContactIndex assignIndex() {
        List<Person> personList = model.getObservablePersonList();
        if (personList.isEmpty()) {
            return new ContactIndex(1);
        }
        OptionalInt takenIndices = IntStream.iterate(1, x -> x + 1)
                .takeWhile(integer -> personList.stream()
                        .anyMatch(person -> person.getContactIndex().equals(new ContactIndex(integer)))).max();
        Integer availableIndex = takenIndices.getAsInt() + 1;
        return new ContactIndex(availableIndex);
    }

    public Optional<Person> getPersonByIndex(ContactIndex index) {
        List<Person> personList = model.getObservablePersonList();
        return personList.stream().filter(person -> person.getContactIndex().equals(index)).findFirst();
    }

}
