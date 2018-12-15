package expression;

public class Minus extends UnaryOperation {
    public Minus(CommonExpression expression) {
        super(expression);
    }

    @Override
    int operation(int value) {
        return -value;
    }

    @Override
    double operation(double value) {
        return -value;
    }
}
