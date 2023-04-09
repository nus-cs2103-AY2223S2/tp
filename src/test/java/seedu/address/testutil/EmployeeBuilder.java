package seedu.address.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.address.model.employee.Address;
import seedu.address.model.employee.Department;
import seedu.address.model.employee.Email;
import seedu.address.model.employee.Employee;
import seedu.address.model.employee.EmployeeId;
import seedu.address.model.employee.Name;
import seedu.address.model.employee.Phone;
import seedu.address.model.employee.PicturePath;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Employee objects.
 */
public class EmployeeBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_ADDRESS = "123, Jurong West Ave 6, #08-111";
    public static final String DEFAULT_DEPARTMENT = "Marketing";
    public static final String DEFAULT_PICTURE_PATH = "src/main/resources/employeepictures/default_employee.png";

    private Name name;
    private EmployeeId employeeId;
    private Phone phone;
    private Email email;
    private Address address;
    private Department department;
    private PicturePath picturePath;
    private Set<Tag> tags;

    /**
     * Creates a {@code EmployeeBuilder} with the default details.
     */
    public EmployeeBuilder() {
        name = new Name(DEFAULT_NAME);
        employeeId = new EmployeeId();
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        address = new Address(DEFAULT_ADDRESS);
        department = new Department(DEFAULT_DEPARTMENT);
        picturePath = new PicturePath(DEFAULT_PICTURE_PATH);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EmployeeBuilder with the data of {@code personToCopy}.
     */
    public EmployeeBuilder(Employee employeeToCopy) {
        name = employeeToCopy.getName();
        employeeId = employeeToCopy.getEmployeeId();
        phone = employeeToCopy.getPhone();
        email = employeeToCopy.getEmail();
        address = employeeToCopy.getAddress();
        department = employeeToCopy.getDepartment();
        picturePath = employeeToCopy.getPicturePath();
        tags = new HashSet<>(employeeToCopy.getTags());
    }

    public static void setEmployeeId(String id) {
        EmployeeId.setCount(Integer.parseInt(id));
    }


    /**
     * Sets the {@code Name} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Sets the {@code EmployeeId} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmployeeId(String employeeId) {
        this.employeeId = new EmployeeId(employeeId);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Employee} that we are building.
     */
    public EmployeeBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code Address} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withAddress(String address) {
        this.address = new Address(address);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code Department} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withDepartment(String department) {
        this.department = new Department(department);
        return this;
    }

    /**
     * Sets the {@code PicturePath} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withPicturePath(String picturePath) {
        this.picturePath = new PicturePath(picturePath);
        return this;
    }

    public Employee build() {
        return new Employee(name, employeeId, phone, email, address, department, picturePath, tags);
    }

    /**
     * Sets the {@code Email} of the {@code Employee} that we are building.
     */
    public EmployeeBuilder withEmptyEmail() {
        this.email = new Email();
        return this;
    }


    /**
     * Sets the {@code Address} of the {@code Person} that we are building.
     */
    public EmployeeBuilder withEmptyAddress() {
        this.address = new Address();
        return this;
    }


    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public EmployeeBuilder withEmptyPhone() {
        this.phone = new Phone();
        return this;
    }
}
