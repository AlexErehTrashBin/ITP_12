import utils.ArrayUtils;

import java.util.List;

import static utils.MathUtils.pow2;

public class Logic {
	public static void drawI(char[][] chars, int height, int startColumnIndex, int startRowIndex) {
		for (int i = startRowIndex; i >= startRowIndex - height + 1; i--) {
			chars[i][startColumnIndex] = '|';
		}
	}
	public static void drawY(
			char[][] chars, int height,
			int startColumnIndex, int startRowIndex) {
		/*
		* Нижняя часть
		* */
		for (int i = startRowIndex; i > startRowIndex - (height/2); i--) {
			chars[i][startColumnIndex] = '|';
		}
		/*
		 * Верхняя часть
		 * */
		int bottomRowIndexOffsetToMinus = 1;
		for (int i = startRowIndex - (height/2); i > startRowIndex - height ; i--) {
			chars[i][startColumnIndex + bottomRowIndexOffsetToMinus] = '/';
			chars[i][startColumnIndex - bottomRowIndexOffsetToMinus] = '\\';
			bottomRowIndexOffsetToMinus++;
		}
	}

	public static void recursiveDraw(
			char[][] chars, List<Character> letters, int currentDeepness, int height,
			int currentRowIndex, int currentColumnIndex) {
		if (height == 1) return;
		if (letters.get(currentDeepness) == 'I'){
			drawI(chars, height, currentColumnIndex, currentRowIndex);
			recursiveDraw(chars, letters, currentDeepness + 1, height / 2, currentRowIndex - height, currentColumnIndex);
		} else {
			drawY(chars, height, currentColumnIndex, currentRowIndex);
			// Наблюдение: ширина боковины буквы Y равна половине её высоты.
			int widthOnOneSide = height / 2;
			int nextColIndexRight = currentColumnIndex + widthOnOneSide;
			int nextColIndexLeft = currentColumnIndex - widthOnOneSide;
			recursiveDraw(chars, letters, currentDeepness + 1, height / 2, currentRowIndex - height, nextColIndexLeft);
			recursiveDraw(chars, letters, currentDeepness + 1, height / 2, currentRowIndex - height, nextColIndexRight);
		}
	}

	public static void drawFractal(String inputText) {
		List<Character> letters = ArrayUtils.getLettersFromStringAsList(inputText);
		int viewportWidth = Logic.getWidth(letters);
		int viewportHeight = Logic.getHeight(letters.size());
		char[][] chars = new char[viewportHeight][viewportWidth];
		fillMatrix(chars, '.');
		int startDeepness = 0;
		int height = pow2(letters.size());
		int startRowIndex = chars.length - 1;
		int startColumnIndex = Logic.getStartIndex(viewportWidth);
		recursiveDraw(chars, letters, startDeepness, height, startRowIndex, startColumnIndex);
		System.out.println(ArrayUtils.toString(chars));
	}

	public static int getHeight(int lettersCount){
		int resultSum = 0;
		for (int i = lettersCount; i > 0; i--) {
			resultSum += pow2(lettersCount - i + 1);
		}
		return resultSum;
	}

	public static int getWidth(List<Character> letters){
		int currentSum = 1;
		int currentFactor = pow2(letters.size() + 1);
		int currentIndex = 0;
		while (currentIndex != letters.size()){
			int term = (letters.get(currentIndex) == 'Y') ? (currentFactor >> 1) : 0;
			currentSum += term;
			currentFactor = currentFactor >> 1;
			currentIndex++;
		}
		return currentSum;
	}

	public static int getStartIndex(int width){
		return ((width - 1) >> 1);
	}

	public static void fillMatrix(char[][] chars, char emptyChar){
		for (int i = 0; i < chars.length; i++) {
			for (int j = 0; j < chars[i].length; j++) {
				chars[i][j] = emptyChar;
			}
		}
	}
}
