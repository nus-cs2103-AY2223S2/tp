package guitests.guihandles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javafx.scene.Node;
import javafx.scene.control.ListView;
import seedu.address.model.recommendation.Recommendation;

/**
 * Provides a handle for {@code MeetListPanel} containing the list of {@code MeetCard}.
 */
public class MeetListPanelHandle extends NodeHandle<ListView<Recommendation>> {
    public static final String MEET_LIST_VIEW_ID = "#meetListView";
    private static final String CARD_PANE_ID = "#cardPane";

    private Optional<Recommendation> lastRememberedSelectedRecommendationCard;

    public MeetListPanelHandle(ListView<Recommendation> recommendationListPanelNode) {
        super(recommendationListPanelNode);
    }

    /**
     * Returns a handle to the selected {@code MeetCardHandle}.
     * A maximum of 1 item can be selected at any time.
     * @throws AssertionError if no card is selected, or more than 1 card is selected.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public MeetCardHandle getHandleToSelectedCard() {
        List<Recommendation> selectedRecommendationList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedRecommendationList.size() != 1) {
            throw new AssertionError("Recommendation list size expected 1.");
        }

        return getAllCardNodes().stream()
                .map(MeetCardHandle::new)
                .filter(handle -> handle.equals(selectedRecommendationList.get(0)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    /**
     * Returns the index of the selected card.
     */
    public int getSelectedCardIndex() {
        return getRootNode().getSelectionModel().getSelectedIndex();
    }

    /**
     * Returns true if a card is currently selected.
     */
    public boolean isAnyCardSelected() {
        List<Recommendation> selectedCardsList = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedCardsList.size() > 1) {
            throw new AssertionError("Card list size expected 0 or 1.");
        }

        return !selectedCardsList.isEmpty();
    }

    /**
     * Navigates the listview to display {@code recommendation}.
     */
    public void navigateToCard(Recommendation recommendation) {
        if (!getRootNode().getItems().contains(recommendation)) {
            throw new IllegalArgumentException("Recommendation does not exist.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(recommendation);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Navigates the listview to {@code index}.
     */
    public void navigateToCard(int index) {
        if (index < 0 || index >= getRootNode().getItems().size()) {
            throw new IllegalArgumentException("Index is out of bounds.");
        }

        guiRobot.interact(() -> {
            getRootNode().scrollTo(index);
        });
        guiRobot.pauseForHuman();
    }

    /**
     * Selects the {@code MeetCard} at {@code index} in the list.
     */
    public void select(int index) {
        getRootNode().getSelectionModel().select(index);
    }

    /**
     * Returns the person card handle of a person associated with the {@code index} in the list.
     * @throws IllegalStateException if the selected card is currently not in the scene graph.
     */
    public MeetCardHandle getMeetCardHandle(int index) {
        return getAllCardNodes().stream()
                .map(MeetCardHandle::new)
                .filter(handle -> handle.getPlace().equals(getPlace(index)))
                .findFirst()
                .orElseThrow(IllegalStateException::new);
    }

    private String getPlace(int index) {
        return getRootNode().getItems().get(index).getLocation().getName()
                + " : " + getRootNode().getItems().get(index).getTimePeriod().getUiDisplay();
    }

    /**
     * Returns all card nodes in the scene graph.
     * Card nodes that are visible in the listview are definitely in the scene graph, while some nodes that are not
     * visible in the listview may also be in the scene graph.
     */
    private Set<Node> getAllCardNodes() {
        return guiRobot.lookup(CARD_PANE_ID).queryAll();
    }

    /**
     * Remembers the selected {@code MeetCard} in the list.
     */
    public void rememberSelectedMeetCard() {
        List<Recommendation> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            lastRememberedSelectedRecommendationCard = Optional.empty();
        } else {
            lastRememberedSelectedRecommendationCard = Optional.of(selectedItems.get(0));
        }
    }

    /**
     * Returns true if the selected {@code MeetCard} is different from the value remembered by the most recent
     * {@code rememberSelectedMeetCard()} call.
     */
    public boolean isSelectedMeetCardChanged() {
        List<Recommendation> selectedItems = getRootNode().getSelectionModel().getSelectedItems();

        if (selectedItems.size() == 0) {
            return lastRememberedSelectedRecommendationCard.isPresent();
        } else {
            return !lastRememberedSelectedRecommendationCard.isPresent()
                    || !lastRememberedSelectedRecommendationCard.get().equals(selectedItems.get(0));
        }
    }

    /**
     * Returns the size of the list.
     */
    public int getListSize() {
        return getRootNode().getItems().size();
    }
}
