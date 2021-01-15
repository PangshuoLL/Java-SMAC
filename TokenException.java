package token;

/**
 * A class to handle Token exceptions. Token exceptions
 * are thrown in case of Token method misuse. For example
 * a TokenException cpuld be thrown by the method peekNextToken
 * from the class Tokenizer in case there is no more token to peek.
 * Consequently those exception should NEVER be thrown in your final
 * version of SMAC because they are "internal" errors
 */
@SuppressWarnings("serial")
public class TokenException extends Exception {

	public TokenException(String msg) {
		super(msg);
	}
}
