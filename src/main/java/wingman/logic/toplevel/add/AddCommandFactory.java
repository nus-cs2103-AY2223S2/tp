package wingman.logic.toplevel.add;

import java.util.Optional;
import java.util.Set;

import wingman.logic.core.CommandFactory;
import wingman.logic.core.CommandParam;
import wingman.logic.core.exceptions.ParseException;
import wingman.model.item.Item;

/**
 * The command factory that's responsible for producing commands that adds an
 * item to the model.
 *
 * @param <T> the type of the item.
 */
public class AddCommandFactory<T extends Item> implements CommandFactory<AddCommand<T>> {
    private static final String COMMAND_WORD = "add";

    /**
     * The prefixes associated with this command.
     */
    private final Optional<Set<String>> prefixes;

    /**
     * The function that's responsible for adding the item.
     */
    private final AddFunction<T> addFunction;

    /**
     * The function that's responsible for creating an item.
     */
    private final ItemFactory<T> itemFactory;

    /**
     * The name of the type of the item that's being added.
     */
    private final String typeName;

    /**
     * Creates a new AddCommandFactory that's responsible for creating
     * commands that add items.
     *
     * @param typeName    the name of the type of items that's added.
     * @param prefixes    the prefixes of the item.
     * @param addFunction the function that will add the corresponding item
     *                    to the list.
     * @param itemFactory the factory that's responsible for creating the item.
     */
    public AddCommandFactory(
            String typeName,
            Optional<Set<String>> prefixes,
            AddFunction<T> addFunction,
            ItemFactory<T> itemFactory
    ) {
        this.typeName = typeName;
        this.prefixes = prefixes;
        this.addFunction = addFunction;
        this.itemFactory = itemFactory;
    }

    @Override
    public String getCommandWord() {
        return COMMAND_WORD;
    }

    @Override
    public Optional<Set<String>> getPrefixes() {
        return prefixes;
    }

    @Override
    public AddCommand<T> createCommand(CommandParam param) throws ParseException {
        T item = itemFactory.create(param);
        return new AddCommand<>(addFunction, item, typeName);
    }
}
