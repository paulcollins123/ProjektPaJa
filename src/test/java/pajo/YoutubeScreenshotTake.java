package pajo;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.*;

public class YoutubeScreenshotTake {

    private SelenideElement dismissLoginButton = $x("//ytd-popup-container//div[@id='dismiss-button']//paper-button[@id='button']");
    private SelenideElement playerContainer = $x("//*[@id='player-container-inner']");

    @Test
    void makeScreenShotsFromYoutube() throws InterruptedException, IOException, FindFailed {
        Configuration.browserSize = "1500x960";
//        Configuration.clickViaJs = true;
        open("https://www.youtube.com");

        TimeUnit.SECONDS.sleep(5);
        dismissLoginButton.click();
        TimeUnit.SECONDS.sleep(2);
        acceptYoutubeCookies();
        TimeUnit.SECONDS.sleep(2);

        VideoInfo[] urlAndDuration = new VideoInfo[8];
        for (int i = 0; i < 8; i++) {
            urlAndDuration[i] = new VideoInfo(getUrlOfVideo(i), getDurationOfVideo(i));
        }

        for (VideoInfo videoInfo : urlAndDuration) {

            //Open url of video in expected time
            open(videoInfo.url + "&t=0m" + videoInfo.length + "s");

            //Handle ads before video
            waitForAds();

            //Pause video
            playerContainer.click();
            TimeUnit.MILLISECONDS.sleep(2000);

            //Take a screenshot of video
            takeScreenshotOfElement(playerContainer, "screenshot");
        }
    }

    private void closePopUpAds() throws FindFailed {
        boolean isPresent = $x("//button[@class='ytp-ad-overlay-close-button']").exists();
        if (isPresent){
            WebDriverRunner.getWebDriver().switchTo().frame($x("//button[@class='ytp-ad-overlay-close-button']"));

            $x("//button[@class='ytp-ad-overlay-close-button']")
                    .waitUntil(Condition.visible,10000).click();
            Screen screen = new Screen();
            Pattern image = new Pattern("C:\\Users\\pavol\\Desktop\\xx.png");
            Pattern image2 = new Pattern("C:\\Users\\pavol\\Desktop\\x.png");


///            try {
//                screen.click(image);
//                screen.click(image2);
//
//            }
//            catch(Exception e) {
//                //  Block of code to handle errors
//            }


            System.out.println("clik");
        }
    }

    private void takeScreenshotOfElement(SelenideElement element, String nameOfScreenshot) throws IOException {
        File screen = ((TakesScreenshot) WebDriverRunner.getWebDriver()).getScreenshotAs(OutputType.FILE);
        Point p = element.getLocation();

        int width = element.getSize().getWidth();
        int height = element.getSize().getHeight();

        BufferedImage img = ImageIO.read(screen);

        BufferedImage dest = img.getSubimage(p.getX(), p.getY(), width,
                height);

        ImageIO.write(dest, "png", screen);

        String localTime = LocalTime.now().toString().replaceAll("[:.]", "_");

        FileUtils.copyFile(screen, new File("C://tmpsel//" + nameOfScreenshot.concat(localTime) + ".png"));
    }

    private void waitForAds() {
        boolean adShowing = $x("//*[@id='movie_player']").getAttribute("class").contains("ad-showing");
        if (adShowing) {
            $x("//div[contains(@class, 'ad-showing')]").waitUntil(Condition.not(Condition.exist), 60000);
        }
    }

    private void acceptYoutubeCookies() {
        Cookie cookie = new Cookie("CONSENT", "YES+SK.sk+V14+BX");
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        refresh();
    }

    private static String getUrlOfVideo(int i) {

        String urlOfVideo = $x("//div[@id='contents']/ytd-rich-item-renderer[" + (i + 1) + "]//a[@id='video-title-link']")
                .getAttribute("href");
        return urlOfVideo;
    }

    private static int getDurationOfVideo(int i) {
        int timeToTakeScreenshotAt = 30;

        int durationSeconds = Integer.valueOf($x("//div[@id='contents']/ytd-rich-item-renderer[" + (i + 1) + "]//ytd-thumbnail-overlay-time-status-renderer")
                .getText().trim().replaceAll("[^:]*:", ""));
        int durationOther = Integer.valueOf($x("//div[@id='contents']/ytd-rich-item-renderer[" + (i + 1) + "]//ytd-thumbnail-overlay-time-status-renderer")
                .getText().trim().replaceAll("([^:]*$)|[:]", ""));

        if ((durationSeconds < 30) & (durationOther == 0)) {
            timeToTakeScreenshotAt = durationSeconds / 2;
        }
//        System.out.println(i + " "+ durationOther +" "+durationSeconds +" " + timeToTakeScreenshotAt);
        return timeToTakeScreenshotAt;
    }

}
//([^:]*$)|[:]      oznaci vsetko okrem cisla minut a hodin
// [^:]*$           oznaci cisla sekund
// [^:]*:           oznaci vsetko okrem sekund
//div[contains(@class, 'ad-showing')]
//div[@id='container']/div[contains(@class, 'ad-showing')]

//
//    open("https://www.youtube.com/");
//    open("https://www.youtube.com/watch?v=F0sHrEAdBZU");

//        boolean adShowing = $x("//*[@id='movie_player']").getAttribute("class").contains("ad-showing");
//        System.out.println(adShowing);

//    String inClass = "nothing";
//    String before  = "Not";
//
//        for (int i = 0; i < 10000; i++) {
//        inClass = ($x("//*[@id='movie_player']").getAttribute("class"));
//
//        if (!inClass.equals(before)) {
//        System.out.println(inClass);
//        before=inClass;
//        }
//        }
//        }



// /html/body/ytd-app/div[@id='content']/ytd-page-manager[@id='page-manager']/ytd-watch-flexy[@class='style-scope ytd-page-manager hide-skeleton']/div[@id='columns']/div[@id='primary']/div[@id='primary-inner']/div[@id='player']/div[@id='player-container-outer']/div[@id='player-container-inner']/div[@id='player-container']/ytd-player[@id='ytd-player']/div[@id='container']/div[@id='movie_player']/div[@class='video-ads ytp-ad-module']/div[@id='invideo-overlay:6o']/div[@class='ytp-ad-overlay-container']/div[@class='ytp-ad-text-overlay']/div[@class='ytp-ad-overlay-close-container']/button[@class='ytp-ad-overlay-close-button']