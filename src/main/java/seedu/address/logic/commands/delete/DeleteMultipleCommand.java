package seedu.address.logic.commands.delete;

/**
 * An abstract class specifically for delete commands that delete multiple of specified objects at one time
 */
public abstract class DeleteMultipleCommand extends DeleteCommand {
    public static final String MESSAGE_USAGE = "(can be comma \",\" separated to include multiple)";
}
