package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.exceptions.DuplicateTagException;

public class UniqueTagListTest {

    private final UniqueTagList uniqueTagList = new UniqueTagList();
    private final Tag tag = new Tag("Hall");
    private final Tag varTag = new Tag("Varsity");

    @Test
    public void contains_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.contains(null));
    }

    @Test
    public void contains_tagNotInList_returnsFalse() {

        assertFalse(uniqueTagList.contains(tag));
    }

    @Test
    public void contains_tagInList_returnsTrue() {
        uniqueTagList.add(tag);
        assertTrue(uniqueTagList.contains(tag));
    }

    @Test
    public void add_nullTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.add(null));
    }

    @Test
    public void add_duplicateTag_throwsDuplicateTagException() {
        uniqueTagList.add(tag);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.add(tag));
    }

    @Test
    public void setTag_nullTargetTag_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTag(null, tag));
    }

    @Test
    public void setTag_editedTagHasSameIdentity_success() {
        uniqueTagList.add(tag);
        Tag editedTag = new Tag("Hall");
        uniqueTagList.setTag(tag, editedTag);
        UniqueTagList expected = new UniqueTagList();
        expected.add(editedTag);
        assertEquals(expected, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasDifferentIdentity_success() {
        uniqueTagList.add(tag);
        uniqueTagList.setTag(tag, varTag);
        UniqueTagList expectedTagList = new UniqueTagList();
        expectedTagList.add(varTag);
        assertEquals(expectedTagList, uniqueTagList);
    }

    @Test
    public void setTag_editedTagHasNonUniqueIdentity_throwsDuplicateTagException() {
        uniqueTagList.add(tag);
        uniqueTagList.add(varTag);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTag(tag, varTag));
    }

    @Test
    public void setTags_nullUniqueTagList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((UniqueTagList) null));
    }

    @Test
    public void setTags_uniqueTagList_replacesOwnListWithProvidedUniqueTagList() {
        uniqueTagList.add(tag);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(tag);
        uniqueTagList.setTags(expectedUniqueTagList);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagList.setTags((List<Tag>) null));
    }

    @Test
    public void setTags_list_replacesOwnListWithProvidedList() {
        uniqueTagList.add(tag);
        List<Tag> tagList = Collections.singletonList(tag);
        uniqueTagList.setTags(tagList);
        UniqueTagList expectedUniqueTagList = new UniqueTagList();
        expectedUniqueTagList.add(tag);
        assertEquals(expectedUniqueTagList, uniqueTagList);
    }

    @Test
    public void setTags_listWithDuplicateTags_throwsDuplicateTagException() {
        List<Tag> listWithDuplicateTags = Arrays.asList(tag, tag);
        assertThrows(DuplicateTagException.class, () -> uniqueTagList.setTags(listWithDuplicateTags));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> uniqueTagList.asUnmodifiableObservableList().remove(0));
    }
}
