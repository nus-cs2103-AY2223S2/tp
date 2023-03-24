package tfifteenfour.clipboard.logic;

import java.util.HashSet;
import java.util.Set;

import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Email;
import tfifteenfour.clipboard.model.student.Name;
import tfifteenfour.clipboard.model.student.Phone;
import tfifteenfour.clipboard.model.student.Remark;
import tfifteenfour.clipboard.model.student.Student;
import tfifteenfour.clipboard.model.student.StudentId;
import tfifteenfour.clipboard.model.tag.Tag;


public class CurrentSelection {

	private static final Course NON_EXISTENT_COURSE = new Course("NON-EXISTENT COURSE");
	private static final Group NON_EXISTENT_GROUP = new Group("NON-EXISTENT GROUP");
	private static final Student NON_EXISTENT_STUDENT = emptyStudentBuilder();
	private PageType currentPage;

	private Course selectedCourse;
	private Group selectedGroup;
	private Student selectedStudent;

	private static Student emptyStudentBuilder() {
		Name name = new Name("NON-EXISTENT STUDENT");
		Phone phone = new Phone("123456");
		Email email = new Email("empty@empty.com");
		StudentId sid = new StudentId("EMPTY99");
		Set<Course> modules = new HashSet<>();
		Remark remark = new Remark("");
		Set<Tag> tags = new HashSet<>();
		return new Student(name, phone, email, sid, modules, remark, tags);
	}

	public CurrentSelection() {
		this.currentPage = PageType.COURSE_PAGE;
	}

	public PageType getCurrentPage() {
		return currentPage;
	}

	public void selectCourse(Course course) {
		this.selectedCourse = course;
		this.currentPage = PageType.GROUP_PAGE;
	}

	public void selectGroup(Group group) {
		this.selectedGroup = group;
		this.currentPage = PageType.STUDENT_PAGE;
	}

	public void selectStudent(Student student) {
		this.selectedStudent = student;
	}

	public void setCurrentPage(PageType newPage) {
		this.currentPage = newPage;
	}

	public Course getSelectedCourse() {
		return this.selectedCourse;
	}

	public Group getSelectedGroup() {
		return this.selectedGroup;
	}

	public Student getSelectedStudent() {
		return this.selectedStudent;
	}

	public void navigateBackFromGroupPage() {
		this.selectedCourse = NON_EXISTENT_COURSE;
		this.currentPage = PageType.COURSE_PAGE;
	}

	public void navigateBackFromStudentPage() {
		this.selectedGroup = NON_EXISTENT_GROUP;
		this.currentPage = PageType.GROUP_PAGE;
	}
}




