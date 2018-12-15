package expression;

public class Multiply extends BinaryOperation {
    public Multiply(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    int operation(int left, int right) {
        return left * right;
    }

    @Override
    double operation(double left, double right) {
        return left * right;
    }
}
