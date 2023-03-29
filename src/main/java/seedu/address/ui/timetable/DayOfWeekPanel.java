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
 * Panel containing day of week (Mon, Tue, Wed,..)
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
     * Creates a {@code DayofWeekPanel} with the given {@code Logic} and {@code Stage}.
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

        setAllFont(18.0);
        setAllWrappingWidth(60.0);

        dayOfWeekPanel.getChildren().addAll(mon, tue, wed, thur, fri, sat, sun);
        dayOfWeekPanel.setSpacing((primaryStage.getWidth() - 420) / 7);
        dayOfWeekPanel.setAlignment(Pos.CENTER);
    }

    /**
     * Sets all text font to a specific size
     * @param fontSize
     */
    private void setAllFont(double fontSize) {
        mon.setFont(new Font(fontSize));
        tue.setFont(new Font(fontSize));
        wed.setFont(new Font(fontSize));
        thur.setFont(new Font(fontSize));
        fri.setFont(new Font(fontSize));
        sat.setFont(new Font(fontSize));
        sun.setFont(new Font(fontSize));

    }

    /**
     * Sets all text with a specific wrapping width
     * @param widthSize
     */
    private void setAllWrappingWidth(double widthSize) {
        mon.setWrappingWidth(widthSize);
        tue.setWrappingWidth(widthSize);
        wed.setWrappingWidth(widthSize);
        thur.setWrappingWidth(widthSize);
        fri.setWrappingWidth(widthSize);
        sat.setWrappingWidth(widthSize);
        sun.setWrappingWidth(widthSize);

    }
}
