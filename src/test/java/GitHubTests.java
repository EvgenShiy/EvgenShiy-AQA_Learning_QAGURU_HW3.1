import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
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

        $(".markdown-body").shouldHave(text("Soft assertions"));

        $(byText("Soft assertions")).click();

        $(".markdown-body").shouldHave(text("""
                @ExtendWith({SoftAssertsExtension.class})
                class Tests {
                  @Test
                  void test() {
                    Configuration.assertionMode = SOFT;
                    open("page.html");
                
                    $("#first").should(visible).click();
                    $("#second").should(visible).click();
                  }
                }
                """));
    }
}