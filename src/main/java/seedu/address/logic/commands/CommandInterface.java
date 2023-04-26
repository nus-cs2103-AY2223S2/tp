package seedu.address.logic.commands;

interface CommandInterface {

    static String getCommandUsage() {
        return "No usage found";
    }

    static String getMessageSuccess() {
        return "No success message found";
    }

}
