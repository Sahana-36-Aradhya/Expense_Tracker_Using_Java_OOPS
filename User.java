package Mini_Project;

import java.util.ArrayList;

public class User implements ExpenseOperations{
	private int userId;
	private double expenses;
	private ArrayList<Expense> expenseList = new ArrayList<>();
	
	public User(int userId, double expenses) {
		this.userId = userId;
		this.expenses = expenses;
	}

	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	public double getExpenses() {
		return expenses;
	}
	
	public void setExpenses(double expenses) {
		this.expenses = expenses;
	}
	
	
	public void addExpense(double amount, String category, String date, String description) {
		int id = expenseList.size()+ 1;
		Expense expense = new Expense(id, amount, category, date, description);
		this.expenses += amount; // expenses is total amount each time the amount is added we will update the expenses with previous amount and current amount
		expenseList.add(expense);
	}
	
	public void removeExpense(int id) {
		boolean found = false;
		
		for(int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i); // expense is the var that retrives and stores the reference of arraylist as we r storing object 
			
			if(id == expense.getId()) {
				this.expenses -= expense.getAmount();
				expenseList.remove(i);
				System.out.println("Expense with ID " + id + " removed.");
				found = true;
				break;
			}
		}
		if(!found){
			System.out.println("Sorry, we couldn't find any expense with ID " + id);
		}
	}
	
	public void getExpensesByCategory(String category) {
		boolean found = false;
		
		for(int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i); 
			
			if(category.equalsIgnoreCase(expense.getCategory())) {
				System.out.println(expense);
				found = true;
			}
		}
		if(!found){
			System.out.println("Sorry, we couldn't find any expense with CATEGORY " + category);
		}
	}
	
	public void getExpensesByDate(String date) {
		boolean found = false;
		
		for(int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i); 
			
			if(date.equalsIgnoreCase(expense.getDate())) {
				System.out.println(expense);
				found = true;
			}
		}
		if(!found){
			System.out.println("Sorry, we couldn't find any expense with DATE " + date);
		}
	}
	
	public void getTotalExpense() {
		 System.out.println("The total expense is: ₹" + this.expenses);
	}
	
	public void displayProfile() {
		for(int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i);
			System.out.println(i+1 + ")" + expense);
		}
	}
	
	public void editExpense(int id, double newAmount, String newCategory, String newDate, String newDescription) {
		for(int i = 0; i < expenseList.size(); i++) {
			Expense expense = expenseList.get(i);
			
			if(expense.getId() == id) {
				this.expenses -= expense.getAmount();
	            this.expenses += newAmount;
	            
	            expense.setAmount(newAmount);
	            expense.setCategory(newCategory);
	            expense.setDate(newDate);
	            expense.setDescription(newDescription);
	            System.out.println("Expense updated successfully!");
	            return;
			}
		}
		System.out.println("Expense with ID" +id+" Not found");
	}
	
	public void saveToFile() {
        InformationManagement.saveExpensesToFile(this.expenseList, "expenses.txt");
    }

	public void loadFromFile() {
	    if (!expenseList.isEmpty()) {
	        expenseList.clear(); // Prevents duplicates
	        this.expenses = 0.0; // Reset total before reloading
	    }
	    InformationManagement.loadExpensesFromFile(this.expenseList, "expenses.txt", this);
	}
}
