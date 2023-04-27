package seedu.sprint.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.sprint.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.sprint.commons.core.GuiSettings;
import seedu.sprint.logic.CommandHistory;
import seedu.sprint.logic.commands.exceptions.CommandException;
import seedu.sprint.model.InternshipBook;
import seedu.sprint.model.Model;
import seedu.sprint.model.ReadOnlyInternshipBook;
import seedu.sprint.model.ReadOnlyUserPrefs;
import seedu.sprint.model.application.Application;
import seedu.sprint.testutil.ApplicationBuilder;

public class AddApplicationCommandTest {
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullApp_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddApplicationCommand(null));
    }

    @Test
    public void execute_appAcceptedByModel_addSuccessful() throws Exception {
        AddApplicationCommandTest.ModelStubAcceptingAppAdded modelStub = new AddApplicationCommandTest
                .ModelStubAcceptingAppAdded();
        Application validApplication = new ApplicationBuilder().build();
        AddApplicationCommand addCommand = new AddApplicationCommand(validApplication);
        commandHistory.addCommand(addCommand.toString());

        CommandResult commandResult = addCommand.execute(modelStub, commandHistory);

        assertEquals(String.format(AddApplicationCommand.MESSAGE_SUCCESS, validApplication), commandResult
                .getFeedbackToUser());
        assertEquals(Arrays.asList(validApplication), modelStub.appsAdded);
    }

    @Test
    public void execute_duplicateApplication_throwsCommandException() {
        Application validApp = new ApplicationBuilder().build();
        AddApplicationCommand addCommand = new AddApplicationCommand(validApp);
        AddApplicationCommandTest.ModelStub modelStub = new AddApplicationCommandTest.ModelStubWithApp(validApp);

        assertThrows(CommandException.class,
                AddApplicationCommand.MESSAGE_DUPLICATE_APPLICATION, (
                ) -> addCommand.execute(modelStub, commandHistory));
    }

    @Test
    public void equals() {
        Application google = new ApplicationBuilder().withCompanyName("Google").build();
        Application hrt = new ApplicationBuilder().withCompanyName("Hudson").build();
        AddApplicationCommand addGoogleCommand = new AddApplicationCommand(google);
        AddApplicationCommand addHudsonCommand = new AddApplicationCommand(hrt);

        // same object -> returns true
        assertTrue(addGoogleCommand.equals(addGoogleCommand));

        // different types -> returns false
        assertFalse(addGoogleCommand.equals(1));

        // null -> returns false
        assertFalse(addGoogleCommand.equals(null));

        // different person -> returns false
        assertFalse(addGoogleCommand.equals(addHudsonCommand));
    }

    /**
     * A default application model stub that have all the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getInternshipBookFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBookFilePath(Path internshipBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addApplication(Application app) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setInternshipBook(ReadOnlyInternshipBook newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyInternshipBook getInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasApplication(Application app) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteApplication(Application app) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setApplication(Application target, Application editedApp) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getFilteredApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredApplicationList(Predicate<Application> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Application> getSortedApplicationList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateSortedApplicationList(Comparator<Application> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean applicationHasTask(Application application) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTaskToApplication(Application target, Application editedApplication) {
            throw new AssertionError("This method should not be called.");
        }
        @Override
        public boolean canUndoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoInternshipBook() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitInternshipBookChange() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single application.
     */
    private class ModelStubWithApp extends AddApplicationCommandTest.ModelStub {
        private final Application app;

        ModelStubWithApp(Application app) {
            requireNonNull(app);
            this.app = app;
        }

        @Override
        public boolean hasApplication(Application app) {
            requireNonNull(app);
            return this.app.equals(app);
        }
    }

    /**
     * A Model stub that always accept the person being added.
     */
    private class ModelStubAcceptingAppAdded extends AddApplicationCommandTest.ModelStub {
        final ArrayList<Application> appsAdded = new ArrayList<>();

        @Override
        public boolean hasApplication(Application app) {
            requireNonNull(app);
            return appsAdded.stream().anyMatch(app::equals);
        }

        @Override
        public void addApplication(Application app) {
            requireNonNull(app);
            appsAdded.add(app);
        }

        @Override
        public ReadOnlyInternshipBook getInternshipBook() {
            return new InternshipBook();
        }

        @Override
        public void commitInternshipBookChange() {}
    }

}
