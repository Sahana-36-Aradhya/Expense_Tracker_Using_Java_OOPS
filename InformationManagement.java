package Mini_Project;

import java.io.*;
import java.util.List;

public class InformationManagement {
	 public static void saveExpensesToFile(List<Expense> expenseList, String fileName) {
	        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
	            for (Expense e : expenseList) {
	                writer.write(e.getId() + "," + e.getAmount() + "," + e.getCategory() + "," + e.getDate() + "," + e.getDescription());
	                writer.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving expenses: " + e.getMessage());
	        }
	    }

	    // Method to load expenses from the file
	    public static void loadExpensesFromFile(List<Expense> expenseList, String fileName, User user) {
	        // Ensure the file exists before attempting to load
	        File file = new File(fileName);
	        if (!file.exists()) {
	            try {
	                file.createNewFile(); // Create the file if it doesn't exist
	            } catch (IOException e) {
	                System.out.println("Error creating file: " + e.getMessage());
	            }
	        }

	        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                String[] parts = line.split(",", 5);
	                if (parts.length == 5) {
	                    int id = Integer.parseInt(parts[0]);
	                    double amount = Double.parseDouble(parts[1]);
	                    String category = parts[2];
	                    String date = parts[3];
	                    String description = parts[4];

	                    Expense expense = new Expense(id, amount, category, date, description);
	                    expenseList.add(expense);
	                    user.setExpenses(user.getExpenses() + amount);
	                }
	            }
	        } catch (FileNotFoundException e) {
	            // File may not exist at first run — no problem
	        } catch (IOException e) {
	            System.out.println("Error loading expenses: " + e.getMessage());
	        }
	    }
}
