package tfifteenfour.clipboard.ui;

import java.util.ArrayList;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextInputControl;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import tfifteenfour.clipboard.commons.core.GuiSettings;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.logic.CurrentSelection;
import tfifteenfour.clipboard.logic.Logic;
import tfifteenfour.clipboard.logic.PageType;
import tfifteenfour.clipboard.logic.commands.BackCommand;
import tfifteenfour.clipboard.logic.commands.ClearCommand;
import tfifteenfour.clipboard.logic.commands.Command;
import tfifteenfour.clipboard.logic.commands.CommandResult;
import tfifteenfour.clipboard.logic.commands.ExitCommand;
import tfifteenfour.clipboard.logic.commands.HelpCommand;
import tfifteenfour.clipboard.logic.commands.HomeCommand;
import tfifteenfour.clipboard.logic.commands.SelectCommand;
import tfifteenfour.clipboard.logic.commands.UndoCommand;
import tfifteenfour.clipboard.logic.commands.UploadCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.AttendanceCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkAbsentCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.MarkPresentCommand;
import tfifteenfour.clipboard.logic.commands.attendancecommand.SessionCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditStudentCommand;
import tfifteenfour.clipboard.logic.commands.exceptions.CommandException;
import tfifteenfour.clipboard.logic.commands.studentcommands.RemarkCommand;
import tfifteenfour.clipboard.logic.commands.taskcommand.AssignCommand;
import tfifteenfour.clipboard.logic.commands.taskcommand.TaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.SessionWithAttendance;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.task.Task;
import tfifteenfour.clipboard.ui.attendancepage.AttendanceListPanel;
import tfifteenfour.clipboard.ui.coursepage.CourseListPanel;
import tfifteenfour.clipboard.ui.gradespage.GradeListPanel;
import tfifteenfour.clipboard.ui.grouppage.GroupListPanel;
import tfifteenfour.clipboard.ui.pagetab.ActiveCourseTab;
import tfifteenfour.clipboard.ui.pagetab.ActiveGroupTab;
import tfifteenfour.clipboard.ui.pagetab.ActiveSessionTab;
import tfifteenfour.clipboard.ui.pagetab.ActiveStudentTab;
import tfifteenfour.clipboard.ui.pagetab.ActiveTaskTab;
import tfifteenfour.clipboard.ui.pagetab.InactiveCourseTab;
import tfifteenfour.clipboard.ui.pagetab.InactiveGroupTab;
import tfifteenfour.clipboard.ui.pagetab.InactiveSessionTab;
import tfifteenfour.clipboard.ui.pagetab.InactiveStudentTab;
import tfifteenfour.clipboard.ui.pagetab.InactiveTaskTab;
import tfifteenfour.clipboard.ui.sessionpage.SessionListPanel;
import tfifteenfour.clipboard.ui.studentspage.StudentListPanel;
import tfifteenfour.clipboard.ui.studentspage.StudentViewCardWithAttendance;
import tfifteenfour.clipboard.ui.taskpage.TaskListPanel;

/**
 * The Main Window. Provides the basic application layout containing
 * a menu bar and space where other JavaFX elements can be placed.
 */
public class MainWindow extends UiPart<Stage> {

    private static final String FXML = "MainWindow.fxml";
    private static Image clippySuccess;
    private static Image clippyFailure;
    private static ArrayList<HelpWindow> helpWindows = new ArrayList<>();

    private final Logger logger = LogsCenter.getLogger(getClass());

    private Stage primaryStage;
    private Logic logic;

    // Independent Ui parts residing in this Ui container
    private CourseListPanel courseListPanel;
    private ResultDisplay resultDisplay;

    @FXML
    private StackPane commandBoxPlaceholder;

    @FXML
    private MenuItem helpMenuItem;

    @FXML
    private StackPane leftPanelPlaceholder;

    @FXML
    private StackPane resultDisplayPlaceholder;

    @FXML
    private StackPane statusbarPlaceholder;

    @FXML
    private HBox studentPanelPlaceholder;

