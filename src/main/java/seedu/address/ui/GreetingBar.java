package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;

/**
 * Informs the user on the number of undone tasks
 */
public class GreetingBar extends UiPart<Region> {
    private static final String FXML = "GreetingBar.fxml";
    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);
    private String greetingText;

    @FXML
    private Label greetings;

    private int size;

    /**
     * Displays the number of students / tasks.
     *
     * @param persons    the observable list containing persons
     */
    public GreetingBar(ObservableList<Person> persons) {
        super(FXML);

        if (persons == null) {
            size = 0;
        } else {
            size = persons.size();
        }

        greetingText = "You have " + size + " students currently";
        greetings.setText(greetingText);

        persons.addListener((ListChangeListener<Person>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    size = change.getList().size();
                    greetingText = "You have " + size + " students currently";
                    greetings.setText(greetingText);
                }
            }
        });
    }
}
