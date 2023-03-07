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
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.student.*;
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

    public static IndexNumber parseIndexNumber(String indexNumber) throws ParseException {
        String trimmedIndexNumber = indexNumber.trim();
        if (!IndexNumber.isValidIndexNumber(trimmedIndexNumber)) {
            throw new ParseException(IndexNumber.MESSAGE_CONSTRAINTS);
        }
        return new IndexNumber(trimmedIndexNumber);
    }

    public static Sex parseSex(String sex) throws ParseException {
        String trimmedSex = sex.trim();
        if (!Sex.isValidSex(trimmedSex)) {
            throw new ParseException(Sex.MESSAGE_CONSTRAINTS);
        }
        return new Sex(trimmedSex);
    }

    public static ParentName parseParentName(String parentName) throws ParseException {
        requireNonNull(parentName);
        String trimmedParentName = parentName.trim();
        if (!ParentName.isValidParentName(trimmedParentName)) {
            throw new ParseException(ParentName.MESSAGE_CONSTRAINTS);
        }
        return new ParentName(trimmedParentName);
    }

    public static Age parseAge(String studentAge) throws ParseException {
        requireNonNull(studentAge);
        String trimmedStudentAge = studentAge.trim();
        if (!Age.isValidAge(trimmedStudentAge)) {
            throw new ParseException(Age.MESSAGE_CONSTRAINTS);
        }
        return new Age(trimmedStudentAge);
    }

    public static Image parseImage(String studentImage) throws ParseException {
        requireNonNull(studentImage);
        String trimmedStudentImage = studentImage.trim();
        if (!Image.isValidImage(trimmedStudentImage)) {
            throw new ParseException(Image.MESSAGE_CONSTRAINTS);
        }
        return new Image(trimmedStudentImage);
    }

    public static CCA parseCCA(String cca) throws ParseException {
        requireNonNull(cca);
        String trimmedCca = cca.trim();
        if (!CCA.isValidCCA(trimmedCca)) {
            throw new ParseException(CCA.MESSAGE_CONSTRAINTS);
        }
        return new CCA(trimmedCca);
    }

    public static StudentClass parseStudentClass(String sc) throws ParseException {
        requireNonNull(sc);
        String trimmedSc = sc.trim();
        if (!StudentClass.isValidStudentClass(trimmedSc)) {
            throw new ParseException(StudentClass.MESSAGE_CONSTRAINTS);
        }
        return new StudentClass(trimmedSc);
    }
    public static Test parseTest(String test) throws ParseException {
        requireNonNull(test);
        String trimmedTest = test.trim();
        if (!Test.isValidTest(trimmedTest)) {
            throw new ParseException(Test.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Test(trimmedTest, LocalDate.now(), 20, 100, 50);
    }
    public static Attendance parseAttendance(String attendance) throws ParseException {
        requireNonNull(attendance);
        String trimmedAttendance = attendance.trim();
        if (!Attendance.isValidAttendance(trimmedAttendance)) {
            throw new ParseException(Attendance.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Attendance(attendance);
    }
    public static Homework parseHomework(String homework) throws ParseException {
        requireNonNull(homework);
        String trimmedHomework = homework.trim();
        if (!Homework.isValidHomework(trimmedHomework)) {
            throw new ParseException(Homework.MESSAGE_CONSTRAINTS);
        }
        //Please edit this
        return new Homework(homework, LocalDate.now(), 20,100,true);
    }
}
