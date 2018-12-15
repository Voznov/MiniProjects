package expression.parser;

import expression.*;
import expression.Parser;

public class ExpressionParser implements Parser {
    private final char[] opSymbols = {'+', '-', '*', '/'};

    private boolean correctBracketSequence(String s) {
        int leftBrackets = 0, rightBrackets = 0;
        for (char c : s.toCharArray()) {
            switch (c) {
                case '(':
                    ++leftBrackets;
                    break;
                case ')':
                    ++rightBrackets;
                    if (leftBrackets < rightBrackets) {
                        return false;
                    }
            }
        }
        if (leftBrackets != rightBrackets) {
            return false;
        }
        return true;
    }

    @Override
    public CommonExpression parse(String expression) {
        StringBuilder stringBuilder = new StringBuilder();
        for (char c : expression.toCharArray()) {
            if (!Character.isWhitespace(c)) {
                stringBuilder.append(c);
            }
        }
        if (!correctBracketSequence(expression)) {
            throw new IllegalArgumentException("Incorrect bracket sequence");
        }
        String s = stringBuilder.toString();
        return parse(s, 0, s.length());
    }

    private CommonExpression parse(String expression, int left, int right) {
        if (left == right) {
            return new Const(0);
        }
        if (expression.charAt(left) == '(' && expression.charAt(right - 1) == ')' && correctBracketSequence(expression.substring(left + 1, right - 1))) {
            return parse(expression, left + 1, right - 1);
        }
        int leftBrackets = 0, rightBrackets = 0;
        for (int i = right - 1; i > left; --i) {
            switch (expression.charAt(i)) {
                case '(':
                    ++leftBrackets;
                    break;
                case ')':
                    ++rightBrackets;
                    break;
                case '<':
                    if ((leftBrackets == rightBrackets) && (expression.charAt(i - 1) == '<')) {
                        return new ShiftLeft(parse(expression, left, i - 1), parse(expression, i + 1, right));
                    }
                case '>':
                    if ((leftBrackets == rightBrackets) && (expression.charAt(i - 1) == '>')) {
                        return new ShiftRight(parse(expression, left, i - 1), parse(expression, i + 1, right));
                    }
            }
        }
        leftBrackets = 0; rightBrackets = 0;
        for (int i = right - 1; i > left; --i) {
            switch (expression.charAt(i)) {
                case '(':
                    ++leftBrackets;
                    break;
                case ')':
                    ++rightBrackets;
                    break;
            }
            boolean isContinue = false;
            for (char opSymbol : opSymbols) {
                if (expression.charAt(i - 1) == opSymbol) {
                    isContinue = true;
                    break;
                }
            }
            if (isContinue) {
                continue;
            }
            switch (expression.charAt(i)) {
                case '+':
                    if (leftBrackets == rightBrackets) {
                        return new Add(parse(expression, left, i), parse(expression, i + 1, right));
                    }
                case '-':
                    if (leftBrackets == rightBrackets) {
                        return new Subtract(parse(expression, left, i), parse(expression, i + 1, right));
                    }
            }
        }
        leftBrackets = 0; rightBrackets = 0;
        for (int i = right - 1; i > left; --i) {
            switch (expression.charAt(i)) {
                case '(':
                    ++leftBrackets;
                    break;
                case ')':
                    ++rightBrackets;
                    break;
                case '*':
                    if (leftBrackets == rightBrackets) {
                        return new Multiply(parse(expression, left, i), parse(expression, i + 1, right));
                    }
                case '/':
                    if (leftBrackets == rightBrackets) {
                        return new Divide(parse(expression, left, i), parse(expression, i + 1, right));
                    }
            }
        }
        switch (expression.charAt(left)) {
            case '+':
                return new Plus(parse(expression, left + 1, right));
            case '-':
                return new Minus(parse(expression, left + 1, right));
        }
        String resultString = expression.substring(left, right);
        if (Character.isDigit(expression.charAt(left))) {
            int result = (int) Long.parseLong(resultString);
            return new Const(result);
        } else {
            return new Variable(resultString);
        }
    }
}
