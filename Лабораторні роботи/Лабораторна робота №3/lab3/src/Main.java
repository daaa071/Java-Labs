import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть текстовий рядок: ");
        String input = scanner.nextLine();

        int punctuationCount = 0;
        for (char c : input.toCharArray()) {
            if (isPunctuation(c)) {
                punctuationCount++;
            }
        }

        System.out.println("\nКількість розділових знаків: " + punctuationCount);

        String[] words = input.split("\\s+");

        System.out.println("\nСлова з парною кількістю літер:");
        for (String word : words) {

            String cleanWord = word.replaceAll("[^a-zA-Zа-яА-ЯіїєІЇЄ]", "");

            if (cleanWord.length() == 0)
                continue;

            // слова з парною кількістю літер
            if (cleanWord.length() % 2 == 0) {
                System.out.println(cleanWord);
            }
        }

        System.out.println("\nСлова із заміненими першою та останньою літерами:");
        for (String word : words) {

            String cleanWord = word.replaceAll("[^a-zA-Zа-яА-ЯіїєІЇЄ]", "");

            if (cleanWord.length() < 2) {
                System.out.println(cleanWord);
                continue;
            }

            char[] chars = cleanWord.toCharArray();
            char temp = chars[0];
            chars[0] = chars[chars.length - 1];
            chars[chars.length - 1] = temp;

            System.out.println(new String(chars));
        }

        scanner.close();
    }

    private static boolean isPunctuation(char c) {
        return ".,!?;:-()\"'".indexOf(c) != -1;
    }
}
