package tfifteenfour.clipboard.model.student;

import java.util.ArrayList;

public class Module {
	String name;
	ArrayList<Group> group = new ArrayList<Group>();

	public Module(String name) {
		this.name = name;
	}
}
