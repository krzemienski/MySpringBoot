package hello;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;

public class QuoteControllerIT extends BaseIntegrationWithTestConfiguration {
    @Autowired
    @org.springframework.beans.factory.annotation.Value("${quoteEndpointStringFormat}")
    public String quoteEndpointStringFormat;

    @Test
    public void getQuote_shouldReturnQuoteDetails_fromThirdPartyRestService() {
        Quote result = restTemplate.getForObject(quoteEndpointStringFormat, Quote.class);
        assertEquals("success", result.getType());
        assertEquals("Working with Spring Boot is like pair-programming with the Spring developers.", result.getValue().getQuote());
        assertEquals(new Long(1L), result.getValue().getId());
    }
}
