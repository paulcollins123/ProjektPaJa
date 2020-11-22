package test;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class ytb {

    SelenideElement dismissLoginButton = $x("//ytd-popup-container//div[@id='dismiss-button']//paper-button[@id='button']");
    SelenideElement playerContainer = $x("//*[@id='player-container-inner']");
    @Test
    void ytbtm() throws InterruptedException {

        open("https://www.youtube.com");
        acceptYoutubeCookies();

        TimeUnit.SECONDS.sleep(2);
        dismissLoginButton.click();

        $x("//div[@id='contents']/ytd-rich-item-renderer[1]").click();

        System.out.println($x("//span[@class='ytp-ad-simple-ad-badge']").getText());
        $x("//span[@class='ytp-ad-simple-ad-badge']")
                .waitUntil(Condition.not(Condition.exist),1000000);

        TimeUnit.SECONDS.sleep(2);
        System.out.println($x("//span[@class='ytp-ad-simple-ad-badge']").getText());

        $x("//span[@class='ytp-ad-simple-ad-badge']")
                .waitUntil(Condition.not(Condition.exist),1000000);

        TimeUnit.SECONDS.sleep(2);


        System.out.println(" mmmm ");

        String currentTime = $x("//span[@class='ytp-time-current']").getText();
        String durationTime = $x("//span[@class='ytp-time-duration']").getText();

        System.out.println(currentTime + " / " + durationTime);
        TimeUnit.SECONDS.sleep(8);
        playerContainer.click();
        currentTime = $x("//span[@class='ytp-time-current']").getText();
        durationTime = $x("//span[@class='ytp-time-duration']").getText();
        System.out.println(currentTime + " / " + durationTime);
        TimeUnit.SECONDS.sleep(150);
//div[@class='video-ads ytp-ad-module']
        //div[@class='ytp-ad-player-overlay']

    }

    private void acceptYoutubeCookies() {
        Cookie cookie = new Cookie("CONSENT", "YES+SK.sk+V14+BX");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
    }
}
