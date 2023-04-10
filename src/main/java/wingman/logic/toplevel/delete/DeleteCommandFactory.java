package wingman.logic.toplevel.delete;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.item.Item;

/**
 * The command factory that produces commands that deletes the item.
 *
 * @param <T> the item.
 */
public class DeleteCommandFactory<T extends Item> implements CommandFactory<DeleteCommand<T>> {
    /**
     * The command word for deletion.
     */
    private static final String COMMAND_WORD = "delete";

    /**
     * The function that gets the manager.
     */
    private final GetManagerFunction<T> getManagerFunction;

    /**
     * The function that deletes the item.
     */
    private final DeleteFunction<T> deleteFunction;

    /**
     * The command factory that deletes things.
     *
     * @param getManagerFunction the function that gets the manager.
     * @param deleteFunction     the function that deletes the object.
     */
    public DeleteCommandFactory(
            GetManagerFunction<T> getManagerFunction,
            DeleteFunction<T> deleteFunction
    ) {
        this.getManagerFunction = getManagerFunction;
        this.deleteFunction = deleteFunction;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return Optional.empty();
    }

    @Override
    public DeleteCommand<T> createCommand(CommandParam param) throws ParseException {
        int index = param.getUnnamedIntOrThrow();

        return new DeleteCommand<>(index, getManagerFunction, deleteFunction);
    }
}
