package guitests.guihandles;

import javafx.scene.Node;
import javafx.scene.control.Label;
import mycelium.mycelium.model.project.Project;

public class SpecialProjectListCardHandle extends NodeHandle<Node> {
    /**
     * Provides a handle to a special project card in the special project list panel.
     */
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String STATUS_FIELD_ID = "#status";
    private static final String CLIENT_EMAIL_FIELD_ID = "#email";
    private static final String DEADLINE_FIELD_ID = "#deadline";


    private final Label idLabel;
    private final Label nameLabel;
    private final Label statusLabel;
    private final Label clientEmailLabel;
    private final Label deadlineLabel;


    /**
     * Creates a {@code ProjectListCardHandle} with the default details.
     */
    public SpecialProjectListCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);
        statusLabel = getChildNode(STATUS_FIELD_ID);
        clientEmailLabel = getChildNode(CLIENT_EMAIL_FIELD_ID);
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
                && getDeadline().equals(project.getDeadline());
    }
}

