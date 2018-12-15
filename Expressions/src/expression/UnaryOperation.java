package expression;

public abstract class UnaryOperation implements CommonExpression {
    private CommonExpression expression;

    UnaryOperation(CommonExpression expression) {
        this.expression = expression;
    }

    @Override
    public int evaluate(int x) {
        return operation(expression.evaluate(x));
    }

    @Override
    public double evaluate(double x) {
        return operation(expression.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return operation(expression.evaluate(x, y, z));
    }

    abstract int operation(int expression);

    abstract double operation(double expression);
}
