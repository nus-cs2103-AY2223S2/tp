package seedu.modtrek.ui.progresssection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import seedu.modtrek.model.degreedata.DegreeProgressionData;
import seedu.modtrek.model.tag.Tag;
import seedu.modtrek.model.tag.ValidTag;

/**
 * A doughnut chart that displays summary statistics of the degree progress.
 * Solution below adapted from https://stackoverflow.com/questions/24121580/
 */
public class DoughnutChart extends PieChart {
    private DegreeProgressionData degreeProgressionData;

    /**
     * The center label of the doughnut (displaying % completion, CAP).
     */
    private StackPane doughnutCenterLabel;
    private Circle innerCircle;

    /**
     * The individual tag labels for each degree requirement.
     */
    private VBox[] dataLabels;

    /**
     * Instantiates a new doughnut chart.
     * @param degreeProgressionData The data to be fed into the doughnut chart and displayed visually.
     */
    public DoughnutChart(DegreeProgressionData degreeProgressionData) {
        super(createDoughnutData(degreeProgressionData));

        this.degreeProgressionData = degreeProgressionData;

        applyChartStyles();

        createCenterLabel();
        createDataLabels();
        addLabels();
    }

    /**
     * Feeds degree progression data into the doughnut chart.
     * @param degreeProgressionData The degree progression data.
     * @return The data for each arc area of the doughnut chart.
     */
    private static ObservableList<PieChart.Data> createDoughnutData(DegreeProgressionData degreeProgressionData) {
        ObservableList<PieChart.Data> doughnutData = FXCollections.observableArrayList();

        Map<String, Integer> completeCreditsMap = degreeProgressionData.getCompletedRequirementCredits();
        Map<String, Integer> totalCreditsMap = degreeProgressionData.getTotalRequirementCredits();

        List<String> tags = ValidTag.getTags();
        for (String tag : tags) {
            String shortFormTag = ValidTag.getShortForm(tag).toString();
            int completeCredits = Math.min(completeCreditsMap.get(shortFormTag), totalCreditsMap.get(shortFormTag));
            int incompleteCredits = totalCreditsMap.get(shortFormTag) - completeCredits;
            doughnutData.add(new PieChart.Data(tag, completeCredits));
            doughnutData.add(new PieChart.Data(tag, incompleteCredits));
        }

        return doughnutData;
    }

    /**
     * {@inheritDoc}
     * Also correctly positions the center label (in the center of the doughnut) and data labels (individual
     * tag labels).
     * @param top {@inheritDoc}
     * @param left {@inheritDoc}
     * @param contentWidth {@inheritDoc}
     * @param contentHeight {@inheritDoc}
     */
    @Override
    protected void layoutChartChildren(double top, double left, double contentWidth, double contentHeight) {
        super.layoutChartChildren(top, left, contentWidth, contentHeight);

        updateCenterLabelLayout();
        updateDataLabelsLayout();
    }

    /**
     * Adds center and data labels on the doughnut chart.
     */
    private void addLabels() {
        addNodeToChart(doughnutCenterLabel);
        for (VBox dataLabel : dataLabels) {
            addNodeToChart(dataLabel);
        }
    }

    /**
     * Creates the center label of the doughnut chart.
     * The center label showcases the percentage completion of the degree progress and CAP.
     */
    private void createCenterLabel() {
        innerCircle = new Circle();
        innerCircle.setStyle("-fx-fill: -black");

        if (degreeProgressionData.isValid()) {
            Text percent = new Text(degreeProgressionData.getOverallPercentage() + "% completed");
            percent.setStyle("-fx-fill: -teal;");
            percent.getStyleClass().add("h2");

            Text totalMc = new Text(degreeProgressionData.getMeaningfulCredit() + "/"
                    + DegreeProgressionData.TOTALCREDIT + " MCs");
            totalMc.setStyle("-fx-fill: -white;");
            totalMc.getStyleClass().add("h3");

            Text cap = new Text("CAP: " + degreeProgressionData.getGpa());
            cap.setStyle("-fx-fill: -white;");
            cap.getStyleClass().add("h3");

            VBox labels = new VBox(percent, totalMc, cap);
            labels.getStyleClass().add("doughnut-center-label");

            doughnutCenterLabel = new StackPane(innerCircle, labels);
        } else {
            Text invalid = new Text("INVALID MODULES");
            invalid.setStyle("-fx-fill: -white;");
            invalid.getStyleClass().add("h3");

            Text helpText = new Text("Input `view progress`\nto find out more!");
            helpText.setStyle("-fx-fill: -white;");
            helpText.getStyleClass().add("h5");

            VBox labels = new VBox(invalid, helpText);
            labels.getStyleClass().add("doughnut-center-label");

            doughnutCenterLabel = new StackPane(innerCircle, labels);
        }
    }

