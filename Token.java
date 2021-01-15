package token;

/**
 * A class for the tokens returned by a tokenizer.
 * A token is an atomic unit read from the user input.
 * YOU MUST COMPLETE AND USE THIS CLASS WITHOUT CHANGING
 * THE PUBLIC METHODS
 */

//token类用于处理用户输入的原子操作
public class Token 
{
	//token根据不同的数字或符号有不同的标示,因此创建枚举类
	//token的类型主要包括：数字、标识符、字符串、
	//加、减、负号、乘、除、次方
	//左括号、右括号、sin、cos、tan
	private enum TokenType {
		Number,Identifier,String,Equal,Plus,Minus,
		Unaryminus,Times,Divide,Power,leftPair,Comma,
		RightPair,Sin,Cos,Tan
	}
	private TokenType type;//定义枚举类对象,覆盖所有的操作类型
	private double number; //用来将数字转换成浮点数
	private String string; //运算式的字符串形式
	
	

	/**
	 * checks if this is the token '='
	 */
	public boolean isEqual() 
	{
		return type == TokenType.Equal;
	}

	/**
	 * checks if this is a number token
	 */
	public boolean isNumber() {
		return type == TokenType.Number;
	}

	/**
	 * if this is a number token, returns the
	 * number as a double, throws a TokenException
	 * otherwise
	 */
	public double getNumber() throws TokenException {
		if(type!=TokenType.Number) {
			throw new TokenException("当前的token的类型:" +type+"异常");
		}
		return number;
	}

	/**
	 * checks if this is a string token
	 */
	public boolean isString() {
		return type == TokenType.String;
	}

	/**
	 * if this is a string token, returns the
	 * string as a String, throws a TokenException
	 * otherwise
	 */
	public String getString() throws TokenException {
		if(type!=TokenType.String) {
			throw new TokenException("当前的token的类型:" +type+"异常");
		}
		return string;
	}

	/**
	 * checks if this is an identifier token
	 */
	public boolean isIdentifier() {
		return type == TokenType.Identifier;
	}

	/**
	 * if this is an identifier token, returns the
	 * identifier as a String, throws a TokenException
	 * otherwise
	 */
	public String getIdentifier() throws TokenException {
		if(type!=TokenType.Identifier) {
			throw new TokenException("当前的token的类型:" +type+"异常");
		}
		return string;
	}

	/**
	 * checks if this is a delimiter token
	 */
	//判断是否是分隔符,即左右括号
	public boolean isDelimiter() {
		return type == TokenType.leftPair || type ==TokenType.RightPair;
	}

	/**
	 * if this is a delimiter token, returns the
	 * delimiter as a String, throws a TokenException
	 * otherwise
	 */
	public String getDelimiter() throws TokenException {
		switch(type) {
		case leftPair:
			return "(";
		case RightPair:
			return ")";
		case Comma:
			return ",";
		default:
			throw new TokenException("当前的token的类型:" +type+"异常");
		}
	}

	/**
	 * checks if this is an operator token
	 */
	public boolean isOperator() {
		return type == TokenType.Plus|| type == TokenType.Minus || type == TokenType.Unaryminus
				|| type == TokenType.Times || type == TokenType.Divide || type == TokenType.Power
				|| type == TokenType.Sin || type == TokenType.Cos || type == TokenType.Tan;
	}

	/**
	 * if this is an operator token, returns the
	 * operator as a String, throws a TokenException
	 * otherwise
	 */
	public String getOperator() throws TokenException {
		switch (type) {
		case Plus:
			return "+";
		case Minus:
			return "-";
		case Unaryminus:
			return "~";
		case Times:
			return "*";
		case Divide:
			return "/";
		case Power:
			return "^";
		case Sin:
			return "sin";
		case Cos:
			return "cos";
		case Tan:
			return "tan";
		default:
			throw new TokenException("当前的token的类型:" +type+"异常");
		}
	}

	/**
	 * returns a string representation
	 * of the token this
	 */
	public String toString() {
		String s = "[";
		switch (type) {
		case Number:
			s += number;
			break;
		case String:
			s += string;
			break;
		default:
			s += type;
		}
		return s + "]";
	}
	
	///// These methods are used to create tokens. You need
	///// to use these methods in the class Tokenizer. Each
	///// method is used to create a specific token of a 
	///// specific type. The methods are not commented because
	///// the name of the method says it all.
	
	//对于运算式中的每一个运算数、符生成一个原子"token"
	public static Token makeNUMBER(double number) {
		return new Token(number);
	}
	public static Token makeIDENTIFIER(String string) {
		return new Token(TokenType.Identifier,string);
	}
	public static Token makeSTRING(String string) {
		return new Token(TokenType.String,string);
	}
	public static Token makeEQUAL() {
		return new Token(TokenType.Equal);
	}
	public static Token makeOPENPAR() { //生成左括号
		return new Token(TokenType.leftPair);
	}
	public static Token makeCLOSEPAR() {
		return new Token(TokenType.RightPair);
	}
	public static Token makeCOMMA() {
		return new Token(TokenType.Comma);
	}
	public static Token makeMINUS() {
		return new Token(TokenType.Minus);
	}
	public static Token makeUNARYMINUS() {
		return new Token(TokenType.Unaryminus);
	}
	public static Token makeTIMES() {
		return new Token(TokenType.Times);
	}
	public static Token makeDIVIDE() {
		return new Token(TokenType.Divide);
	}
	public static Token makePOWER() {
		return new Token(TokenType.Power);
	}
	public static Token makePLUS() {
		return new Token(TokenType.Plus);
	}
	
	////// add your private methods below
	//----定义构造函数
	private Token(TokenType type) //使用枚举类型中的元素对当前的token原子进行初始化
	{								//枚举类中的一个元素
		this.type = type;
	}
	private Token(double number){
		this(TokenType.Number);
		this.number = number;
	}
	private Token(TokenType type,String string) {
		this(type);
		this.string = string;
	}
}
