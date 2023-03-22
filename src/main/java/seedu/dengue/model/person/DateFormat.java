package seedu.dengue.model.person;

public class DateFormat {
    private final String day;
    private final String month;
    private final String year;
    DateFormat(String year, String month, String day) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return this.day;
    }

    public String getMonth() {
        return this.month;
    }

    public String getYear() {
        return this.year;
    }
}
