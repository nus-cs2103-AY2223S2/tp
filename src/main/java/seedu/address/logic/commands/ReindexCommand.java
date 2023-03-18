package seedu.address.logic.commands;

import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import seedu.address.model.Model;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

public class ReindexCommand extends Command {

    public static final String COMMAND_WORD = "reindex";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Resets the indices of contacts "
            + "to remove any gaps.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_REINDEX_ACKNOWLEDGEMENT = "Reindexing contacts as requested...";

    @Override
    public CommandResult execute(Model model) {
        List<Person> sortedPersonList = model.getObservablePersonList().stream()
                .sorted(Comparator.comparing(Person::getContactIndex))
                .collect(Collectors.toList());

        int i = 1;
        for (Iterator<Person> personIterator = sortedPersonList.listIterator(); personIterator.hasNext(); i++) {
            Person person = personIterator.next();
            person.setContactIndex(new ContactIndex(i));
        }

        // currently the only way to sort persons in the list
        model.resetPersons();
        sortedPersonList.forEach(model::addPerson);
        return new CommandResult(MESSAGE_REINDEX_ACKNOWLEDGEMENT);
    }

}
