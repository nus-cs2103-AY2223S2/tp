package taa.logic.commands;

import org.junit.jupiter.api.Test;
import taa.logic.commands.enums.ChartType;
import taa.model.ClassList;
import taa.model.Model;
import taa.model.ModelManager;
import taa.model.UserPrefs;
import taa.model.student.Student;
import taa.storage.TaaData;
import taa.testutil.EditPersonDescriptorBuilder;
import taa.testutil.PersonBuilder;
import taa.testutil.TypicalIndexes;
import taa.testutil.TypicalPersons;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ClassStatisticsCommand.
 */
public class ClassStatisticsCommandTest {
    private final Model model = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());
    private final Model emptyClassListModel = new ModelManager(new TaaData(new ClassList()), new UserPrefs());

    @Test
    public void execute_nonEmptyClassList_displayAttendanceChart_success() {
        ClassStatisticsCommand displayAttendanceChartCommand = new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE);

        String expectedMessage = String.format(ClassStatisticsCommand.MESSAGE_SUCCESS, "attendance", "")
                + "\n\n" + ClassStatisticsCommand.SAVE_IMAGE_HINT;

        // make a copy of original model, to ensure no data is changed
        Model testModel = new ModelManager(new TaaData(TypicalPersons.getTypicalTaaData()), new UserPrefs());

        CommandTestUtil.assertCommandSuccess(displayAttendanceChartCommand, testModel, expectedMessage, model);
    }

    @Test
    public void execute_emptyClassList_displayAttendanceChart_failure() {
        ClassStatisticsCommand displayAttendanceChartCommand = new ClassStatisticsCommand(ChartType.CLASS_ATTENDANCE);

        CommandTestUtil.assertCommandFailure(displayAttendanceChartCommand, emptyClassListModel, ClassStatisticsCommand.MESSAGE_EMPTY_CLASSLIST);
    }
}
