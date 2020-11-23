package test;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import static com.codeborne.selenide.Selenide.*;

public class ScreenShotOfElement {

    @Test
    void test() throws IOException {
        Configuration.startMaximized = true;
        open("https://www.mtbiker.sk/");

        SelenideElement elementToScreenShot = $x("//*[@id='list_video']/div[2]/a/img");

        takeScreenshotOfElement(elementToScreenShot,"menoObrazku");

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

        String localTime = LocalTime.now().toString().replaceAll("[:.]","_");

        FileUtils.copyFile(screen, new File("C://tmpsel//"+nameOfScreenshot.concat(localTime)+".png"));
    }
}
