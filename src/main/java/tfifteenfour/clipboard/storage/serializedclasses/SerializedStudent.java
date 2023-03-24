package tfifteenfour.clipboard.storage.serializedclasses;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;

public class SerializedStudent {
    private String name;
    private String phone;
    private String email;
    private String studentId;
    private String remark;
    // private List<Tag> tags;

    public SerializedStudent(Student student) {
        this.name = student.getName().toString();
        this.phone = student.getPhone().toString();
        this.email = student.getEmail().toString();
        this.studentId = student.getStudentId().toString();
        this.remark = student.getRemark().toString();
        // this.tags = new ArrayList<Tag>(student.getTags());
    }

    public SerializedStudent() {}

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("phone")
    public String getPhone() {
        return phone;
    }

    @JsonProperty("email")
    public String getEmail() {
        return email;
    }

    @JsonProperty("studentId")
    public String getStudentId() {
        return studentId;
    }

    @JsonProperty("remark")
    public String getRemark() {
        return remark;
    }

    // @JsonProperty("tags")
    // public List<Tag> getTagged() {
    //     return tags;
    // }

    public Student toModelType() {
        return new Student(new Name(name), new Phone(phone),
                new Email(email), new StudentId(studentId),
                new Remark(remark));
    }
}
