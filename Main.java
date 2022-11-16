package src;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ScanExc {
        System.out.println("Введите выражение, состоящее из двух натуральных чисел, в арабской или римской системе счисления" +
                " от 1 до 10 включительно и знака операции (+,-,*,/) между ними.\n" +
                "Пример: 2+2.\n" +
                "Пример: II-I.\n" +
                "Нельзя использовать арабские и римские цифры вместе!\n");
        Scanner scanner = new Scanner(System.in);
        System.out.println("Результат: "+calc(scanner.nextLine()));

    }


    public static String calc(String input) throws ScanExc {
        String result;
        int k = 0;
        String[] exp = input.split("");
        for(int i = 0; i < exp.length; i++){
            if((exp[i].equals("+") || exp[i].equals("-") || exp[i].equals("*") || exp[i].equals("/")))
            k++;
        }

        if(k!=1)
            throw new ScanExc("Некорректное выражение");

        if (isInt(exp[0]) && isInt(exp[2])) {
            result = (calcArab(Integer.parseInt(exp[0]), Integer.parseInt(exp[2]), exp[1])) + "";
        } else if (!isInt(exp[0]) && !isInt(exp[2])) {
            int x = toArab(exp[0]);
            int y = toArab(exp[2]);
            int preResult = calcArab(x, y, exp[1]);
            if (preResult <= 0) throw new ScanExc("В римской системе счисления, ответ должен быть больше 0");
            result = toRoman(preResult);
        } else throw new ScanExc("Некорректное выражение");
        return result;
    }

    public static boolean isInt(String x) {
        try {
            Integer.parseInt(x);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static int calcArab(int x, int y, String operation) throws ScanExc {
        if ((x > 10) || (x < 1) || (y > 10) || (y < 1) )
            throw new ScanExc("Некорректное выражение");

        int result;
        if (operation.equals("+")) {
            result = x + y;
        } else if (operation.equals("-")) {
            result = x - y;
        } else if (operation.equals("*")) {
            result = x * y;
        } else {
            result = x / y;
        }
        return result;
    }

    public static int toArab(String x) throws ScanExc {
        int result = 0;
        if (x.contains("XL")) {
            result += 40;
            x = x.replace("XL", "");
        }
        if (x.contains("IX")) {
            result += 9;
            x = x.replace("IX", "");
        }
        if (x.contains("IV")) {
            result += 4;
            x = x.replace("IV", "");
        }
        int j = x.length();
        for (int i = 0; i < j; i++) {
            if (x.contains("L")) {
                x = x.replaceFirst("L", "");
                result += 50;
            } else if (x.contains("X")) {
                x = x.replaceFirst("X", "");
                result += 10;
            } else if (x.contains("V")) {
                x = x.replaceFirst("V", "");
                result += 5;
            } else if (x.contains("I")) {
                x = x.replaceFirst("I", "");
                result += 1;
            }
        }
        if (!x.equals("")) throw new ScanExc("Некорректное выражение");
        return result;
    }

    public static String toRoman(int x) {
        String result = "";
        if (x == 100) {
            result += "C";
            x -= 100;
        }
        if (x >= 50) {
            if (x >= 90) {
                result += "XC";
                x -= 90;
            } else {
                result += "L";
                x -= 50;
            }
        }
        if (x >= 10) {
            if (x >= 40) {
                result += "XL";
                x -= 40;
            } else {
                int j = x / 10;
                for (int i = 0; i < j; i++) {
                    result += "X";
                    x -= 10;
                }
            }

        }
        if (x >= 5) {
            if (x == 9) {
                result += "IX";
                x -= 9;
            } else {
                result += "V";
                x -= 5;
            }
        }
        if (x == 4) {
            result += "IV";
            x -= 4;
        } else {
            for (int i = 0; i < x; i++) {
                result += "I";
            }
        }
        return result;
    }


}
