package tfifteenfour.clipboard.testutil;

import java.nio.file.Path;
import java.nio.file.Paths;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.ReadOnlyRoster;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.UserPrefs;
import tfifteenfour.clipboard.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalModel {

    private Model typicalModel;


    /**
     * Creates a {@code TypicalModel} using sample data.
     */
    public TypicalModel() {
        Path sampleFilePath = Paths.get("src", "test", "data", "sampleRoster.json");
        ReadOnlyRoster sampleRoster = SampleDataUtil.getSampleRoster(sampleFilePath, null);
        Model initModel = new ModelManager(sampleRoster, new UserPrefs());
        Roster typicalRoster = initModel.getRoster();

        CurrentSelection typicalCurrentSelection = new CurrentSelection();

        typicalCurrentSelection.selectCourse(typicalRoster.getUnmodifiableCourseList().get(0));
        typicalCurrentSelection.selectGroup(typicalCurrentSelection.getSelectedCourse()
                .getUnmodifiableGroupList().get(0));
        typicalCurrentSelection.selectStudent(typicalCurrentSelection.getSelectedGroup()
                .getUnmodifiableFilteredStudentList().get(0));
        typicalCurrentSelection.selectSession(typicalCurrentSelection.getSelectedGroup()
                .getUnmodifiableFilteredSessionList().get(0));
        typicalCurrentSelection.selectTask(typicalCurrentSelection.getSelectedGroup()
                .getUnmodifiableTaskList().get(0));

        this.typicalModel = new ModelManager(typicalRoster, new UserPrefs(), typicalCurrentSelection);
    }

    public Model getTypicalModel() {
        return this.typicalModel;
    }


}
