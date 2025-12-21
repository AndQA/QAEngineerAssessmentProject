package tests;

import base.BaseTest;
import bo.InsiderCareerBO;
import org.testng.annotations.Test;

public class InsiderQATest extends BaseTest {

    @Test
    public void verifyQAJobFlow() {

        InsiderCareerBO bo = new InsiderCareerBO();

        bo.openHomePage(baseUrl)
                .navigateToHomePage()
                ;

//        softAssert.assertTrue(bo.hasOpenPositions(), "No QA positions found");
//        softAssert.assertTrue(bo.isApplyButtonVisible(), "Apply button is not visible");
    }
}
