package Mini_Project;

public interface ExpenseOperations {
	public void addExpense(double amount, String category, String date, String description);
	public void removeExpense(int id);
	public void getTotalExpense();
	public void getExpensesByCategory(String category);
	public void getExpensesByDate(String date);
	public void editExpense(int id, double newAmount, String newCategory, String newDate, String newDescription);
}
