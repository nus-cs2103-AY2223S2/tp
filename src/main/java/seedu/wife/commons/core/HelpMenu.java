package seedu.wife.commons.core;

import java.util.Arrays;

/**
 * Menu of commands to be displayed in Help Window.
 */
public enum HelpMenu {
    INVALID("invalid") {
        @Override
        public String getCommandUsage() {
            return INVALID_MESSAGE + " " + getGeneralHelp();
        }
    },
    ADD("add") {
        @Override
        public String getCommandUsage() {
            return "Add food item - add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]";
        }
    },
    UPDATE("update") {
        @Override
        public String getCommandUsage() {
            return "Update food item - update OLD ITEM to/NEW ITEM";
        }
    },
    DELETE("delete") {
        @Override
        public String getCommandUsage() {
            return "Delete food item - delete INDEX";
        }
    },
    TAG("tag") {
        @Override
        public String getCommandUsage() {
            return "Tag food item - tag INDEX t/TAG NAME";
        }
    },
    LIST("list") {
        @Override
        public String getCommandUsage() {
            return "List food items - list";
        }
    },
    EXIT("exit") {
        @Override
        public String getCommandUsage() {
            return "Exit WIFE - exit";
        }
    };

    private static final String COMMANDS_AVAILABLE = "Commands Available: ";

    private static final String INVALID_MESSAGE = "Command does not exist!";

    private static final String MORE_INFO_MESSAGE =
            "Type 'help COMMAND' to see specific help for a command.";

    private final String command;

    /**
     * Creates a new HelpMenu object.
     *
     * @param command type of the command
     */
    HelpMenu(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    /**
     * Retrieves all enums as a String created in displayHelpMenu.
     *
     * @return String of the general help message
     */
    public static String getGeneralHelp() {
        return displayHelpMenu();
    }

    /**
     * Retrieves help message of a given input command.
     *
     * @param helpCommand the input command
     * @return String of the command's specific message
     */
    public static String getCommandHelp(HelpMenu helpCommand) {
        return helpCommand.getCommandUsage();
    }

    public abstract String getCommandUsage();

    /**
     * Checks if an input command is a valid command.
     *
     * @param commandToParse the input command to check
     * @return the command as an enum if it is valid
     *      otherwise the INVALID command
     */
    public static HelpMenu parseCommand(String commandToParse) {
        return Arrays.stream(values())
                .filter(type -> type.command.equals(commandToParse))
                .findFirst().orElse(INVALID);
    }

    /**
     * Displays help menu with command examples
     * and usage format.
     * <p>
     * Does not include the INVALID constant
     *
     * @return list of commands as a single String
     */
    public static String displayHelpMenu() {
        StringBuilder sb = new StringBuilder();
        HelpMenu[] values = HelpMenu.values();
        int len = values().length;

        sb.append(MORE_INFO_MESSAGE);
        sb.append(System.lineSeparator());
        sb.append(COMMANDS_AVAILABLE);

        for (int i = 0; i < len; i++) {
            HelpMenu command = values[i];
            if (command.equals(INVALID)) {
                continue;
            }

            sb.append(command.getCommand());
            if (i != len - 1) {
                sb.append(", ");
            }
        }
        return sb.toString();
    }
}
