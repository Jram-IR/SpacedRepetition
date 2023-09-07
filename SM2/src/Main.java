import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in); // Create a Scanner object

        // Prompt the user for input
        System.out.print("Enter learning tag (Again, Hard, Good, Easy): ");
        String learningTag = scanner.nextLine(); // Read the user's input

        System.out.print("Enter previous learning tag (Again, Hard, Good, Easy): ");
        String previousLearningTag = scanner.nextLine(); // Read the user's input

        System.out.print("Enter previous review time in minutes: ");
        int previousMinutes = scanner.nextInt(); // Read the user's input as an integer

        System.out.print("Is the card already graduated? (true/false): ");
        boolean graduated = scanner.nextBoolean(); // Read the user's input as a boolean

        System.out.print("Enter previous easiness factor: ");
        double previousEasinessFactor = scanner.nextDouble(); // Read the user's input as a double


        // Example usage:
        SpacedRepetitionAlgo.ReviewResult result = SpacedRepetitionAlgo.reviewCard(graduated, learningTag,previousLearningTag, previousMinutes, previousEasinessFactor);
        System.out.println("Graduated: " + result.isGraduated());
        System.out.println("Time until next review (minutes): " + result.getTimeMinutes());
        System.out.println("Easiness Factor: " + result.getEasinessFactor()); // Display the easiness factor

        // Close the Scanner when you're done with it
        scanner.close();

    }
}