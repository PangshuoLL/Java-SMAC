package exception;

/**
 * A class to handle lexical exception.
 * A lexical exception is thrown when a token
 * is malformed (bad number or string) or in case
 * there is a not allowed character in the user
 * input (like '!' or '?')
 */
@SuppressWarnings("serial")
public class LexicalErrorException extends Exception {

	public LexicalErrorException(String msg) {
		super(msg);
	}

	public String toString() {
		return "Lexical error: " + getMessage();
	}
}
