package tfifteenfour.clipboard.logic.parser;

import static java.util.Objects.requireNonNull;
import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_COURSE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_EMAIL;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_NAME;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_PHONE;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_STUDENTID;
import static tfifteenfour.clipboard.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.commons.util.CollectionUtil;
import tfifteenfour.clipboard.logic.commands.addcommand.AddTaskCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCourseCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditGroupCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditSessionCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditStudentCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditTaskCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.tag.Tag;
import tfifteenfour.clipboard.model.task.Task;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class NewEditCommandParser implements Parser<EditCommand> {
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        Index index;
        CommandTargetType editCommandType;
        try {
            editCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Edit type missing! Please enter a valid edit command. \n"
                    + "Available edit commands are: edit course, edit group, edit session, edit task, edit student");
        }

        switch (editCommandType) {
        case MODULE:
            Course newCourse = parseCourseInfo(args);
            index = parseIndex(args);
            return new EditCourseCommand(index, newCourse);
        case GROUP:
            Group newGroup = parseGroupInfo(args);
            index = parseIndex(args);
            return new EditGroupCommand(index, newGroup);
        case SESSION:
            Session newSession = parseSessionInfo(args);
            index = parseIndex(args);
            return new EditSessionCommand(index, newSession);
        case TASK:
            Task newTask = parseTaskInfo(args);
            index = parseIndex(args);
            return new EditTaskCommand(index, newTask);
        case STUDENT:
            EditStudentDescriptor editStudentDescriptor = parseStudentInfo(args);
            index = parseIndex(args);
            return new EditStudentCommand(index, editStudentDescriptor);
        default:
            throw new ParseException("Invalid type for edit command");
        }
    }

    private Index parseIndex(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        Index index = ParserUtil.parseIndex(tokens[2]);
        return index;
    }

    private Course parseCourseInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditCourseCommand.MESSAGE_USAGE));
        }

        Course course = ParserUtil.parseCourse(tokens[3]);
        return course;
    }

    private Group parseGroupInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditGroupCommand.MESSAGE_USAGE));
        }

        Group group = ParserUtil.parseGroup(tokens[3]);
        return group;
    }

    private Session parseSessionInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length != 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditSessionCommand.MESSAGE_USAGE));
        }

        Session session = ParserUtil.parseSession(tokens[3]);
        return session;
    }

    private Task parseTaskInfo(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length < 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTaskCommand.MESSAGE_USAGE));
        }

        String taskName = args.split(" ", 2)[1].split(" ", 2)[1].split(" ", 2)[1];

        Task task = ParserUtil.parseTask(taskName);
        return task;
    }

    private EditStudentDescriptor parseStudentInfo(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenizePrefixes(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_STUDENTID,
                        PREFIX_COURSE, PREFIX_TAG);

        EditStudentDescriptor editStudentDescriptor = new EditStudentDescriptor();
        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
        }
        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
        }
        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
        }
        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
            editStudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get()));
        }
        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
            parseModulesForEdit(argMultimap.getAllValues(PREFIX_COURSE)).ifPresent(editStudentDescriptor::setModules);
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);

        if (!editStudentDescriptor.isAnyFieldEdited()) {
            throw new ParseException(MESSAGE_NOT_EDITED);
        }

        return editStudentDescriptor;
    }

    /**
     * Parses {@code Collection<String> modules} into a {@code Set<ModuleCode>} if {@code modules} is non-empty.
     * @throws ParseException if {@code modules} contain only one element which is an empty string
     */
    private Optional<Set<Course>> parseModulesForEdit(Collection<String> modules) throws ParseException {
        Collection<String> moduleSet = modules;
        return Optional.of(ParserUtil.parseModules(moduleSet));
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

    /**
     * Stores the details to edit the student with. Each non-empty field value will replace the
     * corresponding field value of the student.
     */
    public static class EditStudentDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private StudentId studentId;
        private Set<Course> modules;
        private Set<Tag> tags;

        public EditStudentDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setStudentId(toCopy.studentId);
            setModules(toCopy.modules);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, studentId, modules, tags);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setStudentId(StudentId studentId) {
            this.studentId = studentId;
        }

        public Optional<StudentId> getStudentId() {
            return Optional.ofNullable(studentId);
        }

        /**
         * Sets {@code modules} to this object's {@code modules}.
         * A defensive copy of {@code modules} is used internally.
         */
        public void setModules(Set<Course> modules) {
            this.modules = (modules != null) ? new HashSet<>(modules) : null;
        }

        /**
         * Returns an unmodifiable modules set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code modules} is null.
         */
        public Optional<Set<Course>> getModules() {
            return (modules != null) ? Optional.of(Collections.unmodifiableSet(modules)) : Optional.empty();
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditStudentDescriptor)) {
                return false;
            }

            // state check
            EditStudentDescriptor e = (EditStudentDescriptor) other;

            return getName().equals(e.getName())
                    && getPhone().equals(e.getPhone())
                    && getEmail().equals(e.getEmail())
                    && getStudentId().equals(e.getStudentId())
                    && getModules().equals(e.getModules())
                    && getTags().equals(e.getTags());
        }
    }
}
