// Main.java
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Testing Regular BankAccount ---");
        BankAccount regular = new BankAccount();
        regular.deposit(100);
        regular.withdraw(150); // Should fail/do nothing because regular account doesn't allow negative balance
        System.out.println("Regular Balance: " + regular.getBalance());

        System.out.println("\n--- Testing OverdraftAccount ---");
        OverdraftAccount overdraft = new OverdraftAccount();
        overdraft.deposit(100);
        
        // This drops the balance to -100 (Allowed, it's above -500)
        overdraft.withdraw(200); 
        
        // This would drop the balance to -550 (Should fail/trigger limit exceeded)
        overdraft.withdraw(450); 
        
        System.out.println("Final Overdraft Balance: " + overdraft.getBalance());
    }
}