package seedu.modtrek.ui.graphicssection;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.modtrek.model.module.Module;
import seedu.modtrek.ui.UiPart;
import seedu.modtrek.ui.modulelist.ModuleList;

/**
 * A UI component that displays the portion of a Graphical User Interface to showcase modules.
 */
public class GraphicsSection extends UiPart<Region> {
    private static final String FXML = "graphics_section/GraphicsSection.fxml";

    @FXML
    private VBox sectionContainer;

    @FXML
    private Label headerTitle;

    @FXML
    private Label headerSubtitle;

    @FXML
    private VBox body;


    /**
     * Creates a {@code CliSection} with the given {@code ObservableList<Module>}.
     */
    public GraphicsSection(ObservableList<Module> modules) {
        super(FXML);
        displayAllModules(modules);
    }

    // TODO: next iteration
    public void displayProgress() {
    }

    /**
     * Displays with the updated list of modules.
     *
     * @param modules the list of modules
     */
    public void displayAllModules(ObservableList<Module> modules) {
        headerTitle.setText("Your Modules");
        headerSubtitle.setText("in total");

        String[] buttonLabels = new String[] {"Year 1", "Year 2", "Year 3", "Year 4"};
        Runnable year1ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 1 modules */);
        Runnable year2ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 2 modules */);
        Runnable year3ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 3 modules */);
        Runnable year4ModulesRenderer = getModuleRenderer(modules /* TODO: replace with only year 4 modules */);
        Runnable[] buttonHandlers = new Runnable[] {
                year1ModulesRenderer,
                year2ModulesRenderer,
                year3ModulesRenderer,
                year4ModulesRenderer
        };

        FooterButtonGroup footerButtonGroup =
                new FooterButtonGroup(buttonLabels, buttonHandlers);
        sectionContainer.getChildren().add(footerButtonGroup.getRoot());

        displayModules(modules);
    }

    // TODO: next iteration
    public void displayFindModules(/* ObservableList<Module> modules that are filtered by search query */) {
    }

    // TODO: next iteration
    public void displaySortedModules(/* ObservableList<Module> modules sorted by a certain category, String category */) {
    }

    private void displayModules(ObservableList<Module> modules) {
        ModuleList moduleList = new ModuleList(modules);
        body.getChildren().clear();
        body.getChildren().add(moduleList.getRoot());
    }

    private Runnable getModuleRenderer(ObservableList<Module> modules) {
        return () -> displayModules(modules);
    }
}
