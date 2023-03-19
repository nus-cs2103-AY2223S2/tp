package seedu.wife.commons.core;

/**
 * Menu of commands to be displayed in Help Window.
 */
public enum HelpMenu {
    ADD("add") {
        @Override
        public String getCommandExample() {
            return "Add food item - add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]";
        }
    },
    UPDATE("update") {
        @Override
        public String getCommandExample() {
            return "Update food item - update OLD ITEM to/NEW ITEM";
        }
    },
    DELETE("delete") {
        @Override
        public String getCommandExample() {
            return "Delete food item - delete INDEX";
        }
    },
    TAG("tag") {
        @Override
        public String getCommandExample() {
            return "Tag food item - tag INDEX with/TAG NAME";
        }
    },
    LIST("list") {
        @Override
        public String getCommandExample() {
            return "List food items - list";
        }
    },
    EXIT("exit") {
        @Override
        public String getCommandExample() {
            return "Exit WIFE - exit";
        }
    };

    private static final String HEADER = "Command Examples: ";

    private final String command;
    private String description;
    private String format;

    /**
     * Creates a new HelpMenu.
     *
     * @param command type of the command
     */
    HelpMenu(String command) {
        this.command = command;
    }

    public abstract String getCommandExample();
    public String getDescription() {
        return description;
    }

    public String getFormat() {
        return format;
    }

    /**
     * Displays help menu with command descriptions
     * and usage format.
     * <p>
     * @return list of commands as a single String
     */
    public static String displayHelpMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER + System.lineSeparator());

        for (HelpMenu command : HelpMenu.values()) {
            sb.append(command.getDescription());
            sb.append(command.getFormat());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
