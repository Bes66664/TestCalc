import java.util.Scanner;

class Calculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите арифметическое выражение:");
        String input = scanner.nextLine();

        try {
            String result = calc(input);
            System.out.println("Результат: " + result);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }

    public static String calc(String input) throws Exception {
        String[] tokens = input.split(" "); // ?

        if (tokens.length != 3) {
            throw new Exception("Необходимо использовать \"пробел\"");
        }

        String operator = tokens[1];
        String num1 = tokens[0];
        String num2 = tokens[2];

        boolean isRomanNum = isRoman(num1) && isRoman(num2);
        boolean isArabicNum = isArabic(num1) && isArabic(num2);

        if (!isRomanNum && !isArabicNum) {
            throw new Exception("Используются разные системы счисления");
        }
        int intNum1 = 0;
        int intNum2 = 0;
        if (isRomanNum) {
             intNum1 = romanToInt(num1);
             intNum2 = romanToInt(num2);
        } else {
             intNum1 = Integer.parseInt(num1);
             intNum2 = Integer.parseInt(num2);
        }

        if ((intNum1 < 1 || intNum1 > 10) || (intNum2 < 1 || intNum2 > 10)) {
            throw new Exception("Числа должны быть от 1 до 10 включительно");
        }

        int result;
        switch (operator) {
            case "+":
                result = intNum1 + intNum2;
                break;
            case "-":
                result = intNum1 - intNum2;
                break;
            case "*":
                result = intNum1 * intNum2;
                break;
            case "/":
                result = intNum1 / intNum2;
                break;
            default:
                throw new Exception("Неподдерживаемая арифметическая операция");
        }

        if (isRomanNum) {
            if (result <= 0) {
                throw new Exception("Результат должен быть положительным числом");
            }
            return intToRoman (result);
        } else {
            return String.valueOf(result);
        }
    }

    private static boolean isRoman(String str) {
        return str.matches("[IVXLC]+");
    }

    private static boolean isArabic(String str) {
        return str.matches("[0-9]+");
    }

    static int romanToInt(String s) { //Преобразование из римских в арабские
        int sum = 0;
        for (char i : s.toCharArray()) {
            if (i == 'I') {
                if (i == 'V') {
                    sum += 4;
                    continue;
                } else if (i == 'X') {
                    sum += 9;
                    continue;
                } else
                    sum += 1;
            } else if (i == 'V')
                sum += 5;
            else if (i == 'X') {
                if (i == 'L') {
                    sum += 40;
                    continue;
                } else if (i == 'C') {
                    sum += 90;
                    continue;
                } else
                    sum += 10;
            } else if (i == 'L')
                sum += 50;
        }
        return sum;
    }

    private static String intToRoman (int numArabian) { //Преобразование из арабских в римские.
        String[] roman = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};
        final String s = roman[numArabian];
        return s;
    }
}

