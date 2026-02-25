package si41;
import si42.*;
/**
 *
 * просто собі клас
 * @since 1.3
 *
 */
public class Third
{
int size = 100;
double [] array = new double[size];

java.util.Random r = new java.util.Random();

/**
 *
 * метод, який повертає математичне сподівання 
 * @return повертає мат.сподівання
 *
 */
public double getMean()
{

	for(int i=0;i<array.length;i++)
     array[i]=r.nextDouble();

double mean = 0;
for(int i=0;i<array.length;i++)
{
	mean+=array[i];
}
mean=mean/array.length;
return mean;
}

}
