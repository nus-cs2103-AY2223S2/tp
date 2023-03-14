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
     * Displays the number of students / tasks
     * @param personList
     */
    public GreetingBar(ObservableList<Person> personList) {
        super(FXML);

        if (personList == null) {
            size = 0;
        } else {
            size = personList.size();
        }

        greetingText = "You have " + size + " students for this event";

        personList.addListener((ListChangeListener<Person>) change -> {
            while (change.next()) {
                if (change.wasAdded() || change.wasRemoved()) {
                    size = change.getList().size();
                    greetingText = "You have " + size + " students for this event";
                    greetings.setText(greetingText);
                }
            }
        });
    }
}
