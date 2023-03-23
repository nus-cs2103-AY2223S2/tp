package seedu.address.ui.views;

import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.job.Role;


public class RoleDetailsView {


    private RoleDetailsView() {}
    public static Node from(Role role) {
        final Label name = new Label(role.getName().fullName);
        final Label company = new Label(role.getCompany().value);
        final Label phone = new Label(role.getPhone().value);
        final Label email = new Label(role.getEmail().value);
        final Label website = new Label(role.getWebsite().value);
        final Label jobDescription = new Label(role.getJobDescription().value);

        return new HBox(name, company, phone, email, website, jobDescription);
    }
}
