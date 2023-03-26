package seedu.address.ui.timetable;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.Logic;
import seedu.address.ui.UiPart;

/**
 * Controller for a timetable page
 */
public class DayOfWeekPanel extends UiPart<Region> {

    private static final String FXML = "DayOfWeekPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(getClass());
    private Stage primaryStage;
    private Logic logic;

    private Text mon;
    private Text tue;
    private Text wed;
    private Text thur;
    private Text fri;
    private Text sat;
    private Text sun;

    @FXML
    private HBox dayOfWeekPanel;

    /**
     * Creates a {@code TimeTableWindow} with the given {@code Stage} and {@code Logic}.
     */
    public DayOfWeekPanel(Logic logic, Stage primaryStage) {
        super(FXML);

        // Set dependencies
        this.logic = logic;
        this.primaryStage = primaryStage;

        mon = new Text("Mon");
        tue = new Text("Tue");
        wed = new Text("Wed");
        thur = new Text("Thurs");
        fri = new Text("Fri");
        sat = new Text("Sat");
        sun = new Text("Sun");

        mon.setFont(new Font(18));
        tue.setFont(new Font(18));
        wed.setFont(new Font(18));
        thur.setFont(new Font(18));
        fri.setFont(new Font(18));
        sat.setFont(new Font(18));
        sun.setFont(new Font(18));


        mon.setWrappingWidth(60);
        tue.setWrappingWidth(60);
        wed.setWrappingWidth(60);
        thur.setWrappingWidth(60);
        fri.setWrappingWidth(60);
        sat.setWrappingWidth(60);
        sun.setWrappingWidth(60);

        dayOfWeekPanel.getChildren().addAll(mon, tue, wed, thur, fri, sat, sun);
        dayOfWeekPanel.setSpacing((primaryStage.getWidth() - 420) / 7);
        dayOfWeekPanel.setAlignment(Pos.CENTER);
    }
}
