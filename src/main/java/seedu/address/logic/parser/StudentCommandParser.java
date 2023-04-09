package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_BLANK_FIND;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTENDANCE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CCA;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COMMENTCOMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EDIT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAILSTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FIND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADEDELETE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_HOMEWORKDONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_IMAGESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_INDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_LIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWCLASS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWINDEXNUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWPARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NEWPHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PARENTNAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONEPARENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONESTUDENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RELATIONSHIP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SCORE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SEX;
import static seedu.address.logic.parser.CliSyntax.PREFIX_STUDENTAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TEST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WEIGHTAGE;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import seedu.address.logic.commands.student.StudentAddCommand;
import seedu.address.logic.commands.student.StudentAttendanceCommand;
import seedu.address.logic.commands.student.StudentCommand;
import seedu.address.logic.commands.student.StudentCommentCommand;
import seedu.address.logic.commands.student.StudentDeleteCommand;
import seedu.address.logic.commands.student.StudentEditCommand;
import seedu.address.logic.commands.student.StudentGradeCommand;
import seedu.address.logic.commands.student.StudentGradeDeleteCommand;
import seedu.address.logic.commands.student.StudentListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Class;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.ClassContainsKeywordsPredicate;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.Student;
import seedu.address.model.person.student.Test;
import seedu.address.model.tag.Tag;

/**
 * StudentCommandParser that parses commands starting with "student"
 */
public class StudentCommandParser implements Parser<StudentCommand> {

