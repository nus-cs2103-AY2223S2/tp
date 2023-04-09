package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessDiffModel;
import static seedu.address.logic.commands.DeleteStudentFromEventCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.DeleteStudentFromEventCommand.TUTORIAL_STRING;
import static seedu.address.logic.commands.DeleteStudentFromEventCommand.LAB_STRING;
import static seedu.address.logic.commands.DeleteStudentFromEventCommand.CONSULTATION_STRING;
import static seedu.address.testutil.TypicalEvents.SAMPLE_CONSULTATION;
import static seedu.address.testutil.TypicalEvents.SAMPLE_LAB;
import static seedu.address.testutil.TypicalEvents.SAMPLE_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

class DeleteStudentFromEventCommandTest {
    private static final String WRONG_TYPE = "Leb";
    private static final int ARBITRARILY_LARGE_INTEGER = 99999999;
    private static final int ZEROTH_INDEX = 0;
    private Model model;
    private Model modifiedModel;
    private Index first = INDEX_FIRST_PERSON;
    private Index second = INDEX_SECOND_PERSON;

    @BeforeEach
    public void setUp() {
        // addressbook with non-empty persons list
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        model.addLab(new Lab(SAMPLE_LAB.getName()));
        model.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));
        modifiedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modifiedModel.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        modifiedModel.addLab(new Lab(SAMPLE_LAB.getName()));
        modifiedModel.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));
    }

    @Test
    void execute_tutorial_success() throws CommandException {
        modifiedModel.addStudentToTutorial(first, first);
        assertCommandSuccessDiffModel(new DeleteStudentFromEventCommand(first, first, TUTORIAL_STRING), modifiedModel,
                new CommandResult(MESSAGE_SUCCESS), model);
        assert(modifiedModel.getFilteredTutorialList().get(ZEROTH_INDEX).countStudents() == ZEROTH_INDEX);
    }

    @Test
    void execute_lab_success() throws CommandException {
        modifiedModel.addStudentToLab(first, first);
        assertCommandSuccessDiffModel(new DeleteStudentFromEventCommand(first, first, LAB_STRING), modifiedModel,
                new CommandResult(MESSAGE_SUCCESS), model);
        assert(modifiedModel.getFilteredLabList().get(ZEROTH_INDEX).countStudents() == ZEROTH_INDEX);
    }

    @Test
    void execute_consultation_success() throws CommandException {
        modifiedModel.addStudentToConsultation(first, first);
        assertCommandSuccessDiffModel(new DeleteStudentFromEventCommand(first, first, CONSULTATION_STRING), modifiedModel,
                new CommandResult(MESSAGE_SUCCESS), model);
        assert(modifiedModel.getFilteredConsultationList().get(ZEROTH_INDEX).countStudents() == ZEROTH_INDEX);
    }

    @Test
    void execute_studentIndexTooBig_throwsCommandException() throws CommandException{
        modifiedModel.addStudentToTutorial(first, first);
        modifiedModel.addStudentToLab(first, first);
        modifiedModel.addStudentToConsultation(first, first);
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        first, TUTORIAL_STRING).execute(modifiedModel));
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        first, LAB_STRING).execute(modifiedModel));
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                first, CONSULTATION_STRING).execute(modifiedModel));
    }

    @Test
    void execute_eventIndexTooBig_throwsCommandException() throws CommandException{
        modifiedModel.addStudentToTutorial(first, first);
        modifiedModel.addStudentToLab(first, first);
        modifiedModel.addStudentToConsultation(first, first);
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(first, Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        TUTORIAL_STRING).execute(modifiedModel));
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(first, Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        LAB_STRING).execute(modifiedModel));
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(first, Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        CONSULTATION_STRING).execute(modifiedModel));
    }

    @Test
    void execute_wrongEventType_throwsCommandException() throws CommandException{
        modifiedModel.addStudentToLab(first, first);
        assertThrows(CommandException.class, () ->
                new DeleteStudentFromEventCommand(first, Index.fromOneBased(ARBITRARILY_LARGE_INTEGER),
                        WRONG_TYPE).execute(modifiedModel));
    }

    @Test
    void execute() {
    }
}
