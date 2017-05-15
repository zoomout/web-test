package some.host.here;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import some.host.here.keywords.WebPageKeywords;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class SearchTest extends BaseTest {

    @Test(dataProvider = "testData")
    public void searchTest(final String text) {
        final WebPageKeywords webPageKeywords = webPageKeywordsStore.get();
        webPageKeywords.openWebPage("http://google.com");
        webPageKeywords.searchFor(text);
        String resultsStats = webPageKeywords.getResultsStats();
        assertThat(resultsStats, containsString(".000"));
    }

    @Test()
    public void anotherTest() {
        final WebPageKeywords webPageKeywords = webPageKeywordsStore.get();
        webPageKeywords.openWebPage("http://google.com");
        webPageKeywords.searchFor("something");
        String resultsStats = webPageKeywords.getResultsStats();
        assertThat(resultsStats, containsString(".000"));

    }

    @DataProvider()
    public Object[][] testData() {
        return new Object[][]{
                {"apple"},
                {"pear"},
        };
    }

}
