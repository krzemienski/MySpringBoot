package hello.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import hello.Quote;
import hello.client.QuoteTestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;

public class QuoteWithMockedThirdPartySteps extends BaseStep {
    private ResponseEntity<Quote> response;
    @Autowired
    private QuoteTestClient quoteTestClient;

    @Given("^QuoteEndpointWithMockedThirdParty: I make a GET request$")
    public void callQuoteEndpoint() {
        response = quoteTestClient.getQuote();
    }

    @Then("^QuoteEndpointWithMockedThirdParty: I should see the type as \"(.*)\"$")
    public void assertType(String exptectedType) {
        assertEquals(exptectedType, response.getBody().getType());
    }

    @And("^QuoteEndpointWithMockedThirdParty: I should see the quote as \"(.*)\"$")
    public void assertQuote(String expectedQuote) {
        assertEquals(expectedQuote, response.getBody().getValue().getQuote());
    }

    @And("^QuoteEndpointWithMockedThirdParty: I should see the id as (.*)$")
    public void assertId(Long expectedId) {
        assertEquals(expectedId, response.getBody().getValue().getId());
    }
}
