package test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class MtbikerAveragePriceOfBikes {

    @Test
    void test() {
        ArrayList<SelenideElement> usedBikeElements = new ArrayList<SelenideElement>();
        SelenideElement nextPage = $x("//div[@class='btn-toolbar pagination']//a/i[@class='fa fa-chevron-right']");

        open("https://www.mtbiker.sk/bazar/bicykle/mestske");

        usedBikeElements.addAll(Selenide.$$(By.xpath("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")));

        for (int i = 0; i < 3; i++) {
            nextPage.scrollIntoView(true).click();

            usedBikeElements.addAll(Selenide.$$(By.xpath("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")));
        }

//        while (nextPage.isDisplayed()){
//            usedBikeElements.addAll(Selenide.$$(By.xpath("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")));
//            nextPage.scrollIntoView(true).click();
//        }

        System.out.println(".......................");
        for (SelenideElement element : usedBikeElements) {
            System.out.println(element.find(By.xpath("./p/strong")).getText());

        }
        System.out.println("end");
    }
}
