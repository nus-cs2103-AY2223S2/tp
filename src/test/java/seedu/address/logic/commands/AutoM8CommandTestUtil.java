package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.testutil.TypicalShop;

/**
 * Contains helper methods for testing commands.
 */
public class AutoM8CommandTestUtil {


    public static boolean assertFailure(Command command, Model model, Exception exception) {
        return assertFailure(command, model, exception.getMessage());
    }

    /**
     * Given command, execute it with model. To catch Command Exeception and return if the same msg.
     * Does not test if the nature of the program is correct or not.
     *
     * @param command The command to be executed
     * @param model The model to reference
     * @param msg Expected CommandResult
     * @return Whether executed command returns command result's message.
     */

    public static boolean assertFailure(Command command, Model model, String msg) {
        CommandResult result = null;
        try {
            result = command.execute(model);
        } catch (CommandException ce) {
            String errMsg = ce.getMessage();
            return errMsg != null && msg != null
                    && errMsg.hashCode() == msg.hashCode()
                    && errMsg.equals(msg);
        }
        throw new RuntimeException("Assert Failure returns non-failure condition!");
    }

    public static boolean assertSuccess(Command command, Model model, Exception exception) {
        return assertSuccess(command, model, exception.getMessage());
    }

    /**
     * Given command, execute it with model. Expect to be a successful execution and return if the same msg.
     * Does not test if the nature of the program is correct or not.
     *
     * @param command The command to be executed
     * @param model The model to reference
     * @param msg Expected CommandResult
     * @return Whether executed command returns command result's message.
     */
    public static boolean assertSuccess(Command command, Model model, String msg) {
        CommandResult result = null;
        try {
            result = command.execute(model);
        } catch (CommandException ce) {
            throw new RuntimeException("Assert Success returns failure condition!");
        }
        String posMsg = result.getFeedbackToUser();
        return posMsg != null && msg != null
                && posMsg.hashCode() == msg.hashCode()
                && posMsg.equals(msg);
    }


}
