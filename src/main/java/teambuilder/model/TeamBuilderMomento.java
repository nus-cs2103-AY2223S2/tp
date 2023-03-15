package teambuilder.model;

import teambuilder.commons.core.Momento;

/**
 * This class stores the state of a TeamBuilder
 */
public class TeamBuilderMomento implements Momento {
    private TeamBuilder state;
    private ModelManager origin;
    private String description;

    /**
     * Constructs a TeamBuilderMomento.
     *
     * @param state The state of the TeamBuilder to be saved.
     * @param origin The Model manager to restore the TeamBuilder from.
     */
    public TeamBuilderMomento(TeamBuilder state, ModelManager origin) {
        this.state = state;
        this.origin = origin;
    }

    @Override
    public boolean restore() {
        origin.setAddressBook(state);
        return true;
    }

    @Override
    public void setDescription(String desc) {
        description = desc;
    }

    @Override
    public String toString() {
        return description;
    }
}
