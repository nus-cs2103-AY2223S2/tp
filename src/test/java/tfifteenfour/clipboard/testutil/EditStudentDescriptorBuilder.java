package tfifteenfour.clipboard.testutil;

import tfifteenfour.clipboard.logic.parser.EditCommandParser.EditStudentDescriptor;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;

/**
 * A utility class to help with building EditStudentDescriptor objects.
 */
public class EditStudentDescriptorBuilder {

    private EditStudentDescriptor descriptor;

    public EditStudentDescriptorBuilder() {
        descriptor = new EditStudentDescriptor();
    }

    public EditStudentDescriptorBuilder(EditStudentDescriptor descriptor) {
        this.descriptor = new EditStudentDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditStudentDescriptor} with fields containing {@code student}'s details
     */
    public EditStudentDescriptorBuilder(Student student) {
        descriptor = new EditStudentDescriptor();
        descriptor.setName(student.getName());
        descriptor.setPhone(student.getPhone());
        descriptor.setEmail(student.getEmail());
        descriptor.setStudentId(student.getStudentId());
        // descriptor.setModules(student.getModules());
        // descriptor.setTags(student.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withPhone(String phone) {
        descriptor.setPhone(new Phone(phone));
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withEmail(String email) {
        descriptor.setEmail(new Email(email));
        return this;
    }

    /**
     * Sets the {@code StudentId} of the {@code EditStudentDescriptor} that we are building.
     */
    public EditStudentDescriptorBuilder withStudentId(String address) {
        descriptor.setStudentId(new StudentId(address));
        return this;
    }

    public EditStudentDescriptor build() {
        return descriptor;
    }
}
