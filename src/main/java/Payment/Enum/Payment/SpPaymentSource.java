package Payment.Enum.Payment;

public enum SpPaymentSource {
    MOBILE("MOBILE", 0),
    WEB("WEB", 1),
    SDK("SDK", 2);

    private String name;
    private int value;
    private SpPaymentSource(String name, int value){
        this.name = name;
        this.value = value;
    }
}
