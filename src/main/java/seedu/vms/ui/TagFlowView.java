package seedu.vms.ui;

import java.util.Collection;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;


/**
 * Graphical representation of a list of tags.
 */
public class TagFlowView extends FlowPane {
    private static final String TAG_STYLE_CLASS = "tag";


    /**
     * Constructs a {@code TagFlowView}.
     *
     * @param tags - the tags to display.
     */
    public TagFlowView(Collection<String> tags) {
        setHgap(5);
        setVgap(5);
        for (String tag : tags) {
            getChildren().add(formLabel(tag));
        }
    }


    private static Label formLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.getStyleClass().add(TAG_STYLE_CLASS);
        label.setMinHeight(Region.USE_PREF_SIZE);
        return label;
    }
}
