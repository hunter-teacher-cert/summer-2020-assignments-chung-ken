//ThinkJava Chapter 6 - Exercises 2, 3, and 8

public class Methods {
	
	//Exercise 2
	public static boolean isDivisible (int n, int m) {
		if(n % m == 0) {
			return true;
		}
		return false;
	}
	
	//Exercise 3
	public static boolean isTriangle (int a, int b, int c) {
		if (a < b + c && b < a + c && c < a + b) {
			return true;
		}
		return false;
	}
	
	//Exercise 8
	public static int ack (int m, int n) {
		if (m == 0) {
			return n + 1;
		} else if (m > 0 && n == 0) {
			return ack(m - 1, 1);
		} else if (m > 0 && n >  0) {
			return ack(m - 1, ack(m, n - 1));
		}
		System.out.println("Error: Got out of the recursion; m = " + m + ", n = " + n);
		return -1;
	}
	
	public static void main (String[] args) {
		//test Exercise 2
		System.out.println("24 is divisible by 3: " + isDivisible(24,3)); //check 'true'
		System.out.println("25 is divisible by 3: " + isDivisible(25,3)); //check 'false'
		
		//test Exercise 3
		System.out.println("1, 11, and 1 make a triangle: " + isTriangle(1, 11, 1)); //check 'false'
		System.out.println("3, 4, and 5 make a triangle: " + isTriangle(3, 4, 5)); //check 'true'
		
		//test Exercise 8
		System.out.println("ack(0,3) = " + ack(0,3)); //expected '4'
		System.out.println("ack(1,2) = " + ack(1,2)); //expected '2' from ack(0,1)
		System.out.println("ack(2,3) = " + ack(2,3)); //expected '9' from... 
		/* ack(2,3)
		 * = ack(1,ack(2,2))
		 * = ack(1,ack(1,ack(2,1)))
		 * = ack(1,ack(1,ack(1,ack(2,0))))
		 * = ack(1,ack(1,ack(1,ack(1,1))))
		 * = ack(1,ack(1,ack(1,ack(0,ack(1,0)))))
		 * = ack(1,ack(1,ack(1,ack(0,ack(0,1)))))
		 * = ack(1,ack(1,ack(1,ack(0,2))))
		 * = ack(1,ack(1,ack(1,3)))
		 * = ack(1,ack(1,ack(0,ack(1,2))))
		 * = ack(1,ack(1,ack(0,ack(0,ack(1,1)))))
		 * = ack(1,ack(1,ack(0,ack(0,ack(0,ack(1,0))
		 * = ack(1,ack(1,ack(0,ack(0,ack(0,ack(0,1))))))
		 * = ack(1,ack(1,ack(0,ack(0,ack(0,2)))))
		 * = ack(1,ack(1,ack(0,ack(0,3))))
		 * = ack(1,ack(1,ack(0,4)))
		 * = ack(1,ack(1,5))
		 * = ack(1,ack(0,ack(1,4)))
		 * = ack(1,ack(0,ack(0,ack(1,3))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(1,2)))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(0,ack(1,1))))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(0,ack(0,ack(1,0)))))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,1)))))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(0,ack(0,2))))))
		 * = ack(1,ack(0,ack(0,ack(0,ack(0,3)))))
		 * = ack(1,ack(0,ack(0,ack(0,4))))
		 * = ack(1,ack(0,ack(0,5)))
		 * = ack(1,ack(0,6))
		 * = ack(1,7)
		 * = ack(0,ack(1,6))
		 * = ack(0,ack(0,ack(1,5)))
		 * = ack(0,ack(0,ack(0,ack(1,4))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(1,3))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(1,2))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(1,1)))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(1,0))))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,1))))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,2)))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,ack(0,3))))))
		 * = ack(0,ack(0,ack(0,ack(0,ack(0,4)))))
		 * = ack(0,ack(0,ack(0,ack(0,5))))
		 * = ack(0,ack(0,ack(0,6)))
		 * = ack(0,ack(0,7))
		 * = ack(0,8)
		 * = 9
		 */
	}
}