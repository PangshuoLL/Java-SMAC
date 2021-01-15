package SimpleCalculator;


import java.util.*;

import exception.LexicalErrorException;
import token.Token;
import token.TokenException;
import token.Tokenizer;



public class Calculator 
{
	private Stack<Double> value;   //存储数字的栈
	private Stack<String> operator;  //操作符的栈
	private double last;

	public Calculator() //初始化两个栈
	{
		value = new Stack<Double>();
		operator = new Stack<String>();
	}
	
	//接受一个tokenizer类型的input(即运算式)
	public double algorithm(Tokenizer input) throws TokenException,  LexicalErrorException 
	{
		Token before = null;
		while (input.hasNextToken()) 
		{
			Token t = input.peekNextToken();
			//如果是数字则入数字栈
			if (t.isNumber())
				value.push(t.getNumber());
			//如果是字符则入字符栈
			if (t.isDelimiter() && t.getDelimiter().equals("("))
				operator.push(t.getDelimiter());

			if (t.isDelimiter() && t.getDelimiter().equals(")")) {
				while (!operator.peek().equals("(")) {
					Operator();
				}
				operator.pop();
			}
			//同样对操作符进行处理
			if (t.isOperator())
			{
				//利用操作符类生命一个op类
				tokenPriority thisOp = new tokenPriority(t.getOperator());
				boolean flag = true;
				//如果栈非空,且优先级正确
				while (!operator.isEmpty() && flag) 
				{
					tokenPriority Op = new tokenPriority(operator.peek());
					if (Op.getPrority() >= thisOp.getPrority()) 
					{
						Operator();  //作出运算,否则不进行运算
					} 
					else
						flag = false;
				}
				operator.push(t.getOperator());  //压栈
			}
			before = t;
			input.readNextToken();
			
		}
		while (!operator.isEmpty())
			Operator();

		last = value.pop();
		return last;
	}
	
	private void Operator() throws EmptyStackException,LexicalErrorException
	{
		try 
		{
			String op = operator.pop();
			tokenPriority funop = new tokenPriority(op);
			Evaluator math = new Evaluator();

			if (funop.getArity() == 2) {
				double val1 = value.pop();
				double val2 = value.pop();
				double result = math.getResult(val1, val2, op);
				value.push(result);
			} else if (funop.getArity() == 1) {
				double val = value.pop();
				double result = math.getResult(val, op);
				value.push(result);
			}
		} catch (EmptyStackException e) {
			throw new LexicalErrorException("异常的表达式");
		}

	}
}
