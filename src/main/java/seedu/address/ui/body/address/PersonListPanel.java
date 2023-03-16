package seedu.address.ui.body.address;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
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

        personList.addListener((ListChangeListener<Person>) c -> fillListView(c.getList()));
        fillListView(personList);

        /* Updates PersonDetailPanel accordingly
         * when the selected Person and index changes.
         */
        MultipleSelectionModel<PersonListCellData> model = personListView.getSelectionModel();
        model.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            panel.setPerson(newValue.getPerson());
            panel.setDisplayedIndex(newValue.getIndex());
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
        ObservableList<PersonListCellData> items = personListView.getItems();
        items.clear();
        if (people.isEmpty()) {
            return;
        }

        List<PersonListCardData> allData = assignIndices(people);
        List<PersonListCardData> favoriteData = getFavoriteData(allData);
        if (!favoriteData.isEmpty()) {
            items.add(new PersonListDividerData(String.format(DIVIDER_FAVORITE, favoriteData.size())));
            items.addAll(favoriteData);
        }
        if (!allData.isEmpty()) {
            items.add(new PersonListDividerData(String.format(DIVIDER_ALL, allData.size())));
            items.addAll(allData);
        }
    }

    private List<PersonListCardData> assignIndices(Collection<? extends Person> people) {
        List<PersonListCardData> indexedData = new LinkedList<>();
        int index = 1;
        for (Person person : people) {
            indexedData.add(new PersonListCardData(person, index));
            index += 1;
        }
        return indexedData;
    }

    private List<PersonListCardData> getFavoriteData(Collection<PersonListCardData> data) {
        return data.stream()
                .filter(d -> d.getPerson().getIsFavorite().getFavoriteStatus())
                .map(d -> new PersonListCardData(d.getPerson(), d.getIndex()))
                .collect(Collectors.toList());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListCell extends ListCell<PersonListCellData> {
        private PersonListCellData data;

        @Override
        protected void updateItem(PersonListCellData newData, boolean empty) {
            super.updateItem(newData, empty);

            if (empty || newData == null) {
                data = null;
                setGraphic(null);
                setText(null);
                return;
            }
            if (data == newData) {
                return;
            }

            data = newData;
            setGraphic(data.getUiPart().getRoot());
            setMouseTransparent(data.isMouseTransparent());
        }
    }

    private interface PersonListCellData {
        UiPart<Region> getUiPart();

        boolean isMouseTransparent();

        Person getPerson();

        int getIndex();
    }

    private static class PersonListDividerData implements PersonListCellData {
        private final PersonListDivider divider;

        public PersonListDividerData(String title) {
            divider = new PersonListDivider(title);
        }

        @Override
        public UiPart<Region> getUiPart() {
            return divider;
        }

        @Override
        public boolean isMouseTransparent() {
            return true;
        }

        @Override
        public Person getPerson() {
            return null;
        }

        @Override
        public int getIndex() {
            return EMPTY_INDEX;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof PersonListDividerData)) {
                return false;
            }
            PersonListDividerData that = (PersonListDividerData) other;
            return divider.equals(that.divider);
        }
    }

    private static class PersonListCardData implements PersonListCellData {
        private final PersonCard card;

        public PersonListCardData(Person person, int index) {
            card = new PersonCard(person, index);
        }

        @Override
        public Person getPerson() {
            return card.getPerson();
        }

        @Override
        public int getIndex() {
            return card.getIndex();
        }

        @Override
        public UiPart<Region> getUiPart() {
            return card;
        }

        @Override
        public boolean isMouseTransparent() {
            return false;
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }
            if (!(other instanceof PersonListCardData)) {
                return false;
            }
            PersonListCardData that = (PersonListCardData) other;
            return card.equals(that.card);
        }
    }
}
