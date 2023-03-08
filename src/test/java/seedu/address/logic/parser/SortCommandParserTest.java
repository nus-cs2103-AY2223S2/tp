package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SortCommand;
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

        // address
        testSinglePrefix(String.format(" %s", Prefix.ADDRESS.getPrefix()),
                Comparator.comparing(Person::getAddress), "Address: Ascending");
        testSinglePrefix(String.format(" %sa", Prefix.ADDRESS.getPrefix()),
                Comparator.comparing(Person::getAddress), "Address: Ascending");
        testSinglePrefix(String.format(" %sd", Prefix.ADDRESS.getPrefix()),
                Comparator.comparing(Person::getAddress), "Address: Descending");

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

    /**
     * Tests whether the single prefix parses as expected.
     */
    private void testSinglePrefix(String prefixWithIsAscending, Comparator<Person> comparator, String description) {
        assertParseSuccess(parser, prefixWithIsAscending,
                new SortCommand(comparator, description));
    }
}
