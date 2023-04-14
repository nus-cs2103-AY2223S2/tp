package tfifteenfour.clipboard.logic.commands;

import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.testutil.TypicalModel;

class HomeCommandTest {
    private Model model;
    private CurrentSelection actualSelection;
    private HomeCommand homeCommand;

    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        this.model.getCurrentSelection().setCurrentPage(PageType.SESSION_STUDENT_PAGE);

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.SESSION_STUDENT_PAGE);
    }

    @Test
    public void execute_validHomeCommand_success() {
        homeCommand = new HomeCommand();
        String expectedMessage = HomeCommand.MESSAGE_SUCCESS;
        Model expectedModel = model.copy();

        assertCommandSuccess(homeCommand, model, expectedMessage, expectedModel);
    }


}
