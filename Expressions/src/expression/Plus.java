package expression;

public class Plus extends UnaryOperation {
    public Plus(CommonExpression expression) {
        super(expression);
    }

    @Override
    int operation(int value) {
        return value;
    }

    @Override
    double operation(double value) {
        return value;
    }
}
