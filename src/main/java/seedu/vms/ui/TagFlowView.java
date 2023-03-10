package seedu.vms.ui;

import java.util.Collection;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;


/**
 * Graphical representation of a list of tags.
 */
public class TagFlowView extends FlowPane {
    public static final String STYLE_CLASS_TAG_FLOW_PANE = "tag-flow-pane";
    private static final String STYLE_CLASS_TAG = "tag";


    /**
     * Constructs a {@code TagFlowView} no additional style classes added to
     * its tags.
     *
     * @param tags - the tags to display.
     */
    public TagFlowView(Collection<String> tags) {
        this(tags, List.of());
    }


    /**
     * Constructs a {@code TagFlowView}.
     *
     * @param tags - the tags to display.
     * @param styleClasses - a list of additional style classes to add to the
     *      tags.
     */
    public TagFlowView(Collection<String> tags, Collection<String> styleClasses) {
        getStyleClass().add(STYLE_CLASS_TAG_FLOW_PANE);
        for (String tag : tags) {
            Label label = formLabel(tag);
            label.getStyleClass().addAll(styleClasses);
            getChildren().add(label);
        }
    }


    private Label formLabel(String text) {
        Label label = new Label(text);
        label.setWrapText(true);
        label.getStyleClass().add(STYLE_CLASS_TAG);
        label.setMinHeight(Region.USE_PREF_SIZE);
        return label;
    }
}
