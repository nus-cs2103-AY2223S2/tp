package seedu.wife.commons.core;

public enum HelpMenu {
    ADD("Add food item - ", "add n/NAME u/UNIT q/QUANTITY e/EXPIRY DATE [t/TAG]"),
    UPDATE("Update food item - ", "update <Old Item> /to <New Item>"),
    DELETE("Delete food item - ", "delete <Index>"),
    TAG("Tag food item - ", "tag <Index> /with <Tag Name>"),
    LIST("List food items - ", "list"),
    EXIT("Exit WIFE - ", "exit");

    private String description;
    private String format;

    private static final String HEADER = "Command Examples: ";

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
     * Displays command menu with a command description
     * and example usage.
     * <p>
     * @return list of commands
     */
    public static String displayHelpMenu() {
        StringBuilder sb = new StringBuilder();
        sb.append(HEADER + System.lineSeparator());

        for (HelpMenu command : HelpMenu.values()) {
            sb.append(command.getDescription() + command.getFormat());
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }
}
