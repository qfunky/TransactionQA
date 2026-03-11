import com.microsoft.playwright.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class UiE2eTest {

    static Playwright playwright;
    static Browser browser;
    BrowserContext context;
    Page page;

    @BeforeAll
    static void launchBrowser() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch();
    }

    @AfterAll
    static void closeBrowser() {
        playwright.close();
    }

    @BeforeEach
    void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }

    @Test
    void testLoginAndCheckBalance() {
        page.navigate("https://parabank.parasoft.com/parabank/index.htm");

        page.locator("input[name='username']").fill("john");
        page.locator("input[name='password']").fill("demo");

        page.locator("input[value='Log In']").click();

        page.locator("h1.title:has-text('Accounts Overview')").waitFor();
    }
}