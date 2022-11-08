import java.io.IOException;
import java.util.Scanner;

public class Main {
    static Scanner scn = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        String usrExp = scn.nextLine().replaceAll("\\s+","");
        System.out.println(calc(usrExp));
    }

    public static String calc (String input) throws IOException {
        int operand0, operand1, result=0;
        boolean arab = true;
        String resultStr;

        String[] operands = input.split("[+-/*]");
        if (operands.length != 2) {
            throw new IOException ("Ошибка: неправильная запись выражения. Завершение работы...");
        }
        char operator = input.charAt(operands[0].length());
        try {
            operand0 = Integer.parseInt(operands[0]);
            operand1 = Integer.parseInt(operands[1]);
        } catch (IllegalArgumentException e) {
            try {
                operand0 = Romans.valueOf(operands[0]).ordinal();
                operand1 = Romans.valueOf(operands[1]).ordinal();
                arab = false;
            } catch (IllegalArgumentException e2) {
                throw new IOException("Ошибка: Операнды в разных системах исчисления или неверная запись операндов. Завершение работы...");
            }

        }
        if (operand0>0&operand0<11&operand1>0&operand1<11) {
            result = calcInt(operand0, operand1, operator);
        } else {
            throw new IOException ("Ошибка: Один или оба операнда вне диапазона допустимых значения. Завершение работы...");
        }
        if (arab==true){
            resultStr = result+"";
        } else {
            try {
                resultStr = Romans.values()[result]+"";
            }
            catch (ArrayIndexOutOfBoundsException e) {
                throw new IOException ("Ошибка: Результат находится в области отрицательных чисел. В римской системе исчисления отрицательные числа отсутствуют. Завершение работы...");
            }
        }
        return resultStr;
    }

    static int calcInt(int oprnd0, int oprnd1, char oprtr) {
        int rslt = 0;
        switch (oprtr) {
            case '+':
                rslt = oprnd0 + oprnd1;
                break;
            case '-':
                rslt = oprnd0 - oprnd1;
                break;
            case '*':
                rslt = oprnd0 * oprnd1;
                break;
            case '/':
                rslt = oprnd0 / oprnd1;
                break;
        }
        return rslt;
    }
}