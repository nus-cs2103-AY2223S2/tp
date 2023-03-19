package seedu.calidr.model;

/**
 * Represents the type of page to be displayed by the calendar panel.
 */
public enum PageType {

    DAY("day") {
        @Override
        public PageType next(PageType pt) {
            return WEEK;
        }
    },

    WEEK("week") {
        @Override
        public PageType next(PageType pt) {
            return MONTH;
        }
    },

    MONTH("month") {
        @Override
        public PageType next(PageType pt) {
            return DAY;
        }
    },

    NEXT("next") {
        @Override
        public PageType next(PageType pt) {
            if (pt == NEXT) {
                return DAY;
            }
            return pt.next(pt);
        }
    };

    private final String string;

    PageType(String string) {
        this.string = string;
    }

    public abstract PageType next(PageType pt);

    @Override
    public String toString() {
        return string;
    }
}
