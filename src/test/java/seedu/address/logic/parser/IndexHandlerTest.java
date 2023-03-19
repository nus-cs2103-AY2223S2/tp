package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.getTypicalEduMate;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ViewCommand;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.ContactIndex;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;

class IndexHandlerTest {

    private static Model model;
    private static IndexHandler indexHandler;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalEduMate(), new UserPrefs());
        indexHandler = new IndexHandler(model);
    }

    @Test
    public void execute_emptyList_assignContactIndex1() {
        model = new ModelManager();
        IndexHandler indexHandler = new IndexHandler(model);
        assertTrue(model.getFilteredPersonList().isEmpty());
        assertEquals(indexHandler.assignIndex(), new ContactIndex(1));
    }

    @Test
    public void execute_missingIndexInBetween_assignLowestPossibleIndex() throws CommandException {
        new DeleteCommand(new ContactIndex(2)).execute(model);
        CommandResult viewResult = new ViewCommand(null, new ContactIndex(2)).execute(model);
        assertEquals("No such person found!", viewResult.getFeedbackToUser());
        IndexHandler indexHandler = new IndexHandler(model);
        assertEquals(indexHandler.assignIndex(), new ContactIndex(2));
    }

    @Test
    public void execute_missingIndexInBetween2_assignLowestPossibleIndex() throws CommandException {
        new DeleteCommand(new ContactIndex(5)).execute(model);
        CommandResult viewResult = new ViewCommand(null, new ContactIndex(5)).execute(model);
        assertEquals("No such person found!", viewResult.getFeedbackToUser());
        IndexHandler indexHandler = new IndexHandler(model);
        assertEquals(indexHandler.assignIndex(), new ContactIndex(5));
    }

    @Test
    public void execute_consecutiveIndexPresent_assignLastIndex() {
        model = new ModelManager(getTypicalEduMate(), new UserPrefs());
        assertEquals(indexHandler.assignIndex(), new ContactIndex(16));
    }

    @Test
    public void execute_consecutiveIndexButRemoveLastAgain_assignLastIndex() throws CommandException {
        model = new ModelManager(getTypicalEduMate(), new UserPrefs());
        new DeleteCommand(new ContactIndex(15)).execute(model);
        indexHandler = new IndexHandler(model);
        assertEquals(indexHandler.assignIndex(), new ContactIndex(15));
    }

    @Test
    public void getByContactIndex_indexOne_retrieveAlbert() {
        Optional<Person> person = indexHandler.getPersonByIndex(new ContactIndex(1));
        assertFalse(person.isEmpty());
        Person albert = person.get();
        assertEquals(albert.getContactIndex(), new ContactIndex(1));
        assertEquals(albert.getName(), new Name("Albert Park"));
    }

    @Test
    public void getByContactIndex_indexTwo_retrieveAngMeiHua() {
        Optional<Person> person = indexHandler.getPersonByIndex(new ContactIndex(2));
        assertFalse(person.isEmpty());
        Person meiHua = person.get();
        assertEquals(meiHua.getContactIndex(), new ContactIndex(2));
        assertEquals(meiHua.getName(), new Name("Ang Mei Hua"));
    }

    @Test
    public void getByContactIndex_indexTen_retrieveIsaacNewton() {
        Optional<Person> person = indexHandler.getPersonByIndex(new ContactIndex(10));
        assertFalse(person.isEmpty());
        Person meiHua = person.get();
        assertEquals(meiHua.getContactIndex(), new ContactIndex(10));
        assertEquals(meiHua.getName(), new Name("Isaac Newton"));
    }

    @Test
    public void getByContactIndex_indexOneHundred_noSuchPerson() {
        Optional<Person> person = indexHandler.getPersonByIndex(new ContactIndex(100));
        assertFalse(person.isPresent());
    }
}
