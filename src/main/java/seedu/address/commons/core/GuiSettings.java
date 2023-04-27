package seedu.address.commons.core;

import java.awt.Point;
import java.io.Serializable;
import java.util.Objects;

/**
 * A Serializable class that contains the GUI settings.
 * Guarantees: immutable.
 */
public class GuiSettings implements Serializable {

    private static final double DEFAULT_HEIGHT = 800;
    private static final double DEFAULT_WIDTH = 900;

    private static final int DEFAULT_IMAGEVIEW_HEIGHT = 35;
    private static final int DEFAULT_IMAGEVIEW_WIDTH = 40;

    private static final int DEFAULT_CIRCLE_X = 17;
    private static final int DEFAULT_CIRCLE_Y = 17;
    private static final int DEFAULT_CIRCLE_RADIUS = 17;

    //"Icons made by Freepik from www.flaticon.com"
    private static final String DEFAULT_PHOTO_PATH = "/images/studentProfiles/student_";
    private static final String DEFAULT_MORE_PHOTO = "/images/more_icon.png";
    private static final String DEFAULT_PHOTO_FORMAT = ".png";
    private static final int DEFAULT_PHOTO_START_INDEX = 1;
    private static final int DEFAULT_PHOTO_END_INDEX = 16;

    private static final String DEFAULT_TUTORIAL_ICON = "/images/tutorial.png";
    private static final String DEFAULT_LAB_ICON = "/images/lab.png";
    private static final String DEFAULT_CONSULTATION_ICON = "/images/consultation.png";

    private static final int DEFAULT_EVENT_ICON_SIZE = 30;

    private static final String DEFAULT_ATTACHMENT_ICON = "/images/attachment.png";
    private static final String DEFAULT_NO_ATTACHMENT_ICON = "/images/noAttachment.png";

    private static final String DEFAULT_NOTE_ICON = "/images/note.png";
    private static final String DEFAULT_NO_NOTE_ICON = "/images/noNote.png";

    private static final Point NULL_COORDINATE = null;

    private final double windowWidth;
    private final double windowHeight;
    private final Point windowCoordinates;
    private final int imageViewHeight;
    private final int imageViewWidth;
    private final int circleX;
    private final int circleY;
    private final int circleRadius;
    private final String photoPath;
    private final String photoFormat;
    private final String morePhoto;
    private final int photoStartIndex;
    private final int photoEndIndex;
    private final String tutorialIcon;
    private final String labIcon;
    private final String consultationIcon;
    private final String attachmentIcon;
    private final String noAttachmentIcon;
    private final String noteIcon;
    private final String noNoteIcon;
    private final int eventIconSize;

    /**
     * Constructs a {@code GuiSettings} with the default height, width and position.
     */
    public GuiSettings() {
        windowWidth = DEFAULT_WIDTH;
        windowHeight = DEFAULT_HEIGHT;
        windowCoordinates = NULL_COORDINATE;
        imageViewHeight = DEFAULT_IMAGEVIEW_HEIGHT;
        imageViewWidth = DEFAULT_IMAGEVIEW_WIDTH;
        circleX = DEFAULT_CIRCLE_X;
        circleY = DEFAULT_CIRCLE_Y;
        circleRadius = DEFAULT_CIRCLE_RADIUS;
        photoPath = DEFAULT_PHOTO_PATH;
        morePhoto = DEFAULT_MORE_PHOTO;
        photoFormat = DEFAULT_PHOTO_FORMAT;
        photoStartIndex = DEFAULT_PHOTO_START_INDEX;
        photoEndIndex = DEFAULT_PHOTO_END_INDEX;
        tutorialIcon = DEFAULT_TUTORIAL_ICON;
        labIcon = DEFAULT_LAB_ICON;
        consultationIcon = DEFAULT_CONSULTATION_ICON;
        attachmentIcon = DEFAULT_ATTACHMENT_ICON;
        noAttachmentIcon = DEFAULT_NO_ATTACHMENT_ICON;
        eventIconSize = DEFAULT_EVENT_ICON_SIZE;
        noteIcon = DEFAULT_NOTE_ICON;
        noNoteIcon = DEFAULT_NO_NOTE_ICON;
    }

