package seedu.address.ui;

import java.util.function.Consumer;
import java.util.logging.Logger;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.chart.Axis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.score.Score;
import seedu.address.model.score.UniqueScoreList.ScoreSummary;
import seedu.address.model.student.Student;


/**
 * Panel containing the list of scores.
 */

public class ScoreListPanel extends UiPart<Region> {
    private static final String FXML = "ScoreListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(ScoreListPanel.class);

    @FXML
    private ListView<Score> scoreListView;
    @FXML
    private Label name;
    @FXML
    private Label nameChart;
    @FXML
    private LineChart<String, Double> scoreChart;
    @FXML
    private Axis xAxis;
    @FXML
    private Axis yAxis;
    @FXML
    private TabPane fullTabs;
    @FXML
    private Tab tab1;
    @FXML
    private Tab tab2;
    @FXML
    private ScrollPane scoreScroll;

    @FXML
    private TableView<ScoreSummary> scoreStatistic;

    @FXML
    private TableColumn<ScoreSummary, Double> maxScore;
    @FXML
    private TableColumn<ScoreSummary, Double> minScore;
    @FXML
    private TableColumn<ScoreSummary, Double> average;
    @FXML
    private TableColumn<ScoreSummary, Double> percentage;

    private final Color green = Color.rgb(126, 190, 97);
    private final Color yellow = Color.rgb(244, 181, 55);
    private final Color red = Color.rgb(194, 47, 40);

    /**
     * Creates a {@code ScoreListPanel} with the given {@code Student}, tabNumber, and callBack.
     *
     * @param student The student.
     * @param tabNumber The tab to be displayed.
     * @param callBack Callback method to trigger main window update.
     */
    public ScoreListPanel(Student student, int tabNumber, Consumer<Integer> callBack) {
        super(FXML);

        name.setText("No student being checked now");
        nameChart.setText("No student being checked now");
        scoreScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scoreStatistic.setVisible(false);
        scoreListView.setCellFactory(listView -> new ScoreListPanel.ScoreListViewCell());

        scoreListPanelDisplay(student);

        fullTabs.getSelectionModel().select(tabNumber);

        tab1.setOnSelectionChanged(switchTab(tab1, 0, callBack));

        tab2.setOnSelectionChanged(switchTab(tab2, 1, callBack));
    }

