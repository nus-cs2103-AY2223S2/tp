package wingman.logic.toplevel.link;

import java.util.Map;

import wingman.logic.core.Command;
import wingman.model.item.Item;

/**
 * A function that creates a link command.
 *
 * @param <C> the type of command to be created.
 * @param <S> the type of source item.
 * @param <T> the type of target item.
 * @param <K> the type of key.
 */
@FunctionalInterface
public interface LinkFunction<C extends Command, S extends Item, T extends Item, K> {
    /**
     * Creates a link command.
     *
     * @param source  the source item.
     * @param targets the map of target items.
     * @return the link command.
     */
    C apply(S source, Map<K, T> targets);
}
