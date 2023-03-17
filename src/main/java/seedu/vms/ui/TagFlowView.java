package seedu.vms.ui;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;


/**
 * Graphical representation of a list of tags.
 */
public class TagFlowView extends FlowPane {
    public static final String STYLE_CLASS_TAG_FLOW_PANE = "tag-flow-pane";

    public static final String STYLE_CLASS_TAG_GREEN = "tag-color-green";
    public static final String STYLE_CLASS_TAG_RED = "tag-color-red";
    public static final String STYLE_CLASS_TAG_BLUE = "tag-color-blue";

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


    public TagFlowView(Collection<String> tags, String ... styleClasses) {
        this(tags, List.of(styleClasses));
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
        ArrayList<String> sortedTags = new ArrayList<>(tags);
        sortedTags.sort(Comparator.naturalOrder());
        for (String tag : sortedTags) {
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
