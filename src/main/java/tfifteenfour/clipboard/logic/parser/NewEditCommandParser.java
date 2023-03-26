package tfifteenfour.clipboard.logic.parser;

import tfifteenfour.clipboard.commons.core.index.Index;
import tfifteenfour.clipboard.logic.commands.addcommand.AddCourseCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddGroupCommand;
import tfifteenfour.clipboard.logic.commands.addcommand.AddSessionCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditCourseCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditGroupCommand;
import tfifteenfour.clipboard.logic.commands.editcommand.EditSessionCommand;
import tfifteenfour.clipboard.logic.parser.exceptions.ParseException;
import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.course.Session;
import tfifteenfour.clipboard.model.tag.Tag;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static tfifteenfour.clipboard.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class NewEditCommandParser implements Parser<EditCommand> {

    public EditCommand parse(String args) throws ParseException {
        Index index;
        CommandTargetType addCommandType;
        try {
            addCommandType = CommandTargetType.fromString(ArgumentTokenizer.tokenizeString(args)[1]);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ParseException("Add type missing. \n"
                    + "Available option: add course, add group, add session, add student");
        }

        switch (addCommandType) {
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
//            Student student = parseStudentInfo(args);
//            index = parseIndex(args);
//            return new EditStudentCommand(student);
        default:
            throw new ParseException("Invalid argument for add command");
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
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddSessionCommand.MESSAGE_USAGE));
        }

        Session session = ParserUtil.parseSession(tokens[3]);
        return session;
    }

//    private Student parseStudentInfo(String args) throws ParseException {
//        requireNonNull(args);
//        ArgumentMultimap argMultimap =
//                ArgumentTokenizer.tokenizePrefixes(args, PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_STUDENTID,
//                        PREFIX_COURSE, PREFIX_TAG);
//
//        Index index;
//
//        try {
//            index = ParserUtil.parseIndex(argMultimap.getPreamble());
//        } catch (ParseException pe) {
//            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand.MESSAGE_USAGE), pe);
//        }
//
//        tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand.EditStudentDescriptor editStudentDescriptor = new tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand.EditStudentDescriptor();
//        if (argMultimap.getValue(PREFIX_NAME).isPresent()) {
//            editStudentDescriptor.setName(ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get()));
//        }
//        if (argMultimap.getValue(PREFIX_PHONE).isPresent()) {
//            editStudentDescriptor.setPhone(ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get()));
//        }
//        if (argMultimap.getValue(PREFIX_EMAIL).isPresent()) {
//            editStudentDescriptor.setEmail(ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get()));
//        }
//        if (argMultimap.getValue(PREFIX_STUDENTID).isPresent()) {
//            editStudentDescriptor.setStudentId(ParserUtil.parseStudentId(argMultimap.getValue(PREFIX_STUDENTID).get()));
//        }
//        if (argMultimap.getValue(PREFIX_COURSE).isPresent()) {
//            parseModulesForEdit(argMultimap.getAllValues(PREFIX_COURSE)).ifPresent(editStudentDescriptor::setModules);
//        }
//        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editStudentDescriptor::setTags);
//
//        if (!editStudentDescriptor.isAnyFieldEdited()) {
//            throw new ParseException(tfifteenfour.clipboard.logic.commands.studentcommands.EditCommand.MESSAGE_NOT_EDITED);
//        }
//
//        return new EditStudentCommand(index, editStudentDescriptor);
//    }

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
}
