package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddStudentToEventCommand.CONSULTATION_STRING;
import static seedu.address.logic.commands.AddStudentToEventCommand.LAB_STRING;
import static seedu.address.logic.commands.AddStudentToEventCommand.MESSAGE_EVENT_TYPE_NOT_RECOGNIZED;
import static seedu.address.logic.commands.AddStudentToEventCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.AddStudentToEventCommand.MESSAGE_USAGE;
import static seedu.address.logic.commands.AddStudentToEventCommand.TUTORIAL_STRING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccessDiffModel;
import static seedu.address.testutil.TypicalEvents.SAMPLE_CONSULTATION;
import static seedu.address.testutil.TypicalEvents.SAMPLE_LAB;
import static seedu.address.testutil.TypicalEvents.SAMPLE_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

class AddStudentToEventCommandTest {
    private static final String WRONG_TYPE = "tutorial";
    private Model model;
    private Index first = INDEX_FIRST_PERSON;
    private Index second = INDEX_SECOND_PERSON;

    @BeforeEach
    public void setUp() {
        // addressbook with non-empty persons list
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        model.addLab(new Lab(SAMPLE_LAB.getName()));
        model.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));
    }

    @Test
    void execute_addStudent_success() throws ParseException {
        Model modifiedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modifiedModel.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        modifiedModel.addLab(new Lab(SAMPLE_LAB.getName()));
        modifiedModel.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));

        Model supposedlySameModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        supposedlySameModel.addStudentToTutorial(first, first);

        modifiedModel.addStudentToTutorial(first, first);
        assertCommandSuccessDiffModel(new AddStudentToEventCommand(
                second, first, TUTORIAL_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

        modifiedModel.addStudentToLab(first, first);
        assertCommandSuccessDiffModel(new AddStudentToEventCommand(
                        second, first, LAB_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

        modifiedModel.addStudentToConsultation(first, first);
        assertCommandSuccessDiffModel(new AddStudentToEventCommand(
                        second, first, CONSULTATION_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

    }

    @Test
    void execute_eventTypeNotRecognized_throwsCommandException() {
        assertCommandFailure(new AddStudentToEventCommand(first, first, WRONG_TYPE),
                model, MESSAGE_EVENT_TYPE_NOT_RECOGNIZED  + MESSAGE_USAGE);
    }
}
