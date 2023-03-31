package seedu.address.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import seedu.address.model.tank.Tank;
import seedu.address.model.tank.readings.AmmoniaLevel;
import seedu.address.model.tank.readings.PH;
import seedu.address.model.tank.readings.Reading;
import seedu.address.model.tank.readings.Temperature;
import seedu.address.model.tank.readings.UniqueIndividualReadingLevels;

/**
 * An UI component that displays information of a {@code Tank}.
 */
public class TankCard extends UiPart<Region> {

    private static final String FXML = "TankListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Tank tank;

    private final LineChart<Number, Number> ammoniaChart;
    private final LineChart<Number, Number> phChart;
    private final LineChart<Number, Number> temperatureChart;

    @FXML
    private HBox cardPane;
    @FXML
    private VBox vBox;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label numberOfFish;
    @FXML
    private Label alignment;
    @FXML
    private GridPane readingsSummary;

    /**
     * Creates a {@code TankCard} with the given {@code Tank} and index to display.
     */
    public TankCard(Tank tank, int displayedIndex) {
        super(FXML);
        this.tank = tank;
        id.setText(displayedIndex + ". ");
        //alignment.setText(displayedIndex + ".  ");
        name.setText(tank.getTankName().toString());
        numberOfFish.setText("x" + tank.getFishList().size());

        UniqueIndividualReadingLevels readingLevels = tank.getReadingLevels();
        ObservableList<AmmoniaLevel> ammoniaLevels = readingLevels.asUnmodifiableObservableListAmmonia();
        ObservableList<PH> phLevels = readingLevels.asUnmodifiableObservableListPH();
        ObservableList<Temperature> temperatureLevels = readingLevels.asUnmodifiableObservableListTemp();
        ammoniaChart = createLineChart(ammoniaLevels, 0, 4);
        ammoniaChart.setId("ammoniaChart");
        ammoniaChart.getXAxis().setLabel("Ammonia (ppm)");
        phChart = createLineChart(phLevels, 0, 14);
        phChart.setId("phChart");
        phChart.getXAxis().setLabel("pH (pH)");
        temperatureChart = createLineChart(temperatureLevels, 10, 40);
        temperatureChart.setId("temperatureChart");
        temperatureChart.getXAxis().setLabel("Temperature (˚C)");

        readingsSummary.add(ammoniaChart, 0, 0);
        readingsSummary.add(phChart, 0, 1);
        readingsSummary.add(temperatureChart, 1, 0);
    }

    private LineChart<Number, Number> createLineChart(
            ObservableList<? extends Reading> readings, double lowerBound, double upperBound) {
        XYChart.Series<Number, Number> series = new XYChart.Series<>();
        for (int i = 0; i < readings.size(); i++) {
            series.getData().add(new XYChart.Data<>(i + 1, readings.get(i).getValue()));
        }
        NumberAxis xAxis = new NumberAxis(1, readings.size(), 1);
        xAxis.setTickLabelsVisible(false);
        NumberAxis yAxis = new NumberAxis(lowerBound, upperBound, upperBound - lowerBound);
        LineChart<Number, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.getData().add(series);
        lineChart.setPrefHeight(100);
        lineChart.setMinHeight(100);
        lineChart.setPrefWidth(50);
        lineChart.setMinWidth(50);
        lineChart.setCreateSymbols(false);
        lineChart.setAnimated(false);
        return lineChart;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TankCard)) {
            return false;
        }

        // state check
        TankCard card = (TankCard) other;
        return id.getText().equals(card.id.getText())
                && tank.equals(card.tank);
    }
}
