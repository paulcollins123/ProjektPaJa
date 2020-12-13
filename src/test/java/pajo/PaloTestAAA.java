package pajo;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import test.PaloTest;

import java.sql.SQLOutput;
import java.util.ArrayList;

public class PaloTestAAA {
    @Test
    void name() {

        System.out.println("Palo");

        try {
            int[] myNumbers = {1, 2, 3};
            System.out.println(myNumbers[10]);
        } catch (Exception e) {
            System.out.println("Something went wrong.");
            Assert.fail("No Agreement exist");
        }
    }






}
