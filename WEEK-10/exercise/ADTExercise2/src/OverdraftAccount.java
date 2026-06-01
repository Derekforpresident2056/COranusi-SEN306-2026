// OverdraftAccount.java
public class OverdraftAccount extends BankAccount {
    
    // Define the maximum allowed negative balance limit
    private static final double OVERDRAFT_LIMIT = -500.0;

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) {
            return; // Ignore invalid negative withdrawal amounts
        }
        
        // Calculate what the balance would look like if we allow this
        double newBalance = balance - amount; 
        
        // Check if the new balance stays within the -500 limit
        if (newBalance >= OVERDRAFT_LIMIT) {
            balance = newBalance; // Direct access to the protected 'balance' field
            System.out.println("Withdrew " + amount + ", new balance: " + balance);
        } else {
            System.out.println("Overdraft limit exceeded");
        }
    }

    @Override
    public void deposit(double amount) {
        // Use the parent's deposit logic but add console logging as requested
        super.deposit(amount);
        System.out.println("Deposited " + amount + ", new balance: " + balance);
    }
}