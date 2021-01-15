package SimpleCalculator;

import exception.LexicalErrorException;

 

public class Evaluator 
{
	// 计算类
	public double getResult(double val1, double val2, String op) throws LexicalErrorException {
		if (op == "+")
			return val1 + val2;
		if (op == "-")
			return val2 - val1;
		if (op == "/") {
			if (val1 == 0)
				throw new LexicalErrorException("出现除0错误");
			return val2 / val1;
		}
		if (op == "*")
			return val1 * val2;
		if (op == "^")
			return Math.pow(val2, val1);
		return 0;
	}

	public double getResult(double val, String op) {
		if (op == "sin")
			return Math.sin(val);
		if (op == "cos")
			return Math.cos(val);
		if (op == "tan")
			return Math.tan(val);
		if(op=="~")
			return -val;
		return 0;
	}
}
