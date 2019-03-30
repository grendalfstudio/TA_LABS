package com.grendalfstudio.ta_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int x = scan.nextInt();
        if (x >= 0 && x <= 1000000) {
            System.out.println(Converter.convert(x));
        } else {
            System.out.println("ERROR: incorrect input");
        }
    }
}
class Converter {
    private static String listX[] = {"", "один", "два", "три", "чотири", "п'ять", "шість", "сім", "вісім", "дев'ять"};
    private static String list1X[] = {"десять", "одинадцять", "дванадцять", "тринадцять", "чотирнадцять", "п'ятнадцять",
            "шістнадцять", "сімнадцять", "вісімнадцять", "дев'ятнадцять"};
    private static String listXX[] = {"", "", "двадцять", "тридцять", "сорок", "п'ятдесят", "шістдесят", "сімдесят",
            "вісімдесят", "дев'яносто"};
    private static String listXXX[] = {"", "сто", "двісті", "триста", "чотириста", "п'ятсот", "шістсот", "сімсот",
            "вісімсот", "дев'ятсот"};
    private static String listXXXX[] = {"тисяч", "одна тисяча", "дві тисячі", "три тисячі", "чотири тисячі",
            "п'ять тисяч", "шість тисяч", "сім тисяч", "вісім тисяч", "дев'ять тисяч"};

    static String convert(int value) {
        String result1 = "", result2 = "";

        if (value == 0) result1 = "нуль";  // обробка 0
        if (value > 0) {
            int X = value % 10;  // цифра одиниць
            result1 = listX[X];  // обробка одиниць
        }
        if (value > 9) {
            int XX = (value / 10) % 10;  // цифра десятків
            if (XX == 1)
                result1 = list1X[value % 10];  // обробка чисел 10-19
            else if(XX > 0)
                result1 = listXX[XX] + " " + result1;  // обробка десятків
        }
        if (value > 99) {
            int XXX = (value / 100) % 10;  // цифра сотень
            if(XXX > 0)
                result1 = listXXX[XXX] + " " + result1;  // обробка сотень
        }
        if(value > 999) {
            int XXXX = (value / 1000) % 10;  // цифра тисяч
            result2 = listXXXX[XXXX];  // обробка тисяч
        }
        if(value > 9999) {
            int XXXXX = (value / 10000) % 10;  // цифра десятків тисяч
            if (XXXXX == 1)
                result2 = list1X[(value / 1000) % 10] + " тисяч";  // обробка чисел 10 000 - 19 000
            else if(XXXXX > 0)
                result2 = listXX[XXXXX] + " " + result2;  // обробка десятків тисяч
        }
        if(value > 99999) {
            int XXXXXX = (value / 100000) % 10;  // цифра сотень
            if(XXXXXX > 0)
                result2 = listXXX[XXXXXX] + " " + result2;  // обробка сотень
        }
        if(value == 1000000)result2 = "один мільйон";
        if(!result2.equals(""))
            result2 += " ";
        return result2 + result1;
    }
}