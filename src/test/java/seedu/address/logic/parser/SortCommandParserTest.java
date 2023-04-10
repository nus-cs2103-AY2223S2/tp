package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.EduMateHistory;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Person;

public class SortCommandParserTest {
    private final SortCommandParser parser = new SortCommandParser();

    @Test
    public void parse_singlePrefix_success() {
        // name
        testSinglePrefix(String.format(" %s", Prefix.NAME.getPrefix()),
                Comparator.comparing(Person::getName), "Name: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.NAME.getPrefix()),
                Comparator.comparing(Person::getName), "Name: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.NAME.getPrefix()),
                Comparator.comparing(Person::getName), "Name: Descending");

        // phone
        testSinglePrefix(String.format(" %s", Prefix.PHONE.getPrefix()),
                Comparator.comparing(Person::getPhone), "Phone: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.PHONE.getPrefix()),
                Comparator.comparing(Person::getPhone), "Phone: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.PHONE.getPrefix()),
                Comparator.comparing(Person::getPhone), "Phone: Descending");

        // email
        testSinglePrefix(String.format(" %s", Prefix.EMAIL.getPrefix()),
                Comparator.comparing(Person::getEmail), "Email: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.EMAIL.getPrefix()),
                Comparator.comparing(Person::getEmail), "Email: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.EMAIL.getPrefix()),
                Comparator.comparing(Person::getEmail), "Email: Descending");

        // telegram handle
        testSinglePrefix(String.format(" %s", Prefix.TELEGRAM_HANDLE.getPrefix()),
                Comparator.comparing(Person::getTelegramHandle), "Telegram Handle: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.TELEGRAM_HANDLE.getPrefix()),
                Comparator.comparing(Person::getTelegramHandle), "Telegram Handle: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.TELEGRAM_HANDLE.getPrefix()),
                Comparator.comparing(Person::getTelegramHandle), "Telegram Handle: Descending");

        // station
        testSinglePrefix(String.format(" %s", Prefix.STATION.getPrefix()),
                Comparator.comparing(Person::getStation), "Station: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.STATION.getPrefix()),
                Comparator.comparing(Person::getStation), "Station: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.STATION.getPrefix()),
                Comparator.comparing(Person::getStation), "Station: Descending");

        // group tags
        testSinglePrefix(String.format(" %s", Prefix.GROUP_TAG.getPrefix()),
                Comparator.comparing(Person::getGroupTags), "Group Tags: Descending");
        testSinglePrefix(String.format(" %sa", Prefix.GROUP_TAG.getPrefix()),
                Comparator.comparing(Person::getGroupTags), "Group Tags: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.GROUP_TAG.getPrefix()),
                Comparator.comparing(Person::getGroupTags), "Group Tags: Descending");

        // module tags
        testSinglePrefix(String.format(" %s", Prefix.MODULE_TAG.getPrefix()),
                Comparator.comparing(Person::getModuleTags), "Module Tags: Descending");
        testSinglePrefix(String.format(" %sa", Prefix.MODULE_TAG.getPrefix()),
                Comparator.comparing(Person::getModuleTags), "Module Tags: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.MODULE_TAG.getPrefix()),
                Comparator.comparing(Person::getModuleTags), "Module Tags: Descending");
    }

    @Test
    public void execute_sortIndexCommand_success() throws ParseException {
        Model model = new ModelManager(getTypicalEduMate(), new UserPrefs(), new EduMateHistory());
        SortCommandParser parser = new SortCommandParser();
        List<ContactIndex> sortedIndexSorted = model.getObservablePersonList().stream()
                .map(x -> x.getContactIndex()).collect(Collectors.toUnmodifiableList());
        parser.parse(String.format(" %s", Prefix.NAME.getPrefix()))
                .execute(model);
        List<Person> personList = model.getObservablePersonList();
        List<ContactIndex> contactIndexSortedByName = personList.stream()
                .map(x -> x.getContactIndex()).collect(Collectors.toUnmodifiableList());
        assertNotEquals(contactIndexSortedByName.toArray(), sortedIndexSorted.toArray());
        parser.parse("").execute(model);
        List<ContactIndex> indexStream = personList.stream()
                .map(x -> x.getContactIndex()).collect(Collectors.toUnmodifiableList());
        assertArrayEquals(sortedIndexSorted.toArray(), indexStream.toArray());
    }

    /**
     * Tests whether the single prefix parses as expected.
     */
    private void testSinglePrefix(String prefixWithIsAscending, Comparator<Person> comparator, String description) {
        assertParseSuccess(parser, prefixWithIsAscending,
                new SortCommand(comparator, description + "\nIndex: Ascending"));
    }
}
