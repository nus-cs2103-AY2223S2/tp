package seedu.careflow.ui;
import java.text.DecimalFormat;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import javafx.util.Duration;
import seedu.careflow.commons.core.LogsCenter;
import seedu.careflow.logic.CareFlowLogic;
import seedu.careflow.model.drug.Drug;


/**
 * Panel containing the drug pie chart
 */
public class DrugPieChartPanel extends UiPart<Region> {
    private static final String FXML = "DrugPieChartPanel.fxml";
    private static final Logger logger = LogsCenter.getLogger(DrugPieChartPanel.class);
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    @FXML
    private PieChart drugPieChart;

    /**
     * Displays the graphics of a {@code DrugPieChart}.
     */
    public DrugPieChartPanel(ObservableList<Drug> drugList, CareFlowLogic logic) {
        super(FXML);
        updatePieChartSegments(drugList);
        logic.getFilteredDrugList().addListener(new ListChangeListener<Drug>() {
            @Override
            public void onChanged(Change<? extends Drug> c) {
                updatePieChartSegments(logic.getFilteredDrugList());
            }
        });
    }

    private static double getTotal(ObservableList<PieChart.Data> pieChartData) {
        double total = 0;
        for (PieChart.Data data : pieChartData) {
            total += data.getPieValue();
        }
        return total;
    }

    private void updatePieChartSegments(ObservableList<Drug> drugList) {
        setupPieChartData(drugList);
        logger.info("Updating drug inventory analysis pie chart!");
        // set the percentage label format
        DecimalFormat df = new DecimalFormat("#.##");
        drugPieChart.setData(pieChartData);
        drugPieChart.setLegendSide(Side.RIGHT);

        drugPieChart.getData().forEach(data -> {
            data.setName(data.getName() + " ("
                    + df.format((data.getPieValue() / getTotal(pieChartData)) * 100) + "%)");
            data.getNode().addEventHandler(MouseEvent.MOUSE_ENTERED, new EventHandler<javafx.scene.input.MouseEvent>() {
                @Override
                public void handle(javafx.scene.input.MouseEvent event) {
                    Tooltip tooltip = new Tooltip();
                    tooltip.setShowDelay(Duration.seconds(0.1));
                    String value = ((int) data.getPieValue()) + " currently in storage.";
                    tooltip.setText(value);
                    Tooltip.install(data.getNode(), tooltip);
                }
            });
            Text text = new Text(data.getName());
            text.setStyle("-fx-font-size: 12; -fx-font-weight: bold");
            data.getNode().setUserData(text);
        });
    }

    private void setupPieChartData(ObservableList<Drug> drugList) {
        pieChartData.clear();
        for (int i = 0; i < drugList.size(); i++) {
            Drug drug = drugList.get(i);
            if (drug.getStorageCount().getCount() != 0) {
                PieChart.Data newData = new PieChart.Data(drug.getTradeName().toString(),
                        drug.getStorageCount().getCount());
                pieChartData.add(newData);
            }
        }
    }
}
