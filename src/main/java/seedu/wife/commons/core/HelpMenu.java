package seedu.wife.commons.core;

import java.util.Arrays;

/**
 * Menu of commands to be displayed in Help Window.
 *
 * Dynamic help functionality inspired by AY2223 S1 Team W16-2
 * however, the idea of using Enums is my own as I had implemented
 * this feature in my iP as well.
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
            return "Add food item - add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE";
        }
    },
    EDIT("edit") {
        @Override
        public String getCommandUsage() {
            return "Edit a food item - edit INDEX [n/NAME] [u/UNIT] [q/QUANTITY] [e/EXPIRY DATE]"
                    + OPTIONAL_MESSAGE;
        }
    },
    DECREMENT("dec") {
        @Override
        public String getCommandUsage() {
            return "Decrease qty of item - dec INDEX [q/QUANTITY]" + OPTIONAL_MESSAGE;
        }
    },
    INCREMENT("inc") {
        @Override
        public String getCommandUsage() {
            return "Increase qty of item - inc INDEX [q/QUANTITY]" + OPTIONAL_MESSAGE;
        }
    },
    DELETE("delete") {
        @Override
        public String getCommandUsage() {
            return "Delete food item - delete INDEX";
        }
    },
    FIND("find") {
        @Override
        public String getCommandUsage() {
            return "Find one or more food items - find KEYWORD [KEYWORD]..." + OPTIONAL_MESSAGE;
        }
    },
    LIST("list") {
        @Override
        public String getCommandUsage() {
            return "Lists all food items - list";
        }
    },
    SORTBYEXPIRY("expiry") {
        @Override
        public String getCommandUsage() {
            return "Sort food items by expiry date - expiry";
        }
    },
    VIEW("view") {
        @Override
        public String getCommandUsage() {
            return "View food item - view INDEX";
        }
    },
    TAG("tag") {
        @Override
        public String getCommandUsage() {
            return "Tag a food item - tag INDEX n/TAG NAME";
        }
    },
    UNTAGTAG("untag") {
        @Override
        public String getCommandUsage() {
            return "Remove a tag from a food item - untag INDEX n/TAG NAME";
        }
    },
    DELETEBYTAG("delbytag") {
        @Override
        public String getCommandUsage() {
            return "Deletes food items containing the specified tag(s) - delbytag n/TAG NAME [n/TAG NAME]..."
                    + OPTIONAL_MESSAGE;
        }
    },
    LISTBYTAG("listbytag") {
        @Override
        public String getCommandUsage() {
            return "Lists food items containing the specified tag(s) - listbytag n/TAG NAME [n/TAG NAME]..."
                    + OPTIONAL_MESSAGE;
        }
    },
    CREATETAG("createtag") {
        @Override
        public String getCommandUsage() {
            return "Create new tag(s) - createtag n/TAG NAME [n/TAG NAME]..." + OPTIONAL_MESSAGE;
        }
    },
    DELETETAG("deltag") {
        @Override
        public String getCommandUsage() {
            return "Delete tag(s) - deltag n/TAG NAME [n/TAG NAME]..." + OPTIONAL_MESSAGE;
        }
    },
    LISTTAG("listtag") {
        @Override
        public String getCommandUsage() {
            return "List all tags - listtag";
        }
    },
    CLEAR("clear") {
        @Override
        public String getCommandUsage() {
            return "Clears all food item AND tags - clear";
        }
    },
    HELP("help") {
        @Override
        public String getCommandUsage() {
            return getGeneralHelp();
        }
    },
    EXIT("exit") {
        @Override
        public String getCommandUsage() {
            return "Exits WIFE - exit";
        }
    };

    private static final String COMMANDS_AVAILABLE = "Commands Available: ";
    private static final String INVALID_MESSAGE = "Command does not exist!";
    private static final String OPTIONAL_MESSAGE = "\nParameters in brackets are optional.";
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
     * Reused from https://github.com/AY2223S1-CS2103T-W16-2/tp/blob/master/src/main
     * /java/seedu/foodrem/commons/enums/CommandType.java
     *
     * @author rmj1405 -reused
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
