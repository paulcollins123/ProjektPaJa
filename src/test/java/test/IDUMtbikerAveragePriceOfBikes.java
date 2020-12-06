package test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.OptionalDouble;

import static com.codeborne.selenide.Selenide.*;

public class IDUMtbikerAveragePriceOfBikes {

    @Test
    void test() {
        ArrayList<SelenideElement> usedBikeElements = new ArrayList<>();

        open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=1");
        usedBikeElements.addAll($$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]"));


        open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=2");
        usedBikeElements.addAll($$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]"));

       open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od=3");
//        usedBikeElements.addAll(Selenide.$$(By.xpath("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")));



        System.out.println("...");
        System.out.println("...");
    }

    @Test
    void testLambda() {
        ArrayList<Integer> usedBikeElements = new ArrayList<>();

        for (int i = 1; i < 4; i++) {
            open("https://www.mtbiker.sk/bazar/bicykle/krosove/panske?modul=bazar&od="+i+"");

            $$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]")
                    .stream()
                    .map(e->e.find(By.xpath("./p/strong")).getText())
                    .map(s->s.replaceAll("(€)$","").replace(".",""))
                    .map(Integer::valueOf)
                    .forEach(integer->usedBikeElements.add(integer));
        }

        OptionalDouble average = usedBikeElements.stream().mapToDouble(a -> a).average();
        System.out.println(average);
    }
}
