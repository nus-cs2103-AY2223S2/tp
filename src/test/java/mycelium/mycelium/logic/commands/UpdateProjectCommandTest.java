package mycelium.mycelium.logic.commands;

import static mycelium.mycelium.testutil.Assert.assertThrows;
import static mycelium.mycelium.testutil.TypicalEntities.BARD;
import static mycelium.mycelium.testutil.TypicalEntities.BING;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import mycelium.mycelium.commons.core.Messages;
import mycelium.mycelium.logic.commands.exceptions.CommandException;
import mycelium.mycelium.model.AddressBook;
import mycelium.mycelium.model.Model;
import mycelium.mycelium.model.ModelManager;
import mycelium.mycelium.model.UserPrefs;
import mycelium.mycelium.model.project.Project;
import mycelium.mycelium.model.util.NonEmptyString;
import mycelium.mycelium.testutil.ProjectBuilder;
import mycelium.mycelium.testutil.UpdateProjectDescriptorBuilder;

public class UpdateProjectCommandTest {
    private static final UpdateProjectCommand.UpdateProjectDescriptor EMPTY_DESC =
        new UpdateProjectCommand.UpdateProjectDescriptor();
    // The two sample inputs below change "foobar" to "barfoo"
    private static final NonEmptyString SAMPLE_NAME = new NonEmptyString("foobar");
    private static final NonEmptyString SAMPLE_NEW_NAME = new NonEmptyString("barfoo");

    private static final UpdateProjectCommand.UpdateProjectDescriptor SAMPLE_DESC =
        new UpdateProjectDescriptorBuilder()
            .withName(SAMPLE_NEW_NAME)
            .build();

    // Use an empty address book here
    private Model model = new ModelManager(new AddressBook(), new UserPrefs());


    @Test
    public void execute_nullModel_throwsNullPointerException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        assertThrows(NullPointerException.class, () -> cmd.execute(null));
    }

    @Test
    public void execute_projectNotExist_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        assertThrows(CommandException.class, Messages.MESSAGE_INVALID_PROJECT, () -> cmd.execute(model));
    }

    @Test
    public void execute_duplicateName_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        // Add both projects in
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());
        model.addProject(new ProjectBuilder().withName(SAMPLE_NEW_NAME).build());
        assertThrows(CommandException.class, UpdateProjectCommand.MESSAGE_DUPLICATE_PROJECT, () -> cmd.execute(model));
    }

    @Test
    public void execute_noChanges_throwsCommandException() {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, EMPTY_DESC);
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());
        assertThrows(CommandException.class, UpdateProjectCommand.MESSAGE_NOT_UPDATED, () -> cmd.execute(model));
    }

    @Test
    public void execute_validArgs_success() throws CommandException {
        var cmd = new UpdateProjectCommand(SAMPLE_NAME, SAMPLE_DESC);
        model.addProject(new ProjectBuilder().withName(SAMPLE_NAME).build());
        cmd.execute(model);

        // Check that the project has been updated
        assertFalse(model.hasProject(new ProjectBuilder().withName(SAMPLE_NAME).build()));
        assertTrue(model.hasProject(new ProjectBuilder().withName(SAMPLE_NEW_NAME).build()));
    }

    @Test
    public void execute_changeMultipleFields_success() throws CommandException {
        // Now we'll insert BING, change it to BARD, then assert that we have BARD and no BING
        var cmd = new UpdateProjectCommand(BING.getName(), new UpdateProjectDescriptorBuilder(BARD).build());
        model.addProject(BING);
        cmd.execute(model);

        assertFalse(model.hasProject(BING));
        assertTrue(model.hasProject(BARD));
    }

    @Test
    public void execute_keepNameChangeField_success() throws CommandException {
        var newDeadline = "30/12/1970";
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withDeadline(newDeadline).build());
        model.addProject(BING);
        cmd.execute(model);

        var got = model.getUniqueProject(p -> p.isSame(BING));
        // Check that the name remains the same
        assertTrue(got.isPresent());
        assertEquals(got.get().getName(), BING.getName());
        // Check that the deadline has changed
        assertEquals(got.get().getDeadline().get(), LocalDate.parse(newDeadline, Project.DATE_FMT));
    }

    @Test
    public void execute_changeNameOnly_success() throws CommandException {
        var newName = "billabong";
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withName(newName).build());
        model.addProject(BING);
        cmd.execute(model);

        var got = model.getUniqueProject(p -> p.getName().toString().equals(newName));
        assertTrue(got.isPresent());

        // Check that the old project name is gone
        var got2 = model.getUniqueProject(p -> p.isSame(BING));
        assertFalse(got2.isPresent());
    }

    @Test
    public void execute_nameRemainsTheSame_throwsCommandException() {
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder().withName(BING.getName()).build());
        model.addProject(BING);
        assertThrows(CommandException.class, UpdateProjectCommand.MESSAGE_NOT_UPDATED, () -> cmd.execute(model));
    }

    @Test
    public void execute_allFieldsRemainTheSame_throwsCommandException() {
        var cmd = new UpdateProjectCommand(BING.getName(), new UpdateProjectDescriptorBuilder(BING).build());
        model.addProject(BING);
        assertThrows(CommandException.class, UpdateProjectCommand.MESSAGE_NOT_UPDATED, () -> cmd.execute(model));
    }

    @Test
    public void execute_allFieldsSameExceptName_success() throws CommandException {
        var cmd = new UpdateProjectCommand(BING.getName(),
            new UpdateProjectDescriptorBuilder(BARD).withName(BARD.getName()).build());
        model.addProject(BING);
        cmd.execute(model);

        assertFalse(model.hasProject(BING)); // BING should not be in the model
        assertTrue(model.hasProject(BARD)); // BARD should be in the model
    }
}
