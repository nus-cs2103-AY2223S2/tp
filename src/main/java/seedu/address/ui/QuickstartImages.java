package seedu.address.ui;

import javafx.scene.image.Image;

/**
 * Provides QuickstartWindow class with images to be displayed on screen.
 */
public class QuickstartImages {
    public static final Image QUICKSTART_FIRSTPAGE = new Image("/images/quickstart-1.png");
    public static final Image QUICKSTART_SECONDPAGE = new Image("/images/quickstart-2.png");
    public static final Image QUICKSTART_THIRDPAGE = new Image("/images/quickstart-3.png");
    public static final Image QUICKSTART_FOURTHPAGE = new Image("/images/quickstart-4.png");
    public static final Image QUICKSTART_FIFTHPAGE = new Image("/images/quickstart-5.png");
    public static final Image QUICKSTART_SIXTHPAGE = new Image("/images/quickstart-6.png");
    public static final Image QUICKSTART_SEVENTHPAGE = new Image("/images/quickstart-7.png");
    public static final Image QUICKSTART_EIGHTHPAGE = new Image("/images/quickstart-8.png");

    /**
     * Returns an image according to the current page number in Pagination.
     *
     * @param pageNum page number (index 0) being passed by the quickPaginator.
     */
    public static Image giveImage(int pageNum) {
        switch (pageNum) {
        case 0:
            return QUICKSTART_FIRSTPAGE;
        case 1:
            return QUICKSTART_SECONDPAGE;
        case 2:
            return QUICKSTART_THIRDPAGE;
        case 3:
            return QUICKSTART_FOURTHPAGE;
        case 4:
            return QUICKSTART_FIFTHPAGE;
        case 5:
            return QUICKSTART_SIXTHPAGE;
        case 6:
            return QUICKSTART_SEVENTHPAGE;
        case 7:
            return QUICKSTART_EIGHTHPAGE;
        default:
            return QUICKSTART_FIRSTPAGE;
        }
    }
}
