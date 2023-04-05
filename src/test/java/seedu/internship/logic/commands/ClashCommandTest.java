package seedu.internship.logic.commands;

import seedu.internship.model.InternshipCatalogue;
import seedu.internship.model.Model;
import seedu.internship.model.ReadOnlyInternshipCatalogue;
import seedu.internship.model.internship.*;

import static seedu.internship.model.util.SampleDataUtil.getTagSet;

public class ClashCommandTest {
    private static Internship internship1 = new Internship(new Position("p1"),
            new Company("c1"), new Status(1),
            new Description(""), getTagSet());
    private static Internship internship2 = new Internship(new Position("p2"),
            new Company("c2"), new Status(1),
            new Description(""), getTagSet());
    private static Internship internship3 = new Internship(new Position("p3"),
            new Company("c3"), new Status(1),
            new Description(""), getTagSet());
    private static Internship internship4 = new Internship(new Position("p4"),
            new Company("c4"), new Status(1),
            new Description(""), getTagSet());
    private static Internship internship5 = new Internship(new Position("p5"),
            new Company("c5"), new Status(1),
            new Description(""), getTagSet());

    ReadOnlyInternshipCatalogue internshipCatalogue =  new InternshipCatalogue();
    internshipCatalogue.addInternship(internship1);
    internshipCatalogue.addInternship(internship2);



}


