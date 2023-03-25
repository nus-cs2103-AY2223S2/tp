package seedu.address.ui.displays;

import java.util.Comparator;

import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import seedu.address.model.job.Role;


public final class RoleDisplay {

    private RoleDisplay() {}

    public static Node of(Role roleToDisplay) {
        Label role = new Label(roleToDisplay.getName().fullName + " @ ");
        role.getStyleClass().add("role-display-headers");

        Label company = new Label(roleToDisplay.getCompany().value);
        company.getStyleClass().add("role-display-headers");

        HBox title = new HBox(role, company);

        FlowPane tags = new FlowPane();
        roleToDisplay.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
        tags.getStyleClass().add("role-display-tags");

        Label salary = new Label("Salary: $" + roleToDisplay.getSalary().salary);
        salary.getStyleClass().add("role-display-body");

        Label jobDesc = new Label("Job Description: \n" + roleToDisplay.getJobDescription().value);
        jobDesc.getStyleClass().add("role-display-body");

        VBox container = new VBox(title, tags, salary, jobDesc);
        container.setPadding(new Insets(5, 5, 5, 5));
        container.setSpacing(10);
        return container;
    }
}
