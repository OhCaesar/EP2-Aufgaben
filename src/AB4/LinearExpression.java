package AB4;

/**
 * This class represents a simple linear expression. A linear expression is a sum of
 * linear terms each containing at most a single variable. For example, 3x - y + 5 is such an
 * expression.
 */
//
// TODO: define further classes, if needed.
//
public class LinearExpression {

    IntConst singleParam;
    IntVarConstTreeMap expressionMap;
    IntVarDoublyLinkedList expressionList;


    /**
     * Constructs this linear expression from a specified constant.
     * @param c the constant being wrapped as this linear expression, c != null.
     */
    public LinearExpression(IntConst c) {
        expressionMap  =new IntVarConstTreeMap();
        expressionList = new IntVarDoublyLinkedList();
        singleParam= c;
    }

    /**
     * Constructs this linear expression from a specified variable.
     * @param v the variable being wrapped as this linear expression, v != null.
     */
    public LinearExpression(IntVar v) {
        expressionMap  =new IntVarConstTreeMap();
        expressionList = new IntVarDoublyLinkedList();
        plus(v);
        singleParam= new IntConst(0);
    }

    /**
     * Constructs a linear expression as a copy of the specified expression.
     * Calling methods of this expression will not affect the specified expression
     * and vice versa.
     * @param e the expression from which all the terms are copied to this new expression,
     *          e != null.
     */
    public LinearExpression(LinearExpression e) {
        singleParam = e.singleParam; //Has to be changed later
        expressionMap = new IntVarConstTreeMap(e.expressionMap);
        expressionList = expressionMap.keyList();
    }

    /**
     * Returns a new linear expression representing the sum of 'this' and 'v'.
     * @param v the second summand != null.
     * @return the sum of 'this' and 'v'.
     */
    public LinearExpression plus(IntVar v) {
        if(expressionMap.containsKey(v)){
            expressionMap.put(v,expressionMap.get(v).plus(new IntConst(1)));
        }
        else {
            expressionMap.put(v,new IntConst(1));
            expressionList.addLast(v);
        }
        return new LinearExpression(this);
    }

    /**
     * Returns a new linear expression representing the sum of 'this' and 'e'.
     * @param e the second summand != null.
     * @return the sum of 'this' and 'e'.
     */
    public LinearExpression plus(LinearExpression e) {
        LinearExpression expression = new LinearExpression(this);
        for (int i = 0; i < e.expressionList.size(); i++) {
            IntVar iterableVar =  e.expressionList.get(i);
            if(expression.expressionMap.containsKey(iterableVar)) {
                IntConst newConst = expression.expressionMap.get(iterableVar).plus(e.expressionMap.get(iterableVar));
                expression.expressionMap.put(iterableVar, newConst);
            }else{
                expression.expressionMap.put(iterableVar,e.expressionMap.get(iterableVar));
                expression.expressionList.addFirst(iterableVar);
            }
        }
        expression.singleParam=expression.singleParam.plus(e.singleParam);
        return expression;
    }

    /**
     * Returns a new expression representing the negative of 'this', such that for an expression 'e'
     * the following condition holds: e.plus(e.negate()) represents 0.
     * @return the negative of 'this'.
     */
    public LinearExpression negate() {
        LinearExpression expression = new LinearExpression(this);
        for (int i = 0; i < expression.expressionList.size(); i++) {
            expression.expressionMap.put(expression.expressionList.get(i),expression.expressionMap.get(expression.expressionList.get(i)).times(new IntConst(-1)));
        }
        expression.singleParam = expression.singleParam.negate();
        return expression;
    }

    /**
     * Returns a new expression resulting from assigning specific values to certain variables.
     * In this new expression the corresponding variables have been substituted by the constant
     * values, that were assigned to these variables through mappings in 'varValues'. For example,
     * if 'varValues' associates variable x with constant 2 and variable y with constant 3,
     * calling the method on the expression representing 2x + y will result in the expression
     * representing the constant 7. If 'varValues' contains the mapping for y, but no mapping for
     * x, then the result is the expression 2x + 3. If there is neither a mapping for x nor for y,
     * then the result is a new expression representing 2x + y.
     * @param varValues the map containing mappings from variables to their assigned values,
     *                  varValues != null.
     * @return the new expression in which specific variables have been replaced by constant
     * values (as specified by 'varValues').
     */
    public LinearExpression assignValue(IntVarConstTreeMap varValues) {
        LinearExpression expression = new LinearExpression(this);
        IntVarDoublyLinkedList list = varValues.keyList();
        for (int i = 0; i < list.size(); i++) {
            IntConst c = varValues.get(list.get(i));
            IntConst multiplier = expression.expressionMap.get(list.get(i));
            expression.singleParam=expression.singleParam.plus(c.times(multiplier));
            expression.expressionMap.put(list.get(i),new IntConst(0));
        }
        //remove value by setting value to null and then adding it to the end constant

        return expression;
    }

    /**
     * A readable representation of this expression in which each of its variables appears only
     * once preceded by a coefficient, unless the coefficient is 1 or -1.
     * The variables appear in lexicographic order according to their names.
     * Terms that are zero are omitted. For example, -y + x + x - 4 would be represented by
     * "2x - y - 4" and x + y - x + 0 would be represented by "y".
     * (See Test.java for further examples.)
     * @return readable representation of this expression.
     */
    @Override
    public String toString() {
        String s = "";
        if(expressionList.size()==0) return ""+singleParam;


        for (int i = expressionList.size()-1; i >= 0; i--) {
            IntConst mult = expressionMap.get(expressionList.get(i));
            s+=constString(mult,i);
        }
        if(s.isEmpty()) return ""+singleParam;
        if(singleParam.isZero())
            return s;
        else if(singleParam.lessThan(new IntConst(0)))
            return s + "-" + singleParam;
        else
            return s+ "+" + singleParam;
    }

    public String constString(IntConst mult,int i){
        String s="";
        if (mult.lessThan(new IntConst(2))&&new IntConst(0).lessThan(mult) ){
            if(i==expressionList.size()-1) s=""+ expressionList.get(i);
            else s="+"+ expressionList.get(i);
        }
        else if (new IntConst(-2).lessThan(mult) &&mult.lessThan(new IntConst(0))) {
            s= "-" + expressionList.get(i);
        }
        else if (mult.lessThan(new IntConst(0)))
            s= ""+mult + expressionList.get(i);
        else if (new IntConst(0).lessThan(mult)) {

            if(i==expressionList.size()-1) s=""+mult+ expressionList.get(i);
            else s=" +" +mult + expressionList.get(i) ;
        }
        return s;
    }
}


