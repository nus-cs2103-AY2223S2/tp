package seedu.internship.model.event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;
import seedu.internship.logic.parser.exceptions.ParseException;

/**
 * Parses Date and Time String
 */
public class TimeParser implements Comparable<TimeParser> {
    private DateTimeFormatter DATE_TIME_PATTERN;
    private DateTimeFormatter DATE_PARSE_FORMAT;
    private DateTimeFormatter TIME_PARSE_FORMAT;
    private LocalDate ld;
    private LocalTime lt;
    private LocalDateTime ldt;
    private boolean isValid;


    /**
     * TimeParser Constructor
     * @param dtPattern is the date_time_pattern of the string to be parsed
     * @param dpFormat is the format of the date to be ouputted
     * @param tpFormat is the format of the time to be outputted
     */
    public TimeParser(String dtPattern ,String dpFormat ,String tpFormat) {
        this.DATE_TIME_PATTERN = DateTimeFormatter.ofPattern(dtPattern);
        this.DATE_PARSE_FORMAT = DateTimeFormatter.ofPattern(dpFormat);
        this.TIME_PARSE_FORMAT = DateTimeFormatter.ofPattern(tpFormat);
    }

    /**
     * Parses the Input String
     * @param input
     */
    public void parse(String input)  {
        try {
            String[] DateTime = input.trim().split(" ");
            this.ld = LocalDate.parse(DateTime[0], DATE_PARSE_FORMAT); // dd/mm/yyyy
            this.lt = LocalTime.parse(DateTime[1], TIME_PARSE_FORMAT);// HHMM
            this.ldt = LocalDateTime.of(ld, lt);
            this.isValid = true;
        } catch (DateTimeParseException | ArrayIndexOutOfBoundsException  e) {
            this.isValid = false;
        }

    }

    /**
     *
     * @return True if TimeParser has correctly parsed the dateTimeString
     */
    public boolean isValidTimeParser(){
        return isValid;
    }

    /**
     * TimeParser Contructor Only for the Sentinel Value
     * @param ldt
     */
    private TimeParser(LocalDateTime ldt){
        this.ldt  = ldt;
        this.ld = ldt.toLocalDate();
        this.lt = ldt.toLocalTime();
    }

    /**
     * @return Formatted Date Time String
     */
    @Override
    public String toString(){
        //This was adapted from https://stackoverflow.com/questions/4011075/how-do-you-format-the-day-of-the-month-to-say-11th-21st-or-23rd-ordinal
        int day = ldt.getDayOfMonth();
        String daySuff = this.getDaySuffix(day);
        return String.format(DATE_TIME_PATTERN.format(ldt), daySuff);
    }

    /**
     * TimeParser elemts are compared to on the basis of LocalDateTime object
     * @param other
     * @return
     */
    @Override
    public int compareTo(TimeParser other){
        return this.ldt.compareTo(other.ldt);
    }

    /**
     * @param day
     * @return suffix for days
     */
    public String getDaySuffix(int day) {
        if (day  == 1 || day == 21 || day == 31) {
            return "st";
        } else if(day == 2 || day == 22) {
            return "nd";
        } else if (day == 3 || day ==23) {
            return "rd";
        } else {
            return "th";
        }
    }

}