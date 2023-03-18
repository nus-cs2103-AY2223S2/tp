package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Person;

/**
 * Contains integration tests (interaction with the Model) for {@code SortCommand}.
 */
public class SortCommandTest {
    private static final Comparator<Person> NAME_COMPARATOR =
            Comparator.comparing(Person::getName);
    private static final Comparator<Person> ADDRESS_COMPARATOR =
            Comparator.comparing(Person::getAddress);
    private static final Comparator<Person> TELEGRAM_HANDLE_COMPARATOR =
            Comparator.comparing(Person::getTelegramHandle);
    private static final Comparator<Person> PHONE_COMPARATOR =
            Comparator.comparing(Person::getPhone);
    private static final Comparator<Person> EMAIL_COMPARATOR =
            Comparator.comparing(Person::getEmail);
    private static final Comparator<Person> GROUP_TAGS_COMPARATOR =
            Comparator.comparing(Person::getGroupTags);
    private static final Comparator<Person> MODULE_TAGS_COMPARATOR =
            Comparator.comparing(Person::getModuleTags);
    private static final SortCommand SORT_COMMAND =
            new SortCommand(NAME_COMPARATOR, "Name: Ascending");
    private final Model model = new ModelManager(getTypicalEduMate(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalEduMate(), new UserPrefs());

    @Test
    public void equals_sameObject_true() {
        assertEquals(SORT_COMMAND, SORT_COMMAND);
    }

    @Test
    public void equals_sameFields_true() {
        final SortCommand otherSortCommand =
                new SortCommand(NAME_COMPARATOR, "Name: Ascending");
        assertEquals(SORT_COMMAND, otherSortCommand);
    }

    @Test
    public void execute_sortField_correctOrder() {
        List<Comparator<Person>> comparators = Arrays.asList(
                NAME_COMPARATOR,
                ADDRESS_COMPARATOR,
                TELEGRAM_HANDLE_COMPARATOR,
                PHONE_COMPARATOR,
                EMAIL_COMPARATOR,
                GROUP_TAGS_COMPARATOR,
                MODULE_TAGS_COMPARATOR
        );

        comparators.forEach(this::testComparator);
    }

    private void testComparator(Comparator<Person> comparator) {
        SortCommand sortCommand = new SortCommand(comparator, "");
        sortCommand.execute(model);
        List<Person> personList = model.getObservablePersonList();

        for (int i = 0; i < personList.size() - 1; i++) {
            assertTrue(comparator.compare(personList.get(i), personList.get(i + 1)) <= 0);
        }
    }
}
