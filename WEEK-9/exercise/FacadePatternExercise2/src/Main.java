
class Inventory {
    boolean checkStock(String productId) { return true; }
    void reserve(String productId) { System.out.println("Reserved " + productId); }
    void release(String productId) { System.out.println("Released " + productId); }
}

class Payment {
    boolean charge(String userId, double amount) { return true; }
    void refund(String userId, double amount) { System.out.println("Refunded " + amount); }
}

class Shipping {
    String createLabel(String address) { return "TRK" + System.currentTimeMillis(); }
    void schedulePickup(String label) { System.out.println("Pickup scheduled for " + label); }
    boolean isAvailable() { return true; }
}

class Email {
    void send(String to, String subject, String body) { System.out.println("Email to " + to); }
}


class TaxCalculator {

    double calculateTax(double price, String address) {

        if (address.contains("CA")) {
            return price * 0.08;
        }
        return 0.0;
    }
}


class Logger {
    // Records every attempt (Exercise 2 Requirement)
    void log(String userId, boolean success) {
        System.out.println("[LOG " + System.currentTimeMillis() + "] User: " 
                           + userId + " | Success: " + success);
    }
}


class OrderResult {
    private final boolean success;
    private final String trackingNumber;
    private final String message;

    public OrderResult(boolean success, String trackingNumber, String message) {
        this.success = success;
        this.trackingNumber = trackingNumber;
        this.message = message;
    }
}


class CheckoutFacade {

    private Inventory inventory = new Inventory();
    private Payment payment = new Payment();
    private Shipping shipping = new Shipping();
    private Email email = new Email();
    private TaxCalculator taxCalculator = new TaxCalculator(); 
    private Logger logger = new Logger();                       

 
    public OrderResult checkout(String userId, String productId, double price, String address) {
        

        double tax = taxCalculator.calculateTax(price, address);
        double totalPrice = price + tax;


        if (!inventory.checkStock(productId)) {
            logger.log(userId, false); // Log failure
            return new OrderResult(false, null, "Out of stock");
        }
        inventory.reserve(productId);


        if (!payment.charge(userId, totalPrice)) {
            inventory.release(productId);
            logger.log(userId, false);
            return new OrderResult(false, null, "Payment failed");
        }


        if (!shipping.isAvailable()) {
            payment.refund(userId, totalPrice);
            inventory.release(productId);
            logger.log(userId, false);
            return new OrderResult(false, null, "Shipping down");
        }

        
        String trk = shipping.createLabel(address);
        shipping.schedulePickup(trk);
        
        
        email.send(userId, "Order Success", "Total Paid (inc. tax): " + totalPrice);
        
        logger.log(userId, true);
        return new OrderResult(true, trk, "Done!");
    }
}


public class Main {
    public static void main(String[] args) {

        CheckoutFacade store = new CheckoutFacade();
        

        store.checkout("user123", "laptop_01", 1000.00, "123 Java Lane, CA");
    }
}