package seedu.address.model.session;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.model.person.Name;
import seedu.address.model.person.PayRate;
import seedu.address.model.person.Person;

/**
 * Represents a Session in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Session implements Comparable<Session> {

    public static final String MESSAGE_START_AFTER_END = "Start date time should be before end date time.";
    public static final String MESSAGE_SAME_DATE_TIME = "Start date time must be different from end date time.";
    public static final String MESSAGE_INVALID_LOCATION = "Invalid Location.";
    public static final String MESSAGE_INVALID_NAME = "Invalid Name.";
    public static final String MESSAGE_INVALID_DATES = "Invalid Date Input Format (DD-MM-YYYY HH:mm). "
            + "Please input valid digits.";
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    private String command;
    private final Logger logger = LogsCenter.getLogger(getClass());
    private final String startDateTime;
    private final String endDateTime;
    private final SessionName name;
    private final int id;
    private final Location location;
    private HashMap<String, Boolean> attendanceMap = new HashMap<>();
    private HashMap<String, Integer> payRateMap = new HashMap<>();

    /**
     * Every field must be present and not null.
     * @param startDateTime start time
     * @param endDateTime end time
     * @param name name of session
     * @param location place of event
     */
    public Session(String startDateTime, String endDateTime, SessionName name, Location location) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
        this.location = location;
        this.id = name.hashCode();
        if (!isValidDateTimeFormat(this.startDateTime) || !isValidDateTimeFormat(this.endDateTime)) {
            throw new IllegalArgumentException("Date Time should be in the format dd-MM-yyyy HH:mm");
        }

        int bug = this.isValidSession();
        if (bug == 1) {
            throw new IllegalArgumentException(MESSAGE_SAME_DATE_TIME);
        } else if (bug == 2) {
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME);
        } else if (bug == 3) {
            throw new IllegalArgumentException(MESSAGE_INVALID_LOCATION);
        } else if (bug == 4) {
            throw new IllegalArgumentException(MESSAGE_START_AFTER_END);
        }
    }

    /**
     * Constructs a {@code Session}.
     * @param startDateTime A valid start date and time.
     * @param endDateTime A valid end date and time.
     * @param name A valid session name.
     * @param location A valid location.
     * @param id A valid id.
     * @throws IllegalArgumentException If any of the constraints are violated.
     */
    public Session(String startDateTime, String endDateTime, SessionName name, Location location, int id) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
        this.location = location;
        this.id = id;
        if (!isValidDateTimeFormat(this.startDateTime) || !isValidDateTimeFormat(this.endDateTime)) {
            throw new IllegalArgumentException("Date Time should be in the format dd-MM-yyyy HH:mm");
        }

        int bug = this.isValidSession();
        if (bug == 1) {
            throw new IllegalArgumentException(MESSAGE_SAME_DATE_TIME);
        } else if (bug == 2) {
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME);
        } else if (bug == 3) {
            throw new IllegalArgumentException(MESSAGE_INVALID_LOCATION);
        } else if (bug == 4) {
            throw new IllegalArgumentException(MESSAGE_START_AFTER_END);
        }
    }
    /**
     * Represents a session in the sports tracker.
     * Guarantees: details are present and not null, field values are validated, immutable.
     */
    public Session(String startDateTime,
                   String endDateTime,
                   SessionName name,
                   Location location,
                   int id,
                   List<NameBooleanPair> nameBooleanPairs,
                   List<NamePayRatePair> namePayRatePairs) {
        this.startDateTime = startDateTime;
        this.endDateTime = endDateTime;
        this.name = name;
        this.location = location;
        this.id = id;

        for (NameBooleanPair pair : nameBooleanPairs) {
            attendanceMap.put(pair.getName(), pair.isPresent());
        }

        for (NamePayRatePair pair : namePayRatePairs) {
            payRateMap.put(pair.getName(), pair.getPayRate());
        }

        if (!isValidDateTimeFormat(this.startDateTime) || !isValidDateTimeFormat(this.endDateTime)) {
            throw new IllegalArgumentException("Date Time should be in the format dd-MM-yyyy HH:mm");
        }

        int bug = this.isValidSession();
        if (bug == 1) {
            throw new IllegalArgumentException(MESSAGE_SAME_DATE_TIME);
        } else if (bug == 2) {
            throw new IllegalArgumentException(MESSAGE_INVALID_NAME);
        } else if (bug == 3) {
            throw new IllegalArgumentException(MESSAGE_INVALID_LOCATION);
        } else if (bug == 4) {
            throw new IllegalArgumentException(MESSAGE_START_AFTER_END);
        }
    }

    /**
     * adds person to a session
     * @param person Person to add
     */
    public void addPersonToSession(Person person) {
        String name = person.getName().formattedName;
        PayRate payRate = person.getPayRate();
        attendanceMap.put(name, false);
        payRateMap.put(name, payRate.toInt());
    }

    /**
     * removes person from a session
     * @param name String name of person
     */
    public void removePersonFromSession(String name) {
        attendanceMap.remove(name);
        payRateMap.remove(name);
    }

    /**
     * sets student as present
     * @param name String name of person
     */
    public void markStudentPresent(String name) {
        attendanceMap.remove(name);
        attendanceMap.put(name, true);
    }

    /**
     * sets student as absent
     * @param name String name of person
     */
    public void markStudentAbsent(String name) {
        attendanceMap.remove(name);
        attendanceMap.put(name, false);
    }

    /**
     * checks if session contains student
     * @param name String name of person
     */
    public boolean contains(String name) {
        return attendanceMap.containsKey(name);
    }

    /**
     * Returns true if the given string is a valid date format.
     * The date format is "dd-MM-yyyy".
     */
    public static boolean isValidDateFormat(String str) {
        try {
            LocalDate.parse(str, DateTimeFormatter.ofPattern("dd-MM-yyyy"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    /**
     * Returns true if the given string is a valid time format, i.e. "HH:mm".
     */
    public static boolean isValidTimeFormat(String test) {
        if (test == null || test.isEmpty()) {
            return false;
        }
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalTime.parse(test, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public String getStartDateTime() {
        return startDateTime;
    }

    public String getEndDateTime() {
        return endDateTime;
    }

    public SessionName getSessionName() {
        return name;
    }

    public String getName() {
        return name.toString();
    }

    public Location getLocation() {
        return location;
    }

    /**
     * Returns true if a given string is a valid date time format.
     */
    public static boolean isValidDateTimeFormat(String test) {
        try {
            LocalDateTime.parse(test, DATE_TIME_FORMATTER);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Returns true if both sessions have the same identity and attributes.
     * This defines a stronger notion of equality between two sessions.
     */
    public boolean isSameSession(Session otherSession) {
        return otherSession.getName().equalsIgnoreCase(this.getName());
    }

    /**
     * Returns true if both sessions have the same identity and attributes.
     * This defines a weaker notion of equality between two sessions.
     */
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Session)) {
            return false;
        }

        Session otherSession = (Session) other;
        return otherSession.getStartDateTime().equals(getStartDateTime())
                && otherSession.getEndDateTime().equals(getEndDateTime())
                && otherSession.getName().equals(getName())
                && otherSession.getLocation().equals(getLocation());
    }

    /**
     * Checks if the session is valid based on the start and end date/time, session name, and location.
     * @return an integer value representing the validity of the session:
     */
    public int isValidSession() {
        boolean isSameDateTime = startDateTime.equalsIgnoreCase(endDateTime);
        boolean isBeforeTest = LocalDateTime.parse(startDateTime, DateTimeFormatter
                        .ofPattern("dd-MM-yyyy HH:mm"))
                .isBefore(LocalDateTime.parse(endDateTime, DateTimeFormatter
                        .ofPattern("dd-MM-yyyy HH:mm")));
        boolean validNameTest = Name.isValidName(getName());
        boolean validLocationTest = Location.isValidLocation(getLocation().toString());

        if (isSameDateTime) {
            return 1;
        } else if (!validNameTest) {
            return 2;
        } else if (!validLocationTest) {
            return 3;
        } else if (!isBeforeTest) {
            return 4;
        } else {
            return 0;
        }
    }

    /**
     * Returns the duration of the session.
     * @return duration of the session.
     */
    public Duration getSessionDuration() {
        return Duration.between(LocalDateTime.parse(startDateTime,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")),
                LocalDateTime.parse(endDateTime,
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(startDateTime, endDateTime, name, location);
    }

    public String getAttendees() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Boolean> entry : attendanceMap.entrySet()) {
            sb.append(entry.getKey()).append(": ");
            if (entry.getValue()) {
                sb.append("1, ");
            } else {
                sb.append("0, ");
            }
        }

        if (attendanceMap.size() > 0) {
            sb.setLength(sb.length() - 2);
        }

        return sb.toString();
    }

    /**
     * Calculates the total pay for a session based on the pay rates of the attendees
     * and the duration of the session.
     *
     * @return the total pay for the session
     */
    public float getTotalPay() {
        float totalPay = 0;
        long durationInMins = getSessionDuration().toMinutes();

        try {
            for (Map.Entry<String, Boolean> entry : attendanceMap.entrySet()) {
                if (entry.getValue()) {
                    float indivPay = payRateMap.get(entry.getKey());
                    totalPay += indivPay / 60 * durationInMins;
                }
            }
        } catch (NullPointerException e) {
            logger.warning("No pay rates for session " + getName());
        }
        return totalPay;
    }


    /**
     * Returns a string representation of the attendance count for the session.
     *
     * @return a string of the format "X/Y", where X is the number of attendees
     *      present and Y is the total number of attendees
     */
    public String getAttendanceCount() {
        int totalCount = 0;
        int presentCount = 0;
        for (Map.Entry<String, Boolean> entry : attendanceMap.entrySet()) {
            if (entry.getValue()) {
                presentCount++;
            }
            totalCount++;
        }

        return String.format("%d/%d", presentCount, totalCount);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(" Session name: ")
                .append(getName())
                .append("\n Start: ")
                .append(LocalDateTime.parse(getStartDateTime(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append("\n End: ")
                .append(LocalDateTime.parse(getEndDateTime(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append("\n Location: ")
                .append(getLocation())
                .append("\n Attendees: ")
                .append(getAttendees());
        return builder.toString();
    }

    public String getCommand() {
        return String.format("%s: from %s to %s | at %s",
                getName(), startDateTime, endDateTime, getLocation());
    }

    /**
     * Returns a command string representation of this {@code Session} object.
     * The command string is in the format of "start date time to end date time".
     * @return a command string representation of this {@code Session} object.
     */
    public String toCommandString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(": ")
                .append(LocalDateTime.parse(getStartDateTime(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append(" to ")
                .append(LocalDateTime.parse(getEndDateTime(),
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")).format(DATE_TIME_FORMATTER))
                .append(" at ")
                .append(getLocation());
        return builder.toString();
    }

    public String getDate() {
        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));
    }

    public int getDay() {
        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.getDayOfMonth();
    }

    public int getMonth() {
        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.getMonthValue();
    }

    public int getYear() {
        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.getYear();
    }

    public String getTimeFormat() {
        LocalDateTime dateTime = LocalDateTime.parse(startDateTime, DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        return dateTime.format(DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public int compareTo(Session other) {
        LocalDateTime thisStartDateTime = LocalDateTime.parse(startDateTime, DATE_TIME_FORMATTER);
        LocalDateTime otherStartDateTime = LocalDateTime.parse(other.startDateTime, DATE_TIME_FORMATTER);

        return thisStartDateTime.compareTo(otherStartDateTime);
    }

    public String getId() {
        return String.valueOf(id);
    }

    public ArrayList<NameBooleanPair> getNameBooleanMap() {
        ArrayList<NameBooleanPair> map = new ArrayList<>();
        for (Map.Entry<String, Boolean> set
                : attendanceMap.entrySet()) {
            NameBooleanPair toAdd =
                    new NameBooleanPair(
                            set.getKey(),
                            set.getValue());
            map.add(toAdd);
        }
        return map;
    }

    public ArrayList<NamePayRatePair> getNamePayRateMap() {
        ArrayList<NamePayRatePair> map = new ArrayList<>();
        for (Map.Entry<String, Integer> set
                : payRateMap.entrySet()) {
            NamePayRatePair toAdd =
                    new NamePayRatePair(
                            set.getKey(),
                            set.getValue());
            map.add(toAdd);
        }
        return map;
    }

    /**
     * @return A new Session object that is a copy of this session.
     */
    public Session copy() {
        return new Session(this.startDateTime,
                this.endDateTime,
                this.name,
                this.location,
                this.id,
                this.getNameBooleanMap(),
                this.getNamePayRateMap());
    }

    public HashMap<String, Boolean> getAttendanceMap() {
        return attendanceMap;
    }


    /**
     * Checks if this session overlaps with the given session.
     * Two sessions overlap if their scheduled timings intersect.
     * @param otherSession The other session to check for overlap.
     * @return {@code true} if the sessions overlap, {@code false} otherwise.
     */
    public boolean overlaps(Session otherSession) {
        return LocalDateTime.parse(endDateTime, DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm")).isAfter(LocalDateTime.parse(otherSession.getStartDateTime(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")))
                && LocalDateTime.parse(otherSession.getEndDateTime(), DateTimeFormatter
                .ofPattern("dd-MM-yyyy HH:mm")).isAfter(LocalDateTime.parse(this.getStartDateTime(),
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
    }

}
