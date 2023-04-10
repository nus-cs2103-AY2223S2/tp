package seedu.address.storage;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tag.Tag;
import seedu.address.model.tutee.Tutee;
import seedu.address.model.tutee.TuteeBuilder;
import seedu.address.model.tutee.fields.Address;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Email;
import seedu.address.model.tutee.fields.EndTime;
import seedu.address.model.tutee.fields.Lesson;
import seedu.address.model.tutee.fields.Name;
import seedu.address.model.tutee.fields.Phone;
import seedu.address.model.tutee.fields.Remark;
import seedu.address.model.tutee.fields.Schedule;
import seedu.address.model.tutee.fields.StartTime;
import seedu.address.model.tutee.fields.Subject;

/**
 * Jackson-friendly version of {@link Tutee}.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutee's %s field is missing!";

    private final String address;
    private final String email;
    private final String endTime;
    private final String name;
    private final String phone;
    private final String remark;
    private final String schedule;
    private final String startTime;
    private final String subject;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<LocalDate> attendanceDates = new ArrayList<>();
    private final List<String> lessons = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given tutee details.
     */
    @JsonCreator
    public JsonAdaptedPerson(
        @JsonProperty("name") String name,
        @JsonProperty("phone") String phone,
        @JsonProperty("email") String email,
        @JsonProperty("address") String address,
        @JsonProperty("remark") String remark,
        @JsonProperty("subject") String subject,
        @JsonProperty("schedule") String schedule,
        @JsonProperty("startTime") String startTime,
        @JsonProperty("endTime") String endTime,
        @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
        @JsonProperty("attendances") List<LocalDate> attendances,
        @JsonProperty("lessons") List<String> lessons
    ) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.remark = remark;
        this.subject = subject;
        this.schedule = schedule;
        this.startTime = startTime;
        this.endTime = endTime;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
        if (attendances != null) {
            this.attendanceDates.addAll(attendances);
        }
        if (lessons != null) {
            this.lessons.addAll(lessons);
        }
    }

    /**
     * Converts a given {@code Tutee} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Tutee source) {
        name = source.getName().toString();
        phone = source.getPhone().toString();
        email = source.getEmail().toString();
        address = source.getAddress().toString();
        remark = source.getRemark().toString();
        subject = source.getSubject().toString();
        schedule = source.getSchedule().toString();
        startTime = source.getStartTime().toString();
        endTime = source.getEndTime().toString();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attendanceDates.forEach(attendanceDates::add);
        lessons.addAll(source.getLessons().list());
    }

    /**
     * Call this method to parse the field from its string value. If the field has an
     * invalid value determined by its `isValid` * check, then a
     * {@link IllegalValueException} will be thrown.
     *
     * A {@link RuntimeException} may be thrown if the provided class does not have the requisite
     * validation method, constraint message and constructor. If this is the case, you should modify your class
     * appropriately. This exception should never be thrown when in production.
     *
     * @param <T> Type of the field. Its class must have a public string constant named MESSAGE_CONSTRAINTS and
     *     a public static method called isValid + the name of the class, i.e. `isValidName(String arg)`
     *     and takes in 1 string parameter, returning a boolean. It must also have a constructor that takes a
     *     single string as a parameter.
     * @param value String value to parse
     * @param clazz Class of the field
     *     false is returned
     * @return Parsed field value
     * @throws IllegalValueException
     */
    private static <T> T validateField(String value, Class<T> clazz) throws IllegalValueException {
        String simpleName = clazz.getSimpleName();
        try {
            if (value == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, simpleName));
            }

            String constraintsMessage = (String) clazz.getField("MESSAGE_CONSTRAINTS").get(null);
            boolean isValid = (boolean) clazz.getMethod("isValid" + simpleName, String.class).invoke(null, value);
            if (!isValid) {
                throw new IllegalValueException(constraintsMessage);
            }

            try {
                return clazz.getConstructor(String.class).newInstance(value);
            } catch (NoSuchMethodException e) {
                throw new RuntimeException(String.format(
                    "Could not find a constructor of the signature %s(String value)",
                    simpleName
                ));
            }
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException(String.format(
                "Class %s does not contain a field called MESSAGE_CONSTRAINTS",
                simpleName
            ));
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(String.format(
                "Class %s does not contain a method called isValid%s",
                simpleName,
                simpleName
            ));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(String.format(
                "Could not access the field MESSAGE_CONSTRAINTS or "
                    + " the method isValid%s on class %s",
                simpleName,
                simpleName
            ));
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(String.format(
                "MESSAGE_CONSTRAINTS and isValid%s must both be static",
                simpleName
            ));
        } catch (final InstantiationException e) {
            throw new RuntimeException(String.format(
                "Could not find a constructor of the signature %s(String value)",
                simpleName
            ));
        } catch (final ClassCastException e) {
            throw new RuntimeException(String.format(
                "Class %s: MESSAGE_CONSTRAINTS must be of type String, and isValid%s must return a boolean",
                simpleName
            ));
        }
    }

    /**
     * Converts this Jackson-friendly adapted tutee object into the model's {@code Tutee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tutee.
     */
    public Tutee toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            personTags.add(tag.toModelType());
        }
        final Set<Tag> modelTags = new HashSet<>(personTags);

        TuteeBuilder builder = new TuteeBuilder();
        builder.withName(validateField(name, Name.class))
            .withPhone(validateField(phone, Phone.class))
            .withEmail(validateField(email, Email.class))
            .withAddress(validateField(address, Address.class))
            .withAttendance(new Attendance(new HashSet<>(attendanceDates)))
            .withRemark(validateField(remark, Remark.class))
            .withSubject(validateField(subject, Subject.class))
            .withSchedule(validateField(schedule, Schedule.class))
            .withStartTime(validateField(startTime, StartTime.class))
            .withEndTime(validateField(endTime, EndTime.class))
            .withTags(modelTags)
            .withLessons(new Lesson(new HashSet<>(lessons)));

        return builder.build();
    }
}
