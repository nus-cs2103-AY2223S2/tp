package seedu.address.ui.jobs;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Region;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.sorters.DeliveryFilterOption;
import seedu.address.model.jobs.sorters.DeliverySortOption;
import seedu.address.ui.UiPart;

/**
 * Panel containing the list of jobs.
 */
public class DeliveryJobListPanel extends UiPart<Region> {
    private static final String FXML = "DeliveryJobListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DeliveryJobListPanel.class);

    private BiConsumer<Integer, DeliveryJob> onSelectHandler;
    private Consumer<DeliveryJob> onCheckHandler;
    private Optional<BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>>> sortHandler;

    private boolean isSortByAsc = false;

    @FXML
    private ListView<DeliveryJob> deliveryJobListView;
    @FXML
    private Menu orderOption;
    @FXML
    private Menu filterOption;
    @FXML
    private Label orderIndicator;
    @FXML
    private RadioMenuItem filterAll;
    @FXML
    private RadioMenuItem filterDelivered;
    @FXML
    private RadioMenuItem filterPending;
    @FXML
    private ToggleGroup toggleGroup;

    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public DeliveryJobListPanel(ObservableList<DeliveryJob> jobList,
            BiConsumer<Integer, DeliveryJob> selectHandler,
            Consumer<DeliveryJob> checkHandler,
            Consumer<DeliveryJob> deleteHandler) {
        super(FXML);
        deliveryJobListView.setItems(jobList);
        deliveryJobListView.setCellFactory(listView -> new DeliveryJobListViewCell());
        this.onSelectHandler = selectHandler;

        deliveryJobListView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
            }

        });

        deliveryJobListView.setOnKeyPressed(new EventHandler<KeyEvent>() {

            @Override
            public void handle(KeyEvent event) {
                if (event.getCode().equals(KeyCode.DELETE)) {
                    deleteHandler.accept(deliveryJobListView.getSelectionModel().getSelectedItem());
                    selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
                    return;
                }
                if (event.getCode().equals(KeyCode.UP) || event.getCode().equals(KeyCode.DOWN)) {
                    selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
                    return;
                }
                if (event.isControlDown() && event.getCode().equals(KeyCode.C)) {
                    copyToClipboard();
                }
            }

        });

        onCheckHandler = checkHandler;
    }

    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}
     * without any event handler.
     */
    public DeliveryJobListPanel(ObservableList<DeliveryJob> jobList) {
        this(jobList, (job, idx) -> {}, (job) -> {}, (job) -> {});
    }

    /**
     * selectItem
     *
     * @param idx
     */
    public void selectItem(int idx) {
        if (deliveryJobListView.getItems().size() > 0) {
            logger.info("Delivery selected:" + deliveryJobListView.getSelectionModel().getSelectedIndex());
            if (idx < 0) {
                idx = 0;
            }
            deliveryJobListView.getSelectionModel().select(idx);
            onSelectHandler.accept(idx + 1, deliveryJobListView.getSelectionModel().getSelectedItem());
        } else {
            onSelectHandler.accept(-1, null);
        }
    }

    /**
     * selectPrevious.
     */
    public void selectAvailable() {
        if (this.size() > 0) {
            if (deliveryJobListView.getSelectionModel().getSelectedIndex() != this.size() - 1) {
                deliveryJobListView.getSelectionModel().selectNext();
            } else {
                deliveryJobListView.getSelectionModel().selectPrevious();
            }
        }
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code job} using a
     * {@code PersonCard}.
     */
    class DeliveryJobListViewCell extends ListCell<DeliveryJob> {
        @Override
        protected void updateItem(DeliveryJob job, boolean empty) {
            super.updateItem(job, empty);

            if (empty || job == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DeliveryJobCard(job, getIndex() + 1, check -> {
                    onCheckHandler.accept(check);
                }).getRoot());
            }
        }
    }

    private void copyToClipboard() {
        final Clipboard clipboard = Clipboard.getSystemClipboard();
        final ClipboardContent url = new ClipboardContent();
        url.putString(deliveryJobListView.getSelectionModel().getSelectedItem().getJobId());
        clipboard.setContent(url);
    }

    /**
     * @return the number of item in listview.
     */
    public int size() {
        return deliveryJobListView.getItems().size();
    }

    /**
     * Triggers update event for child elemets.
     */
    public void refresh() {
        selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
    }

    /**
     * Sets the order event handlers.
     *
     * @param handler
     */
    public void setOrderByHandler(BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>> handler) {
        // orderOption = Optional.of(handler);
        sortHandler = Optional.of(handler);
        orderOption.setVisible(true);
    }

    /**
     * Sets handler to filter
     */
    public void setFilterHandler(Consumer<DeliveryFilterOption> con) {
        filterOption.setVisible(true);
        filterDelivered.setToggleGroup(toggleGroup);
        filterPending.setToggleGroup(toggleGroup);
        filterAll.setToggleGroup(toggleGroup);
        toggleGroup.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            switch (((RadioMenuItem) newVal).getText()) {
            case "Delivered":
                con.accept(DeliveryFilterOption.COM);
                break;
            case "Pending":
                con.accept(DeliveryFilterOption.PEN);
                break;
            default:
                con.accept(DeliveryFilterOption.ALL);
                break;
            }
        });
    }

    private void toggleOrder() {
        isSortByAsc = !isSortByAsc;
        if (isSortByAsc) {
            orderIndicator.setRotate(0);
        } else {
            orderIndicator.setRotate(180);
        }
    }

    @FXML
    void handleOrderByDelivered() {
        logger.info("[handleOrderByDelivered]");
        sortHandler.ifPresent(fun -> {
            toggleOrder();
            deliveryJobListView.setItems(fun.apply(DeliverySortOption.COM, isSortByAsc));
        });
    }

    @FXML
    void handleOrderByEarning() {
        logger.info("[handleOrderByEarning]");
        sortHandler.ifPresent(fun -> {
            toggleOrder();
            deliveryJobListView.setItems(fun.apply(DeliverySortOption.EARN, isSortByAsc));
        });
    }

    @FXML
    void handleOrderBySchedule() {
        logger.info("[handleOrderBySchedule]");
        sortHandler.ifPresent(fun -> {
            toggleOrder();
            deliveryJobListView.setItems(fun.apply(DeliverySortOption.DATE, isSortByAsc));
        });
    }
}
