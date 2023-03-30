package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.jobs.DeliveryDate;
import seedu.address.model.jobs.DeliveryJob;
import seedu.address.model.jobs.DeliveryList;
import seedu.address.model.jobs.Earning;
import seedu.address.model.jobs.sorters.SortbyTimeAndEarn;
import seedu.address.model.person.Person;
import seedu.address.model.reminder.Reminder;
import seedu.address.model.stats.WeeklyStats;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    public static final SortbyTimeAndEarn SORTER_BY_DATE = new SortbyTimeAndEarn();

    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final AddressBook addressBook;
    private final DeliveryJobSystem deliveryJobSystem;
    private final UserPrefs userPrefs;
    private final FilteredList<Person> filteredPersons;
    private final FilteredList<DeliveryJob> filteredDeliveryJobs;
    private final SortedList<DeliveryJob> sortedDeliveryJobsList;
    private final ObservableList<Reminder> reminderList;
    private final Map<LocalDate, DeliveryList> weekJobListGroupedByDate;
    private final SortedList<DeliveryJob> sortedDeliveryJobs;
    private LocalDate focusDate;
    private Map<LocalDate, DeliveryList> jobListGroupedByDate;



    /**
     * Initializes a ModelManager with the given addressBook and userPrefs.
     * @param addressBook
     * @param deliveryJobSystem
     * @param userPrefs
     */
    public ModelManager(ReadOnlyAddressBook addressBook, ReadOnlyDeliveryJobSystem deliveryJobSystem,
            ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(addressBook, deliveryJobSystem, userPrefs);

        logger.fine("Initializing with address book: " + addressBook + " and user prefs " + userPrefs);

        this.addressBook = new AddressBook(addressBook);
        this.deliveryJobSystem = new DeliveryJobSystem(deliveryJobSystem);
        this.userPrefs = new UserPrefs(userPrefs);
        this.filteredPersons = new FilteredList<Person>(this.addressBook.getPersonList());
        this.filteredDeliveryJobs = new FilteredList<DeliveryJob>(this.deliveryJobSystem.getDeliveryJobList());
        this.sortedDeliveryJobs = new SortedList<DeliveryJob>(this.deliveryJobSystem.getDeliveryJobList());
        this.sortedDeliveryJobsList = new SortedList<DeliveryJob>(filteredDeliveryJobs);
        //updateSortedDeliveryJobListByDate();
        this.jobListGroupedByDate = new HashMap<LocalDate, DeliveryList>();
        this.weekJobListGroupedByDate = new HashMap<LocalDate, DeliveryList>();
        this.reminderList = this.addressBook.getReminderList();
        this.focusDate = LocalDate.now();
    }

    /**
     * ModelManager.
     */
    public ModelManager() {
        this(new AddressBook(), new DeliveryJobSystem(), new UserPrefs());
    }

    // UserPrefs ===================================================================

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
    public Path getAddressBookFilePath() {
        return userPrefs.getAddressBookFilePath();
    }

    @Override
    public void setAddressBookFilePath(Path addressBookFilePath) {
        requireNonNull(addressBookFilePath);
        userPrefs.setAddressBookFilePath(addressBookFilePath);
    }

    // AddressBook ===============================================================

    @Override
    public ReadOnlyAddressBook getAddressBook() {
        return addressBook;
    }

    @Override
    public void setAddressBook(ReadOnlyAddressBook addressBook) {
        this.addressBook.resetData(addressBook);
    }

    @Override
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return addressBook.hasPerson(person);
    }

    @Override
    public Optional<Person> getPersonById(String id) {
        requireNonNull(id);
        return addressBook.getPersonById(id);
    }

    @Override
    public void deletePerson(Person target) {
        addressBook.removePerson(target);
    }

    @Override
    public void addPerson(Person person) {
        addressBook.addPerson(person);
        updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPerson(Person target, Person editedPerson) {
        requireAllNonNull(target, editedPerson);

        addressBook.setPerson(target, editedPerson);
    }

    // =========== Filtered Person List Accessors ==================

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the
     * internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Person> getFilteredPersonList() {
        return filteredPersons;
    }

    @Override
    public void updateFilteredPersonList(Predicate<Person> predicate) {
        requireNonNull(predicate);
        filteredPersons.setPredicate(predicate);
    }

    // DeliveryJob System =====================================================

    @Override
    public Path getDeliveryJobSystemFilePath() {
        return userPrefs.getDeliveryJobSystemFilePath();
    }

    @Override
    public void setDeliveryJobSystemFilePath(Path deliveryJobSystemFilePath) {
        requireNonNull(deliveryJobSystemFilePath);
        userPrefs.setDeliveryJobSystemFilePath(deliveryJobSystemFilePath);
    }

    @Override
    public void setDeliveryJobSystem(ReadOnlyDeliveryJobSystem jobSystem) {
        this.deliveryJobSystem.resetData(jobSystem);
    }

    @Override
    public ReadOnlyDeliveryJobSystem getDeliveryJobSystem() {
        return deliveryJobSystem;
    }

    @Override
    public boolean hasDeliveryJob(DeliveryJob job) {
        requireNonNull(job);
        return deliveryJobSystem.hasDeliveryJob(job);
    }

    @Override
    public void deleteDeliveryJob(DeliveryJob target) {
        deliveryJobSystem.removeDeliveryJob(target);
    }

    @Override
    public void addDeliveryJob(DeliveryJob job) {
        deliveryJobSystem.addDeliveryJob(job);
        updateFilteredDeliveryJobList(PREDICATE_SHOW_ALL_DELIVERY_JOBS);
    }

    @Override
    public void setDeliveryJob(DeliveryJob target, DeliveryJob editedJob) {
        requireAllNonNull(target, editedJob);

        deliveryJobSystem.setDeliveryJob(target, editedJob);
    }

    // =========== Filtered Delivery Job List Accessors ============

    @Override
    public ObservableList<DeliveryJob> getDeliveryJobList() {
        updateFilteredDeliveryJobList(PREDICATE_SHOW_ALL_DELIVERY_JOBS);
        return filteredDeliveryJobs;
    }

    @Override
    public ObservableList<DeliveryJob> getFilteredDeliveryJobList() {
        return filteredDeliveryJobs;
    }

    @Override
    public void updateFilteredDeliveryJobList(Predicate<DeliveryJob> predicate) {
        requireAllNonNull(predicate);
        filteredDeliveryJobs.setPredicate(predicate);
    }

    @Override
    public void updateSortedDeliveryJobList(Comparator<DeliveryJob> sorter) {
        requireNonNull(sorter);
        sortedDeliveryJobs.setComparator(sorter);
    }

    @Override
    public void updateSortedDeliveryJobListByComparator(Comparator<DeliveryJob> sorter) {
        requireNonNull(sorter);
        sortedDeliveryJobsList.setComparator(sorter);
    }

    @Override
    public ObservableList<DeliveryJob> getSortedDeliveryJobListByComparator() {
        return sortedDeliveryJobsList;
    }

    @Override
    public ObservableList<DeliveryJob> getSortedDeliveryJobList() {
        return FXCollections.observableArrayList(sortedDeliveryJobs);
    }

    @Override
    public void updateSortedDeliveryJobListByDate() {
        jobListGroupedByDate.clear();
        for (int i = 0; i < sortedDeliveryJobs.size(); i++) {
            if (sortedDeliveryJobs.get(i).isScheduled()
                    && (!sortedDeliveryJobs.get(i).getDeliveredStatus())) {
                addJobToJobListBasedOnDay(jobListGroupedByDate, sortedDeliveryJobs.get(i));
            }
        }
    }

    /**
     * Adds job to job list grouped by date according to delivery date
     * Given that job has delivery date and slot
     * @param jobListGroupedByDate
     * @param toAdd
     */
    private void addJobToJobListBasedOnDay(Map<LocalDate, DeliveryList> jobListGroupedByDate, DeliveryJob toAdd) {
        LocalDate jobDate = toAdd.getDate();
        int jobSlot = toAdd.getSlot();
        int slotIndex = (jobSlot) - 1;

        if (jobListGroupedByDate.containsKey(jobDate)) {
            DeliveryList jobsInCurrentSlot = jobListGroupedByDate.get(jobDate);
            if (jobsInCurrentSlot.size() == 0) {
                jobsInCurrentSlot = createEmptyDayJobList();
            }
            if (slotIndex > 4) {
                jobsInCurrentSlot.get(5).add(toAdd);
            } else {
                jobsInCurrentSlot.get(slotIndex).add(toAdd);
            }
            jobListGroupedByDate.put(jobDate, jobsInCurrentSlot);
        } else {
            DeliveryList newDateJobList = createEmptyDayJobList();
            if (slotIndex > 4) {
                newDateJobList.get(5).add(toAdd);
            } else {
                newDateJobList.get(slotIndex).add(toAdd);
            }
            jobListGroupedByDate.put(jobDate, newDateJobList);
        }
    }

    /**
     * Update list of week delivery job list,
     * period spans from 1 week before given date
     * to 1 week after given date
     * @param date given/focused date
     */
    @Override
    public void updateWeekDeliveryJobList(LocalDate date) {
        requireNonNull(date);
        weekJobListGroupedByDate.clear();
        this.focusDate = date;

        int focusDayOfWeek = date.getDayOfWeek().getValue();
        int dayOfWeekTracker = 1;

        while (dayOfWeekTracker < 8) {
            int dayToAdd = dayOfWeekTracker - focusDayOfWeek;
            LocalDate dayInWeek = date.plusDays(dayToAdd);
            addWeekJobList(dayInWeek);
            dayOfWeekTracker++;
        }
    }

    @Override
    public void updateFocusDate(LocalDate jobDate) {
        requireNonNull(jobDate);
        this.focusDate = jobDate;
    }

    private void addWeekJobList(LocalDate dayToAdd) {
        if (jobListGroupedByDate.containsKey(dayToAdd)) {
            DeliveryList currentJobList = jobListGroupedByDate.get(dayToAdd);
            weekJobListGroupedByDate.put(dayToAdd, currentJobList);
        } else {
            weekJobListGroupedByDate.put(dayToAdd, null);
        }
    }

    private DeliveryList createEmptyDayJobList() {
        ArrayList<ArrayList<DeliveryJob>> newEmptyArr = new ArrayList<ArrayList<DeliveryJob>>();
        for (int i = 0; i < 6; i++) {
            newEmptyArr.add(new ArrayList<DeliveryJob>());
        }
        return new DeliveryList(newEmptyArr);
    }

    @Override
    public Map<LocalDate, DeliveryList> getSortedDeliveryJobListByDate() {
        return jobListGroupedByDate;
    }

    @Override
    public Map<LocalDate, DeliveryList> getWeekDeliveryJobList() {
        return weekJobListGroupedByDate;
    }

    @Override
    public DeliveryList getDayOfWeekJob(int dayOfWeek) {
        int focusDayOfWeek = focusDate.getDayOfWeek().getValue();
        LocalDate dayToGet = focusDate.plusDays(dayOfWeek - focusDayOfWeek);
        return weekJobListGroupedByDate.get(dayToGet);
    }

    @Override
    public ObservableList<DeliveryJob> getUnscheduledDeliveryJobList() {
        updateSortedDeliveryJobList(SORTER_BY_DATE);
        FilteredList<DeliveryJob> unscheduledJobList =
                new FilteredList<>(FXCollections.observableArrayList(sortedDeliveryJobs));
        unscheduledJobList.setPredicate(job -> (!job.isValidScheduled()));
        return FXCollections.observableArrayList(unscheduledJobList);
    }

    @Override
    public ObservableList<DeliveryJob> getCompletedDeliveryJobList() {
        updateSortedDeliveryJobList(SORTER_BY_DATE);
        FilteredList<DeliveryJob> unscheduledJobList =
                new FilteredList<>(FXCollections.observableArrayList(sortedDeliveryJobs));
        unscheduledJobList.setPredicate(job -> (job.getDeliveredStatus()));
        return FXCollections.observableArrayList(unscheduledJobList);
    }

    @Override
    public LocalDate getFocusDate() {
        return focusDate;
    }

    //=========== ReminderList Accessors =============================================================

    @Override
    public void deleteReminder(int i) {
        addressBook.removeReminder(i);
    }

    @Override
    public void addReminder(Reminder reminder) {
        addressBook.addReminder(reminder);
    }

    /**
     * Returns an unmodifiable view of the list of {@code Person} backed by the internal list of
     * {@code versionedAddressBook}
     */
    @Override
    public ObservableList<Reminder> getReminderList() {
        return reminderList;
    }

    /**
     * Sorts reminder list
     */
    @Override
    public void sortReminderList() {
        addressBook.sortReminderList();
    }

    @Override
    public void setHasShown(int i, boolean b) {
        reminderList.get(i).setHasShown(b);
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
        return addressBook.equals(other.addressBook)
                && userPrefs.equals(other.userPrefs)
                && filteredPersons.equals(other.filteredPersons);
    }

    @Override
    public boolean sameWeek(DeliveryJob job, WeeklyStats weeklyStats) {
        Optional<DeliveryDate> deliveryDate = job.getDeliveryDate();
        if (!deliveryDate.isEmpty()) {
            DeliveryDate date = deliveryDate.get();
            LocalDate localDate = date.getDate();
            return weeklyStats.getDates().contains(localDate);
        }
        return false;
    }

    @Override
    public ObservableList<DeliveryJob> weekJobsList(ObservableList<DeliveryJob> list, LocalDate date) {
        ObservableList<DeliveryJob> newList = FXCollections.observableArrayList(list);
        WeeklyStats weeklyStats = new WeeklyStats(date);
        for (DeliveryJob job: list) {
            if (!sameWeek(job, weeklyStats)) {
                newList.remove(job);
            }
        }
        return newList;
    }

    @Override
    public double getTotalEarnings(ObservableList<DeliveryJob> list) {
        double earnings = 0;
        for (DeliveryJob job: list) {
            Optional<Earning> earning = job.getEarning();
            Earning earn = earning.get();
            if (earn != null) {
                earnings += Double.parseDouble(earn.value);
            }
        }
        return earnings;
    }

    @Override
    public int getTotalCompleted(ObservableList<DeliveryJob> list) {
        int completed = 0;
        for (DeliveryJob job: list) {
            if (job.getDeliveredStatus()) {
                completed += 1;
            }
        }
        return completed;
    }

    @Override
    public int getTotalPending(ObservableList<DeliveryJob> list) {
        int pending = 0;
        for (DeliveryJob job: list) {
            if (!job.getDeliveredStatus()) {
                pending += 1;
            }
        }
        return pending;
    }

}
