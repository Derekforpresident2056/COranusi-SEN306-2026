
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
}

class Email {
    void send(String to, String subject, String body) { System.out.println("Email to " + to); }
}


class LegacyOrderProcessor {
    public void processOrder(String customerEmail, String itemCode, 
                             double amount, String deliveryAddress) {

        Inventory inv = new Inventory();
        Payment pay = new Payment();
        Shipping ship = new Shipping();
        Email email = new Email();

        if (!inv.checkStock(itemCode)) {
            System.out.println("Out of stock");
            return;
        }
        if (!pay.charge(customerEmail, amount)) {
            System.out.println("Payment fail");
            return;
        }
        inv.reserve(itemCode);
        String label = ship.createLabel(deliveryAddress);
        ship.schedulePickup(label);
        email.send(customerEmail, "Order", "Shipped");
        System.out.println("Order complete");
    }
}


class LegacyOrderFacade {
 
    private LegacyOrderProcessor legacyProcessor;


    public LegacyOrderFacade() {
        this.legacyProcessor = new LegacyOrderProcessor();
    }


    public void placeOrder(String email, String item, double price, String address) {
        System.out.println("--- Facade: Delegating to Legacy System ---");

        legacyProcessor.processOrder(email, item, price, address);
    }
}


public class Main {
    public static void main(String[] args) {

        LegacyOrderFacade orderSystem = new LegacyOrderFacade();


        orderSystem.placeOrder("student@pau.edu.ng", "Java_Book_01", 45.99, "Lagos, Nigeria");
    }
}