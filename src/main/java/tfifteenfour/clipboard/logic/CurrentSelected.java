package tfifteenfour.clipboard.logic;

import tfifteenfour.clipboard.model.course.Course;
import tfifteenfour.clipboard.model.student.Group;
import tfifteenfour.clipboard.model.student.Student;

public class CurrentSelected {

	private PageType currentPage;

	private Course selectedCourse;
	private Group selectedGroup;
	private Student selectedStudent;

	public CurrentSelected() {
		this.currentPage = PageType.COURSE_PAGE;
	}

	public PageType getCurrenPage() {
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

}