    //Allow user to modify student profile size in next iteration
    /**
     * Constructs a {@code GuiSettings} with the specified height, width and position but fixed student profile image
     */
    public GuiSettings(double windowWidth, double windowHeight, int xPosition, int yPosition) {
        this.windowWidth = windowWidth;
        this.windowHeight = windowHeight;
        windowCoordinates = new Point(xPosition, yPosition);
        imageViewHeight = DEFAULT_IMAGEVIEW_HEIGHT;
        imageViewWidth = DEFAULT_IMAGEVIEW_WIDTH;
        circleX = DEFAULT_CIRCLE_X;
        circleY = DEFAULT_CIRCLE_Y;
        circleRadius = DEFAULT_CIRCLE_RADIUS;
        photoPath = DEFAULT_PHOTO_PATH;
        morePhoto = DEFAULT_MORE_PHOTO;
        photoFormat = DEFAULT_PHOTO_FORMAT;
        photoStartIndex = DEFAULT_PHOTO_START_INDEX;
        photoEndIndex = DEFAULT_PHOTO_END_INDEX;
        tutorialIcon = DEFAULT_TUTORIAL_ICON;
        labIcon = DEFAULT_LAB_ICON;
        consultationIcon = DEFAULT_CONSULTATION_ICON;
        attachmentIcon = DEFAULT_ATTACHMENT_ICON;
        noAttachmentIcon = DEFAULT_NO_ATTACHMENT_ICON;
        eventIconSize = DEFAULT_EVENT_ICON_SIZE;
        noteIcon = DEFAULT_NOTE_ICON;
        noNoteIcon = DEFAULT_NO_NOTE_ICON;
    }

    public double getWindowWidth() {
        return windowWidth;
    }

    public double getWindowHeight() {
        return windowHeight;
    }

    public Point getWindowCoordinates() {
        return windowCoordinates != null ? new Point(windowCoordinates) : null;
    }

    public int getImageViewHeight() {
        return imageViewHeight;
    }

    public int getImageViewWidth() {
        return imageViewWidth;
    }

    public int getCircleX() {
        return circleX;
    }

    public int getCircleY() {
        return circleY;
    }

    public int getCircleRadius() {
        return circleRadius;
    }

    public String getPhoto() {
        return photoPath;
    }

    public String getMorePhoto() {
        return morePhoto;
    }

    public String getPhotoFormat() {
        return photoFormat;
    }

    public int getPhotoStartIndex() {
        return photoStartIndex;
    }

    public int getPhotoEndIndex() {
        return photoEndIndex;
    }

    public String getTutorialIcon() {
        return tutorialIcon;
    }

    public String getLabIcon() {
        return labIcon;
    }

    public String getConsultationIcon() {
        return consultationIcon;
    }

    public String getAttachmentIcon() {
        return attachmentIcon;
    }

    public String getNoAttachmentIcon() {
        return noAttachmentIcon;
    }
    public String getNoteIcon() {
        return noteIcon;
    }
    public String getNoNoteIcon() {
        return noNoteIcon;
    }
    public int getEventIconSize() {
        return eventIconSize;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof GuiSettings)) { //this handles null as well.
            return false;
        }

        GuiSettings o = (GuiSettings) other;

        return windowWidth == o.windowWidth
                && windowHeight == o.windowHeight
                && Objects.equals(windowCoordinates, o.windowCoordinates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(windowWidth, windowHeight, windowCoordinates);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Width : " + windowWidth + "\n");
        sb.append("Height : " + windowHeight + "\n");
        sb.append("Position : " + windowCoordinates);
        return sb.toString();
    }
}
