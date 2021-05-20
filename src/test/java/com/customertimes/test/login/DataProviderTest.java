package com.customertimes.test.login;

import com.customertimes.dataprovider.DataProviderClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class DataProviderTest {

    @Test(dataProvider = "data-provider", dataProviderClass = DataProviderClass.class)
    public void testGeneratedValues(int data) {
        SoftAssert softAssert = new SoftAssert();
        Random rand = new Random();
        int random = rand.nextInt(10);
        softAssert.assertEquals(random, data, "generated value is not expected value");
        softAssert.assertAll();
    }

}
