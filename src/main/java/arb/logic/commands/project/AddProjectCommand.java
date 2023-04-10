package arb.logic.commands.project;

import static arb.logic.parser.CliSyntax.PREFIX_CLIENT;
import static arb.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static arb.logic.parser.CliSyntax.PREFIX_NAME;
import static arb.logic.parser.CliSyntax.PREFIX_PRICE;
import static arb.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import arb.commons.core.Messages;
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

    private static final String MAIN_COMMAND_WORD = "add-project";
    private static final String ALIAS_COMMAND_WORD = "ap";
    private static final Set<String> COMMAND_WORDS =
            new HashSet<>(Arrays.asList(MAIN_COMMAND_WORD, ALIAS_COMMAND_WORD));

    public static final String MESSAGE_USAGE = MAIN_COMMAND_WORD + ": Adds a project to the address book. "
            + "Parameters: "
            + "<" + PREFIX_NAME + "NAME> "
            + "[" + PREFIX_DEADLINE + "DEADLINE] "
            + "[" + PREFIX_PRICE + "PRICE] "
            + "[" + PREFIX_CLIENT + "CLIENT]* "
            + "[" + PREFIX_TAG + "TAG]*\n"
            + "Example: " + MAIN_COMMAND_WORD + " "
            + PREFIX_NAME + "Oil Painting "
            + PREFIX_DEADLINE + "2023-04-05 "
            + PREFIX_PRICE + "2.07 "
            + PREFIX_CLIENT + "Alice "
            + PREFIX_TAG + "painting";

    private final Project toAdd;
    private final Optional<NameContainsKeywordsPredicate> clientNamePredicate;

    /**
     * Creates an AddProjectCommand to add the specified {@code Project}
     */
    public AddProjectCommand(Project project, Optional<NameContainsKeywordsPredicate> clientNamePredicate) {
        requireNonNull(project);
        toAdd = project;
        this.clientNamePredicate = clientNamePredicate;
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);

        if (model.hasProject(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PROJECT);
        }

        String message = String.format(MESSAGE_SUCCESS, toAdd);
        ListType toBeShown = ListType.PROJECT;
        if (clientNamePredicate.isPresent()) {
            if (model.numberOfClientsMatchingPredicate(clientNamePredicate.get()) == 0) {
                throw new CommandException(String.format(Messages.MESSAGE_CANNOT_FIND_CLIENT_WITH_KEYWORDS,
                        clientNamePredicate.get().keywordsToString()));
            }
            model.updateFilteredClientList(clientNamePredicate.get());
            model.setProjectToLink(toAdd);
            message += "\n" + LinkProjectToClientCommand.MESSAGE_USAGE;
            toBeShown = ListType.CLIENT;
        }
        model.addProject(toAdd);

        return new CommandResult(message, clientNamePredicate.isPresent(), toBeShown);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddProjectCommand // instanceof handles nulls
                && toAdd.equals(((AddProjectCommand) other).toAdd)
                && clientNamePredicate.equals(((AddProjectCommand) other).clientNamePredicate));
    }

    /** Get all valid command words as an unmodifiable set. */
    public static Set<String> getCommandWords() {
        return Collections.unmodifiableSet(COMMAND_WORDS);
    }

}
