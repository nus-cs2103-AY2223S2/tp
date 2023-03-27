package arb.logic.commands.project;

import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.predicates.NameContainsKeywordsPredicate;
import arb.model.project.Project;

/**
 * Adds a project to the address book.
 */
public class AddProjectCommand extends Command {

    public static final String MESSAGE_SUCCESS = "New project added: %1$s";
    public static final String MESSAGE_DUPLICATE_PROJECT = "This project already exists in the address book";
    public static final String MESSAGE_CANNOT_FIND_CLIENT = "Cannot find this client: %1$s";

    private static final String MAIN_COMMAND_WORD = "add-project";
    private static final String ALIAS_COMMAND_WORD = "ap";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Adds a project to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_DEADLINE + "DEADLINE "
            + PREFIX_PRICE + "PRICE "
            + PREFIX_CLIENT + "CLIENT\n"
            + "Example: " + MAIN_COMMAND_WORD + " "
            + PREFIX_NAME + "Oil Painting "
            + PREFIX_DEADLINE + "2023-04-05 "
            + PREFIX_PRICE + "2.07"
            + PREFIX_CLIENT + "Alice";

    private final Project toAdd;
    private final Optional<String> clientToBeLinked;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project, Optional<String> clientToBeLinked) {
        requireNonNull(project);
        toAdd = project;
        this.clientToBeLinked = clientToBeLinked;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        String message = String.format(MESSAGE_SUCCESS, toAdd);
        ListType toBeShown = ListType.PROJECT;
        if (clientToBeLinked.isPresent()) {
            model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(clientToBeLinked.get())));
            if (model.getFilteredClientList().size() == 0) {
                throw new CommandException(String.format(MESSAGE_CANNOT_FIND_CLIENT, clientToBeLinked.get()));
            }
            model.setProjectToLink(toAdd);
        }
        model.addProject(toAdd);

        if (clientToBeLinked.isPresent()) {
            model.updateFilteredClientList(new NameContainsKeywordsPredicate(Arrays.asList(clientToBeLinked.get())));
            message = LinkProjectToClientCommand.MESSAGE_USAGE;
            toBeShown = ListType.CLIENT;
        }

        return new CommandResult(message, clientToBeLinked.isPresent(), toBeShown);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd)
                && clientToBeLinked.equals(((AddProjectCommand) other).clientToBeLinked));
    }

    public static boolean isCommandWord(String commandWord) {
        return COMMAND_WORDS.contains(commandWord);
    }

    public static List<String> getCommandWords() {
        return new ArrayList<>(COMMAND_WORDS);
    }
}
