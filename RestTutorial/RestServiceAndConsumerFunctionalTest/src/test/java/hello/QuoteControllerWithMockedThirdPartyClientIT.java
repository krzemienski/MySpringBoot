package hello;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class QuoteControllerWithMockedThirdPartyClientIT extends BaseIntegration {
    public static final String quoteEndpointStringFormat = "http://localhost:%s/quote";

    @Autowired
    @Qualifier("myThirdPartyRestQuoteClientImpl")
    ThirdPartyRestQuoteClient mockThirdPartyRestQuoteClient;

    private String getQuoteEndpointString() {
        return String.format(quoteEndpointStringFormat, port);
    }

    @Test
    public void getQuote_shouldReturnQuoteDetails_fromThirdPartyRestService() {
        Quote expectedQuoteFromThirdPartyService = QuoteBuilder.newBuilder().setType("some type")
                .setValue(ValueBuilder.newBuilder().setId(2L).setQuote("some quote"))
                .build();
        when(mockThirdPartyRestQuoteClient.getQuote()).thenReturn(expectedQuoteFromThirdPartyService);

        Quote result = restTemplate.getForObject(getQuoteEndpointString(), Quote.class);
        assertEquals("some type", result.getType());
        assertEquals("some quote", result.getValue().getQuote());
        assertEquals(new Long(2L), result.getValue().getId());
    }
}
