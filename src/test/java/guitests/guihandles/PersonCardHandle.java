package guitests.guihandles;

import java.util.List;
import java.util.stream.Collectors;

import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import seedu.address.model.person.Person;

/**
 * Provides a handle to a person card in the person list panel. Referenced from AB4.
 */
public class PersonCardHandle extends NodeHandle<Node> {
    private static final String ID_FIELD_ID = "#id";
    private static final String NAME_FIELD_ID = "#name";
    private static final String TAGS_FIELD_ID = "#allTags";

    private final Label idLabel;
    private final Label nameLabel;

    private final List<Button> tagLabels;

    /**
     * Constructor for PersonCardHandle
     * @param cardNode PersonCard
     */
    public PersonCardHandle(Node cardNode) {
        super(cardNode);

        idLabel = getChildNode(ID_FIELD_ID);
        nameLabel = getChildNode(NAME_FIELD_ID);

        FlowPane tagsContainer = getChildNode(TAGS_FIELD_ID);
        tagLabels = tagsContainer
                .getChildren()
                .stream()
                .map(Button.class::cast)
                .collect(Collectors.toList());
    }

    public String getId() {
        return idLabel.getText();
    }

    public String getName() {
        return nameLabel.getText();
    }


    public List<String> getTags() {
        return tagLabels
                .stream()
                .map(Button::getText)
                .collect(Collectors.toList());
    }

    /**
     * Returns true if this handle contains {@code person}.
     */
    public boolean equals(Person person) {
        return getName().equals(person.getName().getValue())
                && getTags().equals(person.getGroupTags().getImmutableGroups().stream()
                        .map(tag -> tag.tagName)
                        .sorted()
                        .collect(Collectors.toList())
                        .addAll(person.getModuleTags().getImmutableModules().stream()
                        .map(tag -> tag.tagName)
                        .sorted()
                        .collect(Collectors.toList())));
    }
}
