package utils;

public class MathUtils {
	public static int pow2(int power){
		int result = 1;
		result = result << power;
		return result;
	}
}
