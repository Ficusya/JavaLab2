package main;

import java.util.Stack;
import java.util.Scanner;

public class ExpressionEvaluator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите арифметическое выражение: ");
        String expression = scanner.nextLine();
        scanner.close();
        double result = evaluateExpression(expression);
        System.out.println("Результат выражения: " + result);
    }
    public static double evaluateExpression(String expression) {
        // Создаем стеки для чисел и операторов
        Stack<Double> numbers = new Stack<>();
        Stack<Character> operators = new Stack<>();

        for (char ch : expression.toCharArray()) {
            if (Character.isDigit(ch)) {
                // Если символ - цифра, добавляем ее в стек чисел
                numbers.push((double) (ch - '0'));
            } else if (ch == '(') {
                // Если символ - открывающая скобка, добавляем ее в стек операторов
                operators.push(ch);
            } else if (ch == ')') {
                // Если символ - закрывающая скобка, вычисляем выражение внутри скобок
                while (operators.peek() != '(') {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char operator = operators.pop();
                    numbers.push(applyOperator(a, b, operator));
                }
                operators.pop(); // Удаляем открывающую скобку
            } else if (isOperator(ch)) {
                // Если символ - оператор, обрабатываем его с учетом приоритетов
                while (!operators.isEmpty() && hasHigherPrecedence(operators.peek(), ch)) {
                    double b = numbers.pop();
                    double a = numbers.pop();
                    char operator = operators.pop();
                    numbers.push(applyOperator(a, b, operator));
                }
                operators.push(ch);
            }
        }

        // Вычисляем оставшиеся операции
        while (!operators.isEmpty()) {
            double b = numbers.pop();
            double a = numbers.pop();
            char operator = operators.pop();
            numbers.push(applyOperator(a, b, operator));
        }

        return numbers.pop();
    }

    public static boolean isOperator(char ch) {
        return ch == '+' || ch == '-' || ch == '*' || ch == '/';
    }

    public static boolean hasHigherPrecedence(char op1, char op2) {
        return (op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-');
    }

    public static double applyOperator(double a, double b, char operator) {
        switch (operator) {
            case '+':
                return a + b;
            case '-':
                return a - b;
            case '*':
                return a * b;
            case '/':
                if (b == 0)
                    throw new ArithmeticException("Нельзя делить на ноль!");
                return a / b;
            default:
                throw new IllegalArgumentException("Недопустимый оператор: " + operator);
        }
    }

    public static double foolProofEvaluation(String expression){
        try {
            return evaluateExpression(expression);
        } catch (Exception exception) {
            return Double.NaN;
        }
    }
}