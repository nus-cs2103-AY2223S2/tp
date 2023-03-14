package codoc.ui.infopanel;

import codoc.model.module.Module;
import codoc.model.person.Person;
import codoc.model.skill.Skill;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;

import java.util.Comparator;

/**
 * DetailedSkill controller for showing skill details at DetailedInfoPanel.
 */
public class DetailedSkill extends DetailedInfo {

    private static final String FXML = "DetailedSkill.fxml";

    @FXML
    private ListView<Skill> skillListView;

    public DetailedSkill(Person protagonist) {
        super(FXML);
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        protagonist.getSkills().stream()
                .sorted(Comparator.comparing((Skill skill) -> skill.skillName))
                .forEach(skill -> skills.add(skill));
        skillListView.setItems(skills);
        skillListView.setCellFactory(listView -> new SkillListViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Skill} using a {@code SkillCard}.
     */
    class SkillListViewCell extends ListCell<Skill> {
        @Override
        protected void updateItem(Skill skill, boolean empty) {
            super.updateItem(skill, empty);

            if (empty || skill == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new SkillCard(skill, getIndex() + 1).getRoot());
            }
        }
    }

}