    private EventHandler<Event> switchTab(Tab tab, int i, Consumer<Integer> callBack) {
        return new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (tab.isSelected()) {
                    callBack.accept(i);
                }
            }
        };
    }

    private void scoreListPanelDisplay(Student student) {
        if (student != null) {
            scoreListView.setItems(student.getSortedScoreList());
            if (student.getSortedScoreList().size() != 0) {
                newChart(student);
                statisticTable(student);
                scoreScroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
            } else {
                name.setText("No score history found for " + student.getName().fullName);
                nameChart.setText("No score chart for " + student.getName().fullName);
                if (student.getName().fullName.length() >= 30) {
                    name.setText("No score history found for " + student.getName().fullName.substring(0, 29) + "...");
                    nameChart.setText("No score chart for " + student.getName().fullName.substring(0, 29) + "...");
                }
            }
        }
    }

    /**
     * Generates tableview to show the summary statistic of recent 5 scores.
     *
     * @param student Selected student.
     */
    private void statisticTable(Student student) {

        scoreStatistic.setVisible(true);

        maxScore.setCellValueFactory(new PropertyValueFactory<>("maxScore"));
        minScore.setCellValueFactory(new PropertyValueFactory<>("minScore"));
        average.setCellValueFactory(new PropertyValueFactory<>("average"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        maxScore.setSortable(false);
        minScore.setSortable(false);
        average.setSortable(false);
        percentage.setSortable(false);

        // Due to the nature of callback and setCellFactory,
        // it will be difficult to extract them out as a single method.
        maxScore.setCellFactory(new Callback<TableColumn<ScoreSummary, Double>, TableCell<ScoreSummary, Double>>() {
            public TableCell<ScoreSummary, Double> call(TableColumn<ScoreSummary, Double> param) {
                return new TableCell<ScoreSummary, Double>() {
                    @Override
                    public void updateItem(Double value, boolean empty) {
                        super.updateItem(value, empty);
                        Double scoreValue = param.getTableView().getItems().get(0).getMaxScore();
                        if (!empty) {
                            if (scoreValue >= 80) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(green);
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(yellow);
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(red);
                            }
                        }
                    }
                };
            }
        });

        // Due to the nature of callback and setCellFactory,
        // it will be difficult to extract them out as a single method.
        minScore.setCellFactory(new Callback<TableColumn<ScoreSummary, Double>, TableCell<ScoreSummary, Double>>() {
            public TableCell<ScoreSummary, Double> call(TableColumn<ScoreSummary, Double> param) {
                return new TableCell<ScoreSummary, Double>() {
                    @Override
                    public void updateItem(Double value, boolean empty) {
                        super.updateItem(value, empty);
                        Double scoreValue = param.getTableView().getItems().get(0).getMinScore();
                        if (!empty) {
                            if (scoreValue >= 80) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(green);
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(yellow);
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(red);
                            }
                        }
                    }
                };
            }
        });

        // Due to the nature of callback and setCellFactory,
        // it will be difficult to extract them out as a single method.
        average.setCellFactory(new Callback<TableColumn<ScoreSummary, Double>, TableCell<ScoreSummary, Double>>() {
            public TableCell<ScoreSummary, Double> call(TableColumn<ScoreSummary, Double> param) {
                return new TableCell<ScoreSummary, Double>() {
                    @Override
                    public void updateItem(Double value, boolean empty) {
                        super.updateItem(value, empty);
                        Double scoreValue = param.getTableView().getItems().get(0).getAverage();
                        if (!empty) {
                            if (scoreValue >= 80) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(green);
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(yellow);
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(red);
                            }
                        }
                    }
                };
            }
        });

        percentage.setCellFactory(new Callback<TableColumn<ScoreSummary, Double>, TableCell<ScoreSummary, Double>>() {
            public TableCell<ScoreSummary, Double> call(TableColumn<ScoreSummary, Double> param) {
                return new TableCell<ScoreSummary, Double>() {
                    @Override
                    public void updateItem(Double value, boolean empty) {
                        super.updateItem(value, empty);
                        Double percentage = param.getTableView().getItems().get(0).getPercentage();
                        if (!empty) {
                            if (percentage >= 0) {
                                setText(String.valueOf(percentage));
                                setTextFill(green);
                            } else {
                                setText(String.valueOf(percentage));
                                setTextFill(red);
                            }
                        }
                    }
                };
            }
        });

        scoreStatistic.setItems(student.getScoreSummary());

    }

    /**
     * Generates chart for a student.
     *
     * @param student Selected student.
     */

    private void newChart(Student student) {
        name.setText("Score history for " + student.getName().fullName);
        nameChart.setText("Recent scores for " + student.getName().fullName + " (at most 5)");
        if (student.getName().fullName.length() >= 30) {
            name.setText("Score history for " + student.getName().fullName.substring(0, 29) + "...");
            nameChart.setText("Recent scores for " + student.getName().fullName.substring(0, 29) + "..."
                    + " (at most 5)");
        }
        scoreChart.setVisible(true);
        scoreChart.setLegendVisible(false);
        chartNode();
        constructChart(student);
    }

    private void chartNode() {
        Region chartContent = (Region) scoreChart.lookup(".chart-content");

        //The for loop seems to have an arrow pattern, but the code should not be further split anymore.
        for (Node node: chartContent.getChildrenUnmodifiable()) {
            if (node instanceof Group) {
                node.toFront();
                Group plotArea = (Group) node;
                plotArea.setClip(null);
            }
        }
    }

    private void constructChart(Student student) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        for (int i = 0; i < student.getRecentScoreList().size() && i < 5; i++) {
            String date = student.getRecentScoreList().get(i).getDate().toString();
            Double value = student.getRecentScoreList().get(i).getValue().value;
            String label = student.getRecentScoreList().get(i).getLabel().toString();
            XYChart.Data<String, Double> data = new XYChart.Data<>(date, value);
            data.setNode(new HoveredThresholdNode(data.getYValue(), label));
            series.getData().add(data);
        }

        for (XYChart.Data entry : series.getData()) {
            entry.getNode().setStyle("-fx-background-color: #FF94B4, white; -fx-background-radius: 6;");
        }

        scoreChart.getData().add(series);
    }

    /**
     * Displays "tooltip" when hover over a specific node.
     */
    private class HoveredThresholdNode extends StackPane {
        private HoveredThresholdNode(Double scoreValue, String examLabel) {
            final Label label = createDataThresholdLabel(scoreValue, examLabel);

            //The setOnMouseEntered seems to have an arrow pattern, but the code should not be further split anymore.
            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().setAll(label);
                    setCursor(Cursor.NONE);
                    toFront();
                }
            });

            //The setOnMouseExited seems to have an arrow pattern, but the code should not be further split anymore.
            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().clear();
                    setCursor(Cursor.CROSSHAIR);
                }
            });
        }
    }

    /**
     * Constructs data threshold label.
     *
     * @param scoreValue The score value.
     * @param examLabel The name of the exam.
     * @return A label that contains exam label and exam score value.
     */
    private Label createDataThresholdLabel(Double scoreValue, String examLabel) {
        final Label label = new Label(examLabel + ": " + "\n" + scoreValue);
        label.getStyleClass().addAll("chart-line-symbol", "chart-series-line");
        label.setStyle("-fx-font-size: 12; -fx-font-weight: bold; -fx-background-color: white; "
                + "-fx-border-color: #FF94B4; -fx-border-width: 2; -fx-alignment: center");

        if (scoreValue >= 80) {
            label.setTextFill(green);
        } else if (50 <= scoreValue && scoreValue < 80) {
            label.setTextFill(yellow);
        } else {
            label.setTextFill(red);
        }

        if (examLabel.length() >= 12) {
            label.setText(examLabel.substring(0, 11) + "..." + "\n" + scoreValue);
        }

        label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);

        return label;
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Score} using a {@code ScoreCard}.
     */
    class ScoreListViewCell extends ListCell<Score> {
        @Override
        protected void updateItem(Score score, boolean empty) {
            super.updateItem(score, empty);

            if (empty || score == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new ScoreCard(score, getIndex() + 1).getRoot());
            }
        }
    }
}
