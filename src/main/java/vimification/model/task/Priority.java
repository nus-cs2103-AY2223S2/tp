package vimification.model.task;

public enum Priority {
    UNKNOWN, NOT_URGENT, URGENT, VERY_URGENT;

    /*
     * Possible helper function used by the parser. Help parser to process Integer input (as an
     * alternative input) to return the corresponding Priority enum. 1 being the highest priority, 3
     * being the lowest priority. Other integers will be treated as unknown priority
     */
    public static Priority fromInt(int level) {
        switch (level) {
        case 1:
            return VERY_URGENT;
        case 2:
            return URGENT;
        case 3:
            return NOT_URGENT;
        default:
            return UNKNOWN;
        }
    }

    public String asEnding() {
        switch (this) {
        case NOT_URGENT:
            return " !";
        case URGENT:
            return " !!";
        case VERY_URGENT:
            return " !!!";
        default:
            return ".";
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
