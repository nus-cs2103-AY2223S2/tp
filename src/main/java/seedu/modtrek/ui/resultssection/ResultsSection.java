package seedu.modtrek.ui.resultssection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;
import seedu.modtrek.ui.modulesection.ModuleSection;
import seedu.modtrek.ui.progresssection.ProgressSection;

/**
 * A UI component that displays the right portion of a Graphical User Interface
 * to showcase degree progress and modules.
 */
public class ResultsSection extends UiPart<Region> {
    private static final String FXML = "resultssection/ResultsSection.fxml";

    private ObservableList<Module> modules;

    private FooterButtonGroup footerButtonGroup;

    @FXML
    private VBox resultsSection;

    @FXML
    private Label headerTitle;

    @FXML
    private Label headerSubtitle;

    @FXML
    private VBox body;


    /**
     * Creates a {@code ResultsSection} with the given {@code ObservableList<Module>}.
     */
    public ResultsSection(ObservableList<Module> modules) {
        super(FXML);

        displayFooter("Degree Progress", "Module List",
                () -> displayProgress(), () -> displayAllModules(modules));

        displayProgress();
    }

    /**
     * Displays the footer buttons that enables user to toggle between displaying progress
     * and displaying module list.
     * @param progressButtonLabel The text label of the progress button.
     * @param modulelistButtonLabel The text label of the module-list button.
     * @param progressButtonHandler The function to execute on clicking the progress button.
     * @param modulelistButtonHandler The function to execute on clicking the module-list button.
     */
    private void displayFooter(String progressButtonLabel, String modulelistButtonLabel,
                               Runnable progressButtonHandler, Runnable modulelistButtonHandler) {
        FooterButtonGroup footerButtonGroup =
                new FooterButtonGroup(progressButtonLabel, modulelistButtonLabel,
                        progressButtonHandler, modulelistButtonHandler);
        this.footerButtonGroup = footerButtonGroup;
        resultsSection.getChildren().remove(resultsSection.lookup(".footer-button-group"));
        resultsSection.getChildren().add(footerButtonGroup.getRoot());
    }

    // TODO: next iteration
    public void displayProgress() {
        footerButtonGroup.selectProgressButton();

        body.getChildren().clear();

        headerTitle.setText("Your Degree Progress");
        headerSubtitle.setText("in summary");

        renderSection(new ProgressSection().getRoot());
    }

    /**
     * Displays all the modules, sorted by year.
     *
     * @param modules the list of all modules.
     */
    public void displayAllModules(ObservableList<Module> modules) {
        headerTitle.setText("Your Modules");
        headerSubtitle.setText("in total");

        String[] buttonLabels = new String[] {"Year 1", "Year 2", "Year 3", "Year 4"};


        Runnable[] buttonHandlers = new Runnable[4];

        Runnable year1ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 1 modules */);
        buttonHandlers[0] = year1ModulesRenderer;

        Runnable year2ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 2 modules */);
        buttonHandlers[1] = year2ModulesRenderer;

        Runnable year3ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 3 modules */);
        buttonHandlers[2] = year3ModulesRenderer;

        Runnable year4ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 4 modules */);
        buttonHandlers[3] = year4ModulesRenderer;


//        FooterButtonGroup footerButtonGroup =
//                new FooterButtonGroup(buttonLabels, buttonHandlers);
//        resultsSection.getChildren().remove(resultsSection.lookup(".footer-button-group"));
//        resultsSection.getChildren().add(footerButtonGroup.getRoot());

        displayModules(modules);
    }

    /**
     * Displays the modules that satisfy a given search query.
     */
    public void displayFindModules(/* ObservableList<Module> modules that are filtered by search query */) {
        // TODO: next iteration
    }

    /**
     * Displays all the modules, sorted by a given category.
     */
    public void displaySortedModules(
            /* ObservableList<Module> modules sorted by a certain category, String category */) {
        // TODO: next iteration
    }

    /**
     * Displays a list of modules on the {@code ResultsSection}.
     * @param modules the list of modules.
     */
    private void displayModules(ObservableList<Module> modules) {
        footerButtonGroup.selectModulelistButton();
        ModuleSection moduleSection = new ModuleSection(modules);
        renderSection(moduleSection.getRoot());
    }

    /**
     * Renders a given subsection in ResultsSection. In this case there are only two possible
     * subsections: ProgressSection and ModuleSection.
     * @param section The given subsection to be rendered.
     */
    private void renderSection(Node section) {
        body.getChildren().clear();
        body.getChildren().add(section);
    }

    /**
     * Gets the executable to render a given list of modules.
     * @param modules the list of modules.
     * @return the executable.
     */
    private Runnable getModuleRenderer(ObservableList<Module> modules) {
        return () -> displayModules(modules);
    }
}
