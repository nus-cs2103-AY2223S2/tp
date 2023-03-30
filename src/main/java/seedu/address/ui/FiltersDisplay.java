package seedu.address.ui;

import static java.util.Objects.requireNonNull;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.Filter;

/**
 * This class encapsulates an UI component displaying the current applying filters on {@code PersonListPanel}.
 */
public class FiltersDisplay extends UiPart<Region> {
    private static final String FXML = "FiltersDisplay.fxml";

    @FXML
    private FlowPane filters;

    /**
     * Creates a {@code FiltersDisplay} with the given {@code ObservableList}.
     */
    public FiltersDisplay() {
        super(FXML);
    }

    public void setApplyingFilters(ObservableList<Filter> filtersList) {
        requireNonNull(filtersList);
        filters.getChildren().clear();
        for (Filter filter : filtersList) {
            Label label = new Label(filter.toString());
            filters.getChildren().add(label);
        }
    }
}
