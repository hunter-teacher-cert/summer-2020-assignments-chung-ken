//ThinkJava Chapter 7 - Exercises 3, 4, and 5

public class LoopsTest {
	
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
	public static double factorial (int n) {
		double answer = 1;
		for (int i = 2; i <= n; i++) {
			answer *= i;
		}
		return answer;
	}
	
	//Exercise 5
	public static double myexp1 (double x, int n) {
		double answer = 1;
		double term = 1;
		System.out.print("1");
		for (int i = 1; i < n; i++) {
			term = term * x / i;
			answer += term;
			System.out.print(" + " + term);
		}
		System.out.println(" = " + answer);
		return answer;
	}
	public static double myexp2 (double x, int n) {
		double answer = 1;
		double term = 1;
		System.out.print("1");
		for (int i = 1; i < n; i++) {
			term = Math.pow(x, i) / factorial(i);
			answer += term;
			System.out.print(" + " + term);
		}
		System.out.println(" = " + answer);
		return answer;
	}
	public static void check (double x) {
		int n = 1000;
		/*if ( x > 0 ) {
			while(2 < (int)(Math.exp(x) / myexp1(x,n))) {
				n++;
			}
		} else if ( x < 0 ) {
			while(100 < (int)Math.abs((myexp1(x,n) / Math.exp(x)))) {
				n++;
			}
		}*/
		System.out.println("n=" + n + ":  " + x + "\t" + myexp1(x,n));
		//System.out.println("n=" + n + ":  " + x + "\t" + myexp2(x,n));
		System.out.println(Math.exp(x));
	}
	
	public static void checkFactorial (int n) {
		for(int i = 0; i <= n; i++)
			System.out.println(i + "! = " + factorial(i));
	}
	public static void main (String[] args) {
		
		check(-100);
		//checkFactorial(20);
		//Test1 Exercise 5
		for (double d = 0.1; d < 1000; d *= 10) {
			//check(d);
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
		for (double d = -0.1; d > -1000; d *= 10) {
			//check(d);
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