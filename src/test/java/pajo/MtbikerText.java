package pajo;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class MtbikerText {

    @Test
    void numberOfLetters() {

        open("https://www.mtbiker.sk/");
        int numberOfLetters = $x("//body")
                .getText()
                .replaceAll("[^a-zA-ZáéíĺóŕúýčďľňšťžäôÁÉÍĹÓŔÚÝČĎĽŇŠŤŽÄÔ]","")
                .length();

        System.out.println("Number of letters: " + numberOfLetters);
    }
}
//[^a-zA-áéíĺóŕúýčďľňšťžäôÁÉÍĹÓŔÚÝČĎĽŇŠŤŽÄÔ