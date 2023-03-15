package tfifteenfour.clipboard.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import tfifteenfour.clipboard.MainApp;
import tfifteenfour.clipboard.commons.core.LogsCenter;
import tfifteenfour.clipboard.model.student.Student;


import java.io.File;
import java.util.Comparator;
import java.util.Optional;
import java.util.logging.Logger;

public class StudentViewCard extends UiPart<Region> {

    private static final String FXML = "StudentViewCard.fxml";
    private static final Logger logger = LogsCenter.getLogger(MainApp.class);

    @FXML
    private VBox viewPane;
    @FXML
    private HBox infoPane;
    @FXML
    private VBox studentDetails;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label name;
    @FXML
    private FlowPane modules;
    @FXML
    private FlowPane tags;
    @FXML
    private Label phone;
    @FXML
    private Label studentId;
    @FXML
    private Label email;
    @FXML
    private ImageView displayPhoto;
    @FXML
    private Label remark;

    public StudentViewCard(Student viewedStudent) {
        super(FXML);
        name.setText(viewedStudent.getName().fullName);
        phone.setText(viewedStudent.getPhone().value);
        studentId.setText(viewedStudent.getStudentId().value);
        email.setText(viewedStudent.getEmail().value);
        remark.setText(viewedStudent.getRemark().value);
        viewedStudent.getModules().stream()
                .sorted(Comparator.comparing(module -> module.moduleCode))
                .forEach(module -> modules.getChildren().add(new Label(module.moduleCode)));
        viewedStudent.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));

        displayPhoto.setImage(new Image(this.getClass().getResourceAsStream("/images/studenticon.png")));
        try {
            String imageUrl = "data/" + viewedStudent.getStudentId().value + ".png";
            File file = new File(imageUrl);
            if (!file.exists()) {
                Image defaultImage = new Image(this.getClass().getResourceAsStream("/images/studenticon.png"));
                displayPhoto.setImage(defaultImage);
            } else {
                Image newImage = new Image(file.toURI().toString());
                displayPhoto.setImage(newImage);
            }
        } catch (Exception e) {
            logger.warning(e.getMessage());
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof StudentCard)) {
            return false;
        }

        return true;
    }
}
