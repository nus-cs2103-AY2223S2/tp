package seedu.address.ui.body.address;

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
import seedu.address.logic.Logic;
import seedu.address.model.person.Person;
import seedu.address.ui.UiPart;
import seedu.address.ui.result.ResultDisplay;

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

    private List<PersonListCardData> allData;
    private List<PersonListCardData> favData;
    private int selectedIndex;

    /**
     * Creates a {@code PersonListPanel} with the given {@code ObservableList}.
     */
    public PersonListPanel(Logic logic, ResultDisplay resultDisplay) {
        super(FXML);

        allData = null;
        favData = null;
        selectedIndex = EMPTY_INDEX;

        personListView.setCellFactory(listView -> new PersonListCell());
        personListView.setFocusTraversable(false);
        personListView.setOnMouseClicked(event -> {
            resultDisplay.showDefaultMessage();
            PersonListCellData nextData = personListView.getSelectionModel().getSelectedItem();
            int nextIndex = personListView.getSelectionModel().getSelectedIndex();
            if (selectedIndex == nextIndex) { // clear selection on same selection
                selectedIndex = EMPTY_INDEX;
                logic.setSelectedPerson(null);
                setSelectedData(null);
            } else {
                selectedIndex = nextIndex;
                logic.setSelectedPerson(nextData == null ? null : nextData.getPerson());
                setSelectedData(nextData);
            }
        });

        fillListView(logic.getFilteredPersonList());
        logic.getFilteredPersonList().addListener((ListChangeListener<Person>) c -> fillListView(c.getList()));
    }

    /**
     * Scrolls the {@code ListView} of {@code Person}s to the top.
     */
    public void scrollToTop() {
        personListView.scrollTo(0);
    }

    public void setSelectedPerson(Person selectedPerson) {
        if (allData == null) {
            return;
        }
        setSelectedData(allData
                .stream()
                .filter(data -> Objects.equals(data.getPerson(), selectedPerson))
                .findFirst()
                .orElse(null));
    }

    public PersonListCellData getSelectedData() {
        return personListView.getSelectionModel().getSelectedItem();
    }

    private void setSelectedData(PersonListCellData data) {
        MultipleSelectionModel<PersonListCellData> selectionModel = personListView.getSelectionModel();
        if (Objects.equals(selectionModel.getSelectedItem(), data)) {
            // Equivalent person to be selected, so the selection will not be changed
            return;
        }
        selectionModel.select(data);
        selectedIndex = selectionModel.getSelectedIndex();
    }

    private void fillListView(Collection<? extends Person> people) {
        Objects.requireNonNull(people);
        ObservableList<PersonListCellData> items = personListView.getItems();
        items.clear();
        if (people.isEmpty()) {
            return;
        }

        allData = assignIndices(people);
        favData = getFavoriteData(allData);
        if (!favData.isEmpty()) {
            items.add(new PersonListDividerData(String.format(DIVIDER_FAVORITE, favData.size())));
            items.addAll(favData);
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
                .filter(d -> d.getPerson().getFavorite().getFavoriteStatus())
                .map(d -> new PersonListCardData(d.getPerson(), d.getIndex()))
                .collect(Collectors.toList());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Person} using a {@code PersonCard}.
     */
    static class PersonListCell extends ListCell<PersonListCellData> {
        private final PersonCard personCard = new PersonCard();
        private final PersonListDivider personListDivider = new PersonListDivider(null);

        public PersonCard getPersonCard() {
            return personCard;
        }

        public PersonListDivider getPersonListDivider() {
            return personListDivider;
        }

        @Override
        protected void updateItem(PersonListCellData data, boolean empty) {
            super.updateItem(data, empty);

            if (empty || data == null) {
                setGraphic(null);
                setText(null);
                return;
            }
            data.updateGraphic(this);
        }
    }

    /**
     * Wrapper for a {@code Person} with an {@code Index}.
     */
    public interface PersonListCellData {
        Person getPerson();

        int getIndex();

        void updateGraphic(PersonListCell cell);
    }

    private static class PersonListDividerData implements PersonListCellData {
        private static final boolean IS_MOUSE_TRANSPARENT = true;

        private final String title;

        public PersonListDividerData(String title) {
            Objects.requireNonNull(title);
            this.title = title;
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
        public void updateGraphic(PersonListCell cell) {
            Objects.requireNonNull(cell);
            cell.getPersonListDivider().setTitle(title);
            if (cell.getGraphic() != cell.getPersonListDivider().getRoot()) {
                cell.setGraphic(cell.getPersonListDivider().getRoot());
                cell.setMouseTransparent(IS_MOUSE_TRANSPARENT);
            }
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
            return title.equals(that.title);
        }
    }

    private static class PersonListCardData implements PersonListCellData {
        private static final boolean IS_MOUSE_TRANSPARENT = false;

        private final Person person;
        private final int index;

        public PersonListCardData(Person person, int index) {
            this.person = person;
            this.index = index;
        }

        @Override
        public Person getPerson() {
            return person;
        }

        @Override
        public int getIndex() {
            return index;
        }

        @Override
        public void updateGraphic(PersonListCell cell) {
            Objects.requireNonNull(cell);
            cell.getPersonCard().setPerson(person, index);
            if (cell.getGraphic() != cell.getPersonCard().getRoot()) {
                cell.setGraphic(cell.getPersonCard().getRoot());
                cell.setMouseTransparent(IS_MOUSE_TRANSPARENT);
            }
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
            return index == that.index && person.equals(that.person);
        }
    }
}
