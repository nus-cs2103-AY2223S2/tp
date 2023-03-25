package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import javax.swing.Icon;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.SetPictureCommand.ThumbNailView;

public class ThumbNailViewTest {
    @Test
    public void getIcon() {
        File f = new File("src/main/resources/employeepictures/default.png");
        Icon icon = new ThumbNailView().getIcon(f);
        assertTrue(icon.getIconHeight() == 16 && icon.getIconWidth() == 16);
    }
}
