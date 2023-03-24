package tfifteenfour.clipboard.logic;

import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.course.Group;
import tfifteenfour.clipboard.model.student.Student;


public class CurrentSelection {

	private PageType currentPage;

	private Course selectedCourse;
	private Group selectedGroup;
	private Student selectedStudent;

	public CurrentSelection() {
		this.currentPage = PageType.COURSE_PAGE;
	}

	public PageType getCurrentPage() {
		return currentPage;
	}

	public void setSelectedCourse(Course course) {
		this.selectedCourse = course;
	}

	public void setSelectedGroup(Group group) {
		this.selectedGroup = group;
	}

	public void setSelectedStudent(Student student) {
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
}
