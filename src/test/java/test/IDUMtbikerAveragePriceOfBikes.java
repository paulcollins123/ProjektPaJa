package test;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.$$x;
import static com.codeborne.selenide.Selenide.open;

public class IDUMtbikerAveragePriceOfBikes {

    @Test
    void test() {
        ArrayList<SelenideElement> usedBikeElements = new ArrayList<SelenideElement>();

        open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=1");
        usedBikeElements.addAll($$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]"));
        open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=2");
        usedBikeElements.addAll($$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]"));

       open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=3");
//        usedBikeElements.addAll(Selenide.$$(By.xpath("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")));



        System.out.println("...");
        System.out.println("...");
//        for (SelenideElement element : usedBikeElements) {
//            System.out.println(element.find(By.xpath("./p/strong")).getText());
//        }
    }
}
