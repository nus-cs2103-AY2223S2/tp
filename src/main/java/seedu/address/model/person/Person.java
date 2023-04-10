package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.model.commitment.Commitment;
import seedu.address.model.commitment.Lesson;
import seedu.address.model.tag.GroupTag;
import seedu.address.model.tag.ModuleTag;
import seedu.address.model.time.TimePeriod;
import seedu.address.model.time.util.TimeUtil;
import seedu.address.model.timetable.Timetable;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final TelegramHandle telegramHandle;

    // Indexing fields
    private final ContactIndex contactIndex;

    // Data fields
    private final Station station;
    private final GroupTagSet groupTags = new GroupTagSet();
    private final ModuleTagSet moduleTags = new ModuleTagSet();
    private final Timetable timetable = new Timetable();

    // logger
    private final Logger logger = LogsCenter.getLogger(Person.class);

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Station station, TelegramHandle telegramHandle,
                  ContactIndex contactIndex, Set<GroupTag> groupTags, Set<ModuleTag> moduleTags) {
        requireAllNonNull(name, phone, email, station, telegramHandle, contactIndex, groupTags, moduleTags);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.station = station;
        this.telegramHandle = telegramHandle;
        this.contactIndex = contactIndex;
        this.groupTags.addAll(groupTags);

        if (canAddModuleTags(moduleTags)) {
            addModuleTags(moduleTags);
        }
    }

    /**
     * Copies the data of a person into a new Person.
     */
    public Person copy() {
        return new Person(getName(), getPhone(), getEmail(),
                getStation(), getTelegramHandle(), getContactIndex(),
                getImmutableGroupTags(), getImmutableModuleTags());
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Station getStation() {
        return station;
    }

    public TelegramHandle getTelegramHandle() {
        return telegramHandle;
    }

    public ContactIndex getContactIndex() {
        return contactIndex;
    }

    /**
     * Returns a copy of the person's group tags.
     */
    public GroupTagSet getGroupTags() {
        return groupTags;
    }

    /**
     * Returns an immutable group tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<GroupTag> getImmutableGroupTags() {
        return groupTags.getImmutableGroups();
    }

    /**
     * Returns a copy of the person's module tags.
     */
    public ModuleTagSet getModuleTags() {
        return moduleTags;
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableModuleTags() {
        return moduleTags.getImmutableModules();
    }

    /**
     * Returns an immutable module tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<ModuleTag> getImmutableCommonModuleTags() {
        return Collections.unmodifiableSet(moduleTags.getImmutableCommonModules());
    }

    /**
     * Sets the common modules that the person has with the user.
     */
    public void setCommonModules(Set<ModuleTag> userModules) {
        moduleTags.setCommonModules(userModules);
    }

    /**
     * Creates a new person and sets the contact index.
     */
    public Person setContactIndex(ContactIndex contactIndex) {
        return new Person(name, phone, email, station, telegramHandle,
                contactIndex, groupTags.getImmutableGroups(), moduleTags.getImmutableModules());
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Adds group tags to the {@code GroupTagSet}.
     * @param groupTags Tags to be added to the person.
     * @return group tags that were successfully added.
     */
    public Set<GroupTag> addGroupTags(Set<GroupTag> groupTags) {
        Set<GroupTag> currentGroupTags = getImmutableGroupTags();
        Set<GroupTag> addableGroupTags = groupTags.stream()
                .filter(gt -> !currentGroupTags.contains(gt))
                .collect(Collectors.toSet());
        this.getGroupTags().addAll(addableGroupTags);
        return addableGroupTags;
    }

    /**
     * Removes group tags from the person.
     * @param groupTags group tags to remove.
     * @return group tags that were successfully removed.
     */
    public Set<GroupTag> removeGroupTags(Set<GroupTag> groupTags) {
        Set<GroupTag> currentGroupTags = getImmutableGroupTags();
        Set<GroupTag> removableGroupTags = groupTags.stream()
                .filter(currentGroupTags::contains)
                .collect(Collectors.toSet());
        this.getGroupTags().removeAll(groupTags);
        return removableGroupTags;
    }

    /**
     * Adds module tags to the {@code ModuleTagSet}.
     * Lessons within the {@code moduleTags} must not clash with each other.
     * They must also not clash with the timetable.
     * @param moduleTags Tags to be added to the person.
     * @return module tags that were successfully added.
     */
    public Set<ModuleTag> addModuleTags(Collection<? extends ModuleTag> moduleTags) {
        Set<Lesson> lessons = moduleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        List<TimePeriod> timePeriods = lessons.stream()
                .map(Lesson::getTimePeriod)
                .collect(Collectors.toList());

        assert !TimeUtil.hasAnyClash(timePeriods);
        assert canAddCommitments(lessons);

        this.moduleTags.addAll(moduleTags);
        lessons.forEach(timetable::addCommitment);

        return new HashSet<>(moduleTags);
    }

    /**
     * Overloaded method for adding module tags.
     */
    public Set<ModuleTag> addModuleTags(ModuleTag... moduleTags) {
        return addModuleTags(Set.of(moduleTags));
    }

    /**
     * Checks whether the timetable allows the addition of the commitments.
     */
    public boolean canAddCommitments(Collection<? extends Commitment> commitments) {
        return commitments.stream().allMatch(timetable::canFitCommitment);
    }

    /**
     * Checks which commitments in the timetable clash with the ones given.
     */
    public String getClashingCommitmentsAsStr(Collection<? extends Commitment> commitments) {
        List<Commitment> commitmentsList = new ArrayList<>(commitments);
        List<String> clashingCommitments = commitments.stream()
                .map(timetable::getClashingCommitments)
                .filter(s -> !s.isEmpty())
                .map(socc -> socc.stream()
                        .map(Commitment::toString)
                        .collect(Collectors.joining(", ")))
                .collect(Collectors.toList());

        return CollectionUtil.zip(commitmentsList.stream(), clashingCommitments.stream(), (c, cc)
                        -> String.format("%s clashes with %s", c, cc))
                .collect(Collectors.joining("\n"));
    }

    private boolean canAddModuleTags(Collection<? extends ModuleTag> moduleTags) {
        Set<Lesson> lessons = moduleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .collect(Collectors.toSet());

        List<TimePeriod> timePeriods = lessons.stream()
                .map(Lesson::getTimePeriod)
                .collect(Collectors.toList());

        return canAddCommitments(lessons) && !TimeUtil.hasAnyClash(timePeriods);
    }

    /**
     * Checks the {@code ModuleTagSet} whether the lesson exists.
     * If it exists, then remove the
     * lesson from both the {@code ModuleTagSet} and {@code Timetable}
     */
    public Set<ModuleTag> removeModuleTags(Set<ModuleTag> moduleTags) {
        List<ModuleTag> removableModuleTags = moduleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .map(lesson -> new ModuleTag(lesson.getModuleCode(), lesson))
                .filter(this.moduleTags::canRemove)
                .collect(Collectors.toList());

        List<ModuleTag> completelyRemovableModuleTags = moduleTags.stream()
                .filter(ModuleTag::isBasicTag)
                .collect(Collectors.toList());

        this.moduleTags.removeAll(removableModuleTags);
        removableModuleTags.stream()
                .map(ModuleTag::getImmutableLessons)
                .flatMap(Set::stream)
                .forEach(timetable::removeCommitment);

        completelyRemovableModuleTags.stream()
                .map(ModuleTag::getModuleCode)
                .forEach(this.moduleTags::remove);

        return Stream.of(completelyRemovableModuleTags, removableModuleTags)
                .flatMap(Collection::stream)
                .collect(Collectors.toSet());
    }

    public Timetable getTimetable() {
        return timetable;
    }

    public Set<? extends Commitment> getCommitments() {
        return getLessons();
    }

    private Set<? extends Lesson> getLessons() {
        Set<Lesson> lessons = new HashSet<>();
        getImmutableModuleTags()
                .stream().map(ModuleTag::getImmutableLessons)
                .forEach(lessons::addAll);
        return lessons;
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;

        return otherPerson.getName().equals(getName())
                && otherPerson.getPhone().equals(getPhone())
                && otherPerson.getEmail().equals(getEmail())
                && otherPerson.getGroupTags().equals(getGroupTags())
                && otherPerson.getStation().equals(getStation())
                && otherPerson.getImmutableGroupTags().equals(getImmutableGroupTags())
                && otherPerson.getTelegramHandle().equals(getTelegramHandle())
                && otherPerson.getModuleTags().equals(getModuleTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, station, telegramHandle, groupTags, moduleTags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getName())
                .append(" [Index : " + String.valueOf(contactIndex) + "]")
                .append("\nPhone: ")
                .append(getPhone())
                .append("\nEmail: ")
                .append(getEmail())
                .append("\nStation: ")
                .append(getStation())
                .append("\nTelegram: ")
                .append(getTelegramHandle())
                .append("\nGroups: ")
                .append(getImmutableGroupTags())
                .append("\nModules: ")
                .append(getImmutableModuleTags())
                .append("\nLessons: ")
                .append(getLessonsAsStr());

        return builder.toString();
    }

    public String getLessonsAsStr() {
        return this.moduleTags.lessonsAsStr();
    }

}
