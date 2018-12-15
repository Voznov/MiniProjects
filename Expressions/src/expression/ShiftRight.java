package expression;

public class ShiftRight extends BinaryOperation {
    public ShiftRight(CommonExpression left, CommonExpression right) {
        super(left, right);
    }

    @Override
    int operation(int left, int right) {
        return left >> right;
    }

    @Override
    double operation(double left, double right) {
        return (int) left >> (int) right;
    }
}
