package SimpleCalculator;
import java.util.*;

import token.Token;
import token.Tokenizer;


public class testSimpleCal 
{
	public static void main(String[] args) 
	{
		Scanner console = new Scanner(System.in);

		System.out.print("> ");
		String input = console.nextLine().trim();
		
		while ( ! input.equals("exit") ) 
		{
			try 
			{
				Tokenizer tokenizer = new Tokenizer(input);
				Calculator cal = new Calculator();
				double res = cal.algorithm(tokenizer);
				System.out.println("运算结果是: "+res);
				 
				System.out.println();
			}
			catch ( Exception e ) 
			{
				System.out.println("\nERROR: " + e.getMessage());
			}
			System.out.print("> ");
			input = console.nextLine();
		}
		
		
	}
}
