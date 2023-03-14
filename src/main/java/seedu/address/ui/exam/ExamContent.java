//package seedu.address.ui.exam;
//
//import java.util.logging.Logger;
//
//import javafx.collections.ObservableList;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.Region;
//import javafx.scene.layout.StackPane;
//import seedu.address.commons.core.LogsCenter;
//import seedu.address.model.student.Lesson;
//import seedu.address.model.student.Name;
//import seedu.address.model.student.Student;
//import seedu.address.ui.UiPart;
//import seedu.address.ui.homework.HomeworkContent;
//
///**
// * Panel containing the list of Exams.
// */
//public class ExamContent extends UiPart<Region> {
//    private static final Logger logger = LogsCenter.getLogger(HomeworkContent.class);
//
//    private static final String FXML = "LessonsContent.fxml";
//    private final Name studentName;
//
//    @FXML
//    private Label name;
//    @FXML
//    private Label examsListName;
//
//    @FXML
//    private StackPane examsListPlaceholder;
//
//    @FXML
//    private Label upcomingExamsListName;
//
//    @FXML
//    private StackPane upcomingExamsListPlaceholder;
//
//    /**
//     * Creates a {@code HomeworkContent} with the given {@code Student}.
//     */
//    public ExamContent(Student student) {
//        super(FXML);
//        studentName = student.getName();
//
//        name.setText(String.format("Full Name: %s", student.getName().fullName));
//        examsListName.setText("Past Exams: ");
//
//        // Set the past exams list panel to display the past exams of the student
//        ObservableList<Lesson> pastExamsList = student.getPastExamsList();
//        ExamsListPanel pastExamsListPanel = new ExamsListPanel(pastExamsList);
//        examsListPlaceholder.getChildren().add(pastExamsListPanel.getRoot());
//
//        upcomingExamsListName.setText("Upcoming Exams: ");
//
//        // Set the upcoming exams list panel to display the upcoming exams of the student
//        ObservableList<Lesson> upcomingExamsList = student.getUpcomingExamsList();
//        ExamsListPanel upcomingExamsListPanel = new ExamsListPanel(upcomingExamsList);
//        upcomingExamsListPlaceholder.getChildren().add(upcomingExamsListPanel.getRoot());
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        // short circuit if same object
//        if (other == this) {
//            return true;
//        }
//
//        // instanceof handles nulls
//        if (!(other instanceof ExamContent)) {
//            return false;
//        }
//
//        // state check
//        ExamContent card = (ExamContent) other;
//        return studentName.equals(card.studentName);
//    }
//}
