package tfifteenfour.clipboard.storage.serializedClasses;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.tag.Tag;

public class SerializedStudent {
    private final String name;
    private final String phone;
    private final String email;
    private final String studentId;
    private final String remark;
    private final List<Tag> tags;

    public SerializedStudent(Student student) {
        this.name = student.getName().toString();
        this.phone = student.getPhone().toString();
        this.email = student.getEmail().toString();
        this.studentId = student.getStudentId().toString();
        this.remark = student.getRemark().toString();
        this.tags = new ArrayList<Tag>(student.getTags());
    }

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

    @JsonProperty("tags")
    public List<Tag> getTagged() {
        return tags;
    }
}