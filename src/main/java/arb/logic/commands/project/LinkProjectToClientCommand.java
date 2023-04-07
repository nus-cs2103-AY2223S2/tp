package arb.logic.commands.project;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.logging.Logger;

import arb.commons.core.LogsCenter;
import arb.commons.core.Messages;
import arb.commons.core.index.Index;
import arb.logic.commands.Command;
import arb.logic.commands.CommandResult;
import arb.logic.commands.exceptions.CommandException;
import arb.model.ListType;
import arb.model.Model;
import arb.model.client.Client;

/**
 * Links {@code projectToBeLinked} in the model to the client identified using its displayed index from
 * the address book.
 */
public class LinkProjectToClientCommand extends Command {

    public static final String MESSAGE_LINK_PROJECT_TO_CLIENT_SUCCESS = "Successfully linked the project to %1$s";
    public static final String CANCEL_MESSAGE = "Linking between project and client has been cancelled";

    public static final String MESSAGE_USAGE = "Please select a client to link this project to.\n"
            + "For example, enter 1 to select the first client in the list.\n"
            + "Enter 0 to cancel this operation.";

    private static final Logger logger = LogsCenter.getLogger(LinkProjectToClientCommand.class);

    private final Index targetIndex;

    /**
     * Creates a LinkProjectToClient to link the client at {@code targetIndex} to
     * {@code projectToBeLinked} in the model.
     * If the user enters "0", the linking is cancelled.
     */
    public LinkProjectToClientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
        logger.info("Inputted index: " + this.targetIndex.getOneBased());
    }

    @Override
    public CommandResult execute(Model model, ListType currentListBeingShown) throws CommandException {
        requireNonNull(model);
        assert currentListBeingShown == ListType.CLIENT : "This command should only be executed "
                + "with a client list being shown";

        if (targetIndex.getOneBased() == 0) {
            model.resetProjectToLink();
            return new CommandResult(CANCEL_MESSAGE, ListType.PROJECT);
        }

        List<Client> lastShownList = model.getSortedClientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CLIENT_DISPLAYED_INDEX);
        }

        Client clientToLinkTo = lastShownList.get(targetIndex.getZeroBased());
        model.linkProjectToClient(clientToLinkTo);
        return new CommandResult(String.format(MESSAGE_LINK_PROJECT_TO_CLIENT_SUCCESS,
                clientToLinkTo.getName().fullName), ListType.PROJECT);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short-circuit if same object
                || (other instanceof LinkProjectToClientCommand // handles null
                && targetIndex.equals(((LinkProjectToClientCommand) other).targetIndex)); // state check
    }

}
