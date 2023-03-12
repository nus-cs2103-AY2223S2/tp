package seedu.address.ui;
import javafx.collections.ObservableList;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Region;
import seedu.address.model.drug.Drug;

public class DrugPieChartPanel extends UiPart<Region>{
    private static final String FXML = "DrugPieChartPanel.fxml";

    public DrugPieChartPanel(ObservableList<Drug> drugList) {
        super(FXML);

    }
}
