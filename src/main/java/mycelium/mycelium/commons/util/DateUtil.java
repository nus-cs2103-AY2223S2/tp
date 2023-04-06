package mycelium.mycelium.commons.util;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.time.temporal.TemporalAdjusters;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;


/**
 * A class for Date utility functions.
 */
public class DateUtil extends JsonDeserializer<LocalDate> {
    /**
     * Parses dates in the dd/MM/uuuu format. Range of allowed years is [-9999, 9999].
     */
    public static final DateTimeFormatter DATE_FMT =
        DateTimeFormatter.ofPattern("dd/MM/uuuu").withResolverStyle(ResolverStyle.STRICT);

    /**
     * Checks if the {@code date} is within this or next week.
     * Week is counted as starting from Sunday.
     *
     * @param date cannot be null
     * @return true if the {@code date} is within this or next week, or false otherwise.
     */
    public static boolean isWithinThisAndNextWeek(LocalDate date) {
        requireNonNull(date);
        assert date != null;
        LocalDate currentDate = LocalDate.now();
        LocalDate startOfCurrentWeek =
            currentDate.with(TemporalAdjusters.previousOrSame(DayOfWeek.SUNDAY));
        LocalDate endOfNextWeek =
            startOfCurrentWeek.plusDays(13);

        return (date.isAfter(startOfCurrentWeek) || date.isEqual(startOfCurrentWeek))
            && (date.isEqual(endOfNextWeek) || date.isBefore(endOfNextWeek));
    }

    /**
     * Checks if {@code date} is before current date.
     *
     * @param date cannot be null
     * @return true if {@code date} is before current time, or false otherwise.
     */
    public static boolean isBeforeToday(LocalDate date) {
        requireNonNull(date);
        LocalDate currentDate = LocalDate.now();

        return date.isBefore(currentDate);
    }

    @Override
    public LocalDate deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        String value = p.getValueAsString();
        return LocalDate.parse(value, DATE_FMT);
    }
}
