package SimpleCalculator;

public class tokenPriority 
{
	private int arity;// 运算符所涉及的参数数量
	private int prority;
	private String operator;

	tokenPriority(String operator) 
	{
		this.operator = operator;
		arity = 2;
		if (operator == "(")
			prority = 0;
		if (operator == "+" || operator == "-")
			prority = 1;
		if (operator == "*" || operator == "/" || operator == "^")
			prority = 2;
		if (operator == "~" || operator == "sin" || operator == "cos" || operator == "tan") {
			arity = 1;
			prority = 3;
		}
	}

	public int getPrority() {
		return prority;
	}

	public int getArity() {
		return arity;
	}

	public String getOperator() {
		return operator;
	}
}
