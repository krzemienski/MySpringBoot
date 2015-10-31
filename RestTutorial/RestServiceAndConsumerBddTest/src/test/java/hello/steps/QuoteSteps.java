package hello.steps;

import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import hello.Quote;
import hello.client.QuoteTestClient;
import hello.helper.TestHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class QuoteSteps extends BaseStep {
    private ResponseEntity<Quote> response;

    @Autowired
    private QuoteTestClient quoteTestClient;
    @Autowired
    private TestHelper testHelper;

    @Before
    public void setup() {
        testHelper.configureThirdPartyRestQuoteClientUrl();
    }

    @Given("^QuoteEndpoint: I make a GET request$")
    public void callQuoteEndpoint() {
        response = quoteTestClient.getQuote();
    }

    @Then("^QuoteEndpoint: I should see the type as \"(.*)\"$")
    public void assertType(String exptectedType) {
        assertEquals(exptectedType, response.getBody().getType());
    }
    @And("^QuoteEndpoint: I should see the quote as \"(.*)\"$")
    public void assertQuote(String expectedQuote) {
        assertEquals(expectedQuote, response.getBody().getValue().getQuote());
    }

    @And("^QuoteEndpoint: I should see the id as (.*)$")
    public void assertId(Long expectedId) {
        assertEquals(expectedId, response.getBody().getValue().getId());
    }

}