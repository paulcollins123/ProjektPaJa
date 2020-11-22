package test;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;

import java.nio.file.OpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.codeborne.selenide.Selenide.*;


public class PictureDimensions {


    @Test
    void test() {

        //open("https://www.google.com/");
        open("https://www.mtbiker.sk/");

        ElementsCollection pictureElements = $$x("//body//img");


        pictureElements
                .stream()
                .forEach(e-> System.out.println(e.getSize()));

        List<Integer> collect = pictureElements
                .stream()
                .map(e -> e.getSize().getHeight())
                .collect(Collectors.toList());

        System.out.println(collect);


//        int width=$(By.xpath("//*[@id=\"hplogo\"]")).getSize().getWidth();
//        int height=$(By.xpath("//*[@id=\"hplogo\"]")).getSize().getHeight();
//        System.out.println(width +">>>"+height);
    }
}