    /**
     * Creates the data labels of the doughnut chart.
     */
    private void createDataLabels() {
        dataLabels = new VBox[Tag.NUM_TAGS];

        Map<String, String> labelTexts = getDataLabelTexts();

        ObservableList<Data> doughnutData = getData();
        assert doughnutData.size() == Tag.NUM_TAGS * 2 : "Number of divisions of doughnut chart should be 12.";
        for (int i = 0; i < doughnutData.size(); i += 2) {
            Data completeData = doughnutData.get(i);

            String tag = ValidTag.getShortForm(completeData.getName()).toString();
            Text completeLabel = new Text(labelTexts.get(tag));
            completeLabel.getStyleClass().addAll("p1");
            completeLabel.setStyle("-fx-fill: -tag-" + ValidTag.getColor(tag) + ";");
            completeLabel.setTextAlignment(TextAlignment.CENTER);

            VBox dataLabel = new VBox(completeLabel);
            dataLabel.getStyleClass().add("data-label");
            dataLabels[i / 2] = dataLabel;
        }
    }


    /**
     * Positions the center label correctly such that it is in the center
     * of the doughnut chart.
     */
    private void updateCenterLabelLayout() {
        Map<String, Double> chartProperties = getChartProperties();
        double centerX = chartProperties.get("centerX");
        double centerY = chartProperties.get("centerY");
        double radius = chartProperties.get("radius");

        doughnutCenterLabel.setLayoutX(centerX);
        doughnutCenterLabel.setLayoutY(centerY);

        final double scale = 1.3;
        innerCircle.setRadius(radius / scale);
    }

    /**
     * Positions each data label correctly such that it is aligned to its corresponding
     * arc area on the doughnut chart.
     */
    private void updateDataLabelsLayout() {
        Map<String, Double> angles = getDataLabelAngles();

        final double offsetScale = 1.4;
        Map<String, Double> chartProperties = getChartProperties();
        double centerX = chartProperties.get("centerX");
        double centerY = chartProperties.get("centerY");
        double radius = chartProperties.get("radius") * offsetScale;

        ObservableList<Data> doughnutData = getData();
        assert dataLabels.length == doughnutData.size() / 2
                : "Number of data labels should be half the number of doughnut data";
        for (int i = 0; i < doughnutData.size(); i += 2) {
            VBox dataLabel = dataLabels[i / 2];
            Data completeData = doughnutData.get(i);
            String tag = ValidTag.getShortForm(completeData.getName()).toString();

            double angle = angles.get(tag);
            double xCoord = centerX + radius * Math.cos(angle);
            double yCoord = centerY + radius * Math.sin(angle);

            dataLabel.setLayoutX(xCoord);
            dataLabel.setLayoutY(yCoord);
        }
    }

    /**
     * Sets the CSS styles of the doughnut chart and its arc areas.
     */
    private void applyChartStyles() {
        super.setLegendVisible(false);
        super.setLabelsVisible(false);

        /* Change colors of doughnut chart */
        ObservableList<Data> doughnutData = getData();
        assert doughnutData.size() == Tag.NUM_TAGS * 2 : "Number of divisions of doughnut chart should be 12.";
        for (int i = 0; i < doughnutData.size(); i += 2) {
            Data completeData = doughnutData.get(i);
            Data incompleteData = doughnutData.get(i + 1);

            Node completeDataNode = completeData.getNode();
            String tag = ValidTag.getShortForm(completeData.getName()).toString();
            completeDataNode.setStyle(
                    "-fx-background-color: -tag-" + ValidTag.getColor(tag) + "; "
                            + "-fx-border-width: 0;"
            );

            Node incompleteDataNode = incompleteData.getNode();
            if (degreeProgressionData.isNoneCompleted()) {
                incompleteDataNode.setStyle(
                        "-fx-background-color: -tag-" + ValidTag.getColor(tag) + "; "
                                + "; -fx-opacity: 0.25;" + "-fx-border-width: 0;"
                );
            } else {
                incompleteDataNode.setStyle("-fx-background-color: -black; -fx-border-width: 0;");
            }

        }
    }


