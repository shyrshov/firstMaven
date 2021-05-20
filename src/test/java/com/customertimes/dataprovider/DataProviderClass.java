package com.customertimes.dataprovider;

import org.testng.annotations.DataProvider;

import org.testng.annotations.DataProvider;

public class DataProviderClass
{
    @DataProvider(name = "data-provider")
    public static Object[][] dataProviderMethod()
    {
        return new Object[][] { {1}, {5}, {10}};
    }
}