package test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;

public class Calendar {
    @Test
    void calendar() {
        open("https://www.mtbiker.sk/kalendar");
        ElementsCollection calendarElements = $$x("/html/body/div[6]/div/div/div/div/table/tbody/tr");

        for (int i = 1; i < 10; i++) {

            $x("//td[@id='kalendar_month_"+i+"']").scrollIntoView(true).click();
        }

        for (SelenideElement calendarElement : calendarElements) {
            if (calendarElement.find(By.xpath("./td[2]")).exists()){
                System.out.print(calendarElement.find(By.xpath("./td[2]")).getText()+ " ");
                System.out.print(calendarElement.find(By.xpath("./td[3]/a/strong")).getText()+ " ");
                System.out.println(calendarElement.find(By.xpath("./td[4]")).getText());
            }
        }
    }
}