    public static final String HELP_MESSAGE = "Student command has to include a class and action.\n"
            + StudentCommand.MESSAGE_USAGE;
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<class>\\S+)(?<arguments>.*)");

    /**
     * Parse the command into their respective prefixes
     * @param args the command input by user
     * @return A StudentCommand
     * @throws ParseException
     */
    public StudentCommand parse(String args) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(args.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
        final String studentClass = matcher.group("class");
        final String arguments = matcher.group("arguments");
        ArgumentMultimap argMultimapAdd = getArgAdd(arguments);
        ArgumentMultimap argMultimapDelete = getArgDelete(arguments);
        ArgumentMultimap argMultimap = getArg(arguments);
        ArgumentMultimap argMultimapGrade = getArgGrade(arguments);
        ArgumentMultimap argMultimapGradeDelete = getArgGradeDelete(arguments);
        ArgumentMultimap argMultimapEdit = getArgEdit(arguments);
        ArgumentMultimap argumentMultimapAtt = getArgAtt(arguments);
        return getCommand(argMultimapAdd, argMultimapDelete, argMultimap, argMultimapGrade,
                argMultimapGradeDelete, argMultimapEdit, argumentMultimapAtt, studentClass, arguments);
    }

    /**
     * Get the arguments for the student commands
     * @param argMultimapAdd
     * @param argMultimapDelete
     * @param argMultimap
     * @param argMultimapGrade
     * @param argMultimapGradeDelete
     * @param argMultimapEdit
     * @param argumentMultimapAtt
     * @param studentClass
     * @param arguments
     * @return StudentCommand
     * @throws ParseException
     */
    private StudentCommand getCommand(ArgumentMultimap argMultimapAdd, ArgumentMultimap argMultimapDelete,
            ArgumentMultimap argMultimap, ArgumentMultimap argMultimapGrade,
            ArgumentMultimap argMultimapGradeDelete, ArgumentMultimap argMultimapEdit,
            ArgumentMultimap argumentMultimapAtt, String studentClass, String arguments) throws ParseException {
        if (argMultimapAdd.getValue(PREFIX_ADD).isPresent()) {
            return parseStudentAddCommand(studentClass, argMultimapAdd);
        } else if (argMultimapDelete.getValue(PREFIX_DELETE).isPresent()) {
            return parseStudentDeleteCommand(studentClass, argMultimapDelete);
        } else if (argMultimap.getValue(PREFIX_COMMENTCOMMAND).isPresent()) {
            return commentCommand(studentClass, argMultimap);
        } else if (argMultimapGrade.getValue(PREFIX_GRADE).isPresent()
                && !argMultimapGradeDelete.getValue(PREFIX_GRADEDELETE).isPresent()) {
            return gradeCommand(studentClass, argMultimapGrade);
        } else if (argMultimapGradeDelete.getValue(PREFIX_GRADEDELETE).isPresent()) {
            return gradeDeleteCommand(studentClass, argMultimapGradeDelete);
        } else if (argMultimapEdit.getValue(PREFIX_EDIT).isPresent()) {
            return parseStudentEditCommand(studentClass, argMultimapEdit);
        } else if (argumentMultimapAtt.getValue(PREFIX_ADDATTENDANCE).isPresent()) {
            return parseStudentAttCommand(studentClass, argumentMultimapAtt);
        } else if (argMultimap.getValue(PREFIX_FIND).isPresent()) {
            if (arguments.trim().equals(PREFIX_FIND.toString())) {
                throw new ParseException(MESSAGE_BLANK_FIND);
            }
            return new StudentFindCommandParser().parse(studentClass + arguments);
        } else if (argMultimap.getValue(PREFIX_LIST).isPresent()) {
            return new StudentListCommand(Class.of(studentClass),
                    new ClassContainsKeywordsPredicate(Class.of(studentClass)));
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HELP_MESSAGE));
        }
    }
    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgAtt(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_ADDATTENDANCE, PREFIX_INDEXNUMBER, PREFIX_ATTENDANCE);
    }
    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgEdit(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_EDIT, PREFIX_NAME, PREFIX_INDEXNUMBER, PREFIX_SEX,
                PREFIX_NEWPARENTNAME, PREFIX_NEWPHONEPARENT, PREFIX_RELATIONSHIP, PREFIX_STUDENTAGE,
                PREFIX_IMAGESTUDENT, PREFIX_EMAILSTUDENT, PREFIX_PHONESTUDENT, PREFIX_CCA, PREFIX_ADDRESS,
                PREFIX_NEWCLASS, PREFIX_NEWINDEXNUMBER, PREFIX_NEWNAME, PREFIX_COMMENT);

    }
    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgGradeDelete(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_GRADEDELETE, PREFIX_INDEXNUMBER, PREFIX_TEST,
                PREFIX_HOMEWORK);
    }
    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgGrade(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_GRADE, PREFIX_INDEXNUMBER, PREFIX_TEST,
                PREFIX_HOMEWORK, PREFIX_SCORE, PREFIX_DEADLINE, PREFIX_WEIGHTAGE, PREFIX_HOMEWORKDONE);
    }
    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArg(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_LIST, PREFIX_COMMENTCOMMAND, PREFIX_FIND, PREFIX_COMMENT,
                PREFIX_ADD, PREFIX_INDEXNUMBER, PREFIX_SEX, PREFIX_PARENTNAME, PREFIX_PHONEPARENT,
                PREFIX_RELATIONSHIP, PREFIX_STUDENTAGE, PREFIX_IMAGESTUDENT, PREFIX_EMAILSTUDENT,
                PREFIX_PHONESTUDENT, PREFIX_CCA, PREFIX_TEST, PREFIX_ATTENDANCE, PREFIX_HOMEWORK,
                PREFIX_ADDRESS);
    }

    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgDelete(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_DELETE, PREFIX_INDEXNUMBER);
    }

    /**
     * Parse the command into their respective prefixes
     * @param arguments
     * @return ArgumentMultimap
     */
    private ArgumentMultimap getArgAdd(String arguments) {
        return ArgumentTokenizer.tokenize(arguments, PREFIX_ADD, PREFIX_NAME, PREFIX_INDEXNUMBER, PREFIX_SEX,
                PREFIX_PARENTNAME, PREFIX_PHONEPARENT, PREFIX_RELATIONSHIP, PREFIX_STUDENTAGE,
                PREFIX_IMAGESTUDENT, PREFIX_EMAILSTUDENT, PREFIX_PHONESTUDENT, PREFIX_CCA, PREFIX_TEST,
                PREFIX_ATTENDANCE, PREFIX_HOMEWORK, PREFIX_SCORE, PREFIX_DEADLINE, PREFIX_WEIGHTAGE,
                PREFIX_ADDRESS);
    }

    private StudentAttendanceCommand parseStudentAttCommand(String studentClass,
                                                            ArgumentMultimap argumentMultimap) throws ParseException {
        if (!arePrefixesPresent(argumentMultimap, PREFIX_INDEXNUMBER, PREFIX_ATTENDANCE)
                || !argumentMultimap.getPreamble().isEmpty()
                || studentClass.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentAttendanceCommand.MESSAGE_USAGE));
        }
        Class sc = ParserUtil.parseStudentClass(studentClass);
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argumentMultimap.getValue(PREFIX_INDEXNUMBER).get());
        Attendance attendance = ParserUtil.parseAttendance(argumentMultimap.getValue(PREFIX_ATTENDANCE).get());
        return new StudentAttendanceCommand(sc, indexNumber, attendance);
    }

    /**
     * Function to parse the "student class add" command
     * @param studentClass class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentAddCommand
     * @throws ParseException
     */
    private StudentAddCommand parseStudentAddCommand(String studentClass, ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_INDEXNUMBER, PREFIX_SEX, PREFIX_PARENTNAME,
                PREFIX_PHONEPARENT, PREFIX_RELATIONSHIP)
                || !argMultimap.getPreamble().isEmpty()
                || studentClass.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentAddCommand.MESSAGE_USAGE));
        }
        Class sc = ParserUtil.parseStudentClass(studentClass);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
        Sex sex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Name parentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_PARENTNAME).get());
        Phone parentNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONEPARENT).get());
        Relationship rls = ParserUtil.parseRelationship(argMultimap.getValue(PREFIX_RELATIONSHIP).get());
        Age age = ParserUtil.parseAge((argMultimap.getValue(PREFIX_STUDENTAGE).get()));
        Image image = ParserUtil.parseImage(argMultimap.getValue(PREFIX_IMAGESTUDENT).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAILSTUDENT).get());
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONESTUDENT).get());
        Cca cca = ParserUtil.parseCca(argMultimap.getValue(PREFIX_CCA).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        Attendance attendance = ParserUtil.parseAttendance(argMultimap.getValue(PREFIX_ATTENDANCE).get());
        String score = argMultimap.getValue(PREFIX_SCORE).get();
        String deadline = argMultimap.getValue(PREFIX_DEADLINE).get();
        String weightage = argMultimap.getValue(PREFIX_WEIGHTAGE).get();
        String homeworkDone = argMultimap.getValue(PREFIX_HOMEWORKDONE).get();
        Homework homework = ParserUtil.parseHomework("Insert student homework here!", score, deadline,
                weightage, homeworkDone);
        Test test = ParserUtil.parseTest("Insert student test here!", score, deadline, weightage);
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));
        Student student = new Student(name, sc, indexNumber, sex, parentName, parentNumber, rls, age, image, email,
                phone, cca, address, getAttendanceSet(attendance), getHomeworkSet(homework), getTestSet(test),
                tagList, comment);
        return new StudentAddCommand(student);
    }

    /**
     * Helper method to get set containing Attendance.
     *
     * @param attendance Attendance to be added into the Attendance set.
     * @return A set of attendance.
     */
    public Set<Attendance> getAttendanceSet(Attendance attendance) {
        Set<Attendance> newAttendanceSet = new HashSet<>();
        newAttendanceSet.add(attendance);
        return newAttendanceSet;
    }

    /**
     * Helper method to get set containing Test.
     *
     * @param test Test to be added into the Test set.
     * @return A set of tests.
     */
    public Set<Test> getTestSet(Test test) {
        Set<Test> newTestSet = new HashSet<>();
        newTestSet.add(test);
        return newTestSet;
    }

    /**
     * Helper method to get set containing homework.
     *
     * @param homework Homework to be added into the Homework set.
     * @return A set of homework.
     */
    public Set<Homework> getHomeworkSet(Homework homework) {
        Set<Homework> newHomeworkSet = new HashSet<>();
        newHomeworkSet.add(homework);
        return newHomeworkSet;
    }

    /**
     * Function to parse the "student class comment" command
     * @param studentClass class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentCommentCommand
     * @throws ParseException
     */
    private StudentCommentCommand commentCommand(String studentClass, ArgumentMultimap argMultimap)
            throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_COMMENT)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentCommentCommand.MESSAGE_USAGE));
        }
        Class sc = ParserUtil.parseStudentClass(studentClass);
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
        Comment comment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get());
        return new StudentCommentCommand(sc, indexNumber, comment);
    }

    /**
     * Function to parse the "student class delete" command
     * @param studentClass class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentDeleteCommand
     * @throws ParseException
     */
    public StudentDeleteCommand parseStudentDeleteCommand(String studentClass, ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentDeleteCommand.MESSAGE_USAGE));
        }
        Class sc = ParserUtil.parseStudentClass(studentClass);
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
        return new StudentDeleteCommand(indexNumber, sc);
    }

    /**
     * Function to parse the "student class grade" command
     * @param studentClass class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentGradeCommand
     * @throws ParseException
     */
    public StudentGradeCommand gradeCommand(String studentClass, ArgumentMultimap argMultimap) throws ParseException {
        if ((arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_TEST)
                || arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_HOMEWORK))
                && argMultimap.getPreamble().isEmpty()) {
            Class sc = ParserUtil.parseStudentClass(studentClass);
            IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
            String score = argMultimap.getValue(PREFIX_SCORE).get();
            String deadline = argMultimap.getValue(PREFIX_DEADLINE).get();
            String weightage = argMultimap.getValue(PREFIX_WEIGHTAGE).get();
            String homeworkDone = argMultimap.getValue(PREFIX_HOMEWORKDONE).get();
            Test test;
            Homework homework;
            if (arePrefixesPresent(argMultimap, PREFIX_TEST)) {
                test = ParserUtil.parseTest(argMultimap.getValue(PREFIX_TEST).get(), score, deadline, weightage);
                return new StudentGradeCommand(sc, indexNumber, test);
            } else {
                homework = ParserUtil.parseHomework(argMultimap.getValue(PREFIX_HOMEWORK).get(), score, deadline,
                        weightage, homeworkDone);
                return new StudentGradeCommand(sc, indexNumber, homework);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentGradeCommand.MESSAGE_USAGE));
        }
    }
    /**
     * Function to parse the "student class grade delete" command
     * @param studentClass class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentGradeDeleteCommand
     * @throws ParseException
     */
    public StudentGradeDeleteCommand gradeDeleteCommand(String studentClass, ArgumentMultimap argMultimap)
            throws ParseException {
        if ((arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_TEST)
                || arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER, PREFIX_HOMEWORK))
                && argMultimap.getPreamble().isEmpty()) {
            Class sc = ParserUtil.parseStudentClass(studentClass);
            IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
            Test test;
            Homework homework;
            if (arePrefixesPresent(argMultimap, PREFIX_TEST)) {
                test = ParserUtil.parseTest(argMultimap.getValue(PREFIX_TEST).get(),
                        "Insert student score here!", argMultimap.getValue(PREFIX_DEADLINE).get(),
                        "Insert student weightage here!");
                return new StudentGradeDeleteCommand(sc, indexNumber, test);
            } else {
                homework = ParserUtil.parseHomework(argMultimap.getValue(PREFIX_HOMEWORK).get(),
                        "Insert student score here!", argMultimap.getValue(PREFIX_DEADLINE).get(),
                        "Insert student weightage here!", "Insert student homework done here!");
                return new StudentGradeDeleteCommand(sc, indexNumber, homework);
            }
        } else {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    StudentGradeDeleteCommand.MESSAGE_USAGE));
        }
    }

    /**
     * Function to parse the "student class edit" command
     * @param sc class of student
     * @param argMultimap mapper for each prefix
     * @return A StudentEditCommand
     * @throws ParseException
     */
    private StudentEditCommand parseStudentEditCommand(String sc, ArgumentMultimap argMultimap) throws ParseException {
        if (!arePrefixesPresent(argMultimap, PREFIX_INDEXNUMBER)
                || !argMultimap.getPreamble().isEmpty()
                || sc.length() == 0) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, StudentEditCommand.MESSAGE_USAGE));
        }

        Class studentClass = ParserUtil.parseStudentClass(sc);
        Name newName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NEWNAME).get());
        Phone newStudentPhoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONESTUDENT).get());
        Email newEmail = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAILSTUDENT).get());
        Address newAddress = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        IndexNumber indexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_INDEXNUMBER).get());
        IndexNumber newIndexNumber = ParserUtil.parseIndexNumber(argMultimap.getValue(PREFIX_NEWINDEXNUMBER).get());
        Sex newSex = ParserUtil.parseSex(argMultimap.getValue(PREFIX_SEX).get());
        Age newAge = ParserUtil.parseAge(argMultimap.getValue(PREFIX_STUDENTAGE).get());
        Image newImage = ParserUtil.parseImage(argMultimap.getValue(PREFIX_IMAGESTUDENT).get());
        Cca newCca = ParserUtil.parseCca(argMultimap.getValue(PREFIX_CCA).get());
        Class newStudentClass = ParserUtil.parseStudentClass(argMultimap.getValue(PREFIX_NEWCLASS).get());
        Comment newComment = ParserUtil.parseComment(argMultimap.getValue(PREFIX_COMMENT).get());
        Name newParentName = ParserUtil.parseName(argMultimap.getValue(PREFIX_NEWPARENTNAME).get());
        Phone newParentPhoneNumber = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_NEWPHONEPARENT).get());
        Relationship newRelationship = ParserUtil.parseRelationship(argMultimap.getValue(PREFIX_RELATIONSHIP).get());
        return new StudentEditCommand(newName, indexNumber, newIndexNumber, studentClass, newStudentClass, newSex,
                newParentPhoneNumber, newParentName, newRelationship, newAge, newImage, newCca,
                newComment, newStudentPhoneNumber, newEmail, newAddress);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
