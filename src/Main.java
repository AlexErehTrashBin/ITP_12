import java.util.*;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Введите строку (1-7 символов) из I или Y для отрисовки: ");
		String input = scanner.nextLine();
		if (input.length() < 1 || input.length() > 7) {
			System.err.println("Кому-то в новый год не достались подарки!");
			return;
		}
		System.out.println("Вывод: ");
		Logic.drawFractal(input);
	}
}