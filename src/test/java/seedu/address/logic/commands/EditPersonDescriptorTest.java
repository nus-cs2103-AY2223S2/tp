package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.DESC_ALEX;
import static seedu.address.logic.commands.CommandTestUtil.DESC_BEN;
import static seedu.address.logic.commands.CommandTestUtil.EMAIL_BEN;
import static seedu.address.logic.commands.CommandTestUtil.NAME_BEN;
import static seedu.address.logic.commands.CommandTestUtil.PHONE_BEN;
import static seedu.address.logic.commands.CommandTestUtil.STATION_BEN;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GROUP_1;
import static seedu.address.testutil.TypicalPersons.ALBERT;
import static seedu.address.testutil.TypicalPersons.BEN;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.testutil.EditPersonDescriptorBuilder;

public class EditPersonDescriptorTest {

    private static final EditPersonDescriptor EDIT_PERSON_DESCRIPTOR =
            new EditPersonDescriptorBuilder(ALBERT).build();

    @Test
    public void constructor_empty_success() {
        assertDoesNotThrow(() -> new EditPersonDescriptor());
    }

    @Test
    public void constructor_fromAnotherDescriptor_success() {
        assertDoesNotThrow(() -> new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR));
    }

    @Test
    public void setName_validName_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getName().isPresent());
        assertEquals(descriptor.getName().get(), ALBERT.getName());

        descriptor.setName(BEN.getName());
        assertTrue(descriptor.getName().isPresent());
        assertEquals(descriptor.getName().get(), BEN.getName());
    }

    @Test
    public void setName_validContactIndex_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertEquals(descriptor.getContactIndex(), ALBERT.getContactIndex());

        descriptor.setContactIndex(BEN.getContactIndex());
        assertEquals(descriptor.getContactIndex(), BEN.getContactIndex());
    }

    @Test
    public void setName_validPhone_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getPhone().isPresent());
        assertEquals(descriptor.getPhone().get(), ALBERT.getPhone());

        descriptor.setPhone(BEN.getPhone());
        assertTrue(descriptor.getPhone().isPresent());
        assertEquals(descriptor.getPhone().get(), BEN.getPhone());
    }

    @Test
    public void setName_validEmail_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getEmail().isPresent());
        assertEquals(descriptor.getEmail().get(), ALBERT.getEmail());

        descriptor.setEmail(BEN.getEmail());
        assertTrue(descriptor.getEmail().isPresent());
        assertEquals(descriptor.getEmail().get(), BEN.getEmail());
    }

    @Test
    public void setName_validTelegramHandle_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getTelegramHandle().isPresent());
        assertEquals(descriptor.getTelegramHandle().get(), ALBERT.getTelegramHandle());

        descriptor.setTelegramHandle(BEN.getTelegramHandle());
        assertTrue(descriptor.getTelegramHandle().isPresent());
        assertEquals(descriptor.getTelegramHandle().get(), BEN.getTelegramHandle());
    }

    @Test
    public void setName_validStation_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getStation().isPresent());
        assertEquals(descriptor.getStation().get(), ALBERT.getStation());

        descriptor.setStation(BEN.getStation());
        assertTrue(descriptor.getStation().isPresent());
        assertEquals(descriptor.getStation().get(), BEN.getStation());
    }

    @Test
    public void setName_validGroupTags_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getGroupTags().isPresent());
        assertEquals(descriptor.getGroupTags().get(), ALBERT.getGroupTags().getImmutableGroups());

        descriptor.setGroupTags(BEN.getGroupTags().getImmutableGroups());
        assertTrue(descriptor.getGroupTags().isPresent());
        assertEquals(descriptor.getGroupTags().get(), BEN.getGroupTags().getImmutableGroups());
    }

    @Test
    public void setName_validModuleTags_success() {
        EditPersonDescriptor descriptor = new EditPersonDescriptor(EDIT_PERSON_DESCRIPTOR);
        assertTrue(descriptor.getModuleTags().isPresent());
        assertEquals(descriptor.getModuleTags().get(), ALBERT.getModuleTags().getImmutableModules());

        descriptor.setModuleTags(BEN.getModuleTags().getImmutableModules());
        assertTrue(descriptor.getModuleTags().isPresent());
        assertEquals(descriptor.getModuleTags().get(), BEN.getModuleTags().getImmutableModules());
    }

    @Test
    public void equals() {
        // same values -> returns true
        EditPersonDescriptor descriptorWithSameValues = new EditPersonDescriptor(DESC_ALEX);
        assertTrue(DESC_ALEX.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_ALEX.equals(DESC_ALEX));

        // null -> returns false
        assertFalse(DESC_ALEX.equals(null));

        // different types -> returns false
        assertFalse(DESC_ALEX.equals(5));

        // different values -> returns false
        assertFalse(DESC_ALEX.equals(DESC_BEN));

        // different name -> returns false
        EditPersonDescriptor editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withName(NAME_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different phone -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withPhone(PHONE_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different email -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withEmail(EMAIL_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different station -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withStation(STATION_BEN).build();
        assertFalse(DESC_ALEX.equals(editedAmy));

        // different tags -> returns false
        editedAmy = new EditPersonDescriptorBuilder(DESC_ALEX).withGroupTags(VALID_GROUP_1).build();
        assertFalse(DESC_ALEX.equals(editedAmy));
    }
}
