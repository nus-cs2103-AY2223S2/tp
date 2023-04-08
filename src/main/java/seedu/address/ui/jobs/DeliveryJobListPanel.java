package seedu.address.ui.jobs;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.collections.ListChangeListener;
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

    private Optional<BiConsumer<Integer, DeliveryJob>> onSelectHandler = Optional.empty();
    private Optional<Consumer<DeliveryJob>> onCheckHandler = Optional.empty();
    private Optional<Consumer<DeliveryJob>> onDeleteHandler = Optional.empty();
    private Optional<BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>>> sortHandler = Optional
            .empty();

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

    private EventHandler<KeyEvent> listKeyEventHandler = new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().equals(KeyCode.DELETE)) {
                onDeleteHandler.ifPresent(handler -> {
                    logger.info("[DeliveryJobListPanel] KeyPressed: "
                            + deliveryJobListView.getSelectionModel().getSelectedIndex());
                    handler.accept(deliveryJobListView.getSelectionModel().getSelectedItem());
                });
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
    };

    private ListChangeListener<DeliveryJob> listChangeHandler = new ListChangeListener<DeliveryJob>() {
        @Override
        public void onChanged(Change<? extends DeliveryJob> c) {
            logger.info("[DeliveryJobListPanel] list update event");
            selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
        }
    };

    private EventHandler<Event> listClickEventHandler = new EventHandler<Event>() {
        @Override
        public void handle(Event event) {
            logger.info("[DeliveryJobListPanel] MouseClicked: "
                    + deliveryJobListView.getSelectionModel().getSelectedIndex());
            selectItem(deliveryJobListView.getSelectionModel().getSelectedIndex());
        }
    };

    /**
     * Creates a {@code DeliveryJobListPanel} with the given {@code ObservableList}.
     */
    public DeliveryJobListPanel(ObservableList<DeliveryJob> jobList) {
        super(FXML);
        deliveryJobListView.setItems(jobList);
        deliveryJobListView.setCellFactory(listView -> new DeliveryJobListViewCell());

        deliveryJobListView.setOnMouseClicked(listClickEventHandler);

        deliveryJobListView.setOnKeyPressed(listKeyEventHandler);
        jobList.addListener(listChangeHandler);
    }

    /**
     * Selects first applicable item in the job list.
     *
     */
    public void selectDefault() {
        selectItem(0);
    }

    /**
     * Selects the item in the job list.
     *
     * @param idx
     */
    private void selectItem(int idx) {
        if (onSelectHandler.isEmpty()) {
            return;
        }
        if (deliveryJobListView.getItems().size() > 0) {
            logger.info("[selectItem] selected:" + deliveryJobListView.getSelectionModel().getSelectedIndex());
            if (idx < 0) {
                idx = 0;
            }
            if (onSelectHandler.isPresent()) {
                onSelectHandler.get().accept(idx + 1, deliveryJobListView.getItems().get(idx));
            }
        } else {
            if (onSelectHandler.isPresent()) {
                onSelectHandler.get().accept(-1, null);
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
                    onCheckHandler.ifPresent(handler -> handler.accept(check));
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
     * Sets the select handler.
     *
     * @param handler
     */
    public void setSelectHandler(BiConsumer<Integer, DeliveryJob> handler) {
        assert handler != null;

        this.onSelectHandler = Optional.of(handler);
    }

    /**
     * Sets the check status complete handler.
     *
     * @param handler
     */
    public void setCheckHandler(Consumer<DeliveryJob> handler) {
        assert handler != null;

        this.onCheckHandler = Optional.of(handler);
    }

    /**
     * Sets the delete handler.
     *
     * @param handler
     */
    public void setDeleteHandler(Consumer<DeliveryJob> handler) {
        assert handler != null;

        this.onDeleteHandler = Optional.of(handler);
    }

    /**
     * Sets the order event handlers.
     *
     * @param handler
     */
    public void setOrderByHandler(BiFunction<DeliverySortOption, Boolean, ObservableList<DeliveryJob>> handler) {
        assert handler != null;

        sortHandler = Optional.of(handler);
        orderOption.setVisible(true);
    }

    /**
     * Sets handler to filter
     */
    public void setFilterHandler(Consumer<DeliveryFilterOption> con) {
        assert con != null;

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
