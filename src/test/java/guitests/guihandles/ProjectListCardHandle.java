package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import mycelium.mycelium.model.project.Project;

/**
 * Provides a handle to a person card in the person list panel.
 */
public class ProjectListCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String STATUS_FIELD_ID = "#status";
    private static final String CLIENT_EMAIL_FIELD_ID = "#email";
    private static final String SOURCE_FIELD_ID = "#source";
    private static final String DESCRIPTION_FIELD_ID = "#description";
    private static final String ACCEPTED_ON_FIELD_ID = "#acceptedOn";
    private static final String DEADLINE_FIELD_ID = "#deadline";


    private final Label idLabel;
    private final Label nameLabel;
    private final Label statusLabel;
    private final Label clientEmailLabel;
    private final Label sourceLabel;
    private final Label descriptionLabel;
    private final Label acceptedOnLabel;
    private final Label deadlineLabel;



    public ProjectListCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        statusLabel = getChildNode(STATUS_FIELD_ID);
        clientEmailLabel = getChildNode(CLIENT_EMAIL_FIELD_ID);
        sourceLabel = getChildNode(SOURCE_FIELD_ID);
        descriptionLabel = getChildNode(DESCRIPTION_FIELD_ID);
        acceptedOnLabel = getChildNode(ACCEPTED_ON_FIELD_ID);
        deadlineLabel = getChildNode(DEADLINE_FIELD_ID);
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }

    public String getStatus() {
        return statusLabel.getText();
    }

    public String getClientEmail() {
        return clientEmailLabel.getText();
    }

    public String getSource() {
        return sourceLabel.getText();
    }
    public String getDescription() {
        return descriptionLabel.getText();
    }

    public String getAcceptedOn() {
        return acceptedOnLabel.getText();
    }

    public String getDeadline() {
        return deadlineLabel.getText();
    }

    /**
     * Returns true if this handle contains {@code person}.
     */
    public boolean equals(Project project) {
        return getName().equals(project.getName().toString())
                && getStatus().equals(project.getStatus())
                && getClientEmail().equals(project.getClientEmail())
                && getSource().equals(project.getSource())
                && getDescription().equals(project.getDescription())
                && getAcceptedOn().equals(project.getAcceptedOn())
                && getDeadline().equals(project.getDeadline());
    }
}