    @FXML
    private StackPane rightPanelPlaceholder;

    @FXML
    private VBox courseTabPlaceholder;

    @FXML
    private VBox groupTabPlaceholder;

    @FXML
    private VBox studentTabPlaceholder;

    @FXML
    private VBox sessionTabPlaceholder;

    @FXML
    private VBox taskTabPlaceholder;

    @FXML
    private HBox navigationBarPlaceholder;

    @FXML
    private ImageView logoPlaceholder;


    /**
     * Creates a {@code MainWindow} with the given {@code Stage} and {@code Logic}.
     */
    public MainWindow(Stage primaryStage, Logic logic) {
        super(FXML, primaryStage);

        // Set dependencies
        this.primaryStage = primaryStage;
        this.logic = logic;

        // Configure the UI
        setWindowDefaultSize(logic.getGuiSettings());

        setAccelerators();

        initClippy();

    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }

    private void setAccelerators() {
        setAccelerator(helpMenuItem, KeyCombination.valueOf("F1"));
    }

    /**
     * Sets the accelerator of a MenuItem.
     * @param keyCombination the KeyCombination value of the accelerator
     */
    private void setAccelerator(MenuItem menuItem, KeyCombination keyCombination) {
        menuItem.setAccelerator(keyCombination);

        /*
         * TODO: the code below can be removed once the bug reported here
         * https://bugs.openjdk.java.net/browse/JDK-8131666
         * is fixed in later version of SDK.
         *
         * According to the bug report, TextInputControl (TextField, TextArea) will
         * consume function-key events. Because CommandBox contains a TextField, and
         * ResultDisplay contains a TextArea, thus some accelerators (e.g F1) will
         * not work when the focus is in them because the key event is consumed by
         * the TextInputControl(s).
         *
         * For now, we add following event filter to capture such key events and open
         * help window purposely so to support accelerators even when focus is
         * in CommandBox or ResultDisplay.
         */
        getRoot().addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getTarget() instanceof TextInputControl && keyCombination.match(event)) {
                menuItem.getOnAction().handle(new ActionEvent());
                event.consume();
            }
        });
    }

    private void initClippy() {
        clippySuccess = new Image(this.getClass().getResourceAsStream("/images/CommandSuccess.GIF"));

        clippyFailure = new Image(this.getClass().getResourceAsStream("/images/CommandFail.GIF"));
    }

    /**
     * Fills up all the placeholders of this window.
     */
    void fillInnerParts() {
        courseListPanel = new CourseListPanel(logic.getRoster().getUnmodifiableFilteredCourseList());
        leftPanelPlaceholder.getChildren().add(courseListPanel.getRoot());

        resultDisplay = new ResultDisplay();
        resultDisplayPlaceholder.getChildren().add(resultDisplay.getRoot());

        StatusBarFooter statusBarFooter = new StatusBarFooter(logic.getRosterFilePath());
        statusbarPlaceholder.getChildren().add(statusBarFooter.getRoot());

        CommandBox commandBox = new CommandBox(this::executeCommand);
        commandBoxPlaceholder.getChildren().add(commandBox.getRoot());

        initializePageTabs();
    }

    /**
     * Initializes page tabs.
     */
    private void initializePageTabs() {
        courseTabPlaceholder.getChildren().add(new ActiveCourseTab().getRoot());
        groupTabPlaceholder.getChildren().add(new InactiveGroupTab().getRoot());
        studentTabPlaceholder.getChildren().add(new InactiveStudentTab().getRoot());
        sessionTabPlaceholder.getChildren().add(new InactiveSessionTab().getRoot());
        taskTabPlaceholder.getChildren().add(new InactiveTaskTab().getRoot());
    }

    /**
     * Sets the default size based on {@code guiSettings}.
     */
    private void setWindowDefaultSize(GuiSettings guiSettings) {
        primaryStage.setHeight(guiSettings.getWindowHeight());
        primaryStage.setWidth(guiSettings.getWindowWidth());
        if (guiSettings.getWindowCoordinates() != null) {
            primaryStage.setX(guiSettings.getWindowCoordinates().getX());
            primaryStage.setY(guiSettings.getWindowCoordinates().getY());
        }
    }

    void show() {
        primaryStage.show();
    }

    /**
     * Opens the help window with help specific to the current page the user
     * is currently on.
     */
    @FXML
    public void handleHelp() {
        String message = "";
        PageType currentPage = logic.getModel().getCurrentSelection().getCurrentPage();

        switch (currentPage) {
        case COURSE_PAGE:
            message = HelpWindowMessages.COURSE_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case GROUP_PAGE:
            message = HelpWindowMessages.GROUP_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case STUDENT_PAGE:
            message = HelpWindowMessages.STUDENT_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case SESSION_PAGE:
            message = HelpWindowMessages.SESSION_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case TASK_PAGE:
            message = HelpWindowMessages.TASK_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case SESSION_STUDENT_PAGE:
            message = HelpWindowMessages.ATTENDANCE_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        case TASK_STUDENT_PAGE:
            message = HelpWindowMessages.GRADES_HELP_MESSAGE + HelpWindowMessages.GENERAL_HELP_MESSAGE;
            break;
        default:
            break;
        }

        if (!helpWindows.isEmpty()) {
            HelpWindow prevHelpWindow = helpWindows.remove(0);
            prevHelpWindow.hide();
        }
        HelpWindow currHelpWindow = new HelpWindow(message);
        helpWindows.add(currHelpWindow);
        currHelpWindow.show();
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        GuiSettings guiSettings = new GuiSettings(primaryStage.getWidth(), primaryStage.getHeight(),
                (int) primaryStage.getX(), (int) primaryStage.getY());
        logic.setGuiSettings(guiSettings);
        if (!helpWindows.isEmpty()) {
            helpWindows.get(0).hide();
        }
        primaryStage.hide();
    }

    /**
     * Navigates GUI back to main course page.
     */
    private void handleHome() {
        showCoursePane();
        showModuleTab();
        closeViewPane();
        closeGroupTab();
        closeStudentTab();
        closeSessionTab();
        closeTaskTab();
        closeNavigationBar();
    }

    private void refreshNavigationBar() {
        NavigationBar navigationBar = new NavigationBar(logic);
        navigationBarPlaceholder.getChildren().clear();
        navigationBarPlaceholder.getChildren().add(navigationBar.getRoot());
    }

    private void closeNavigationBar() {
        navigationBarPlaceholder.getChildren().clear();
    }

    /**
     * Displays currently viewed student in right pane.
     */
    public void refreshViewPane() {
        rightPanelPlaceholder.getChildren().clear();
        ObservableList<Student> viewedStudent =
                logic.getModel().getCurrentSelection().getSelectedGroup().getUnmodifiableFilteredStudentList()
                        .filtered(student -> student.isSameStudent(logic.getModel()
                        .getCurrentSelection().getSelectedStudent()));
        ObservableList<SessionWithAttendance> sessionList =
                logic.getModel().getCurrentSelection().getSelectedStudent().getObservableSessionList();
        if (viewedStudent.size() != 0) {
            rightPanelPlaceholder.getChildren()
                    .add(new StudentViewCardWithAttendance(viewedStudent.get(0),
                            sessionList, 0).getRoot());
        }
    }

    /**
     * Closes viewed student
     */
    public void closeViewPane() {
        rightPanelPlaceholder.getChildren().clear();
        logic.getModel().getCurrentSelection().emptySelectedStudent();
    }



    /**
     * Shows course pane.
     */
    private void showCoursePane() {
        courseListPanel = new CourseListPanel(logic.getRoster().getUnmodifiableFilteredCourseList());
        leftPanelPlaceholder.getChildren().add(courseListPanel.getRoot());
    }

    /**
     * Show group pane.
     * @param course that groups belong to.
     */
    private void showGroupPane(Course course) {
        GroupListPanel groupListPanel = new GroupListPanel(course.getUnmodifiableFilteredGroupList());
        leftPanelPlaceholder.getChildren().add(groupListPanel.getRoot());
    }

    /**
     * Show student pane.
     * @param group that students belong to.
     */
    private void showStudentPane(Group group) {
        StudentListPanel studentListPanel = new StudentListPanel(group.getUnmodifiableFilteredStudentList());
        leftPanelPlaceholder.getChildren().add(studentListPanel.getRoot());
    }

    private void showSessionPane(Group group) {
        SessionListPanel sessionListPanel = new SessionListPanel(group.getUnmodifiableFilteredSessionList());
        leftPanelPlaceholder.getChildren().add(sessionListPanel.getRoot());
    }

    private void showTaskPane(Group group) {
        TaskListPanel taskListPanel = new TaskListPanel(group.getUnmodifiableFilteredTaskList());
        leftPanelPlaceholder.getChildren().add(taskListPanel.getRoot());
    }

    private void showAttendancePane(Session session) {
        AttendanceListPanel attendanceListPanel = new AttendanceListPanel(session.getUnmodifiableStudentList());
        rightPanelPlaceholder.getChildren().add(attendanceListPanel.getRoot());
    }

    private void showGradePane(Task task) {
        GradeListPanel gradeListPanel = new GradeListPanel(task.getUnmodifiableStudentList());
        rightPanelPlaceholder.getChildren().add(gradeListPanel.getRoot());
    }

    private void showModuleTab() {
        courseTabPlaceholder.getChildren().clear();
        courseTabPlaceholder.getChildren().add(new ActiveCourseTab().getRoot());
    }

    private void showGroupTab() {
        groupTabPlaceholder.getChildren().clear();
        groupTabPlaceholder.getChildren().add(new ActiveGroupTab().getRoot());
    }

    private void showStudentTab() {
        studentTabPlaceholder.getChildren().clear();
        studentTabPlaceholder.getChildren().add(new ActiveStudentTab().getRoot());
    }

    private void showSessionTab() {
        sessionTabPlaceholder.getChildren().clear();
        sessionTabPlaceholder.getChildren().add(new ActiveSessionTab().getRoot());
    }

    private void showTaskTab() {
        taskTabPlaceholder.getChildren().clear();
        taskTabPlaceholder.getChildren().add(new ActiveTaskTab().getRoot());
    }

    private void closeModuleTab() {
        courseTabPlaceholder.getChildren().clear();
        courseTabPlaceholder.getChildren().add(new InactiveCourseTab().getRoot());
    }

    private void closeGroupTab() {
        groupTabPlaceholder.getChildren().clear();
        groupTabPlaceholder.getChildren().add(new InactiveGroupTab().getRoot());
    }

    private void closeStudentTab() {
        studentTabPlaceholder.getChildren().clear();
        studentTabPlaceholder.getChildren().add(new InactiveStudentTab().getRoot());
    }

    private void closeSessionTab() {
        sessionTabPlaceholder.getChildren().clear();
        sessionTabPlaceholder.getChildren().add(new InactiveSessionTab().getRoot());
    }


    private void closeTaskTab() {
        taskTabPlaceholder.getChildren().clear();
        taskTabPlaceholder.getChildren().add(new InactiveTaskTab().getRoot());
    }

    /**
     * Handles UI for select command.
     */
    private void handleSelectCommand() {

        PageType currentPage = logic.getModel().getCurrentSelection().getCurrentPage();

        switch (currentPage) {
        case COURSE_PAGE:
            showCoursePane();
            showModuleTab();
            closeGroupTab();
            refreshNavigationBar();
            break;
        case GROUP_PAGE:
            showGroupPane(logic.getModel().getCurrentSelection().getSelectedCourse());
            closeModuleTab();
            showGroupTab();
            refreshNavigationBar();
            break;
        case STUDENT_PAGE:
            if (logic.getModel().getCurrentSelection().getSelectedStudent()
                    .equals(CurrentSelection.NON_EXISTENT_STUDENT)) {
                showStudentPane(logic.getModel().getCurrentSelection().getSelectedGroup());
                showStudentTab();
                refreshNavigationBar();
            } else {
                showStudentPane(logic.getModel().getCurrentSelection().getSelectedGroup());
                refreshViewPane();
            }
            break;
        case SESSION_STUDENT_PAGE:
            logic.getModel().getCurrentSelection().getSelectedSession().selectSession();
            showSessionPane(logic.getModel().getCurrentSelection().getSelectedGroup());
            showAttendancePane(logic.getModel().getCurrentSelection().getSelectedSession());
            refreshNavigationBar();
            break;
        case TASK_STUDENT_PAGE:
            logic.getModel().getCurrentSelection().getSelectedTask().selectTask();
            showTaskPane(logic.getModel().getCurrentSelection().getSelectedGroup());
            showGradePane(logic.getModel().getCurrentSelection().getSelectedTask());
            refreshNavigationBar();
            break;
        default:
            break;
        }
    }

    /**
     * Handles UI for back command.
     */
    private void handleBackCommand() {
        CurrentSelection currentSelection = logic.getModel().getCurrentSelection();
        PageType currentPage = currentSelection.getCurrentPage();

        switch (currentPage) {
        case COURSE_PAGE:
            showCoursePane();
            showModuleTab();
            closeGroupTab();
            refreshNavigationBar();
            break;
        case GROUP_PAGE:
            showGroupPane(currentSelection.getSelectedCourse());
            showGroupTab();
            closeViewPane();
            closeStudentTab();
            closeSessionTab();
            closeTaskTab();
            refreshNavigationBar();
            break;
        case STUDENT_PAGE:
            if (currentSelection.getSelectedStudent().equals(CurrentSelection.NON_EXISTENT_STUDENT)) {
                closeViewPane();
            } else {
                showStudentPane(logic.getModel().getCurrentSelection().getSelectedGroup());
                refreshViewPane();
            }
            break;
        case SESSION_PAGE:
            currentSelection.getSelectedGroup().unMarkAllSessions();
            showSessionPane(logic.getModel().getCurrentSelection().getSelectedGroup());
            rightPanelPlaceholder.getChildren().clear();
            refreshNavigationBar();
            break;
        case TASK_PAGE:
            currentSelection.getSelectedGroup().unMarkAllTasks();
            showTaskPane(logic.getModel().getCurrentSelection().getSelectedGroup());
            rightPanelPlaceholder.getChildren().clear();
            refreshNavigationBar();
            break;
        default:
            break;
        }
    }

    private void handleUndoCommand(CommandResult commandResult) {
        UndoCommand command = (UndoCommand) commandResult.getCommand();
        Command prevCommand = command.getPrevModel().getCommandExecuted();

        if (prevCommand instanceof SelectCommand
                && !rightPanelPlaceholder.getChildren().isEmpty()
                && logic.getModel().getCurrentSelection().getCurrentPage().equals(PageType.STUDENT_PAGE)) {

            rightPanelPlaceholder.getChildren().clear();

        } else if (prevCommand instanceof SelectCommand) {

            handleBackCommand();

        } else if (prevCommand instanceof ClearCommand
                && logic.getModel().getCurrentSelection().getCurrentPage().equals(PageType.SESSION_PAGE)) {

            showSessionPane(logic.getModel().getCurrentSelection().getSelectedGroup());

        } else if (prevCommand instanceof ClearCommand
                && logic.getModel().getCurrentSelection().getCurrentPage().equals(PageType.TASK_PAGE)) {

            showSessionPane(logic.getModel().getCurrentSelection().getSelectedGroup());

        } else if (prevCommand instanceof MarkPresentCommand
                || prevCommand instanceof  MarkAbsentCommand
                || prevCommand instanceof AssignCommand) {

            //Do nothing

        } else {
            // handleSelectCommand acts like refreshing whatever page you're on
            // undo needs to refresh the page after restoring previous state
            handleSelectCommand();
        }
    }

    private void handleSessionCommand() {
        showSessionPane(logic.getModel().getCurrentSelection().getSelectedGroup());
        showSessionTab();
        refreshNavigationBar();
    }

    private void handleTaskCommand() {
        showTaskPane(logic.getModel().getCurrentSelection().getSelectedGroup());
        showTaskTab();
        refreshNavigationBar();
    }

    private void handleAttendanceCommand() {
        if (logic.getModel().getCurrentSelection().getCurrentPage().equals(PageType.STUDENT_PAGE)) {
            showStudentAttendance();
        }
    }

    private void showStudentAttendance() {
        rightPanelPlaceholder.getChildren().clear();
        ObservableList<Student> viewedStudent =
                logic.getModel().getCurrentSelection().getSelectedGroup().getUnmodifiableFilteredStudentList()
                        .filtered(student ->
                        student.isSameStudent(logic.getModel().getCurrentSelection().getSelectedStudent()));
        ObservableList<SessionWithAttendance> sessionList =
                logic.getModel().getCurrentSelection().getSelectedStudent().getObservableSessionList();
        rightPanelPlaceholder.getChildren()
                .add(new StudentViewCardWithAttendance(viewedStudent.get(0), sessionList, 1).getRoot());
    }

    private void handleSpecialCommandConsiderations(CommandResult commandResult) {

        if (commandResult.getCommand() instanceof SelectCommand) {
            handleSelectCommand();

        } else if (commandResult.getCommand() instanceof BackCommand) {
            handleBackCommand();

        } else if (commandResult.getCommand() instanceof ExitCommand) {
            handleExit();

        } else if (commandResult.getCommand() instanceof HelpCommand) {
            handleHelp();

        } else if (commandResult.getCommand() instanceof HomeCommand) {
            handleHome();

        } else if (commandResult.getCommand() instanceof SessionCommand) {
            handleSessionCommand();

        } else if (commandResult.getCommand() instanceof TaskCommand) {
            handleTaskCommand();

        } else if (commandResult.getCommand() instanceof MarkAbsentCommand
                || commandResult.getCommand() instanceof MarkPresentCommand) {
            showAttendancePane(logic.getModel().getCurrentSelection().getSelectedSession());

        } else if (commandResult.getCommand() instanceof AssignCommand) {
            showGradePane(logic.getModel().getCurrentSelection().getSelectedTask());

        } else if (commandResult.getCommand() instanceof UploadCommand
                || commandResult.getCommand() instanceof EditStudentCommand
                || commandResult.getCommand() instanceof RemarkCommand) {
            refreshViewPane();

        } else if (commandResult.getCommand() instanceof AttendanceCommand) {
            handleAttendanceCommand();

        } else if (commandResult.getCommand() instanceof UndoCommand) {
            handleUndoCommand(commandResult);

        } else if (commandResult.getCommand() instanceof ClearCommand) {
            leftPanelPlaceholder.getChildren().clear();
            rightPanelPlaceholder.getChildren().clear();
        }
    }

    private void showClippySuccess() {
        logoPlaceholder.setImage(clippySuccess);
    }

    private void showClippyFailure() {
        logoPlaceholder.setImage(clippyFailure);
    }

    /**
     * Executes the command and returns the result.
     *
     * @see tfifteenfour.clipboard.logic.Logic#execute(String)
     */
    private CommandResult executeCommand(String commandText) throws CommandException, ParseException {
        try {
            CommandResult commandResult = logic.execute(commandText);
            logger.info("Result: " + commandResult.getFeedbackToUser());
            resultDisplay.setFeedbackToUser(commandResult.getFeedbackToUser());

            handleSpecialCommandConsiderations(commandResult);
            showClippySuccess();

            return commandResult;
        } catch (CommandException | ParseException e) {
            logger.info("Invalid command: " + commandText);
            resultDisplay.setFeedbackToUser(e.getMessage());

            showClippyFailure();
            throw e;
        }
    }
}
