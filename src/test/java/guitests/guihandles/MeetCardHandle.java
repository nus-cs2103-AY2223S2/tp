package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import seedu.address.model.recommendation.Recommendation;


/**
 * Provides a handle to a meet card in the meet list panel.
 */
public class MeetCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String PLACE_FIELD_ID = "#place";
    private final Label idLabel;
    private final Label placeLabel;

    /**
     * Public constructor for MeetCardHandle
     * @param cardNode
     */
    public MeetCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        placeLabel = getChildNode(PLACE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getPlace() {
        return placeLabel.getText();
    }


    /**
     * Returns true if this handle contains {@code recommendation}.
     */
    public boolean equals(Recommendation recommendation) {
        return getPlace().equals(recommendation.getLocation().getName()
                + " : " + recommendation.getTimePeriod().getUiDisplay());
    }
}
