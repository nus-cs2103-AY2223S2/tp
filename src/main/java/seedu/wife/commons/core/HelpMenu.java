package seedu.wife.commons.core;

/**
 * Menu of commands to be displayed in Help Window.
 */
public enum HelpMenu {
    ADD("Add food item - ", "add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]"),
    UPDATE("Update food item - ", "update <Old Item> /to <New Item>"),
    DELETE("Delete food item - ", "delete <Index>"),
    TAG("Tag food item - ", "tag <Index> /with <Tag Name>"),
    LIST("List food items - ", "list"),
    EXIT("Exit WIFE - ", "exit");

    private static final String HEADER = "Command Examples: ";
    private String description;
    private String format;

    /**
     * Creates a new HelpMenu.
     *
     * @param description of the command
     * @param format of how to execute the command
     */
    HelpMenu(String description, String format) {
        this.description = description;
        this.format = format;
    }

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
