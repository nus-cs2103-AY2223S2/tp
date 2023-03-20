package seedu.address.logic.commands;

import seedu.address.logic.parser.UntagCommandParser;
import seedu.address.model.lecture.LectureName;
import seedu.address.model.lecture.ReadOnlyLecture;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.video.VideoName;
import seedu.address.testutil.ModelStub;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

public class UntagCommandTest {
    private final UntagCommandParser parser = new UntagCommandParser();

    /**
     * A {@code Model} stub that contains a single module.
     */
    private class ModelStubWithModule extends ModelStub {
        private Module module;

        public ModelStubWithModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        @Override
        public boolean hasModule(ModuleCode module) {
            requireNonNull(module);
            return this.module.getCode().equals(module);
        }

        @Override
        public boolean hasLecture(ModuleCode module, LectureName lecture) {
            requireAllNonNull(module, lecture);
            return this.module.getLecture(lecture) != null;
        }

        public boolean hasVideo(ReadOnlyLecture lecture, VideoName video) {
            requireAllNonNull(module, lecture, video);
            return lecture.hasVideo(video);
        }
    }
}