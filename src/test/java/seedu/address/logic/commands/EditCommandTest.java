package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.Test;
import seedu.address.commons.core.GuiSettings;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.logic.parser.UiSwitchMode;
import seedu.address.model.Model;
import seedu.address.model.ReadOnlyReroll;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.Reroll;
import seedu.address.model.entity.Character;
import seedu.address.model.entity.Classification;
import seedu.address.model.entity.Entity;
import seedu.address.model.entity.Name;
import seedu.address.testutil.EntityBuilder;

public class EditCommandTest {

    @Test
    public void execute_edit_success() throws CommandException {
        Entity testEntity = new EntityBuilder().buildChar();
        Model model = new ModelStubWithTestEntity(testEntity);
        Model expectedModel = new ModelStubWithTestEntity(testEntity);

        CommandResult expectedCommandResult = new CommandResult(EditCommand.MESSAGE_SUCCESS,
                false, false, UiSwitchMode.VIEW);

        expectedModel.setCurrentSelectedEntity(testEntity);
        CommandResult commandResult = new EditCommand("char", testEntity.getName().toString()).execute(model);

        assertEquals(expectedCommandResult, commandResult);
        assertEquals(model.getCurrentSelectedEntity(), expectedModel.getCurrentSelectedEntity());
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
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
        public Path getRerollFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setRerollFilePath(Path addressBookFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<Entity> getClassificationPredicate(Classification classification) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyReroll getReroll() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setReroll(ReadOnlyReroll newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasEntity(Entity entity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntities(List<Entity> entities) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteEntity(Entity target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setEntity(Entity target, Entity editedEntity) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<Entity> getSnapshotEntities(Predicate<? super Entity> predicate) {
            return null;
        }

        @Override
        public ObservableList<Entity> getFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredEntityList(Predicate<Entity> predicate) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Predicate<? super Entity> getCurrentPredicate() {
            throw new AssertionError("This method should not be called.");
        }

        /**
         * Adds the predicate of the filtered entity list to filter by the given {@code predicate}.
         *
         * @param predicate
         * @throws NullPointerException if {@code predicate} is null.
         */
        @Override
        public void addPredicate(Predicate<Entity> predicate) {

        }

        @Override
        public void resetFilteredEntityList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listItems() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listCharacters() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void listMobs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Entity getCurrentSelectedEntity() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setCurrentSelectedEntity(Entity newSelection) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Entity> getListByClassification(Classification classification) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Character createFromTemplate(Name entityName, Name templateName) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public List<String> getTemplates() {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single person.
     */
    private class ModelStubWithTestEntity extends ModelStub {

        private final Reroll reroll;
        private Entity currentSelectedEntity;

        ModelStubWithTestEntity(Entity testEntity) {
            reroll = new Reroll();
            reroll.addEntity(testEntity);
        }

        @Override
        public ReadOnlyReroll getReroll() {
            return reroll;
        }

        @Override
        public void setCurrentSelectedEntity(Entity newSelection) {
            currentSelectedEntity = newSelection;
        }

        @Override
        public Entity getCurrentSelectedEntity() {
            return currentSelectedEntity;
        }
    }
}
