package seedu.address.ui.body.address;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of persons.
 */
public class PersonListPanel extends UiPart<Region> {
    private static final String FXML = "body/address/PersonListPanel.fxml";
    private static final int EMPTY_INDEX = -1;
    private static final String DIVIDER_FAVORITE = "Favourites (%d)";
    private static final String DIVIDER_ALL = "All contacts (%d)";

    private final Logger logger = LogsCenter.getLogger(PersonListPanel.class);

    @FXML
    private ListView<PersonListCellData> personListView;

    private int selectedIndex;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(ObservableList<Person> personList, PersonDetailPanel panel) {
        super(FXML);
        fillListView(personList);
        personList.addListener((ListChangeListener<Person>) c -> fillListView(c.getList()));
        personListView.setCellFactory(listView -> new PersonListCell());
        personListView.setFocusTraversable(false);
        personListView.setOnMouseClicked(event -> {
            int clickedIndex = personListView.getSelectionModel().getSelectedIndex();
            if (clickedIndex == selectedIndex) {
                clearSelection();
            } else {
                selectedIndex = clickedIndex;
            }
        });

        /* Updates PersonDetailPanel accordingly
         * when the selected Person and index changes.
         */
        MultipleSelectionModel<PersonListCellData> model = personListView.getSelectionModel();
        model.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            panel.setPerson(extractPersonFromData(newValue));
            panel.setDisplayedIndex(extractIndexFromData(newValue));
        });
    }

    /**
     * Scrolls the {@code ListView} of {@code Person}s to the top.
     */
    public void scrollToTop() {
        personListView.scrollTo(0);
    }

    /**
     * Clears the selection in the {@code ListView}.
     */
    public void clearSelection() {
        personListView.getSelectionModel().clearSelection();
        selectedIndex = EMPTY_INDEX;
    }

    private void fillListView(Collection<? extends Person> people) {
        Objects.requireNonNull(people);
        List<PersonListCellData> allData = assignIndices(people);
        List<PersonListCellData> favoriteData = getFavoriteData(allData);
        ObservableList<PersonListCellData> items = personListView.getItems();

        items.clear();
        if (!favoriteData.isEmpty()) {
            items.add(PersonListCellData.ofDivider(String.format(DIVIDER_FAVORITE, favoriteData.size())));
            items.addAll(favoriteData);
        }
        if (!allData.isEmpty()) {
            items.add(PersonListCellData.ofDivider(String.format(DIVIDER_ALL, allData.size())));
            items.addAll(allData);
        }
    }

    private List<PersonListCellData> assignIndices(Collection<? extends Person> people) {
        List<PersonListCellData> indexedData = new LinkedList<>();
        int index = 1;
        for (Person person : people) {
            indexedData.add(PersonListCellData.ofPerson(index, person));
            index += 1;
        }
        return indexedData;
    }

    private List<PersonListCellData> getFavoriteData(Collection<PersonListCellData> data) {
        return data.stream().filter(d -> {
            Person person = d.getPerson().orElse(null);
            if (person == null) {
                return false;
            }
            return person.getIsFavorite().getFavoriteStatus();
        }).collect(Collectors.toList());
    }

    private Person extractPersonFromData(PersonListCellData data) {
        if (data == null || !data.hasPerson()) {
            return null;
        }
        return data.getPerson().orElse(null);
    }

    private int extractIndexFromData(PersonListCellData data) {
        return data == null ? EMPTY_INDEX : data.getIndex();
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListCell extends ListCell<PersonListCellData> {
        private PersonCard personCard;
        private PersonListDivider personListDivider;

        @Override
        protected void updateItem(PersonListCellData data, boolean empty) {
            super.updateItem(data, empty);

            if (empty || data == null) {
                personCard = null;
                personListDivider = null;
                setGraphic(null);
                setText(null);
                return;
            }

            boolean needsUpdate;
            Person person = data.getPerson().orElse(null);
            if (person == null) { // data represents a divider
                needsUpdate = personListDivider == null;
                switchVariant(data.getTitle());
            } else { // data represents a Person to be displayed
                needsUpdate = personCard == null;
                switchVariant(person, data.getIndex());
            }
            if (needsUpdate) {
                if (personCard != null) {
                    setGraphic(personCard.getRoot());
                    setDisable(false);
                } else if (personListDivider != null) {
                    setGraphic(personListDivider.getRoot());
                    setDisable(true);
                } else {
                    setGraphic(null);
                }
            }
            if (getIndex() == 0) { // formats the first ListCell with extra spacing
                getStyleClass().add("first-cell");
            } else {
                getStyleClass().remove("first-cell");
            }
        }

        private void switchVariant(Person person, int index) {
            personListDivider = null;
            if (personCard == null) {
                personCard = new PersonCard(person, index);
            }
            personCard.setPerson(person, index);
        }

        private void switchVariant(String title) {
            personCard = null;
            if (personListDivider == null) {
                personListDivider = new PersonListDivider(title);
            }
            personListDivider.setTitle(title);
        }
    }

    private static class PersonListCellData {
        private final int index;
        private final String title;
        private final Person person;

        private PersonListCellData(int index, String title, Person person) {
            this.index = index;
            this.title = title;
            this.person = person;
        }

        public static PersonListCellData ofPerson(int index, Person person) {
            return new PersonListCellData(index, person.getName().toString(), person);
        }

        public static PersonListCellData ofDivider(String title) {
            return new PersonListCellData(EMPTY_INDEX, title, null);
        }

        public int getIndex() {
            return index;
        }

        public Optional<Person> getPerson() {
            return Optional.ofNullable(person);
        }

        public boolean hasPerson() {
            return getPerson().isPresent();
        }

        public String getTitle() {
            return title;
        }
    }
}
