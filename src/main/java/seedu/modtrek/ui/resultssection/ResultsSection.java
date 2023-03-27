package seedu.modtrek.ui.resultssection;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.function.Consumer;
import java.util.function.Supplier;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.logic.Logic;
import seedu.modtrek.logic.commands.SortCommand;
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
                displayAllModules(logic.getDegreeProgression().getModuleGroups(),
                        logic.getDegreeProgression().getSort(), getSorters(logic)), () ->
                displayFindModules(logic.getFilteredModuleList(), logic.getFiltersList()));

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
     * Displays the modules that satisfy a given search query.
     */
    public void displayFindModules(ObservableList<Module> modules, List<String> filters) {
        footerButtonGroup.selectModuleSearchButton();

        body.getChildren().clear();

        headerTitle.setText("Module Search");
        headerSubtitle.setText("find a module");

        ModuleSection moduleSearchSection = new ModuleSearchSection(modules, filters);
        renderSection(moduleSearchSection.getRoot());
    }

    /**
     * Displays all the modules, sorted by a given category.
     */
    public void displayAllModules(TreeMap<? extends Object, ObservableList<Module>> sortedLists, String sort,
                                  List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> sorters) {
        headerTitle.setText("My Modules");
        headerSubtitle.setText("in total");

        footerButtonGroup.selectModuleListButton();
        ModuleSection moduleListSection = new ModuleListSection(sortedLists, sort, sorters);
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

    /**
     * Gets the executables to sort the list of modules.
     * @param modules the list of modules.
     * @return the executable.
     */
    public List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> getSorters(Logic logic) {
        List<Supplier<TreeMap<? extends Object, ObservableList<Module>>>> sorters = new ArrayList<>();

        for (SortCommand.Sort sort : SortCommand.Sort.values()) {
            sorters.add(() -> {
                logic.sortModuleGroups(sort);
                return logic.getDegreeProgression().getModuleGroups();
            });
        }

        return sorters;
    }
}
