package seedu.address.logic.commands;

import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.PersonBuilder;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.function.Predicate;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

public class GroupCommandTest {

    @Test
    public void equals() {
        Person alice = new PersonBuilder().withName("Alice").build();
        Person bob = new PersonBuilder().withName("Bob").build();
        GroupCommand groupHallCommand = new GroupCommand(new Tag("hall"));
        GroupCommand groupVarsityCommand = new GroupCommand(new Tag("Varsity"));

        // same object -> returns true
        assertTrue(groupHallCommand.equals(groupHallCommand));

        // same values -> returns true
        GroupCommand groupHallCommandCopy = new GroupCommand(new Tag("hall"));
        assertTrue(groupHallCommand.equals(groupHallCommandCopy));

        // different types -> returns false
        assertFalse(groupHallCommand.equals(1));

        // null -> returns false
        assertFalse(groupHallCommand.equals(null));

        // different person -> returns false
        assertFalse(groupHallCommand.equals(groupVarsityCommand));
    }
}
