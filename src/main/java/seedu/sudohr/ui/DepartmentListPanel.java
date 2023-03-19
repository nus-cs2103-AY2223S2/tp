package seedu.sudohr.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.sudohr.commons.core.LogsCenter;
import seedu.sudohr.model.department.Department;


/**
 * A panel that displays all departments
 */
public class DepartmentListPanel extends UiPart<Region> {
    private static final String FXML = "DepartmentListPanel.fxml";
    private final Logger logger = LogsCenter.getLogger(DepartmentListPanel.class);

    @FXML
    private ListView<Department> departmentListView;

    /**
     * Creates a {@code DepartmentListPanel} with the given {@code ObservableList}.
     * @param departmentList the list of Departments to be displayed
     */
    public DepartmentListPanel(ObservableList<Department> departmentList) {
        super(FXML);
        departmentListView.setItems(departmentList);
        departmentListView.setCellFactory(listView -> new DepartmentListViewCell());
    }

    class DepartmentListViewCell extends ListCell<Department> {
        @Override
        protected void updateItem(Department department, boolean empty) {
            super.updateItem(department, empty);

            if (empty || department == null) {
                setGraphic(null);
                setText((null));
            } else {
                setGraphic(new DepartmentCard(department, getIndex() + 1).getRoot());
            }
        }
    }
}
