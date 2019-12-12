package be.kuleuven.sessylibrary.scenarios;

import be.kuleuven.sessylibrary.BaseScenarioTestCase;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class FindingBooksScenarioTests extends BaseScenarioTestCase {

    private WebElement getHeader() {
        return driver().findElement(By.tagName("h3"));
    }

    private void assertOnZoekenPagina() {
        var zoeken = getHeader();
        assertThat(zoeken.getText(), is(" Zoeken"));
    }

    private void searchForBook(String title) {
        var zoekenInput = driver().findElement(By.id("searchterm"));
        zoekenInput.sendKeys(title);
        zoekenInput.sendKeys(Keys.ENTER);
    }

    private void assertBooksFound() {
        var searchResults = driver().findElement(By.className("resultcontainer"));
        waitUntilText(searchResults, "Bread");
        assertThat(searchResults.getText(), containsString("Bread: A Baker's Book"));
    }

    private void goToFirstBookDetail() {
        var naarDetail = driver().findElement(By.className("resultcontainer")).findElement(By.className("btn-primary"));
        naarDetail.click();
        waitFor(By.className("boekdetail"));
    }

    private void assertOnBoekDetailPagina() {
        var boekDetailTitle = getHeader();
        assertThat(boekDetailTitle.getText(), containsString("Boek detail:"));
    }

    private void assertBoekDetailTitelContains(String titel) {
        var boekDetailContainer = driver().findElement(By.className("boekdetail"));
        assertThat(boekDetailContainer.getText(), containsString(titel));
    }

    @Test
    public void searchesForBook_ClicksOnDetail() {
        driver().get("http://localhost:8080");

        assertOnZoekenPagina();
        searchForBook("bread");
        assertBooksFound();

        goToFirstBookDetail();
        assertOnBoekDetailPagina();
        assertBoekDetailTitelContains("Bread: A Baker's Book");
    }
}
