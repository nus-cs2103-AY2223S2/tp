package wingman.logic.toplevel.link;

import java.util.Map;
import java.util.Optional;

import wingman.commons.fp.Lazy;
import wingman.logic.core.Command;
import wingman.logic.core.CommandFactory;
import wingman.logic.core.exceptions.NumberParseException;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.ReadOnlyItemManager;
import wingman.model.item.Item;

/**
 * The base class for all link command factories.
 *
 * @param <C> the type of command to be created.
 * @param <S> the type of source item.
 * @param <T> the type of target item.
 */
public abstract class LinkFactoryBase
        <C extends Command, S extends Item, T extends Item, K>
        implements CommandFactory<C> {
    /**
     * The source manager.
     */
    protected final Lazy<ReadOnlyItemManager<S>> sourceManagerLazy;

    /**
     * The target manager.
     */
    protected final Lazy<ReadOnlyItemManager<T>> targetManagerLazy;

    /**
     * Creates a new link factory.
     *
     * @param sourceManagerLazy the source manager.
     * @param targetManagerLazy the target manager.
     */
    public LinkFactoryBase(
            Lazy<ReadOnlyItemManager<S>> sourceManagerLazy,
            Lazy<ReadOnlyItemManager<T>> targetManagerLazy
    ) {
        this.sourceManagerLazy = sourceManagerLazy;
        this.targetManagerLazy = targetManagerLazy;
    }

    /**
     * Gets the item or throws the corresponding exception.
     *
     * @param idOptional the id of the item.
     * @param manager    the manager of the item.
     * @param <I>        the type of item.
     * @return the item.
     */
    protected <I extends Item> I getItemOrThrow(
            Optional<String> idOptional,
            ReadOnlyItemManager<I> manager
    ) throws ParseException {
        if (idOptional.isEmpty()) {
            throw ParseException.formatted(
                    "%s: ID is missing.",
                    getCommandWord()
            );
        }
        int flightId;
        try {
            flightId = Integer.parseInt(idOptional.get());
        } catch (NumberFormatException e) {
            throw NumberParseException.formatted(
                    "%s: ID (%s) is not a number: %s",
                    getCommandWord(),
                    idOptional.get(),
                    e.getMessage()
            );
        }
        boolean isIndexValid = flightId >= 0 && flightId < manager.size();
        if (!isIndexValid) {
            throw NumberParseException.formatted(
                    "%s: ID (%d) is out of bounds, should be between 1 and %d.",
                    getCommandWord(),
                    flightId,
                    manager.size()
            );
        }
        Optional<I> flightOptional = manager.getItemOptional(flightId);
        if (flightOptional.isEmpty()) {
            throw NumberParseException.formatted(
                    "%s: with index %d is not found.",
                    getCommandWord(),
                    flightId
            );
        }
        return flightOptional.get();
    }

    /**
     * Gets the target from the {@code targetIdOptional}, and if it is
     * present, we add it to the {@code targetMap} with the {@code type}.
     * Note that this method will not throw an exception if the
     * targetIdOptional passed in is empty.
     *
     * @param targetIdOptional the id of the target.
     * @param type             the type of the target.
     * @param targetMap        the map to add the target to.
     * @return true if the target is present, false otherwise.
     * @throws ParseException if the target is present but cannot be parsed.
     */
    protected boolean addTarget(
            Optional<String> targetIdOptional,
            K type,
            Map<K, T> targetMap
    ) throws ParseException {
        if (targetIdOptional.isEmpty() || targetIdOptional.get().isBlank()) {
            return false;
        }
        T target = getItemOrThrow(
                targetIdOptional,
                targetManagerLazy.get()
        );
        targetMap.put(type, target);
        return true;
    }

    /**
     * Gets the source from the {@code sourceIdOptional}, and if it is
     * present, we add it to the {@code sourceMap} with the {@code type}.
     *
     * @param sourceIdOptional the id of the source.
     * @return the source.
     * @throws ParseException if the source is present but cannot be parsed.
     */
    protected S getSourceOrThrow(
            Optional<String> sourceIdOptional
    ) throws ParseException {
        return getItemOrThrow(
                sourceIdOptional,
                sourceManagerLazy.get()
        );
    }
}
