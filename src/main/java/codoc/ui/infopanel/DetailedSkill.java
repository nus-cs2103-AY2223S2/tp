package codoc.ui.infopanel;

import codoc.model.person.Person;

/**
 * DetailedSkill controller for showing skill details at DetailedInfoPanel.
 */
public class DetailedSkill extends DetailedInfo {

    private static final String FXML = "DetailedSkill.fxml";

    public DetailedSkill(Person protagonist) {
        super(FXML);
    }

}
