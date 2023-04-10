package seedu.address.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.client.policy.Policy;

/**
 * Panel containing the list of clients.
 */
public class PolicyListPanel extends UiPart<Region> {
    private static final String FXML = "PolicyListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(PolicyListPanel.class);

    @FXML
    private ListView<Policy> policyListView;

    /**
     * Creates a {@code PolicyListPanel} with the given {@code ObservableList}
     */
    public PolicyListPanel(ObservableList<Policy> policyList) {
        super(FXML);
        policyListView.setItems(policyList);
        policyListView.setCellFactory(listView -> new PolicyListViewCell());
    }

    /**
     * Updates a {@code PolicyListPanel} with the given {@code ObservableList}
     */
    public void updatePolicyList(ObservableList<Policy> policyList) {
        policyListView.setItems(policyList);
        policyListView.setCellFactory(listView -> new PolicyListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Policy} using a {@code PolicyCard}
     */
    class PolicyListViewCell extends ListCell<Policy> {
        @Override
        protected void updateItem(Policy policy, boolean empty) {
            super.updateItem(policy, empty);

            if (empty || policy == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new PolicyCard(policy, getIndex() + 1).getRoot());
            }
        }
    }
}
