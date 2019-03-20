package com.dima;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.Assert.assertTrue;


public class YahooSearchTest {

    private WebDriver driver;
    private String url;
    private String searchPhrase;

    @Before

    public void beforeMethod(){
        driver = new ChromeDriver();
        url = "https://www.yahoo.com/";
        searchPhrase = "Jurassic Park";
        driver.get(url);
    }

    @Test

    public void testMethodYahooSearchTest () {
        driver.findElement(By.cssSelector("input[name='p']")).sendKeys(searchPhrase);
        driver.findElement(By.cssSelector("button#uh-search-button")).click();
        List<WebElement> searchResults = driver.findElements(By.cssSelector("a.ac-algo"));
        String[] splittedPhrase = searchPhrase.split("\\s+");
        //System.out.println(splittedPhrase.length);
        for (String words : splittedPhrase) {
            for (WebElement el : searchResults) {
                assertTrue(el.getText().toLowerCase().contains(words.toLowerCase()));
            }
        }
    }

        @Test

        public void testMethodYahooSearchTestSecondPage (){
            driver.get(url);
            driver.findElement(By.cssSelector("input[name='p']")).sendKeys(searchPhrase);
            driver.findElement(By.cssSelector("button#uh-search-button")).click();
            driver.findElement(By.cssSelector("a[title='Results 11-20']")).click();
            List<WebElement> searchResults2 = driver.findElements(By.cssSelector("a.ac-algo"));
            String[] splittedPhrase = searchPhrase.split("\\s+");
            //System.out.println(splittedPhrase.length);
            for (String words: splittedPhrase) {
                for (WebElement elem : searchResults2) {
                    assertTrue(elem.getText().toLowerCase().contains(words.toLowerCase()));
                }
            }

    }

    @After
    public void afterMethod () {
        driver.quit();
    }

}
