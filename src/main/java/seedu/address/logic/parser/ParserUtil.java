package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Age;
import seedu.address.model.person.Comment;
import seedu.address.model.person.Email;
import seedu.address.model.person.Image;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Sex;
import seedu.address.model.person.parent.Relationship;
import seedu.address.model.person.student.Attendance;
import seedu.address.model.person.student.Cca;
import seedu.address.model.person.student.Homework;
import seedu.address.model.person.student.IndexNumber;
import seedu.address.model.person.student.ParentName;
import seedu.address.model.person.student.StudentClass;
import seedu.address.model.person.student.Test;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String address} into an {@code Address}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code address} is invalid.
     */
    public static Address parseAddress(String address) throws ParseException {
        requireNonNull(address);
        String trimmedAddress = address.trim();
        if (!Address.isValidAddress(trimmedAddress)) {
            throw new ParseException(Address.MESSAGE_CONSTRAINTS);
        }
        return new Address(trimmedAddress);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses indexNumber into an IndexNumber
     * @param indexNumber
     * @return An IndexNumber
     * @throws ParseException
     */
    public static IndexNumber parseIndexNumber(String indexNumber) throws ParseException {
        String trimmedIndexNumber = indexNumber.trim();
        if (!IndexNumber.isValidIndexNumber(trimmedIndexNumber)) {
            throw new ParseException(IndexNumber.MESSAGE_CONSTRAINTS);
        }
        return new IndexNumber(trimmedIndexNumber);
    }

    /**
     * Parses sex into Sex
     * @param sex
     * @return A Sex
     * @throws ParseException
     */

    public static Sex parseSex(String sex) throws ParseException {
        String trimmedSex = sex.trim().toUpperCase();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(trimmedSex);
    }

    /**
     * Parses parentName to ParentName
     * @param parentName
     * @return a ParentName
     * @throws ParseException
     */
    public static ParentName parseParentName(String parentName) throws ParseException {
        requireNonNull(parentName);
        String trimmedParentName = parentName.trim();
        if (!ParentName.isValidParentName(trimmedParentName)) {
            throw new ParseException(ParentName.MESSAGE_CONSTRAINTS);
        }
        return new ParentName(trimmedParentName);
    }

    /**
     * Parses relationship of Parent object.
     *
     * @param relationship Relation of Parent object to Student (parent / next-of-kin [NOK]).
     * @return Relationship object stating the relationship of a Parent object to a Student object.
     * @throws ParseException
     */
    public static Relationship parseRelationship(String relationship) throws ParseException {
        requireNonNull(relationship);
        String trimmedRelationship = relationship.trim();
        if (!Relationship.isValidRelationship(trimmedRelationship)) {
            throw new ParseException(Relationship.MESSAGE_CONSTRAINTS);
        }
        return new Relationship(trimmedRelationship);
    }

    /**
     * Parses age to Age
     * @param studentAge
     * @return an Age
     * @throws ParseException
     */

    public static Age parseAge(String studentAge) throws ParseException {
        requireNonNull(studentAge);
        String trimmedStudentAge = studentAge.trim();
        if (!Age.isValidAge(trimmedStudentAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedStudentAge);
    }

    /**
     * Parses studentImage to Image
     * @param studentImage
     * @return a studentImage
     * @throws ParseException
     */

    public static Image parseImage(String studentImage) throws ParseException {
        requireNonNull(studentImage);
        String trimmedStudentImage = studentImage.trim();
        if (!Image.isValidImage(trimmedStudentImage)) {
            throw new ParseException(Image.MESSAGE_CONSTRAINTS);
        }
        return new Image(trimmedStudentImage);
    }

    /**
     * Parse cca to CCA
     * @param cca
     * @return a CCA
     * @throws ParseException
     */

    public static Cca parseCca(String cca) throws ParseException {
        requireNonNull(cca);
        String trimmedCca = cca.trim();
        if (!Cca.isValidCca(trimmedCca)) {
            throw new ParseException(Cca.MESSAGE_CONSTRAINTS);
        }
        return new Cca(trimmedCca);
    }

    /**
     * Parses sc to StudentClass
     * @param sc
     * @return a StudentClass
     * @throws ParseException
     */

    public static StudentClass parseStudentClass(String sc) throws ParseException {
        requireNonNull(sc);
        String trimmedSc = sc.trim();
        if (!StudentClass.isValidStudentClass(trimmedSc)) {
            throw new ParseException(StudentClass.MESSAGE_CONSTRAINTS);
        }
        return new StudentClass(trimmedSc);
    }

    /**
     * Parses test to Test
     * @param test
     * @return a Test
     * @throws ParseException
     */
    public static Test parseTest(String test) throws ParseException {
        requireNonNull(test);
        String trimmedTest = test.trim();
        if (!Test.isValidTest(trimmedTest)) {
            throw new ParseException(Test.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Test(trimmedTest, LocalDate.now(), 20, 100, 50);
    }

    /**
     * Parses attendance to Attendance
     * @param attendance
     * @return An Attendance
     * @throws ParseException
     */
    public static Attendance parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (!Attendance.isValidAttendance(trimmedAttendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Attendance(attendance);
    }

    /**
     * Parses homework to Homework
     * @param homework
     * @return A Homework
     * @throws ParseException
     */
    public static Homework parseHomework(String homework) throws ParseException {
        requireNonNull(homework);
        String trimmedHomework = homework.trim();
        if (!Homework.isValidHomework(trimmedHomework)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Homework(homework, LocalDate.now(), 20, 100, 0, true);
    }

    /**
     * Parses comment to Comment
     * @param comment
     * @return A Comment
     * @throws ParseException
     */
    public static Comment parseComment(String comment) throws ParseException {
        requireNonNull(comment);
        String trimmedComment = comment.trim();
        if (!Comment.isValidComment(trimmedComment)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Comment(trimmedComment);
    }
}
