package seedu.address.testutil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.session.Location;
import seedu.address.model.session.NameBooleanPair;
import seedu.address.model.session.NamePayRatePair;
import seedu.address.model.session.Session;
import seedu.address.model.session.SessionName;

/**
 * A utility class to help with building Session objects for testing purposes.
 */
public class SessionBuilder {

    /**
     * The default SessionName used in test cases.
     */
    public static final SessionName DEFAULT_SESSION_NAME = new SessionName("Hall");

    /**
     * The default start time used in test cases.
     */
    public static final String DEFAULT_START_TIME = "10-03-2022 10:00";

    /**
     * The default end time used in test cases.
     */
    public static final String DEFAULT_END_TIME = "10-03-2022 11:00";

    /**
     * The default Location used in test cases.
     */
    public static final Location DEFAULT_LOCATION = new Location("Leon Lim Sports Hall Of Champions");

    /**
     * The default ID used in test cases.
     */
    public static final int DEFAULT_ID = 1;

    /**
     * The default NameBooleanPair used in test cases.
     */
    public static final NameBooleanPair DEFAULT_BOOLEAN_PAIR = new NameBooleanPair("John", true);

    /**
     * The default NamePayRatePair used in test cases.
     */
    public static final NamePayRatePair DEFAULT_PAYRATE_PAIR = new NamePayRatePair("John", 45);

    /**
     * The default List of NameBooleanPair used in test cases.
     */
    public static final List<NameBooleanPair> DEFAULT_ATTENDANCE_MAP =
            new ArrayList<>(Arrays.asList(DEFAULT_BOOLEAN_PAIR));

    /**
     * The default List of NamePayRatePair used in test cases.
     */
    public static final List<NamePayRatePair> DEFAULT_PAY_RATE_MAP =
            new ArrayList<>(Arrays.asList(DEFAULT_PAYRATE_PAIR));

    /**
     * Generates a default Session object without attendance and pay rate map.
     *
     * @return A default Session object.
     */
    public static Session generateDefaultSession() {
        return new Session(DEFAULT_START_TIME, DEFAULT_END_TIME,
                DEFAULT_SESSION_NAME, DEFAULT_LOCATION);
    }

    /**
     * Generates a default Session object with attendance and pay rate map.
     *
     * @return A default Session object with attendance and pay rate map.
     */
    public static Session generateDefaultSessionWithMap() {
        return new Session(DEFAULT_START_TIME, DEFAULT_END_TIME,
                DEFAULT_SESSION_NAME, DEFAULT_LOCATION,
                DEFAULT_ID,
                DEFAULT_ATTENDANCE_MAP,
                DEFAULT_PAY_RATE_MAP);
    }
}

