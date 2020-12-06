package pajo;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class MtbikerAveragePriceOfBikesDone {

    @Test
    void test() {
        ArrayList<String> usedBikeElementsStrings = new ArrayList<String>();
        SelenideElement nextPage = $x("//div[@class='btn-toolbar pagination']//a/i[@class='fa fa-chevron-right']");
        open("https://www.mtbiker.sk/bazar/bicykle/horske-bicykle/pevne-a-hardtail");
        int lastPage = Integer.valueOf($x("(//div[@class='btn-toolbar pagination']//a[@class='btn'])[last()-1]").getText());

        ElementsCollection usedBikeElements;

        for (int i = 0; i < lastPage; i++) {
            usedBikeElements = $$x("//div[(contains(@class,'bazar_row')) and not(contains(@class,'bazarAdRow'))]");
            for (SelenideElement usedBikeElement : usedBikeElements) {
                usedBikeElementsStrings.add(usedBikeElement.find(By.xpath("./p/strong")).getText());
            }
            if ((nextPage.isDisplayed())){
                nextPage.scrollIntoView(true).click();
            }
        }

//        for (String element : usedBikeElementsStrings) {
//            System.out.println(element);
//        }

        Integer sum = usedBikeElementsStrings
                .stream()
                .map(s->s.replaceAll("(€)$","").replace(".",""))
                .map(Integer::valueOf)
                .reduce(0, Integer::sum);


        double average = usedBikeElementsStrings
                .stream()
                .map(s -> s.replaceAll("(€)$", "").replace(".", ""))
                .map(Integer::valueOf)
                .mapToDouble(a -> a)
                .average()
                .getAsDouble();

      System.out.println("All together: " + sum + "€");
      System.out.println("Average price: " + String.format("%.2f", average) + "€");

//
//        AtomicInteger count = new AtomicInteger();
//        Stream<Double> doubles = Stream.of(1.11, 2.22, 3.33);
//        Double suma = doubles.reduce(0.0, (x,y) -> {count.incrementAndGet(); return x+y;});
//        System.out.println(suma/count.get());
    }
}
