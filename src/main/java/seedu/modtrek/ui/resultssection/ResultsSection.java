package seedu.modtrek.ui.resultssection;

import java.util.TreeMap;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.logic.Logic;
import seedu.modtrek.model.ReadOnlyDegreeProgression;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;
import seedu.modtrek.ui.modulesection.ModuleListSection;
import seedu.modtrek.ui.modulesection.ModuleSearchSection;
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
    public ResultsSection(Logic logic) {
        super(FXML);

        displayFooter("Degree Progress", "Module List", "Module Search", () ->
                displayProgress(logic.getDegreeProgression()), () ->
                displaySortedModules(logic.getDegreeProgression().getModuleGroups(),
                        logic.getDegreeProgression().getSort()), () ->
                displayFindModules(logic.getFilteredModuleList()));

        displayProgress(logic.getDegreeProgression());
    }

    /**
     * Displays the footer buttons that enables user to toggle between displaying progress
     * and displaying module list.
     * @param progressButtonLabel The text label of the progress button.
     * @param moduleListButtonLabel The text label of the module-list button.
     * @param progressButtonHandler The function to execute on clicking the progress button.
     * @param moduleListButtonHandler The function to execute on clicking the module-list button.
     */
    private void displayFooter(String progressButtonLabel, String moduleListButtonLabel, String moduleSearchButtonLabel,
                               Runnable progressButtonHandler, Runnable moduleListButtonHandler,
                               Runnable moduleSearchButtonHandler) {
        FooterButtonGroup footerButtonGroup =
                new FooterButtonGroup(progressButtonLabel, moduleListButtonLabel, moduleSearchButtonLabel,
                        progressButtonHandler, moduleListButtonHandler, moduleSearchButtonHandler);
        this.footerButtonGroup = footerButtonGroup;
        resultsSection.getChildren().remove(resultsSection.lookup(".footer-button-group"));
        resultsSection.getChildren().add(footerButtonGroup.getRoot());
    }

    /**
     * Displays the current degree progress.
     */
    public void displayProgress(ReadOnlyDegreeProgression degreeProgression) {
        footerButtonGroup.selectProgressButton();

        body.getChildren().clear();

        headerTitle.setText("My Degree Progress");
        headerSubtitle.setText("in summary");

        renderSection(new ProgressSection(degreeProgression.getProgressionData()).getRoot());
    }

    /**
     * Displays all the modules, sorted by year.
     *
     * @param modules the list of all modules.
     */
    public void displayAllModules(ObservableList<Module> modules) {
        headerTitle.setText("My Modules");
        headerSubtitle.setText("in total");

        displayModules(modules);
    }

    /**
     * Displays the modules that satisfy a given search query.
     */
    public void displayFindModules(ObservableList<Module> modules /* replace with modules filtered by search query */) {
        footerButtonGroup.selectModuleSearchButton();

        body.getChildren().clear();

        headerTitle.setText("Module Search");
        headerSubtitle.setText("find a module");

        ModuleSection moduleSearchSection = new ModuleSearchSection(modules);
        renderSection(moduleSearchSection.getRoot());
    }

    /**
     * Displays all the modules, sorted by a given category.
     */
    public void displaySortedModules(TreeMap<? extends Object, ObservableList<Module>> sortedLists, String sort) {
        headerTitle.setText("My Modules");
        headerSubtitle.setText("sorted by " + sort);

        footerButtonGroup.selectModuleListButton();
        ModuleSection moduleListSection = new ModuleListSection(sortedLists);
        renderSection(moduleListSection.getRoot());
    }

    /**
     * Displays a list of modules on the {@code ResultsSection}.
     * @param modules the list of modules.
     */
    private void displayModules(ObservableList<Module> modules) {
        footerButtonGroup.selectModuleListButton();
        ModuleSection moduleListSection = new ModuleListSection(modules);
        renderSection(moduleListSection.getRoot());
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
}
