import java.util.Scanner;
public class Main {
    static Scanner scn = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
        String usrExp = scn.nextLine().replaceAll("\\s+","");
        System.out.println("Результат:" + calc(usrExp));
    }
    public static String calc (String input) throws Exception {
        int operand0, operand1, result;
        char typeOperands = 'a';
        String[] operands = input.split("[+-/*]");
        if (operands.length != 2) {
            throw new Exception ("Ошибка: неправильная запись выражения. Завершение работы...");
        }
        char operator = input.charAt(operands[0].length());
        try {
            operand0 = Integer.parseInt(operands[0]);
            operand1 = Integer.parseInt(operands[1]);
        } catch (Exception e) {
           try {
                operand0 = Romans.valueOf(operands[0]).ordinal();
                operand1 = Romans.valueOf(operands[1]).ordinal();
                typeOperands = 'r';
            }
        catch (Exception e2) {
                throw new Exception("Ошибка: Операнды в разных системах исчисления или неверная запись операндов. Завершение работы...");
            }
       }
        if ((operand0<0||operand0>11) && (operand1<0||operand1>11)) {
            throw new Exception ("Ошибка: Один или оба операнда вне диапазона допустимых значения. Завершение работы...");
        } else {
            result = calcInt(operand0, operand1, operator);
            if (typeOperands == 'r' & result <= 0) {typeOperands = 'e';}
            return switch (typeOperands) {
                case 'a' -> " " + result;
                case 'r' -> " " + Romans.values()[result];
                default -> throw new Exception ("Ошибка: Результат находится в области отрицательных чисел. В римской системе исчисления отрицательные числа отсутствуют. Завершение работы...");
                };
        }
    }
    static int calcInt(int oprnd0, int oprnd1, char oprtr) {
        return switch (oprtr) {
            case '+' -> oprnd0 + oprnd1;
            case '-' -> oprnd0 - oprnd1;
            case '*' -> oprnd0 * oprnd1;
            case '/' -> oprnd0 / oprnd1;
            default -> throw new IllegalStateException("Результат не определён в коде: " + oprtr);
        };
    }
}
