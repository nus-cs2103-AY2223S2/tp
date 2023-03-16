package seedu.address.ui.body.address;

import java.util.Objects;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.ui.UiPart;

/**
 * A UI component to divide a {@code ListView} of {@code Person}s
 * into multiple sections.
 */
public class PersonListDivider extends UiPart<Region> {
    private static final String FXML = "body/address/PersonListDivider.fxml";

    @FXML
    private Label title;

    /**
     * Creates a {@code PersonListDivider} with the given {@code title}.
     */
    public PersonListDivider(String title) {
        super(FXML);

        setTitle(title);
    }

    public void setTitle(String title) {
        Objects.requireNonNull(title);
        this.title.setText(title);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof PersonListDivider)) {
            return false;
        }
        PersonListDivider that = (PersonListDivider) other;
        return Objects.equals(title.getText(), that.title.getText());
    }
}
