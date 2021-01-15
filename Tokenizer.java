package token;

import exception.LexicalErrorException;

/**
 * This class implements a tokenizer. A tokenizer is used to
 * split a string (user input) in tokens
 * YOU MUST COMPLETE AND USE THIS CLASS WITHOUT CHANGING
 * THE PUBLIC METHODS
 */

//即拆分为token原子
public class Tokenizer {

	private String Input;//接受需要拆分字符串
	private int index;//标示当前正在处理的token
	private Token current;//标示当前要处理的Token原子
	/**
	 * create a tokenizer on the string 'input'
     * YOU MAY ADD THROW CLAUSES TO THIS CONSTRUCTOR
	 */
	public Tokenizer(String input) throws TokenException, LexicalErrorException
	{
		Input = input;
		index = 0;//当前处理指针初始化为0
		current = getNextToken();//指向第一个操作元素
	}

	/**
	 * checks if there is more token to read
	 */
	public boolean hasNextToken() {
		//返回一个布尔类型
		return current!=null;
	}

	/**
	 * returns the next token to be read
	 * Throws a TokenException if there is
	 * no more token to peek in the Tokenizer
	 * YOU MAY ADD THROW CLAUSES TO THIS METHOD
	 */
	public Token peekNextToken() throws TokenException{
		if(current == null)//如果当前的token为空,则无法获取写一个token
		{
			throw new TokenException("没有下一个Token");
		}
		return current;
	}

	/**
	 * reads and returns the next token to be read
	 * (i.e. the next token is removed from the tokenizer)
     * YOU MAY ADD THROW CLAUSES TO THIS METHOD
	 */
	public Token readNextToken() throws TokenException, LexicalErrorException {
		if (current == null)
			throw new TokenException("当前的token为空,没有下一个token");
		Token tmp = current;
		current = getNextToken();
		return tmp;
	}
	
	////// add your private methods below
	private void moveToNextToken() {
		char c = 0;
		//从当前的字符（跳过空格和制表符）移动到下一个字符
		while (index < Input.length() && ((c = Input.charAt(index)) == ' ' || c == '\t'))
			index++;
	}
	//获取下一个token
	private Token getNextToken() throws TokenException, LexicalErrorException {
		if (index >= Input.length())
			return null;
		char c = Input.charAt(index);
		//根据不同的类型返回不同的token原子
		if (c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z') //如果是标识符(即字符）
			return nextIdentifier();
		if (c >= '0' && c <= '9')//如果当前是数字
			return nextNumber();
		if (c == '(' || c == ')' || c == ',') //如果当前是分隔符
			return nextDelimiter();
		if (isOperator(c))     //如果当前是操作符
			return nextOperator();
		if (c == '"')
			return nextString();
		if (c == '=')
			return nextEqual();
		current = null;
		throw new TokenException("异常的字符: " + c);
	}

	private boolean isOperator(char c) {
		return c == '+' || c =='-' || c == '*' || c == '/' || c == '^';
	}
	private boolean isLetter(char c) {
		return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z';
	}

	private boolean isDigit(char c) {
		return c >= '0' && c <= '9';
	}


	//查询下一个数字类型的token并返回
	private Token nextNumber() throws LexicalErrorException 
	{
		int i = index++;
		int dots = 0; //统计小数点的个数
		char c = 0;
		while (index < Input.length() && isDigit(c = Input.charAt(index)) || c == '.') 
		{
			if (c == '.')
				dots++;
			if (dots > 1) //如果多个小数点,则此时是一种错误的表示法
			{
				int j = index;
				current = null;
				throw new LexicalErrorException("错误的数字: " + Input.substring(i, j + 1));
			}
			index++;
		}
		int j = index;
		if (j < Input.length() && (isLetter(Input.charAt(j)) || Input.charAt(j) == '('))
			throw new LexicalErrorException("错误的数字: " + Input.substring(i, j + 1));
		moveToNextToken();
		return Token.makeNUMBER(Double.parseDouble(Input.substring(i, j)));//强制类型转换
	}
	

	private Token nextIdentifier() 
	{
		int i = index++;
		char c = 0;
		while (index < Input.length() && (isLetter(c = Input.charAt(index)) || isDigit(c)))
			index++;
		int j = index;
		moveToNextToken();
		return Token.makeIDENTIFIER(Input.substring(i, j));
	}
	
	private Token nextString() throws LexicalErrorException 
	{
		int i = ++index;
		while (index < Input.length() && Input.charAt(index) != '"')
			index++;
		if (index >= Input.length()) {
			int j = index;
			index = Input.length();
			current = null;
			throw new LexicalErrorException("异常的字符串: " + Input.substring(i - 1, j));
		}
		int j = index++;
		moveToNextToken();
		return Token.makeSTRING(Input.substring(i, j));
	}
	
	private Token nextDelimiter() 
	{
		Token temp = null;
		switch (Input.charAt(index++)) {
		case '(':
			temp = Token.makeOPENPAR();
			break;
		case ')':
			temp = Token.makeCLOSEPAR();
			break;
		default:
			temp = Token.makeCOMMA();
		}
		moveToNextToken();
		return temp;
	}
	
	private Token nextOperator() 
	{
		Token to = null;
		switch (Input.charAt(index++)) 
		{
		case '+':
			to = Token.makePLUS();
			break;
		case '-':
			if (isMinus())   //判断是减号还是负号
				to = Token.makeMINUS();
			else
				to = Token.makeUNARYMINUS();
			break;
		case '*':
			to = Token.makeTIMES();
			break;
		case '/':
			to = Token.makeDIVIDE();
			break;
		default:
			to = Token.makePOWER();
		}
		moveToNextToken();
		return to;
	}
	private boolean isMinus() 
	{
		try 
		{
			return current != null && (current.isIdentifier() || current.isNumber()
					|| (current.isDelimiter() && current.getDelimiter().equals(")")));
		} catch (Exception e) {
			return false;
		}
	}

	private Token nextEqual() 
	{
		index++;
		moveToNextToken();
		return Token.makeEQUAL();
	}
}
