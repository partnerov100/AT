package utils;

import io.qameta.allure.Step;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

public class CustomAssert {

    private static final Logger logger = LoggerFactory.getLogger(CustomAssert.class.getName());

    @Step("Compare the actual value of {0} with the expected: {0} | {1}")
    public static <T, E> void assertEquals(T act, E exp ) {
        try {
            Assert.assertEquals(act, exp);
            logger.info(act +" is equal "+ exp);
        } catch (AssertionError e) {
            logger.trace("Not equal");
            throw e;
        }
    }
}
