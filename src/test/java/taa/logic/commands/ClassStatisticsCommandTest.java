package taa.logic.commands;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import taa.logic.commands.enums.ChartType;
import taa.logic.commands.exceptions.CommandException;
import taa.model.ClassList;
import taa.model.Model;
import taa.model.ModelManager;
import taa.model.UserPrefs;
import taa.model.student.Student;
import taa.storage.TaaData;
import taa.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClassStatisticsCommand.
 */
public class ClassStatisticsCommandTest {
    private final Model model = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());
    private final Model emptyClassListModel = new ModelManager(new TaaData(new ClassList()), new UserPrefs());

    @Test
    public void execute_nonEmptyClassListAttendanceChart_success() {
        ClassStatisticsCommand displayAttendanceChartCommand = new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE);

        String expectedMessage = String.format(ClassStatisticsCommand.MESSAGE_SUCCESS, "attendance", "")
                + "\n\n" + ClassStatisticsCommand.SAVE_IMAGE_HINT;

        // make a copy of original model, to ensure no data is changed
        Model testModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(displayAttendanceChartCommand, testModel, expectedMessage, model);
    }

    @Test
    public void execute_nonEmptyClassListWithGradeVarianceGradesChart_success() {
        ClassStatisticsCommand displayGradesChartCommand = new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test1");

        String expectedMessage = String.format(ClassStatisticsCommand.MESSAGE_SUCCESS, "grades", "(test1)")
            + "\n\n" + ClassStatisticsCommand.SAVE_IMAGE_HINT;

        Model testModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());

        try {
            testModel.addAssignment("test1", 100);
        } catch (Exception e) {
            Assertions.fail("ModelManager::addAssignment failed: " + e.getMessage());
        }

        try {
            testModel.grade("test1", 1, 20, false);
            testModel.grade("test1", 3, 30, true);
            testModel.grade("test1", 5, 50, false);
            testModel.grade("test1", 7, 70, true);
        } catch (Exception e) {
            Assertions.fail("ModelManager::grade failed: " + e.getMessage());
        }

        Model originalModel = new ModelManager(testModel.getTaaData(), testModel.getUserPrefs());
        CommandTestUtil.assertCommandSuccess(displayGradesChartCommand, testModel, expectedMessage, originalModel);

        // cleanup -- delete assignment
        try {
            testModel.deleteAssignment("test1");
        } catch (Exception e) {
            Assertions.fail("ModelManager::deleteAssignment failed: " + e.getMessage());
        }

        //cleanup -- delete submissions
        Student student1 = testModel.getFilteredStudentList().get(0);
        Student student3 = testModel.getFilteredStudentList().get(2);
        Student student5 = testModel.getFilteredStudentList().get(4);
        Student student7 = testModel.getFilteredStudentList().get(6);
        student1.deleteSubmission(student1.getLatestSubmission());
        student3.deleteSubmission(student3.getLatestSubmission());
        student5.deleteSubmission(student5.getLatestSubmission());
        student7.deleteSubmission(student7.getLatestSubmission());
    }

    @Test
    public void execute_emptyClassListAttendanceChart_failure() {
        ClassStatisticsCommand displayAttendanceChartCommand = new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE);

        CommandTestUtil.assertCommandFailure(displayAttendanceChartCommand,
            emptyClassListModel,
            ClassStatisticsCommand.MESSAGE_EMPTY_CLASSLIST);
    }

    @Test
    public void execute_emptyClassListGradesChart_failure() {
        ClassStatisticsCommand displayGradesChartCommand = new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test1");

        CommandTestUtil.assertCommandFailure(displayGradesChartCommand,
            emptyClassListModel,
            ClassStatisticsCommand.MESSAGE_EMPTY_CLASSLIST);
    }

    @Test
    public void execute_nonEmptyClassListWithNoGradeVarianceGradesChart_failure() {
        ClassStatisticsCommand displayGradesChartCommand = new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test1");

        Model testModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());

        try {
            testModel.addAssignment("test2", 100);
        } catch (Exception e) {
            Assertions.fail("ModelManager::addAssignment failed: " + e.getMessage());
        }

        try {
            testModel.grade("test2", 1, 50, false);
            testModel.grade("test2", 3, 50, true);
            testModel.grade("test2", 5, 50, false);
            testModel.grade("test2", 7, 50, true);
        } catch (Exception e) {
            Assertions.fail("ModelManager::grade failed: " + e.getMessage());
        }

        CommandTestUtil.assertCommandFailure(displayGradesChartCommand,
            emptyClassListModel,
            ClassStatisticsCommand.MESSAGE_EMPTY_CLASSLIST);
    }

    @Test
    public void execute_nonEmptyClassListWithInvalidAssignmentNameGradesChart_failure() {
        ClassStatisticsCommand displayGradesChartCommand = new ClassStatisticsCommand(
            ChartType.CLASS_GRADES, "unknown");

        Model testModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());

        Assertions.assertThrows(CommandException.class, () -> displayGradesChartCommand.execute(testModel));
    }

    @Test
    public void equals() {
        ClassStatisticsCommand attendanceCommand = new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE);
        ClassStatisticsCommand test1GradesCommand = new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test1");

        Assertions.assertEquals(attendanceCommand, new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE));
        Assertions.assertNotEquals(attendanceCommand, new ClassStatisticsCommand(ChartType.CLASS_GRADES));

        Assertions.assertEquals(test1GradesCommand, new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test1"));
        Assertions.assertNotEquals(test1GradesCommand, new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE));
        Assertions.assertNotEquals(test1GradesCommand, new ClassStatisticsCommand(ChartType.CLASS_GRADES, "test2"));

        Assertions.assertEquals(attendanceCommand, attendanceCommand);
        Assertions.assertNotEquals(attendanceCommand, "");
    }
}
