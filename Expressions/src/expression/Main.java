package expression;

import expression.parser.ExpressionParser;

public class Main {
    private static int it = 1;
    private static Parser p;
    private static void SOut(String s, int x, int y, int z) {
        System.out.print(new Integer(it++).toString() + ") \"" + s + "\" = " + p.parse(s).evaluate(x, y, z) + ' ');
        System.out.println("[x = " + new Integer(x).toString() + "; y = " + new Integer(y).toString() + "; z = " + new Integer(z).toString() + "]");
    }
    public static void main(String[] args) {
        p = new ExpressionParser();
        SOut("",0, 0, 0);
        SOut("0",0, 0, 0);
        SOut("1 + 1",0, 0, 0);
        SOut("      ",0, 0, 0);
        SOut("\n\r\t\t",0, 0, 0);
        SOut("2 + 3 * 4 + 5",0, 0, 0);
        SOut("2*x+3*y-4",7, 8, 0);
        SOut("x*x*x*x*x*x",6, 0, 0);
        SOut("---+-+--+7",0, 0, 0);
        SOut("()",0, 0, 0);
        SOut("()+x",13, 0, 0);
        SOut("(((())))",0, 0, 0);
        SOut("((22)-(-(+9)))",0, 0, 0);
        SOut("x--x",3, 0, 0);
        SOut("x++x",8, 0, 0);
        SOut("x*-x",6, 0, 0);
        SOut("x / y / z",18, 3, 2);
        SOut("(2*(3)/(4)*((5)))",0, 0, 0);
        SOut("x*+-+--------x",4, 0, 0);
        SOut("-(-(-		-5 + 16   *x*y) + 1 * z) -(((-11)))", 0, 0, 0);
        SOut("2147483647",3, 0, 0);
        SOut("2147483647",3, 0, 0);
    }
}
