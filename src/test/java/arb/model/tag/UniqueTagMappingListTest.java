package arb.model.tag;

import static arb.testutil.Assert.assertThrows;
import static arb.testutil.TypicalClients.AMY;
import static arb.testutil.TypicalClients.BOB;
import static arb.testutil.TypicalProjects.OIL_PAINTING;
import static arb.testutil.TypicalProjects.SKY_PAINTING;
import static arb.testutil.TypicalTagMappings.FRIEND_TAG;
import static arb.testutil.TypicalTagMappings.HUSBAND_TAG;
import static arb.testutil.TypicalTagMappings.PAINTING_TAG;
import static arb.testutil.TypicalTagMappings.POTTERY_TAG;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import arb.model.client.Client;
import arb.model.project.Project;
import arb.model.tag.exceptions.DuplicateTagMappingException;
import arb.testutil.TagMappingBuilder;

public class UniqueTagMappingListTest {

    private final UniqueTagMappingList uniqueTagMappingList = new UniqueTagMappingList();

    @Test
    public void setTagMappings_nullList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.setTagMappings((List<TagMapping>) null));
    }

    @Test
    public void setTagMappings_list_replacesOwnListWithProvidedList() {
        uniqueTagMappingList.addClientTags(BOB);
        uniqueTagMappingList.addClientTags(AMY);
        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(2).build();
        TagMapping potteryTagMapping = new TagMappingBuilder(POTTERY_TAG)
                .withNumberOfProjectsTagged(1).build();
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.addProjectTags(SKY_PAINTING);
        expectedUniqueTagMappingList.addProjectTags(OIL_PAINTING);
        uniqueTagMappingList.setTagMappings(Arrays.asList(potteryTagMapping, paintingTagMapping));
        assertEquals(expectedUniqueTagMappingList, uniqueTagMappingList);
    }

    @Test
    public void setTagMappings_nullClientListOrNullProjectList_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList
                .setTagMappings((List<Client>) null, (List<Project>) null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList
                .setTagMappings(new ArrayList<>(), (List<Project>) null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList
                .setTagMappings((List<Client>) null, new ArrayList<>()));
    }

    @Test
    public void setTagMappings_clientListAndProjectList_replacesOwnList() {
        uniqueTagMappingList.addClientTags(BOB);
        uniqueTagMappingList.addProjectTags(SKY_PAINTING);

        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(1).build();
        TagMapping friendTagMapping = new TagMappingBuilder(FRIEND_TAG)
                .withNumberOfClientsTagged(1).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(friendTagMapping, paintingTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.setTagMappings(Arrays.asList(AMY), Arrays.asList(OIL_PAINTING));

        assertEquals(uniqueTagMappingList, expectedUniqueTagMappingList);
    }

    @Test
    public void setTagMappings_listWithDuplicateTagMappings_throwsDuplicateProjectException() {
        List<TagMapping> listWithDuplicateTagMappings = Arrays.asList(HUSBAND_TAG, HUSBAND_TAG);
        assertThrows(DuplicateTagMappingException.class, () -> uniqueTagMappingList
                .setTagMappings(listWithDuplicateTagMappings));
    }

    @Test
    public void asUnmodifiableObservableList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
            -> uniqueTagMappingList.asUnmodifiableObservableList().remove(0));
    }

    @Test
    public void addClientTags_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.addClientTags(null));
    }

    @Test
    public void addClientTags_success() {
        TagMapping husbandTagMapping = new TagMappingBuilder(HUSBAND_TAG)
                .withNumberOfClientsTagged(1).build();
        TagMapping friendTagMapping = new TagMappingBuilder(FRIEND_TAG)
                .withNumberOfClientsTagged(1).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(friendTagMapping, husbandTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addClientTags(BOB);

        assertEquals(expectedUniqueTagMappingList, uniqueTagMappingList);
    }

    @Test
    public void addProjectTags_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.addProjectTags(null));
    }

    @Test
    public void addProjectTags_success() {
        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(1).build();
        TagMapping potteryTagMapping = new TagMappingBuilder(POTTERY_TAG)
                .withNumberOfProjectsTagged(1).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(potteryTagMapping, paintingTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addProjectTags(SKY_PAINTING);

        assertEquals(expectedUniqueTagMappingList, uniqueTagMappingList);
    }

    @Test
    public void deleteClientTags_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.deleteClientTags(null));
    }

    @Test
    public void deleteClientTags_success() {
        TagMapping husbandTagMapping = new TagMappingBuilder(HUSBAND_TAG)
                .withNumberOfClientsTagged(1).build();
        TagMapping friendTagMapping = new TagMappingBuilder(FRIEND_TAG)
                .withNumberOfClientsTagged(1).build();
        List<TagMapping> initialTagMappings = Arrays.asList(husbandTagMapping, friendTagMapping);
        List<TagMapping> expectedTagMappings = Arrays.asList(husbandTagMapping);

        uniqueTagMappingList.setTagMappings(initialTagMappings);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.deleteClientTags(AMY);

        assertEquals(expectedUniqueTagMappingList, uniqueTagMappingList);
    }

    @Test
    public void deleteProjectTags_nullClient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.deleteProjectTags(null));
    }

    @Test
    public void deleteProjectTags_success() {
        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(1).build();
        TagMapping potteryTagMapping = new TagMappingBuilder(POTTERY_TAG)
                .withNumberOfProjectsTagged(1).build();
        List<TagMapping> initialTagMappings = Arrays.asList(paintingTagMapping, potteryTagMapping);
        List<TagMapping> expectedTagMappings = Arrays.asList(potteryTagMapping);

        uniqueTagMappingList.setTagMappings(initialTagMappings);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.deleteProjectTags(OIL_PAINTING);

        assertEquals(expectedUniqueTagMappingList, uniqueTagMappingList);
    }

    @Test
    public void editClientTags_nullClients_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editClientTags(null, null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editClientTags(AMY, null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editClientTags(null, AMY));
    }

    @Test
    public void editClientTags_success() {
        TagMapping friendTagMapping = new TagMappingBuilder(FRIEND_TAG)
                .withNumberOfClientsTagged(2).build();
        TagMapping husbandTagMapping = new TagMappingBuilder(HUSBAND_TAG)
                .withNumberOfClientsTagged(2).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(friendTagMapping, husbandTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addClientTags(BOB);
        uniqueTagMappingList.addClientTags(AMY);
        uniqueTagMappingList.editClientTags(AMY, BOB);

        assertEquals(uniqueTagMappingList, expectedUniqueTagMappingList);
    }

    @Test
    public void editProjectTags_nullProjects_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editProjectTags(null, null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editProjectTags(SKY_PAINTING, null));
        assertThrows(NullPointerException.class, () -> uniqueTagMappingList.editProjectTags(null, SKY_PAINTING));
    }

    @Test
    public void editProjectTags_success() {
        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(2).build();
        TagMapping potteryTagMapping = new TagMappingBuilder(POTTERY_TAG)
                .withNumberOfProjectsTagged(2).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(potteryTagMapping, paintingTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addProjectTags(SKY_PAINTING);
        uniqueTagMappingList.addProjectTags(OIL_PAINTING);
        uniqueTagMappingList.editProjectTags(OIL_PAINTING, SKY_PAINTING);

        assertEquals(uniqueTagMappingList, expectedUniqueTagMappingList);
    }

    @Test
    public void resetClientTags_success() {
        TagMapping paintingTagMapping = new TagMappingBuilder(PAINTING_TAG)
                .withNumberOfProjectsTagged(1).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(paintingTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addClientTags(BOB);
        uniqueTagMappingList.addProjectTags(OIL_PAINTING);
        uniqueTagMappingList.resetClientTagMappings();

        assertEquals(uniqueTagMappingList, expectedUniqueTagMappingList);
    }

    @Test
    public void resetProjectTags_success() {
        TagMapping friendTagMapping = new TagMappingBuilder(FRIEND_TAG)
                .withNumberOfClientsTagged(1).build();
        List<TagMapping> expectedTagMappings = Arrays.asList(friendTagMapping);
        UniqueTagMappingList expectedUniqueTagMappingList = new UniqueTagMappingList();
        expectedUniqueTagMappingList.setTagMappings(expectedTagMappings);

        uniqueTagMappingList.addProjectTags(SKY_PAINTING);
        uniqueTagMappingList.addClientTags(AMY);
        uniqueTagMappingList.resetProjectTagMappings();

        assertEquals(uniqueTagMappingList, expectedUniqueTagMappingList);
    }

    @Test
    public void equals() {
        UniqueTagMappingList otherTagMappingList = new UniqueTagMappingList();
        uniqueTagMappingList.addClientTags(BOB);
        uniqueTagMappingList.addProjectTags(SKY_PAINTING);

        otherTagMappingList.addProjectTags(SKY_PAINTING);
        otherTagMappingList.addClientTags(BOB);

        assertEquals(uniqueTagMappingList, otherTagMappingList); // different order
    }
}
