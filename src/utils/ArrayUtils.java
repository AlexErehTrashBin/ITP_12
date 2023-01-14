package utils;

import java.util.ArrayList;
import java.util.List;

public class ArrayUtils {
	public static String toString(char[][] matrix) {
		StringBuilder resultingString = new StringBuilder();
		for (char[] chars : matrix) {
			for (char aChar : chars) {
				resultingString.append(aChar);
			}
			resultingString.append("\n");
		}
		return resultingString.toString();
	}

	public static List<Character> getLettersFromStringAsList(String text){
		List<Character> result = new ArrayList<>();
		for (char c: text.toCharArray()) {
			switch (c){
				case 'I':
				case 'Y':
					result.add(c);
					break;
				default:
					break;
			}
		}
		return result;
	}
}
