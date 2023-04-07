package seedu.careflow.ui;

import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import seedu.careflow.logic.CareFlowLogic;
import seedu.careflow.model.drug.Drug;

/**
 * Panel containing the list of drugs.
 */
public class DrugListPanel extends UiPart<Region> {
    private static final String FXML = "DrugListPanel.fxml";

    @FXML
    private ListView<Drug> drugListView;

    @FXML
    private ScrollPane drugDetailDisplay;

    @FXML
    private VBox drugDetailContainer;

    @FXML
    private Label activeIngredientField;
    @FXML
    private Label directionField;
    @FXML
    private Label purposesField;
    @FXML
    private Label sideEffectsField;
    @FXML
    private Label storageCountField;

    @FXML
    private Label selectedTradeName;
    @FXML
    private Label selectedActiveIngredient;
    @FXML
    private Label selectedDirection;
    @FXML
    private Label selectedPurposes;
    @FXML
    private Label selectedSideEffects;
    @FXML
    private Label selectedStorageCount;

    /**
     * Creates a {@code DrugListPanel} with the given {@code ObservableList}.
     */
    public DrugListPanel(ObservableList<Drug> drugList, CareFlowLogic logic) {
        super(FXML);
        // DRUG LIST
        drugListView.setItems(drugList);
        drugListView.setCellFactory(listView -> new DrugListViewCell());
        // listener for click events on drug cards in drugListView
        setClickEventListener();
        // listener for changes occur inside the ObservableList
        setUpdateEventListener(logic);

        setupStyle();
    }

    /**
     * Set up event listener to listen to updates within the ObservableList
     */
    private void setUpdateEventListener(CareFlowLogic logic) {
        logic.getFilteredDrugList().addListener(new ListChangeListener<Drug>() {
            @Override
            public void onChanged(Change<? extends Drug> c) {
                Drug selectedDrug = drugListView.getSelectionModel().getSelectedItem();
                if (selectedDrug != null) {
                    updateDisplay(selectedDrug);
                } else {
                    updateNullDisplay();
                }
            }
        });
    }

    /**
     * Set up event listener to listen to click events within patientListView
     */
    private void setClickEventListener() {
        drugListView.setOnMouseClicked(event -> {
            Drug selectedDrug = drugListView.getSelectionModel().getSelectedItem();
            updateDisplay(selectedDrug);
        });
    }

    /**
     * Update the display inside {@code drugDetailDisplay} in response to changes inside the ObservableList or click
     * @param selectedDrug the drug to be displayed
     */
    public void updateDisplay(Drug selectedDrug) {
        Label[] fields = new Label[]{
            activeIngredientField, directionField, purposesField,
            sideEffectsField, storageCountField};
        Label[] details = new Label[]{selectedTradeName, selectedActiveIngredient,
            selectedDirection, selectedPurposes,
            selectedSideEffects, selectedStorageCount};
        setupStyle();
        setDrugFieldsDisplay(fields);
        updateDisplayedDrugDetail(selectedDrug, details);
    }

    /**
     * Update the display inside {@code drugDetailDisplay} in response to view command
     * @param selectedDrug the drug to be displayed
     */
    public void updateViewCommandDisplay(Drug selectedDrug) {
        drugListView.getSelectionModel().select(drugListView.getItems().indexOf(selectedDrug));
        Label[] fields = new Label[]{
            activeIngredientField, directionField, purposesField,
            sideEffectsField, storageCountField};
        Label[] details = new Label[]{selectedTradeName, selectedActiveIngredient,
            selectedDirection, selectedPurposes,
            selectedSideEffects, selectedStorageCount};
        setupStyle();
        setDrugFieldsDisplay(fields);
        updateDisplayedDrugDetail(selectedDrug, details);
    }

    /**
     * Set drug display to empty
     */
    public void updateNullDisplay() {
        selectedTradeName.setText("Please select a drug");
        selectedActiveIngredient.setText("");
        selectedDirection.setText("");
        selectedStorageCount.setText("");
        selectedPurposes.setText("");
        selectedSideEffects.setText("");
        activeIngredientField.setText("");
        directionField.setText("");
        purposesField.setText("");
        sideEffectsField.setText("");
        storageCountField.setText("");
    }

    /**
     * Set up the display of relevant Drug fields
     * @param fields fields to be displayed
     */
    private void setDrugFieldsDisplay(Label[] fields) {
        activeIngredientField.setText("Active Ingredient");
        directionField.setText("Direction");
        purposesField.setText("Purposes");
        sideEffectsField.setText("Side Effects");
        storageCountField.setText("Storage Count");
        for (Label field : fields) {
            field.setBackground(new Background(new BackgroundFill(Color.BLACK, new CornerRadii(4),
                    new Insets(-3, -10, -3, -10))));
            VBox parent = (VBox) field.getParent();
            parent.setMargin(field, new Insets(0, 0, 10, 0));
        }
    }

    /**
     * Update the detail information to be displayed inside {@code drugDetailDisplay}
     * @param selectedDrug the drug whose information is to be displayed
     * @param details the Labels to hold the information of the selected Drug
     */
    private void updateDisplayedDrugDetail(Drug selectedDrug, Label[] details) {
        selectedTradeName.setText(selectedDrug.getTradeName().tradeName);
        selectedTradeName.setPadding(new Insets(0, -10, 0, -10));
        selectedActiveIngredient.setText(selectedDrug.getActiveIngredient().value);
        selectedDirection.setText(selectedDrug.getDirection().value);
        selectedPurposes.setText(selectedDrug.getPurposes().purpose);
        selectedSideEffects.setText(selectedDrug.getSideEffects().sideEffect);
        selectedStorageCount.setText(selectedDrug.getStorageCount().toString());
        for (Label detail : details) {
            detail.setWrapText(true);
            detail.setMinWidth(0);
        }
    }


    /**
     * Set up the styling and spacing of {@code drugDetailDisplay} region
     */
    private void setupStyle() {
        drugDetailContainer.setSpacing(10);
        drugDetailContainer.setPadding(new Insets(10, 0, 0, 20));
    }


    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Drug} using a {@code DrugCard}.
     */
    static class DrugListViewCell extends ListCell<Drug> {

        @Override
        protected void updateItem(Drug drug, boolean empty) {
            super.updateItem(drug, empty);

            if (empty || drug == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new DrugCard(drug, getIndex() + 1).getRoot());
            }
        }
    }
}