    /* HELPER FUNCTIONS */

    /**
     * Adds a JavaFX Node to the doughnut chart's topmost component.
     * @param node The JavaFX Node to be added.
     */
    private void addNodeToChart(Node node) {
        if (getData().size() > 0) {
            Node pie = getData().get(0).getNode();
            if (pie.getParent() instanceof Pane) {
                Pane parent = (Pane) pie.getParent();

                if (!parent.getChildren().contains(node)) {
                    parent.getChildren().add(node);
                }
            }
        }
    }

    /**
     * Calculates and obtains some of the doughnut chart's geometric properties.
     * @return The x and y coordinates of the center, and radius of the doughnut chart, in the
     *     form of a hashmap.
     */
    private Map<String, Double> getChartProperties() {
        //@@author jmestxr-reused
        //Reused from https://stackoverflow.com/questions/24121580/ with minor modifications
        double minX = Double.MAX_VALUE;
        double minY = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double maxY = Double.MIN_VALUE;

        for (Data data : getData()) {
            Node node = data.getNode();

            Bounds bounds = node.getBoundsInParent();
            if (bounds.getMinX() < minX) {
                minX = bounds.getMinX();
            }
            if (bounds.getMinY() < minY) {
                minY = bounds.getMinY();
            }
            if (bounds.getMaxX() > maxX) {
                maxX = bounds.getMaxX();
            }
            if (bounds.getMaxY() > maxY) {
                maxY = bounds.getMaxY();
            }
        }

        double centerX = minX + (maxX - minX) / 2;
        double centerY = minY + (maxY - minY) / 2;
        double radius = (maxX - minX) / 2;

        Map<String, Double> properties = new HashMap<>();
        properties.put("centerX", centerX);
        properties.put("centerY", centerY);
        properties.put("radius", radius);

        return properties;
    }

    /**
     * Calculates and obtains the positional angles for all 6 data label components, so that
     * each data label component can be aligned to its corresponding arc area on the doughnut chart.
     * @return The positional angles for each data label.
     */
    private Map<String, Double> getDataLabelAngles() {
        Map<String, Double> angles = new HashMap<>();

        double accCredits = 0;

        double fullAngle = 2 * Math.PI;

        Map<String, Integer> totalCreditsMap = degreeProgressionData.getTotalRequirementCredits();

        ObservableList<Data> doughnutData = getData();
        assert doughnutData.size() == Tag.NUM_TAGS * 2 : "Number of divisions of doughnut chart should be 12.";
        for (int i = 0; i < doughnutData.size(); i += 2) {
            Data completeData = doughnutData.get(i);

            String tag = ValidTag.getShortForm(completeData.getName()).toString();

            double totalCredits = totalCreditsMap.get(tag);

            double offset = totalCredits / 2;
            offset += accCredits;

            double angle = (offset / degreeProgressionData.getTotalWithDuplicatedCredit()) * fullAngle;
            angles.put(tag, angle);

            accCredits += totalCredits;
        }

        return angles;
    }


    /**
     * Gets the labels for each degree requirement, to be displayed alongside the corresponding
     * arc area.
     * @return The labels for each degree requirement.
     */
    private Map<String, String> getDataLabelTexts() {
        Map<String, String> tagLongDisplay = new HashMap<>();
        tagLongDisplay.put("ULR", "University Level\nRequirements");
        tagLongDisplay.put("CSF", "CS Foundation");
        tagLongDisplay.put("CSBD", "CS\nBreadth & Depth");
        tagLongDisplay.put("ITP", "IT\nProfessionalism");
        tagLongDisplay.put("UE", "Unrestricted\nElectives");
        tagLongDisplay.put("MS", "Mathematics &\nSciences");

        Map<String, String> texts = new HashMap<>();

        Map<String, Integer> requirementsPercentage = degreeProgressionData.getRequirementsPercentage();

        ObservableList<Data> doughnutData = getData();

        assert doughnutData.size() == Tag.NUM_TAGS * 2 : "Number of divisions of doughnut chart should be 12.";
        for (int i = 0; i < doughnutData.size(); i += 2) {
            Data completeData = doughnutData.get(i);

            String tag = ValidTag.getShortForm(completeData.getName()).toString();

            int percentCompleted = requirementsPercentage.get(tag);

            String text = tagLongDisplay.get(tag) + "\n" + percentCompleted + "%";
            texts.put(tag, text);
        }

        return texts;
    }
}
