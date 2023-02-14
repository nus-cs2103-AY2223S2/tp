package seedu.address.commons.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Collections;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.Events;

/**
 * Utility class for Google Calendar API
 */
public class GcUtil {

    private static final String APPLICATION_NAME = "Calidr Google Calendar API";
    /**
     * Global instance of the JSON factory.
     */
    private static final JsonFactory JSON_FACTORY = GsonFactory.getDefaultInstance();
    /**
     * Directory to store authorization tokens for this application.
     */
    private static final String TOKENS_DIRECTORY_PATH = "tokens";

    /**
     * Global instance of the scopes required by this quickstart.
     * If modifying these scopes, delete your previously saved tokens/ folder.
     */
    private static final List<String> SCOPES = Collections.singletonList(CalendarScopes.CALENDAR_READONLY);
    private static final String CREDENTIALS_FILE_PATH = "/credentials.json";
    private static Calendar service;

    private static String calendarId = "primary";

    /**
     * Creates an authorized Credential object.
     *
     * @param httpTransport The network HTTP Transport.
     * @return An authorized Credential object.
     * @throws IOException If the credentials.json file cannot be found.
     */
    private static Credential getCredentials(final NetHttpTransport httpTransport) throws IOException {
        // Load client secrets.
        InputStream in = GcUtil.class.getResourceAsStream(CREDENTIALS_FILE_PATH);
        if (in == null) {
            throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
        }
        GoogleClientSecrets clientSecrets = GoogleClientSecrets
                .load(JSON_FACTORY, new InputStreamReader(in));

        // Build flow and trigger user authorization request.
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow
                .Builder(httpTransport, JSON_FACTORY, clientSecrets, SCOPES)
                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
                .setAccessType("offline")
                .build();
        LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
        //returns an authorized Credential object.
        return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
    }

    private static Calendar getService() throws IOException, GeneralSecurityException {
        if (service == null) {
            // Build a new authorized API client service.
            return reconnectService();
        }
        return service;
    }

    private static Calendar reconnectService() throws IOException, GeneralSecurityException {
        final NetHttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
        service = new Calendar
                .Builder(httpTransport, JSON_FACTORY, getCredentials(httpTransport))
                .setApplicationName(APPLICATION_NAME)
                .build();
        return service;
    }

    public static List<Event> getEvents(
            String calendarId, Integer limit, DateTime endsAfter,
            String order, Boolean expandRecure)
            throws IOException, GeneralSecurityException {
        Events events = GcUtil.getService()
                .events()
                .list(calendarId)
                .setMaxResults(limit)
                .setTimeMin(endsAfter)
                .setOrderBy(order)
                .setSingleEvents(expandRecure)
                .execute();
        return events.getItems();
    }

    public static List<Event> getEvents() throws IOException, GeneralSecurityException {
        // List the next 10 events from the primary calendar.
        DateTime now = new DateTime(System.currentTimeMillis());
        return getEvents(GcUtil.calendarId, 10, now, "startTime", true);
    }

    /**
     * Prints the next 10 events from the primary calendar.
     *
     * @throws IOException              If the credentials.json file cannot be found.
     * @throws GeneralSecurityException If the credentials.json file cannot be found.
     */
    public static String eventsToString() throws IOException, GeneralSecurityException {
        List<Event> events = getEvents();
        if (events.isEmpty()) {
            return "No upcoming events found.";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("Upcoming events\n");
            for (Event event : events) {
                DateTime start = event.getStart().getDateTime();
                if (start == null) {
                    start = event.getStart().getDate();
                }
                sb.append(String.format("\t%s (%s)\n", event.getSummary(), start));
            }
            return sb.toString();
        }
    }
}
