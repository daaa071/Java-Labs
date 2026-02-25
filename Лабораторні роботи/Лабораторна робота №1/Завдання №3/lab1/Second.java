package si42;

/**
 * це дуже корисний клас який не робить нічого корисного
 *
 */
public class Second
{

	/** 
	 * конструктор класу Second
	 * @exception Exception виняткова ситуація яка може виникати
	 * @since 1.8
	 */
	public Second()
	{
	
	}

	/** 
	 * другий конструктор класу Second
	 * @since 1.8
	 *
	 */
	public Second(String nameOfclass)
	{
	System.out.println("This is "+nameOfclass);
	}



/**
 * це дуже корисний клас який не робить нічого корисного
 * @return повертає якесь значення типу double
 * @author СІ-42
 * @version 0.01
 */
	public double getSomeResult(int someVal, int someVal2, int someVal3)
	{
	double result = someVal3+someVal2-someVal;
	return result;
	}
/**
 * це дуже корисний клас який не робить нічого корисного
 * @return повертає якесь значення типу double
 * @author СІ-42
 * @version 0.01
 */
	public double getSomeResult2(int someVal, int someVal2)
	{
	double result = someVal+someVal2;
	return result;
	}
}
