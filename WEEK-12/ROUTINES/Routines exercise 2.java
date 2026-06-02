void processCustomer(String n, String a, double d, int t, String e, boolean g,
                     double[] orders, int x) {
    sumorders();
    processdiscount();
    notifyuser();
    sendemail();
}

void sumorders()
{
    for (int i = 0; i < x; i++) sum += orders[i];
}

void processdiscount()
{
    if (t == 1) disc = 0.1;
    else if (t == 2) disc = 0.2;
    double total = sum - sum * disc;   
}

void notifyuser()
{
    String msg = "Hello " + n + " of " + a + ", your total is " + total;
    if (g) msg += " (VIP)";
    System.out.println(msg);
}

void sendemail()
{
    if (e != null) sendEmail(e, msg);   
}