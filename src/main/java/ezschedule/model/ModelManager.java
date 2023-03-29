package ezschedule.model;

import static ezschedule.commons.util.CollectionUtil.requireAllNonNull;
import static ezschedule.logic.commands.ShowNextCommand.SHOW_UPCOMING_COUNT_ONE;
import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.logging.Logger;
import java.util.HashMap;

import ezschedule.commons.core.GuiSettings;
import ezschedule.commons.core.LogsCenter;
import ezschedule.logic.commands.Command;
import ezschedule.model.event.Event;
import ezschedule.model.event.UpcomingEventPredicate;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import java.util.ArrayList;

/**
 * Represents the in-memory model of the scheduler data.
 */
public class ModelManager implements Model {

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final Scheduler scheduler;
    private final UserPrefs userPrefs;
    private ArrayList<Command> recentCommand;
    private ArrayList<Event> recentEvent;
    private final FilteredList<Event> upcomingEvents;
    /**
     * Initializes a ModelManager with the given scheduler and userPrefs.
     */
    public ModelManager(ReadOnlyScheduler scheduler, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(scheduler, userPrefs);

        logger.fine("Initializing with scheduler: " + scheduler + " and user prefs " + userPrefs);

        this.scheduler = new Scheduler(scheduler);
        this.userPrefs = new UserPrefs(userPrefs);
        
        recentCommand = new ArrayList<Command>();
        recentEvent = new ArrayList<Event>();
        
        upcomingEvents = new FilteredList<>(this.scheduler.getEventList());
        updateUpcomingEventList(new UpcomingEventPredicate(SHOW_UPCOMING_COUNT_ONE));
    }

    public ModelManager() {
        this(new Scheduler(), new UserPrefs());
    }
    
    public ModelManager(ArrayList<Command> command, ArrayList<Event> event) {
        this(new Scheduler(), new UserPrefs());
        this.recentCommand = command;
        this.recentEvent = event;
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getSchedulerFilePath() {
        return userPrefs.getSchedulerFilePath();
    }

    @Override
    public void setSchedulerFilePath(Path schedulerFilePath) {
        requireNonNull(schedulerFilePath);
        userPrefs.setSchedulerFilePath(schedulerFilePath);
    }

    //=========== Scheduler ================================================================================

    @Override
    public ReadOnlyScheduler getScheduler() {
        return scheduler;
    }

    @Override
    public void setScheduler(ReadOnlyScheduler scheduler) {
        this.scheduler.resetData(scheduler);
    }

    @Override
    public boolean hasEvent(Event event) {
        requireNonNull(event);
        return scheduler.hasEvent(event);
    }

    @Override
    public boolean hasEventAtTime(Event event) {
        requireNonNull(event);
        return scheduler.hasEventAtTime(event);
    }

    @Override
    public void deleteEvent(Event target) {
        scheduler.removeEvent(target);
        updateUpcomingEventList(new UpcomingEventPredicate());
    }

    @Override
    public void addEvent(Event event) {
        scheduler.addEvent(event);
        updateFilteredEventList(PREDICATE_SHOW_ALL_EVENTS);
        updateUpcomingEventList(new UpcomingEventPredicate());
    }

    @Override
    public void setEvent(Event target, Event editedEvent) {
        requireAllNonNull(target, editedEvent);
        scheduler.setEvent(target, editedEvent);
        updateUpcomingEventList(new UpcomingEventPredicate());
    }
    
    @Override
    public ArrayList<Command> recentCommand() {
        return recentCommand;
    }
    
    @Override
    public ArrayList<Event> recentEvent() {
        return recentEvent;
    }
    
    @Override
    public void undoRecent(ArrayList<Command> commandList, ArrayList<Event> eventList) {
        this.recentCommand = commandList;
        this.recentEvent = eventList;
    }
    
    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
    

    //=========== Event List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Event} backed by the internal list of
     * {@code scheduler}
     */
    @Override
    public ObservableList<Event> getEventList() {
        return scheduler.getEventList();
    }

    @Override
    public ObservableList<Event> getFilteredEventList() {
        return upcomingEvents;
    }

    @Override
    public ObservableList<Event> getUpcomingEventList() {
        return upcomingEvents;
    }

    @Override
    public void updateFilteredEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        upcomingEvents.setPredicate(predicate);
    }

    @Override
    public void updateUpcomingEventList(Predicate<Event> predicate) {
        requireNonNull(predicate);
        upcomingEvents.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return scheduler.equals(other.scheduler)
                && userPrefs.equals(other.userPrefs)
                && upcomingEvents.equals(other.upcomingEvents);
    }
}
