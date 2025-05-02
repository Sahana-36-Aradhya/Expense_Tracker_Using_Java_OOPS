package Mini_Project;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.text.ParseException;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        User user = new User(0, 0.0);
        user.loadFromFile(); // Load data ONCE at start

        boolean doYouWantToContinue = true;
        String name;

        System.out.print("Please enter your name: ");
        name = scanner.nextLine();
        System.out.println();
        System.out.println("Hello " + name.toUpperCase() + ", Welcome To Expense Tracker 💰");
        System.out.println();
        
        while (doYouWantToContinue) {
            System.out.println("-------------------------");
            System.out.println("1. Add Expense");
            System.out.println("2. Remove Expense");
            System.out.println("3. View Total Expense");
            System.out.println("4. Filter By Category");
            System.out.println("5. Filter By Date");
            System.out.println("6. View All Expenses");
            System.out.println("7. Edit Expenses");
            System.out.println("8. Exit");
            System.out.println("-------------------------");

            System.out.println();
            int choice = getValidChoice(scanner, 1, 8); // Validate menu input

            switch (choice) {
                case 1:
                    double amount = getValidAmount(scanner);
                    String category = getNonEmptyInput(scanner, "Enter the category: ");
                    String date = getValidDate(scanner);
                    String description = getNonEmptyInput(scanner, "Enter the description: ");
                    user.addExpense(amount, category, date, description);
                    break;

                case 2:
                    int removeId = getValidInt(scanner, "Enter the ID to remove: ");
                    user.removeExpense(removeId);
                    break;

                case 3:
                    user.getTotalExpense();
                    break;

                case 4:
                    String catFilter = getNonEmptyInput(scanner, "Enter the category: ");
                    user.getExpensesByCategory(catFilter);
                    break;

                case 5:
                    String dateFilter = getValidDate(scanner);
                    user.getExpensesByDate(dateFilter);
                    break;

                case 6:
                    user.displayProfile();
                    break;

                case 7:
                    int editId = getValidInt(scanner, "Enter the ID to edit: ");
                    double newAmount = getValidAmount(scanner);
                    String newCat = getNonEmptyInput(scanner, "Enter the new category: ");
                    String newDate = getValidDate(scanner);
                    String newDesc = getNonEmptyInput(scanner, "Enter the new description: ");
                    user.editExpense(editId, newAmount, newCat, newDate, newDesc);
                    break;

                case 8:
                    user.saveToFile(); // Save before exiting
                    doYouWantToContinue = false;
                    break;
            }
        }

        scanner.close();
    }


    public static int getValidChoice(Scanner scanner, int min, int max) {
        while (true) {
            System.out.print("Please enter your choice: ");
            try {
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice >= min && choice <= max) {
                    return choice;
                } else {
                    System.out.println("❌ Enter a valid choice between " + min + " and " + max);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input. Enter a number.");
            }
        }
    }

    public static double getValidAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter the amount: ");
            try {
                double amount = Double.parseDouble(scanner.nextLine());
                if (amount > 0) {
                    return amount;
                } else {
                    System.out.println("❌ Amount must be greater than 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Please enter a valid number.");
            }
        }
    }

    public static int getValidInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number. Try again.");
            }
        }
    }

    public static String getNonEmptyInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                return input;
            }
            System.out.println("❌ Input cannot be empty.");
        }
    }

    public static String getValidDate(Scanner scanner) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);

        while (true) {
            System.out.print("Enter the date (yyyy-MM-dd): ");
            String input = scanner.nextLine();
            try {
                sdf.parse(input);
                return input;
            } catch (ParseException e) {
                System.out.println("❌ Invalid date format. Try again.");
            }
        }
    }
}
