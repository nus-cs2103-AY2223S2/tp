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
    private TableView<ScoreSummary> scoreStatistic;

    @FXML
    private TableColumn<ScoreSummary, Double> maxScore;
    @FXML
    private TableColumn<ScoreSummary, Double> minScore;
    @FXML
    private TableColumn<ScoreSummary, Double> average;
    @FXML
    private TableColumn<ScoreSummary, Double> percentage;

    /**
     * Creates a {@code ScoreListPanel} with the given {@code ObservableList}.
     *
     * @param student Selected student's scores.
     * @param tabNumber Which tab is being selected.
     */
    public ScoreListPanel(seedu.address.model.student.Student student, int tabNumber, Consumer<Integer> callBack) {
        super(FXML);

        name.setText("No student being checked now");
        nameChart.setText("No student being checked now");
        scoreStatistic.setVisible(false);

        scoreListView.setCellFactory(listView -> new ScoreListPanel.ScoreListViewCell());

        if (student != null) {
            scoreListView.setItems(student.getSortedScoreList());
            if (student.getSortedScoreList().size() != 0) {
                newChart(student);
                statisticTable(student);
            } else {
                name.setText("No score history found for " + student.getName().fullName);
                nameChart.setText("No score chart for " + student.getName().fullName);
            }
        }

        fullTabs.getSelectionModel().select(tabNumber);

        tab1.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (tab1.isSelected()) {
                    callBack.accept(0);
                }
            }
        });

        tab2.setOnSelectionChanged(new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                if (tab2.isSelected()) {
                    callBack.accept(1);
                }
            }
        });

    }

    private void statisticTable(seedu.address.model.student.Student student) {

        scoreStatistic.setVisible(true);

        maxScore.setCellValueFactory(new PropertyValueFactory<>("maxScore"));
        minScore.setCellValueFactory(new PropertyValueFactory<>("minScore"));
        average.setCellValueFactory(new PropertyValueFactory<>("average"));
        percentage.setCellValueFactory(new PropertyValueFactory<>("percentage"));

        maxScore.setSortable(false);
        minScore.setSortable(false);
        average.setSortable(false);
        percentage.setSortable(false);

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
                                setTextFill(Color.rgb(126, 190, 97));
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(244, 181, 55));
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(194, 47, 40));
                            }
                        }
                    }
                };
            }
        });

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
                                setTextFill(Color.rgb(126, 190, 97));
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(244, 181, 55));
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(194, 47, 40));
                            }
                        }
                    }
                };
            }
        });

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
                                setTextFill(Color.rgb(126, 190, 97));
                            } else if (50 <= scoreValue) {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(244, 181, 55));
                            } else {
                                setText(String.valueOf(scoreValue));
                                setTextFill(Color.rgb(194, 47, 40));
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
                                setTextFill(Color.rgb(126, 190, 97));
                            } else {
                                setText(String.valueOf(percentage));
                                setTextFill(Color.rgb(194, 47, 40));
                            }
                        }
                    }
                };
            }
        });

        scoreStatistic.setItems(student.getScoreSummary());

    }

    /**
     * Generates chart for a specific student.
     *
     * @param student Selected student.
     */
    private void newChart(seedu.address.model.student.Student student) {
        name.setText("Score history for " + student.getName().fullName);
        nameChart.setText("Recent 5 scores for " + student.getName().fullName);
        scoreChart.setVisible(true);
        scoreChart.setLegendVisible(false);
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        Region chartContent = (Region) scoreChart.lookup(".chart-content");
        for (Node node: chartContent.getChildrenUnmodifiable()) {
            if (node instanceof Group) {
                node.toFront();
                Group plotArea = (Group) node;
                plotArea.setClip(null);
            }
        }

        for (int i = 0; i < student.getRecentScoreList().size() && i < 5; i++) {
            String date = student.getRecentScoreList().get(i).getDate().toString();
            Double value = student.getRecentScoreList().get(i).getValue().value;
            String label = student.getRecentScoreList().get(i).getLabel().toString();
            XYChart.Data<String, Double> data = new XYChart.Data<>(date, value);
            data.setNode(new HoveredThresholdNode(data.getYValue(), label));
            series.getData().add(data);
        }

        scoreChart.getData().add(series);
    }

    /**
     * Displays "tooltip" when hover over a specific node.
     */
    private class HoveredThresholdNode extends StackPane {
        HoveredThresholdNode(Double scoreValue, String examLabel) {
            final Label label = createDataThresholdLabel(scoreValue, examLabel);

            setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().setAll(label);
                    setCursor(Cursor.NONE);
                    toFront();
                }
            });

            setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    getChildren().clear();
                    setCursor(Cursor.CROSSHAIR);
                }
            });
        }

        private Label createDataThresholdLabel(Double scoreValue, String examLabel) {
            final Label label = new Label(examLabel + "\n \t" + scoreValue);
            label.getStyleClass().addAll("chart-line-symbol", "chart-series-line");
            label.setStyle("-fx-font-size: 14; -fx-font-weight: bold; -fx-background-color: white; "
                    + "-fx-border-color: #605BF1; -fx-border-width: 2; ");

            if (scoreValue >= 80) {
                label.setTextFill(Color.rgb(126, 190, 97));
            } else if (50 <= scoreValue && scoreValue < 80) {
                label.setTextFill(Color.rgb(244, 181, 55));
            } else {
                label.setTextFill(Color.rgb(194, 47, 40));
            }

            label.setMinSize(Label.USE_PREF_SIZE, Label.USE_PREF_SIZE);
            return label;
        }
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
