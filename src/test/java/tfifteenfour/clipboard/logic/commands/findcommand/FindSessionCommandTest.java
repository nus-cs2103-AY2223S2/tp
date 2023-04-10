package tfifteenfour.clipboard.logic.commands.findcommand;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static tfifteenfour.clipboard.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.predicates.SessionNameContainsPredicate;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.testutil.TypicalModel;

class FindSessionCommandTest {
    private Model model;
    private Model expectedModel;
    private Session selectedSession;
    private CurrentSelection actualSelection;
    private SessionNameContainsPredicate predicate;


    @BeforeEach
    public void setUp() {
        this.model = new TypicalModel().getTypicalModel();
        expectedModel = model.copy();
        selectedSession = model.getCurrentSelection().getSelectedSession();

        actualSelection = this.model.getCurrentSelection();
        actualSelection.setCurrentPage(PageType.COURSE_PAGE);
        predicate = new SessionNameContainsPredicate(new String[] {selectedSession.getSessionName()});
    }

    @Test
    public void execute_zeroResultsFound() {
        predicate = new SessionNameContainsPredicate(new String[]{"NonexistentSession"});
        FindSessionCommand command = new FindSessionCommand(predicate, actualSelection);

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredSessions(predicate);
        assertCommandSuccess(command, model, String.format(FindStudentCommand.MESSAGE_SUCCESS, 0), expectedModel);
    }

    @Test
    public void execute_multipleResultsFound() {
        predicate = new SessionNameContainsPredicate(new String[]{"1"});
        FindSessionCommand command = new FindSessionCommand(predicate, actualSelection);

        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getModifiableSessionList()
                .filtered(predicate).size();

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredSessions(predicate);
        assertCommandSuccess(command, model,
                String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void execute_oneResultFound() {
        FindSessionCommand command = new FindSessionCommand(predicate, actualSelection);

        int expectedSize = expectedModel
                .getCurrentSelection()
                .getSelectedGroup()
                .getModifiableSessionList()
                .filtered(predicate).size();

        expectedModel.getCurrentSelection().getSelectedGroup().updateFilteredSessions(predicate);
        assertCommandSuccess(command, model,
                String.format(FindStudentCommand.MESSAGE_SUCCESS, expectedSize), expectedModel);
    }

    @Test
    public void equals() {
        SessionNameContainsPredicate predicate1 = predicate;
        FindSessionCommand findSessionCommand1 = new FindSessionCommand(predicate1, actualSelection);

        SessionNameContainsPredicate predicate2 =
                new SessionNameContainsPredicate(new String[]{"session predicate 2"});
        FindSessionCommand findSessionCommand2 = new FindSessionCommand(predicate2, actualSelection);

        // same object -> returns true
        assertEquals(findSessionCommand1, findSessionCommand1);

        // same values -> returns true
        FindSessionCommand findSessionCommand1Copy = new FindSessionCommand(predicate1, actualSelection);
        assertEquals(findSessionCommand1, findSessionCommand1Copy);

        // different types -> returns false
        assertNotEquals(1, findSessionCommand1);

        // null -> returns false
        assertNotEquals(null, findSessionCommand1);

        // different search term -> returns false
        assertNotEquals(findSessionCommand1, findSessionCommand2);
    }
}
