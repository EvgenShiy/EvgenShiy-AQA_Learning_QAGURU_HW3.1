import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class GitHubTests {

    @BeforeAll
    public static void setUp() {
        Configuration.browserSize = "1920x1080";  // Устанавливаем размер окна браузера
        Configuration.pageLoadStrategy = "eager";
        Configuration.holdBrowserOpen = false; // используется только для отладки тестов (установить значение true)
        Configuration.timeout = 5000; // default 4000
        Configuration.baseUrl = "https://github.com"; // устанавливаем базовый URL
    }

    @Test
    public void wikiExistsSoftAssertTest() {
        open("/selenide/selenide");

        actions().moveToElement($("#wiki-tab")).click().perform();

        $$("#wiki-body ul").findBy(text("Soft assertions")).should(exist);

        $("#wiki-body ul:nth-child(3) li:nth-child(8) a").click();

        $$("#wiki-body div:nth-child(18) h4").findBy(text("Using JUnit5 extend test class:")).should(exist);

    }
}
