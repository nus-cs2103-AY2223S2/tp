package tfifteenfour.clipboard.logic.parser;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCourseCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditGroupCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditSessionCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditStudentCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class NewEditCommandParser implements Parser<EditCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditCommand parse(String args) throws ParseException {
        Index index;
        CommandTargetType editCommandType;
        try {
            editCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Edit type missing! Please enter a valid edit command. \n"
                    + "Available edit commands are: edit course, edit group, edit session, edit student");
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
        case STUDENT:
            /*  Note: Parsing of student info is done in EditStudentCommand::execute to catch error when user is not
                on STUDENT_PAGE, therefore we need to catch ArrayIndexOutOfBoundsException separately in
                parseStudentIndex. (Others are handled in their parseInfo methods)
             */
            index = parseStudentIndex(args);
            return new EditStudentCommand(index, args);
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

    private Index parseStudentIndex(String args) throws ParseException {
        String[] tokens = ArgumentTokenizer.tokenizeString(args);
        if (tokens.length < 4) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, EditStudentCommand.MESSAGE_USAGE));
        }
        return parseIndex(args);
    }


//    /**
//     * Stores the details to edit the student with. Each non-empty field value will replace the
//     * corresponding field value of the student.
//     */
//    public static class EditStudentDescriptor {
//        private Name name;
//        private Phone phone;
//        private Email email;
//        private StudentId studentId;
//
//        public EditStudentDescriptor() {}
//
//        /**
//         * Copy constructor.
//         * A defensive copy of {@code tags} is used internally.
//         */
//        public EditStudentDescriptor(EditStudentDescriptor toCopy) {
//            setName(toCopy.name);
//            setPhone(toCopy.phone);
//            setEmail(toCopy.email);
//            setStudentId(toCopy.studentId);
//        }
//
//        /**
//         * Returns true if at least one field is edited.
//         */
//        public boolean isAnyFieldEdited() {
//            return CollectionUtil.isAnyNonNull(name, phone, email, studentId);
//        }
//
//        public void setName(Name name) {
//            this.name = name;
//        }
//
//        public Optional<Name> getName() {
//            return Optional.ofNullable(name);
//        }
//
//        public void setPhone(Phone phone) {
//            this.phone = phone;
//        }
//
//        public Optional<Phone> getPhone() {
//            return Optional.ofNullable(phone);
//        }
//
//        public void setEmail(Email email) {
//            this.email = email;
//        }
//
//        public Optional<Email> getEmail() {
//            return Optional.ofNullable(email);
//        }
//
//        public void setStudentId(StudentId studentId) {
//            this.studentId = studentId;
//        }
//
//        public Optional<StudentId> getStudentId() {
//            return Optional.ofNullable(studentId);
//        }
//
//
//        @Override
//        public boolean equals(Object other) {
//            // short circuit if same object
//            if (other == this) {
//                return true;
//            }
//
//            // instanceof handles nulls
//            if (!(other instanceof EditStudentDescriptor)) {
//                return false;
//            }
//
//            // state check
//            EditStudentDescriptor e = (EditStudentDescriptor) other;
//
//            return getName().equals(e.getName())
//                    && getPhone().equals(e.getPhone())
//                    && getEmail().equals(e.getEmail())
//                    && getStudentId().equals(e.getStudentId());
//        }
//    }
}
