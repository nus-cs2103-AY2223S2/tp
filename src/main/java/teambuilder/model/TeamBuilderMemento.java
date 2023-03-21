package teambuilder.model;

import static java.util.Objects.requireNonNull;

import teambuilder.commons.core.Memento;

/**
 * This class stores the state of a TeamBuilder
 */
public class TeamBuilderMemento implements Memento {
    private TeamBuilder state;
    private ModelManager origin;

    /**
     * Constructs a TeamBuilderMemento.
     *
     * @param state The state of the TeamBuilder to be saved.
     * @param origin The Model manager to restore the TeamBuilder from.
     */
    public TeamBuilderMemento(TeamBuilder state, ModelManager origin) {
        requireNonNull(state);
        requireNonNull(origin);

        this.state = state;
        this.origin = origin;
    }

    @Override
    public boolean restore() {
        origin.setAddressBook(state);
        return true;
    }

    @Override
    public Memento getUpdatedMemento() {
        return origin.save();
    }
}
