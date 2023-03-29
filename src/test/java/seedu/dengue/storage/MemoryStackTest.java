package seedu.dengue.storage;

import java.util.ArrayList;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.dengue.storage.temporary.MemoryStack;


public class MemoryStackTest {
    private static final int BOUND = 100;
    private MemoryStack<Integer> memoryStack;
    private final Random random = new Random();

    @BeforeEach
    public void setUp() {
        this.memoryStack = new MemoryStack<Integer>();
    }

    @Test
    public void push_randomIntegers_givesCorrectResults() {

        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            memoryStack.push(newInt);
            assert(memoryStack.peek() == newInt);
        }

    }

    @Test
    public void removeOld_randomIntegers_givesCorrectResults() {
        int first = BOUND + 1;
        memoryStack.push(first);
        for (int i = 0; i < 20; i++) {
            memoryStack.push(random.nextInt(BOUND));
        }
        assert(first == memoryStack.removeOld());
    }

    @Test
    public void temporaryPop_randomIntegers_givesCorrectResults() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            integers.add(newInt);
            memoryStack.push(newInt);
        }
        int size = integers.size();
        for (int i = size - 1; i >= 0; i--) {
            assert(memoryStack.temporaryPop() == integers.get(i));
        }
        assert(memoryStack.getStorage().size() == size);
    }

    @Test
    public void temporaryPopThenPush_randomIntegers_givesCorrectResults() {
        ArrayList<Integer> integers = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            int newInt = random.nextInt(BOUND);
            integers.add(newInt);
            memoryStack.push(newInt);
        }
        int size = integers.size();
        for (int i = size - 1; i >= 0; i--) {
            memoryStack.temporaryPop();
        }
        for (int x : integers) {
            memoryStack.pushOneFromTemporaryPop();
            assert(memoryStack.peek() == x);
        }


    }
}
