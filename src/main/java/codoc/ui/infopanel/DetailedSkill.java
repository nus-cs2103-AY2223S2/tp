package codoc.ui.infopanel;

import java.util.Comparator;

import codoc.logic.commands.exceptions.CommandException;
import codoc.logic.parser.exceptions.ParseException;
import codoc.model.person.Person;
import codoc.model.skill.Skill;
import codoc.ui.MainWindow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;


/**
 * DetailedSkill controller for showing skill details at DetailedInfoPanel.
 */
public class DetailedSkill extends DetailedInfo {

    private static final String FXML = "DetailedSkill.fxml";

    @FXML
    private ListView<Skill> skillListView;

    private MainWindow mainWindow;


    /**
     * Creates a {@code DetailedSkill} tab with the given {@code protagonist}.
     */
    public DetailedSkill(MainWindow mainWindow) {
        super(FXML);
        this.mainWindow = mainWindow;
        Person protagonist = mainWindow.getLogic().getProtagonist();
        ObservableList<Skill> skills = FXCollections.observableArrayList();
        protagonist.getSkills().stream()
                .sorted(Comparator.comparing((Skill skill) -> skill.skillName))
                .forEach(skill -> skills.add(skill));
        skillListView.setItems(skills);
        skillListView.setCellFactory(listView -> new SkillListViewCell());
        skillListView.setPrefHeight((52 * skills.size()) + 2);
    }

    @FXML
    private void viewContactTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view c");
    }

    @FXML
    private void viewModulesTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view m");
    }

    @FXML
    private void viewSkillsTab() throws CommandException, ParseException {
        mainWindow.clickExecuteCommand("view s");
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
