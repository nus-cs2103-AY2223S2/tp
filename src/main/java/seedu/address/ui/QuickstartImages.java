package seedu.address.ui;

import javafx.scene.image.Image;

public class QuickstartImages {
    public static final Image quickstartFirstPage = new Image("/image/quickstart-1.png");
    public static final Image quickstartSecondPage = new Image("/image/quickstart-2.png");
    public static final Image quickstartThirdPage = new Image("/image/quickstart-3.png");
    public static final Image quickstartFourthPage = new Image("/image/quickstart-4.png");
    public static final Image quickstartFifthPage = new Image("/image/quickstart-5.png");
    public static final Image quickstartSixthPage = new Image("/image/quickstart-6.png");
    public static final Image quickstartSeventhPage = new Image("/image/quickstart-7.png");
    public static final Image quickstartEighthPage = new Image("/image/quickstart-8.png");

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
