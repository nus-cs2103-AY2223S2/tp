package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.address.logic.commands.CommandTestUtil.DESC_AMY;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.FieldContainsPartialKeywordsPredicate;
import seedu.address.model.person.Name;
import seedu.address.model.person.PersonDescriptor;
import seedu.address.model.tag.Tag;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RemarkCommand.
 */
public class FilterCommandTest {

    private final String expectedSuccessMessage = FilterCommand.MESSAGE_FILTER_SUCCESS;
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        final FilterCommand standardCommand = new FilterCommand(DESC_AMY);

        // same values -> returns true
        PersonDescriptor copyDescriptor = new PersonDescriptor(DESC_AMY);
        FilterCommand commandWithSameValues = new FilterCommand(DESC_AMY);
        assertEquals(standardCommand, commandWithSameValues);

        // same object -> returns true
        assertEquals(standardCommand, standardCommand);

        // null -> returns false
        assertNotEquals(null, standardCommand);

        // different types -> returns false
        assertNotEquals(standardCommand, new ClearCommand());

        // different descriptor -> returns false
        assertNotEquals(standardCommand, new FilterCommand(DESC_BOB));
    }

    @Test
    public void execute_zeroKeywords_noPersonFiltered() {
        PersonDescriptor emptyDescriptor = new PersonDescriptor();
        FilterCommand command = new FilterCommand(emptyDescriptor);

        assertCommandSuccess(command, model, expectedSuccessMessage, expectedModel);
        assertEquals(expectedModel.getFilteredPersonList(), model.getFilteredPersonList());
    }

    @Test
    public void execute_singleTag_multiplePersonsFound() {
        PersonDescriptor personDescriptor = new PersonDescriptor();
        personDescriptor.setTags(Set.of(new Tag("friend"))); // intentional 's' missing
        Predicate predicate = preparePredicate(personDescriptor);

        FilterCommand command = new FilterCommand(personDescriptor);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, DANIEL), model.getFilteredPersonList());
    }

    @Test
    public void execute_multipleKeywords_multiplePersonsFound() {
        PersonDescriptor personDescriptor = new PersonDescriptor();
        personDescriptor.setName(new Name("l"));
        personDescriptor.setAddress(new Address("street"));
        personDescriptor.setEmail(new Email("a@example.com"));
        Predicate predicate = preparePredicate(personDescriptor);

        FilterCommand command = new FilterCommand(personDescriptor);
        expectedModel.updateFilteredPersonList(predicate);
        assertCommandSuccess(command, model, expectedSuccessMessage, expectedModel);
        assertEquals(Arrays.asList(DANIEL), model.getFilteredPersonList());
    }

    /**
     * Parses {@code PersonDescriptor} into a {@code FieldContainsPartialKeywordsPredicate}.
     */
    private FieldContainsPartialKeywordsPredicate preparePredicate(PersonDescriptor personDescriptor) {
        String name = personDescriptor.getName().map(n -> n.toString()).orElse("");
        String phone = personDescriptor.getPhone().map(p -> p.toString()).orElse("");;
        String email = personDescriptor.getEmail().map(e -> e.toString()).orElse("");;
        String address = personDescriptor.getAddress().map(a -> a.toString()).orElse("");;
        List<String> tags = personDescriptor.getTags().orElse(Collections.emptySet())
                .stream().map(tag -> tag.tagName).collect(Collectors.toList());
        return new FieldContainsPartialKeywordsPredicate(name, phone, email, address, tags);
    }
}
