package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.model.util.TypicalPersons.VALID_ADDRESS_AMY;
import static seedu.address.model.util.TypicalPersons.VALID_ADDRESS_BOB;
import static seedu.address.model.util.TypicalPersons.VALID_EMAIL_AMY;
import static seedu.address.model.util.TypicalPersons.VALID_EMAIL_BOB;
import static seedu.address.model.util.TypicalPersons.VALID_NAME_AMY;
import static seedu.address.model.util.TypicalPersons.VALID_NAME_BOB;
import static seedu.address.model.util.TypicalPersons.VALID_PHONE_AMY;
import static seedu.address.model.util.TypicalPersons.VALID_PHONE_BOB;
import static seedu.address.model.util.TypicalPersons.VALID_TAG_FRIEND;
import static seedu.address.model.util.TypicalPersons.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.OfficeConnectModel;
import seedu.address.model.RepositoryModelManager;
import seedu.address.model.mapping.AssignTask;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.EditPersonDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel, new OfficeConnectModel());
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            System.out.println(ce.getMessage());
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     * - the {@code actualOfficeConnectModel} matches {@code expectedOfficeConnectModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
                                            Model expectedModel, OfficeConnectModel actualOfficeConnectModel,
                                            OfficeConnectModel expectedOfficeConnectModel) {
        try {
            CommandResult result = command.execute(actualModel, actualOfficeConnectModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            RepositoryModelManager<Task> actualModelTaskModelManager =
                    actualOfficeConnectModel.getTaskModelManager();
            RepositoryModelManager<Task> expectedModelTaskModelManager =
                    expectedOfficeConnectModel.getTaskModelManager();
            assertEquals(actualModelTaskModelManager, expectedModelTaskModelManager);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult,
     *                                                      Model, OfficeConnectModel, OfficeConnectModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
                                            Model expectedModel, OfficeConnectModel actualOfficeConnectModel,
                                            OfficeConnectModel expectedOfficeConnectModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel,
                actualOfficeConnectModel, expectedOfficeConnectModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} for assignTasks
     */
    public static void assertAssignTaskCommandSuccess(Command command, OfficeConnectModel actualModel,
                                                      CommandResult expectedCommandResult,
                                                      OfficeConnectModel expectedModel) {
        try {
            CommandResult result = command.execute(new ModelManager(), actualModel);
            assertEquals(expectedCommandResult, result);
            RepositoryModelManager<AssignTask> actualModelAssignTaskModelManager =
                    actualModel.getAssignTaskModelManager();
            RepositoryModelManager<AssignTask> expectedModelAssignTaskModelManager =
                    expectedModel.getAssignTaskModelManager();
            assertEquals(actualModelAssignTaskModelManager, expectedModelAssignTaskModelManager);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} for assignTasks
     */
    public static void assertAssignTaskCommandSuccess(Command command, Model model, OfficeConnectModel actualModel,
                                                      CommandResult expectedCommandResult,
                                                      OfficeConnectModel expectedModel) {
        try {
            CommandResult result = command.execute(model, actualModel);
            assertEquals(expectedCommandResult, result);
            RepositoryModelManager<AssignTask> actualModelAssignTaskModelManager =
                    actualModel.getAssignTaskModelManager();
            RepositoryModelManager<AssignTask> expectedModelAssignTaskModelManager =
                    expectedModel.getAssignTaskModelManager();
            assertEquals(actualModelAssignTaskModelManager, expectedModelAssignTaskModelManager);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {#assertAssignTaskCommandSuccess(Command, OfficeConnectModel, String, OfficeConnectModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertAssignTaskCommandSuccess(Command command, OfficeConnectModel actualModel,
                                                      String expectedMessage, OfficeConnectModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertAssignTaskCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Convenience wrapper to {#assertAssignTaskCommandSuccess(Command, Model, OfficeConnectModel, String,
     * OfficeConnectModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertAssignTaskCommandSuccess(Command command, Model model, OfficeConnectModel actualModel,
                                                      String expectedMessage, OfficeConnectModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertAssignTaskCommandSuccess(command, model, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} for tasks
     */
    public static void assertTaskCommandSuccess(Command command, OfficeConnectModel actualModel,
                                                CommandResult expectedCommandResult, OfficeConnectModel expectedModel) {
        try {
            CommandResult result = command.execute(new ModelManager(), actualModel);
            assertEquals(expectedCommandResult, result);
            RepositoryModelManager<Task> actualModelTaskModelManager = actualModel.getTaskModelManager();
            RepositoryModelManager<Task> expectedModelTaskModelManager = expectedModel.getTaskModelManager();
            assertEquals(actualModelTaskModelManager, expectedModelTaskModelManager);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }


    /**
     * Convenience wrapper to {#assertTaskCommandSuccess(Command, OfficeConnectModel, String, OfficeConnectModel)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertTaskCommandSuccess(Command command, OfficeConnectModel actualModel, String expectedMessage,
                                            OfficeConnectModel expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertTaskCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered person list and selected person in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Person> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPersonList());

        assertThrows(CommandException.class, expectedMessage, () ->
                command.execute(actualModel, new OfficeConnectModel()));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredPersonList());
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the repository, filtered person list and selected task in {@code actualModel} remain unchanged
     */
    public static void assertTaskCommandFailure(Command command,
                                                OfficeConnectModel actualModel, String expectedMessage) {
        // we are unable to defensively copy the OfficeConnectModel for comparison later, so we can
        // only do so by copying its components. copy repository model manager as filtered item list
        // must be same
        RepositoryModelManager<Task> expectedRepositoryModelManager = actualModel.getTaskModelManager();
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getTaskModelManager().getFilteredItemList());
        assertThrows(CommandException.class, expectedMessage, () ->
                command.execute(new ModelManager(), actualModel));
        assertEquals(expectedRepositoryModelManager, actualModel.getTaskModelManager());
        assertEquals(expectedFilteredList, actualModel.getTaskModelManager().getFilteredItemList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the person at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showPersonAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPersonList().size());

        Person person = model.getFilteredPersonList().get(targetIndex.getZeroBased());
        final String[] splitName = person.getName().fullName.split("\\s+");
        model.updateFilteredPersonList(new NameContainsKeywordsPredicate(Collections.singletonList(splitName[0])));

        assertEquals(1, model.getFilteredPersonList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s repository.
     */
    public static void showTaskAtIndex(OfficeConnectModel model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getTaskModelManager().getFilteredItemList().size());

        Task task = model.getTaskModelManagerFilteredItemList().get(targetIndex.getZeroBased());
        String taskSubject = task.getTitle().getValue();
        model.updateTaskModelManagerFilteredItemList(x -> x.getTitle().getValue().equals(taskSubject));
        assertEquals(1, model.getTaskModelManagerFilteredItemList().size());

    }

}
