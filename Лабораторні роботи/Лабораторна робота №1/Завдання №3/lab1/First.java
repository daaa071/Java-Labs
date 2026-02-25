package si42;
import si41.*;
import java.lang.*;
import java.util.Random;
/**
 *   це дуже корисний клас який не робить нічого корисного
 *   
 */

public class First
{	
	static char ch = '\u2122';
	static Second s = new Second();
	static Second s2 = new Second("Si-41 "+ch+"\n");
	
	static	int a = 3;
	static	int b = 2;
		static double c = a+b;
	static double f = s.getSomeResult(a,b,(int)c);
	static double g = s.getSomeResult2(a,b);
	
	public static void main(String [] args)
	{
		Third t = new Third();
		double m = t.getMean();
		System.out.println("Математичне сподiвання становить: "+m+" \n");
	System.out.println("\nHello Si-42!\n Result: "+c+" \n");
	System.out.println("\nx"+f+"Result: "+g+" \n");
	System.out.println("\nx");

	}


}
