package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.module.ReadOnlyModule;
import seedu.address.model.person.Person;

/**
 * A UI component that displapys information of a {@code Module}.
 */
public class ModuleCard extends UiPart<Region> {

    private static final String FXML = "ModuleListCard.fxml";

    private final ReadOnlyModule module;

    @FXML
    private HBox cardPane;
    @FXML
    private Label id;
    @FXML
    private Label moduleCode;
    @FXML
    private Label moduleName;
    @FXML
    private Label lectureCount;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code ModuleCard} with the given {@code ReadOnlyModule}.
     */
    public ModuleCard(ReadOnlyModule module, int displayedIndex) {
        super(FXML);
        this.module = module;
        id.setText(displayedIndex + ". ");
        moduleCode.setText(module.getCode().toString());
        moduleName.setText(module.getName().toString());

        lectureCount.setText("Lectures: " + module.getLectureList().size());
        module.getTags().stream().sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModuleCard)) {
            return false;
        }

        // state check
        ModuleCard card = (ModuleCard) other;
        return id.getText().equals(card.id.getText()) && module.equals(card.module);
    }
}
