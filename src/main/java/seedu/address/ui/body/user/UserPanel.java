package seedu.address.ui.body.user;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.ReadOnlyUserData;
import seedu.address.model.user.User;
import seedu.address.ui.UiPart;
import seedu.address.ui.body.address.PersonDetailCard;
import seedu.address.ui.body.address.PersonModulesCard;

/**
 * A UI component that displays {@code User} information.
 */
public class UserPanel extends UiPart<Region> {
    private static final String FXML = "body/user/UserPanel.fxml";

    @FXML
    private Label name;
    @FXML
    private VBox dataContainer;

    /**
     * Creates a {@code UserPanel} with the given {@code userData}.
     */
    public UserPanel(ReadOnlyUserData userData) {
        super(FXML);
        Objects.requireNonNull(userData);

        setUser(userData.getData().get());
        userData.getData().addListener((observable, oldValue, newValue) -> setUser(newValue));
    }

    public void setUser(User user) {
        name.setText(null);
        dataContainer.getChildren().clear();
        if (user == null) {
            return;
        }

        name.setText(user.getName().toString());
        dataContainer.getChildren().addAll(getDataCardCollection(user));
    }

    private Collection<Region> getDataCardCollection(User user) {
        List<Region> regions = Stream.of(
                new PersonDetailCard.DetailCardData("Phone", user.getPhone().toString()),
                new PersonDetailCard.DetailCardData("Address", user.getAddress().toString()),
                new PersonDetailCard.DetailCardData("Email", user.getEmail().toString()),
                new PersonDetailCard.DetailCardData("Gender", user.getGender().toString()),
                new PersonDetailCard.DetailCardData("Race", user.getRace().toString()),
                new PersonDetailCard.DetailCardData("Communication channels", user.getComms().toString()),
                new PersonDetailCard.DetailCardData("Major", user.getMajor().toString()))
                .map(PersonDetailCard::new)
                .map(PersonDetailCard::getRoot).collect(Collectors.toCollection(LinkedList::new));

        // Adds card for modules taken, if any
        boolean hasModules = user.getModules() != null
                && user.getModules().mods != null
                && !user.getModules().mods.isEmpty();
        if (hasModules) {
            regions.add(new PersonModulesCard("Modules", user.getModules().mods).getRoot());
        }

        return regions;
    }
}
