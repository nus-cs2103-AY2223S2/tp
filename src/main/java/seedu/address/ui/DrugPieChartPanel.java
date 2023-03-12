package seedu.address.ui;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import javafx.scene.text.Text;
import seedu.address.model.drug.Drug;

import java.text.DecimalFormat;
import java.util.Objects;

public class DrugPieChartPanel extends UiPart<Region>{
    private static final String FXML = "DrugPieChartPanel.fxml";
    private ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    @FXML
    private PieChart drugPieChart;


    public DrugPieChartPanel(ObservableList<Drug> drugList) {
        super(FXML);
        updatePieChartData(drugList);
    }

    private static double getTotal(ObservableList<PieChart.Data> pieChartData) {
        double total = 0;
        for (PieChart.Data data : pieChartData) {
            total += data.getPieValue();
        }
        return total;
    }

    private void updatePieChartData(ObservableList<Drug> drugList) {
        pieChartData.clear();
        for (int i = 0; i < drugList.size(); i++) {
            Drug drug =   drugList.get(i);
            PieChart.Data newData = new PieChart.Data(drug.getTradeName().toString(),
                    drug.getStorageCount().getCount());
            pieChartData.add(newData);
        }

        // set the percentage label format
        DecimalFormat df = new DecimalFormat("#.##");
        drugPieChart.setData(pieChartData);
        drugPieChart.getData().forEach(data -> {
            data.setName(data.getName() + " (" +
                    df.format((data.getPieValue() / getTotal(pieChartData)) * 100) + "%)");
        });

        // add drug names and percentage values to the pie chart
        for (PieChart.Data data : drugPieChart.getData()) {
            Text text = new Text(data.getName());
            text.setStyle("-fx-font-size: 12; -fx-font-weight: bold");
            data.getNode().setUserData(text);
        }
    }
}
