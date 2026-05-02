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


    public OrderResult checkout(String userId, String productId, double price, String address) {

        if (!inventory.checkStock(productId)) return new OrderResult(false, null, "Out of stock");
        inventory.reserve(productId);


        if (!payment.charge(userId, price)) {
            inventory.release(productId);
            return new OrderResult(false, null, "Payment failed");
        }


        if (!shipping.isAvailable()) {
            payment.refund(userId, price); 
            inventory.release(productId);
            return new OrderResult(false, null, "Shipping down");
        }


        String trk = shipping.createLabel(address);
        shipping.schedulePickup(trk);
        email.send(userId, "Success", "Your code: " + trk);
        
        return new OrderResult(true, trk, "Done!");
    }
}


public class Main {
    public static void main(String[] args) {
 
        CheckoutFacade store = new CheckoutFacade();
        
 
        OrderResult result = store.checkout("user123", "laptop_01", 999.99, "123 Java Lane");
    }
}