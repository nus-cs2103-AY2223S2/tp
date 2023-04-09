package seedu.address.storage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.LeaveCounter;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Payroll;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.PicturePath;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Employee}.
 */
class JsonAdaptedEmployee {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Employee's %s field is missing!";

    private final String name;
    private final String employeeId;
    private final String phone;
    private final String email;
    private final String address;
    private final String department;
    private final JsonAdaptedPayroll payroll;
    private final JsonAdaptedLeaveCounter leaveCounter;
    private final String dateOfBirth;
    private final String dateOfJoining;
    private final String picturePath;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    @JsonCreator
    public JsonAdaptedEmployee(@JsonProperty("name") String name, @JsonProperty("employeeId") String employeeId,
                               @JsonProperty("phone") String phone, @JsonProperty("email") String email,
                               @JsonProperty("address") String address, @JsonProperty("department") String department,
                               @JsonProperty("payroll") JsonAdaptedPayroll payroll,
                               @JsonProperty("leaveCounter") JsonAdaptedLeaveCounter leaveCounter,
                               @JsonProperty("dateOfBirth") String dateOfBirth,
                               @JsonProperty("dateOfJoining") String dateOfJoining,
                               @JsonProperty("picturePath") String picturePath,
                               @JsonProperty("tagged") List<JsonAdaptedTag> tagged) {
        this.name = name;
        this.employeeId = employeeId;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.department = department;
        this.payroll = payroll;
        this.leaveCounter = leaveCounter;
        this.dateOfBirth = dateOfBirth;
        this.dateOfJoining = dateOfJoining;
        this.picturePath = picturePath;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedEmployee(Employee source) {
        name = source.getName().fullName;
        employeeId = source.getEmployeeId().value;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        department = source.getDepartment().value;
        payroll = new JsonAdaptedPayroll(source.getPayroll());
        leaveCounter = new JsonAdaptedLeaveCounter(source.getLeaveCounter());
        dateOfBirth = source.getDateOfBirth();
        dateOfJoining = source.getDateOfJoining();
        picturePath = source.getPicturePath().value;
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted employee object into the model's {@code Employee} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted employee.
     */
    public Employee toModelType() throws IllegalValueException {
        final List<Tag> employeeTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            employeeTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (employeeId == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, EmployeeId.class.getSimpleName()));
        }
        if (!EmployeeId.isValidEmployeeId(employeeId)) {
            throw new IllegalValueException(EmployeeId.MESSAGE_CONSTRAINTS);
        }
        final EmployeeId modelEmployeeId = new EmployeeId(employeeId);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        if (department == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Department.class.getSimpleName()));
        }
        if (!Department.isValidDepartment(department)) {
            throw new IllegalValueException(Department.MESSAGE_CONSTRAINTS);
        }
        final Department modelDepartment = new Department(department);

        if (payroll == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Payroll.class.getSimpleName()));
        }
        if (!Payroll.isValidPayroll(payroll.getPayroll())) {
            throw new IllegalValueException(Payroll.MESSAGE_CONSTRAINTS);
        }
        final Payroll modelPayroll = new Payroll(payroll.getPayroll());

        if (leaveCounter == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    LeaveCounter.class.getSimpleName()));
        }
        if (!LeaveCounter.isValidLeaveCount(leaveCounter.getLeaveCount())) {
            throw new IllegalValueException(LeaveCounter.MESSAGE_CONSTRAINTS);
        }
        final LeaveCounter modelLeaveCounter = new LeaveCounter(leaveCounter.toModelType().getLeaveCount());

        Optional<LocalDate> modelDateOfBirthTemp = Optional.empty();
        if (dateOfBirth != "") {
            modelDateOfBirthTemp = Optional.ofNullable(LocalDate.parse(dateOfBirth));
        }
        final Optional<LocalDate> modelDateOfBirth = modelDateOfBirthTemp;

        Optional<LocalDate> modelDateOfJoiningTemp = Optional.empty();
        if (dateOfJoining != "") {
            modelDateOfJoiningTemp = Optional.ofNullable(LocalDate.parse(dateOfJoining));
        }
        final Optional<LocalDate> modelDateOfJoining = modelDateOfJoiningTemp;
        if (picturePath == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    PicturePath.class.getSimpleName()));
        }
        if (!PicturePath.isValidPicturePath(picturePath)) {
            throw new IllegalValueException(PicturePath.MESSAGE_CONSTRAINTS);
        }
        final PicturePath modelPicturePath = new PicturePath(picturePath);

        final Set<Tag> modelTags = new HashSet<>(employeeTags);
        return new Employee(modelName, modelEmployeeId, modelPhone, modelEmail, modelAddress, modelDepartment,
                modelPayroll, modelLeaveCounter, modelDateOfBirth, modelDateOfJoining, modelPicturePath, modelTags);
    }

}
