package expression;

public abstract class BinaryOperation implements CommonExpression {
    private CommonExpression left;
    private CommonExpression right;

    BinaryOperation(CommonExpression left, CommonExpression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate(int x) {
        return operation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return operation(left.evaluate(x), right.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(left.evaluate(x, y, z), right.evaluate(x, y, z));
    }

    abstract int operation(int left, int right);

    abstract double operation(double left, double right);
}
