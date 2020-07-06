//ThinkJava Chapter 7 - Exercises 3, 4, and 5
import java.math.BigInteger;

public class Loops {

	//Exercise 3
	public static double power (double x, int n) {
		double answer = 1;
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				answer *= x;
			}
		} else if (n < 0) {
			for (int i = 0; i > n; i--) {
				answer /= x;
			}
		}
		return answer;
	}

	//Exercise 4
	public static int factorial (int n) {
		int answer = 1;
		for (int i = 2; i <= n; i++) {
			answer *= i;
		}
		return answer;
	}

	//Exercise 5
	public static double myexp (double x, int n) {
		double answer = 1;
		double term = 1;
		for (int i = 1; i < n; i++) {
			term = term * x / i;
			answer += term;
		}
		return answer;
	}

	public static void check (double x) {
		int n = 18; //number of terms in the series
		System.out.println(x + "\t" + myexp(x,n) + "\t" + Math.exp(x));
	}
	public static void check2 (double x) {
		int n = 18; //number of terms in the series
		System.out.println(x + "\t" + myexp2((int)x,n) + "\t" + Math.exp(x));
	}
	public static double myexp2(int x, int n) {
		BigInteger ansNum = BigInteger.valueOf(1);
		BigInteger ansDen = BigInteger.valueOf(1);
		BigInteger termNum = BigInteger.valueOf(1);
		BigInteger termDen = BigInteger.valueOf(1);
		BigInteger X = BigInteger.valueOf(x);
		for (int i = 1; i < n; i++) {
			BigInteger I = BigInteger.valueOf(i);
			termNum = termNum.multiply(X);
			termDen = termDen.multiply(I);
			ansNum = ansNum.multiply(termDen).add(termNum.multiply(ansDen));
			ansDen = ansDen.multiply(termDen);
			System.out.print(i + ": " + ansDen + ", ");
		}
		return ansNum.divide(ansDen).doubleValue();
	}

	public static void main (String[] args) {
		//Test Exercise 3
		System.out.println("3^4 = " + power(3, 4));
		System.out.println("2^-3 = " + power(2, -3));

		//Test Exercise 4
		System.out.println("0! = " + factorial(0));
		System.out.println("1! = " + factorial(1));
		System.out.println("5! = " + factorial(5));

		//Test1 Exercise 5
		for (double d = 1; d < 1000; d *= 10) {
			check(d);
			check2(d);
		}
		/* Q: How does the accuracy of the result vary as x varies? (with the values 0.1, 1.0, 10.0, and 100.0)
		 * Results when n = 18:
		 * 0.1     1.1051709180756473      1.1051709180756477		// When x = 0.1, the sum is accurate for 16 digits.
		 * 1.0     2.7182818284590455      2.718281828459045		// When x = 1.0, the sum is accurate for 16 digits.
		 * 10.0    21711.980427279836      22026.465794806718		// When x = 10.0, the sum is only accurate for 1 digit.
		 * 100.0   3.379190152800617E19    2.6881171418161356E43	// When x = 100.0, we're not even in the same ball park.
		 * Further experimentation shows that x = 10 is accurate to 11 decimal places when n = 45
		 * while x = 100 is only accurate for the first 3 digits when n = 131 and for the first 14 digits when n = 190.
		 */

		//Test2 Exercise 5
		for (double d = -1; d > -1000; d *= 10) {
			check(d);
			check2(d);
		}
		/* Q:
		 * Results when n = 18:
		 * -0.1    0.9048374180359596      0.9048374180359595		// When x = -0.1, the sum is accurate for 15 digits.
		 * -1.0    0.3678794411714423      0.36787944117144233		// When x = -1.0, the sum is accurate for 16 digits.
		 * -10.0   -101.69202745704473     4.5399929762484854E-5	// When x = -10.0, we're off by an order of roughly 2 * 10^6.
		 * -100.0  -2.399930098642292E19   3.720075976020836E-44	// Again, when x = -100.0, we're not even in the same ball park.
		 * Further analysis reveals that for x = -10.0, we are able to get the first 8 digits correct when n = 47.
		 * For x = -100.0, we never approach the right answer. As n increase, the calculation starts oscillating around the wrong value.
		 * This may be due to a rounding error with such large numbers.
		 */

	}

} //End of class
