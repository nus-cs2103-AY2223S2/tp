package seedu.address.ui;

import javafx.scene.image.Image;

public class QuickstartImages {
    public static final Image quickstartFirstPage = new Image("/images/quickstart-1.png");
    public static final Image quickstartSecondPage = new Image("/images/quickstart-2.png");
    public static final Image quickstartThirdPage = new Image("/images/quickstart-3.png");
    public static final Image quickstartFourthPage = new Image("/images/quickstart-4.png");
    public static final Image quickstartFifthPage = new Image("/images/quickstart-5.png");
    public static final Image quickstartSixthPage = new Image("/images/quickstart-6.png");
    public static final Image quickstartSeventhPage = new Image("/images/quickstart-7.png");
    public static final Image quickstartEighthPage = new Image("/images/quickstart-8.png");

    /**
     * Returns an image according to the current page number in Pagination.
     *
     * @param pageNum page number (index 0) being passed by the quickPaginator.
     */
    public static Image giveImage(int pageNum) {
        switch (pageNum) {
        case 0:
            return quickstartFirstPage;
        case 1:
            return quickstartSecondPage;
        case 2:
            return quickstartThirdPage;
        case 3:
            return quickstartFourthPage;
        case 4:
            return quickstartFifthPage;
        case 5:
            return quickstartSixthPage;
        case 6:
            return quickstartSeventhPage;
        case 7:
            return quickstartEighthPage;
        default:
            return quickstartFirstPage;
        }
    }
}
