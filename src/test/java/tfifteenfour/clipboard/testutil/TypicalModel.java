package tfifteenfour.clipboard.testutil;

import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.model.Model;
import tfifteenfour.clipboard.model.ModelManager;
import tfifteenfour.clipboard.model.Roster;
import tfifteenfour.clipboard.model.UserPrefs;
import tfifteenfour.clipboard.model.util.SampleDataUtil;

/**
 * A utility class containing a list of {@code Student} objects to be used in tests.
 */
public class TypicalModel {
        private static Path sampleFilePath = Paths.get("data", "sampleRoster.json");
        private static InputStream sampleResourceStream = TypicalModel.class.getClass().getResourceAsStream("/assets/sampleRoster.json");
        private Model typicalModel;


        public TypicalModel() {
                Roster typicalRoster = getTypicalRoster();
                CurrentSelection typicalCurrentSelection = new CurrentSelection();

                typicalCurrentSelection.selectCourse(getTypicalRoster().getUnmodifiableCourseList().get(0));
                typicalCurrentSelection.selectGroup(typicalCurrentSelection.getSelectedCourse().getUnmodifiableGroupList().get(0));
                typicalCurrentSelection.selectStudent(typicalCurrentSelection.getSelectedGroup().getUnmodifiableFilteredStudentList().get(0));
                typicalCurrentSelection.selectSession(typicalCurrentSelection.getSelectedGroup().getUnmodifiableFilteredSessionList().get(0));
                typicalCurrentSelection.selectTask(typicalCurrentSelection.getSelectedGroup().getUnmodifiableTaskList().get(0));

                this.typicalModel = new ModelManager(typicalRoster, new UserPrefs(),typicalCurrentSelection);
        }

        private static Roster getTypicalRoster() {
                return new Roster(SampleDataUtil.getSampleRoster(sampleFilePath, sampleResourceStream));
        }

        public Model getTypicalModel() {
                return this.typicalModel;
        }



}
