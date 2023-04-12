package seedu.address.testutil;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.score.UniqueScoreList;
import seedu.address.model.student.Name;
import seedu.address.model.student.Student;
import seedu.address.model.task.UniqueTaskList;


/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalMockStudents {

    public static final UniqueTaskList TYPICAL_TASK_LIST = TypicalMockTasks.getTypicalTasks();
    public static final UniqueScoreList TYPICAL_SCORE_LIST = TypicalMockScores.getTypicalScores();
    public static final Student ALICE = mock(Student.class);
    public static final Name ALICE_NAME = spy(new Name("Alice Pauline"));
    public static final Student BENSON = mock(Student.class);
    public static final Name BENSON_NAME = spy(new Name("Benson Meier"));
    public static final Student CARL = mock(Student.class);
    public static final Name CARL_NAME = spy(new Name("Carl Kunz"));
    public static final Student DANIEL = mock(Student.class);
    public static final Name DANIEL_NAME = spy(new Name("Daniel Meier"));

    public static final Student ELLE = mock(Student.class);
    public static final Name ELLE_NAME = spy(new Name("Elle Meyer"));
    public static final Student FIONA = mock(Student.class);
    public static final Name FIONA_NAME = spy(new Name("Fiona Kunz"));
    public static final Student GEORGE = mock(Student.class);
    public static final Name GEORGE_NAME = spy(new Name("George Best"));

    // Manually added
    public static final Student HOON = mock(Student.class);
    public static final Name HOON_NAME = spy(new Name("Hoon Meier"));
    public static final Student IDA = mock(Student.class);
    public static final Name IDA_NAME = spy(new Name("Ida Mueller"));

    // Manually added - Student's details found in {@code CommandTestUtil}

    public static final Student AMY = mock(Student.class);
    public static final Name AMY_NAME = spy(new Name(VALID_NAME_AMY));
    public static final Student BOB = mock(Student.class);
    public static final Name BOB_NAME = spy(new Name(VALID_NAME_BOB));

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalMockStudents() {} // prevents instantiation

    private static void setAlice() {
        assert(ALICE_NAME.fullName.equals("Alice Pauline"));
        doReturn(ALICE_NAME).when(ALICE).getName();
        doReturn(TYPICAL_TASK_LIST).when(ALICE).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(ALICE).getScoreList();
    }

    private static void setBenson() {
        assert(BENSON_NAME.fullName.equals("Benson Meier"));
        doReturn(BENSON_NAME).when(BENSON).getName();
        doReturn(TYPICAL_TASK_LIST).when(BENSON).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(BENSON).getScoreList();
    }

    private static void setCarl() {
        assert(CARL_NAME.fullName.equals("Carl Kunz"));
        doReturn(CARL_NAME).when(CARL).getName();
        doReturn(TYPICAL_TASK_LIST).when(CARL).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(CARL).getScoreList();
    }

    private static void setDaniel() {
        assert(DANIEL_NAME.fullName.equals("Daniel Meier"));
        doReturn(DANIEL_NAME).when(DANIEL).getName();
        doReturn(TYPICAL_TASK_LIST).when(DANIEL).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(DANIEL).getScoreList();
    }

    private static void setElle() {
        assert(ELLE_NAME.fullName.equals("Elle Meyer"));
        doReturn(ELLE_NAME).when(ELLE).getName();
        doReturn(TYPICAL_TASK_LIST).when(ELLE).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(ELLE).getScoreList();
    }

    private static void setFiona() {
        assert(FIONA_NAME.fullName.equals("Fiona Kunz"));
        doReturn(FIONA_NAME).when(FIONA).getName();
        doReturn(TYPICAL_TASK_LIST).when(FIONA).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(FIONA).getScoreList();
    }

    private static void setGeorge() {
        assert(GEORGE_NAME.fullName.equals("George Best"));
        doReturn(GEORGE_NAME).when(GEORGE).getName();
        doReturn(TYPICAL_TASK_LIST).when(GEORGE).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(GEORGE).getScoreList();
    }
    private static void setHoon() {
        assert(HOON_NAME.fullName.equals("Hoon Meier"));
        doReturn(HOON_NAME).when(HOON).getName();
        doReturn(TYPICAL_TASK_LIST).when(HOON).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(HOON).getScoreList();
    }

    private static void setIda() {
        assert(IDA_NAME.fullName.equals("Ida Mueller"));
        doReturn(IDA_NAME).when(IDA).getName();
        doReturn(TYPICAL_TASK_LIST).when(IDA).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(IDA).getScoreList();
    }

    private static void setAmy() {
        assert(AMY_NAME.fullName.equals(VALID_NAME_AMY));
        doReturn(AMY_NAME).when(AMY).getName();
        doReturn(TYPICAL_TASK_LIST).when(AMY).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(AMY).getScoreList();
    }

    private static void setBob() {
        assert(BOB_NAME.fullName.equals(VALID_NAME_BOB));
        doReturn(BOB_NAME).when(BOB).getName();
        doReturn(TYPICAL_TASK_LIST).when(BOB).getTaskList();
        doReturn(TYPICAL_SCORE_LIST).when(BOB).getScoreList();
    }

    public static List<Student> getTypicalMockStudents() {
        setAlice();
        setBenson();
        setCarl();
        setDaniel();
        setElle();
        setFiona();
        setGeorge();
        setHoon();
        setIda();
        setAmy();
        setBob();
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
