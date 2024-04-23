package AB4;

public class ExpressionNode {

    private IntVar variable;
    private IntConst constant;

    private ExpressionNode previousExpression;


    public ExpressionNode(IntVar variable,IntConst constant){
        this.variable=variable;
        this.constant=constant;
    }

    public ExpressionNode(IntConst constant){
        this.constant=constant;
    }

    public ExpressionNode(IntVar variable){
        this.variable=variable;
    }



    public IntConst setValue(int value){
        variable=null;
        return constant.times(new IntConst(value));
    }

    public IntConst getConstant(){
        return constant;
    }
    public ExpressionNode getPreviousExpression(){
        return previousExpression;
    }
    public void setPreviousExpression(ExpressionNode expression){
        previousExpression = expression;
    }

    @Override
    public String toString() {
        if (variable==null) return String.format(" %s ",constant);
        if (constant.lessThan(new IntConst(2))) return String.format(" %s ",variable);
        return String.format(" %s%s ",constant,variable);
    }
}
