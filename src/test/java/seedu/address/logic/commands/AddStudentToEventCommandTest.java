package seedu.address.logic.commands;

import static seedu.address.logic.commands.AddStudentToEventCommand.CONSULTATION_STRING;
import static seedu.address.logic.commands.AddStudentToEventCommand.LAB_STRING;
import static seedu.address.logic.commands.AddStudentToEventCommand.MESSAGE_SUCCESS;
import static seedu.address.logic.commands.AddStudentToEventCommand.TUTORIAL_STRING;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalEvents.SAMPLE_CONSULTATION;
import static seedu.address.testutil.TypicalEvents.SAMPLE_LAB;
import static seedu.address.testutil.TypicalEvents.SAMPLE_TUTORIAL;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.event.Consultation;
import seedu.address.model.event.Lab;
import seedu.address.model.event.Tutorial;

class AddStudentToEventCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        // addressbook with non-empty persons list
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        model.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        model.addLab(new Lab(SAMPLE_LAB.getName()));
        model.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));
    }

    @Test
    void execute_new_person_success() {
        Index first = INDEX_FIRST_PERSON;

        Model modifiedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        modifiedModel.addTutorial(new Tutorial(SAMPLE_TUTORIAL.getName()));
        modifiedModel.addLab(new Lab(SAMPLE_LAB.getName()));
        modifiedModel.addConsultation(new Consultation(SAMPLE_CONSULTATION.getName()));

        modifiedModel.addStudentToTutorial(first, SAMPLE_TUTORIAL.getName());
        assertCommandSuccess(new AddStudentToEventCommand(
                first, SAMPLE_TUTORIAL.getName(), TUTORIAL_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

        modifiedModel.addStudentToLab(first, SAMPLE_LAB.getName());
        assertCommandSuccess(new AddStudentToEventCommand(
                        first, SAMPLE_LAB.getName(), LAB_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

        modifiedModel.addStudentToConsultation(first, SAMPLE_CONSULTATION.getName());
        assertCommandSuccess(new AddStudentToEventCommand(
                        first, SAMPLE_CONSULTATION.getName(), CONSULTATION_STRING),
                model, new CommandResult(MESSAGE_SUCCESS), modifiedModel);

    }
}