package seedu.address.storage;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.management.RuntimeErrorException;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.tutee.*;
import seedu.address.model.tutee.fields.Address;
import seedu.address.model.tutee.fields.Attendance;
import seedu.address.model.tutee.fields.Email;
import seedu.address.model.tutee.fields.EndTime;
import seedu.address.model.tutee.fields.Name;
import seedu.address.model.tutee.fields.Phone;
import seedu.address.model.tutee.fields.Remark;
import seedu.address.model.tutee.fields.Schedule;
import seedu.address.model.tutee.fields.StartTime;
import seedu.address.model.tutee.fields.Subject;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Tutee}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Tutee's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String remark;
    private final String subject;
    private final String schedule;
    private final String startTime;
    private final String endTime;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();
    private final List<LocalDate> attendanceDates = new ArrayList<>();

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
        @JsonProperty("attendances") List<LocalDate> attendances
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
    }

    /**
     * Converts a given {@code Tutee} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Tutee source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        remark = source.getRemark().value;
        subject = source.getSubject().subject;
        schedule = source.getSchedule().schedule;
        startTime = source.getStartTime().startTime;
        endTime = source.getEndTime().endTime;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        attendanceDates.forEach(attendanceDates::add);
    }

    private static <T> T validateField(String value, Class<T> clazz) throws IllegalValueException {
        String simpleName = clazz.getSimpleName();
        try {
            if (value == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, simpleName));
            }

            String constraintsMessage = (String) clazz.getDeclaredField("MESSAGE_CONSTRAINTS").get(null);
            boolean isValid = (boolean) clazz.getDeclaredMethod("isValid" + simpleName).invoke(null, value);
            if (!isValid) {
                throw new IllegalValueException(constraintsMessage);
            }

            return clazz.getConstructor().newInstance(value);
        } catch (final NoSuchFieldException e) {
            throw new RuntimeException(String.format("Class %s does not contain a field called MESSAGE_CONSTRAINTS", simpleName));
        } catch (final NoSuchMethodException e) {
            throw new RuntimeException(String.format("Class %s does not contain a method called isValid%s", simpleName, simpleName));
        } catch (IllegalAccessException e) {
            throw new RuntimeException(String.format("Could not access the field MESSAGE_CONSTRAINTS or the method isValid%s on class %s",
                simpleName,
                simpleName
            ));
        } catch (final InvocationTargetException e) {
            throw new RuntimeException(String.format("MESSAGE_CONSTRAINTS and isValid%s must both be static", simpleName));
        } catch (final InstantiationException e) {
            throw new RuntimeException(String.format("Could not find a constructor of the signature %s(String value)"));
        } catch (final ClassCastException e) {
            throw new RuntimeException(String.format("MESSAGE_CONSTRAINTS must be of type String, and isValid%s must return a boolean"));
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
        
        final Name modelName = validateField(name, Name.class);
        final Phone modelPhone = validateField(phone, Phone.class);
        final Email modelEmail = validateField(email, Email.class);
        final Address modelAddress = validateField(address, Address.class);
        final Attendance modelAttendance = new Attendance(new HashSet<>(attendanceDates));
        final Remark modelRemark = validateField(remark, Remark.class);
        final Subject modelSubject = validateField(subject, Subject.class);
        final Schedule modelSchedule = validateField(schedule, Schedule.class);
        final StartTime modelStartTime = validateField(startTime, StartTime.class);
        final EndTime modelEndTime = validateField(endTime, EndTime.class);

        return new Tutee(modelName, modelPhone, modelEmail, modelAddress, modelAttendance, modelRemark, modelSubject, modelSchedule,
                modelStartTime, modelEndTime, modelTags);
    }

}
