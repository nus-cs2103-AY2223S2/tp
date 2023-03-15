package seedu.address.ui.body.address;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Region;
import seedu.address.model.person.fields.subfields.NusMod;
import seedu.address.ui.UiPart;

/**
 * A UI component that displays a {@code Person}'s list of modules.
 */
public class PersonModulesCard extends UiPart<Region> {
    private static final String FXML = "body/address/PersonModulesCard.fxml";

    @FXML
    private Label title;
    @FXML
    private FlowPane modules;

    /**
     * Creates a {@code PersonModulesCard} with the given
     * {@code title} and collection of {@code NusMod}s.
     */
    public PersonModulesCard(String title, Collection<NusMod> mods) {
        super(FXML);
        Objects.requireNonNull(title);
        Objects.requireNonNull(mods);
        this.title.setText(title);
        this.modules.getChildren().addAll(getModuleLabels(mods));
    }

    private Collection<Label> getModuleLabels(Collection<NusMod> mods) {
        if (mods == null || mods.isEmpty()) {
            return List.of(new Label("No modules"));
        }

        return mods.stream()
                .filter(Objects::nonNull)
                .filter(mod -> mod.name != null && !mod.name.isBlank())
                .sorted(Comparator.comparing(mod -> mod.name))
                .map(mod -> mod.name)
                .map(Label::new)
                .collect(Collectors.toList());
    }
}
