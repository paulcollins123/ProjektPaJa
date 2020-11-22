package test;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.open;


public class MtbikerText {

    @Test
    void test() {

        open("https://www.mtbiker.sk/");
        String text = $x("//body").getText().replace("\n"," ");
        String str[] = text.split(" ");


        System.out.println(text);
        System.out.println(str);

        List<String> al = new ArrayList<String>();
        al = Arrays.asList(str);

        for (String s : al) {
            System.out.println(s);
        }


    }

    @Test
    void name() {

        open("https://www.mtbiker.sk/");
        String text = $x("//body").getText().replaceAll("[^a-zA-áéíĺóŕúýčďľňšťžäôÁÉÍĹÓŔÚÝČĎĽŇŠŤŽÄÔ]","");
        int numberOfLetters = text.length();
        System.out.println(numberOfLetters);



        System.out.println(text);
        System.out.println("!!!!!!!!!!!!!!!!!!!");




        String str = "a string";
        String stringReplaced = str.replace(" ","");

        int length = str.length( );
        int lengthReplaced = stringReplaced.length( );

        System.out.println(length);
        System.out.println(lengthReplaced);




    }

    public static void main(String args[]) {
        String toUp = "áéíĺóŕúýčďľňšťžäô";
        System.out.println(toUp.toUpperCase());


        String num = "22,33,44,55,66,77";
        List<String> al = new ArrayList<String>();

        String str[] = num.split(",");
        al = Arrays.asList(str);
        for (String s : al) {
            System.out.println(s);
        }
    }
}
