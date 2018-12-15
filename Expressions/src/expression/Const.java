package expression;

public class Const implements CommonExpression {
    private Number _const;

    public Const(Number x) {
        _const = x;
    }

    @Override
    public int evaluate(int x) {
        return _const.intValue();
    }

    @Override
    public double evaluate(double x) {
        return _const.doubleValue();
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return _const.intValue();
    }
}
