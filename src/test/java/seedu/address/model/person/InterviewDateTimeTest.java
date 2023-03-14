package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.exceptions.ParseException;

public class InterviewDateTimeTest {
    private static final String templateDateTimeString = "12-02-2023 18:00";
    private static InterviewDateTime template = null;

    static {
        try {
            template = new InterviewDateTime(templateDateTimeString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createInterviewDateTime_validFormat() {
        try {
            LocalDateTime dateTime = DateTimeParser.parseDateTime(templateDateTimeString);
            assertEquals(template, dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void createInterviewDateTime_emptyString_returnsNullInterviewDateTime() {
        try {
            String interviewDateTime = new InterviewDateTime(InterviewDateTime.EMPTY_DATE_TIME).toString();
        } catch (ParseException e) {
            System.out.println("Invalid datetime string provided");
        } catch (NullPointerException e) {
            System.out.println("No datetime string provided");
        }
    }

    @Test
    public void checkEqualityOfIdenticalInterviewDateTime() {
        try {
            InterviewDateTime interviewDateTime = new InterviewDateTime(templateDateTimeString);
            boolean equals = interviewDateTime.equals(template);
            assertEquals(interviewDateTime, template);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}
