package seedu.address.model.application;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class ReflectionTest {
    @Test
    public void equals() {
        Reflection reflection1 = new Reflection("I learned a lot during my internship.");
        Reflection reflection2 = new Reflection("I learned a lot during my internship.");
        Reflection reflection3 = new Reflection("I had a great time during my internship.");

        // reflexive property
        assertEquals(reflection1, reflection1);

        // symmetric property
        assertEquals(reflection1, reflection2);
        assertEquals(reflection2, reflection1);

        // transitive property
        assertEquals(reflection1, reflection2);
        assertNotEquals(reflection2, reflection3);
        assertNotEquals(reflection1, reflection3);

        // null and different type
        assertNotEquals(reflection1, null);
        assertNotEquals(reflection1, "I learned a lot during my internship.");

        // comparing reflection with different value
        Reflection differentReflection = new Reflection("I did not learn anything during my internship.");
        assertNotEquals(reflection1, differentReflection);
    }

}
