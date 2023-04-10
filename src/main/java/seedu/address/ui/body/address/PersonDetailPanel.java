package seedu.address.ui.body.address;

import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.person.Person;
import seedu.address.model.person.fields.subfields.Tag;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays detailed information of a {@code Person}.
 */
public class PersonDetailPanel extends UiPart<Region> {
    private static final String FXML = "body/address/PersonDetailPanel.fxml";
    private static final String INDEX_UNSPECIFIED = "Select a contact";
    private static final String INDEX_SPECIFIED = "Index %d";
    private static final int EMPTY_INDEX = -1;

    @FXML
    private ScrollPane scrollContainer;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private FlowPane tags;
    @FXML
    private VBox dataContainer;
    @FXML
    private ImageView star;

    /**
     * Creates a blank {@code PersonDetailPanel}.
     */
    public PersonDetailPanel() {
        super(FXML);
        clearPerson();
    }

    public void setPerson(Person person) {
        if (person == null) {
            return;
        }

        name.setText(person.getName().toString());
        dataContainer.getChildren().addAll(getDataCardCollection(person));
        star.setVisible(person.getFavorite().getFavoriteStatus());
    }

    /**
     * Sets the index of the {@code Person} to be displayed to the user.
     * If the given {@code index} is less than 1, it is assumed that
     * no {@code Person} is selected.
     *
     * @param index 1-based index of the corresponding {@code Person}.
     */
    public void setDisplayedIndex(int index) {
        if (index < 1) {
            id.setText(INDEX_UNSPECIFIED);
        } else {
            id.setText(String.format(INDEX_SPECIFIED, index));
        }
    }

    /**
     * Sets specified selected person and corresponding index in detail panel.
     */
    public void setSelectedPerson(PersonListPanel.PersonListCellData data) {
        clearPerson();
        if (data == null) {
            return;
        }
        this.setPerson(data.getPerson());
        this.setDisplayedIndex(data.getIndex());
    }

    /**
     * Empties the fields, resulting in a blank {@code PersonDetailPanel}.
     */
    public void clearPerson() {
        setDisplayedIndex(EMPTY_INDEX);

        name.setText(null);
        tags.getChildren().clear();
        dataContainer.getChildren().clear();
        scrollContainer.setVvalue(0);
        star.setVisible(false);
    }

    private Collection<Label> getTagLabels(Person person) {
        return person.getSetOfTags().stream()
                .sorted(Comparator.comparing(tag -> tag.value))
                .map(Tag::truncateValue)
                .map(Label::new)
                .collect(Collectors.toList());
    }

    private Collection<Region> getDataCardCollection(Person person) {
        List<Region> regions = new LinkedList<>();

        // Adds Person tags, if any
        if (!person.getSetOfTags().isEmpty()) {
            tags.getChildren().addAll(getTagLabels(person));
            regions.add(tags);
        }

        // Adds all detail cards, except modules taken
        Collection<Region> dataRegions = Stream.of(
                new PersonDetailCard.DetailCardData("Phone", person.getPhone().toString()),
                new PersonDetailCard.DetailCardData("Address", person.getAddress().toString()),
                new PersonDetailCard.DetailCardData("Email", person.getEmail().toString()),
                new PersonDetailCard.DetailCardData("Gender", person.getGender().toString()),
                new PersonDetailCard.DetailCardData("Race", person.getRace().toString()),
                new PersonDetailCard.DetailCardData("Communication channels", person.getComms().toString()),
                new PersonDetailCard.DetailCardData("Major", person.getMajor().toString()),
                new PersonDetailCard.DetailCardData("Faculty", person.getFaculty().toString()))
                .map(PersonDetailCard::new)
                .map(PersonDetailCard::getRoot)
                .collect(Collectors.toList());
        regions.addAll(dataRegions);

        // Adds card for modules taken, if any
        boolean hasModules = person.getModules() != null
                && person.getModules().values != null
                && !person.getModules().values.isEmpty();
        if (hasModules) {
            regions.add(new PersonModulesCard("Modules", person.getModules().values).getRoot());
        }

        return regions;
    }
}
