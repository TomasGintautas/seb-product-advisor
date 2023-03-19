package lt.seb.productadvisor.models;

public enum Product {
    CURRENT_ACCOUNT("currentAccount"),
    CURRENT_ACCOUNT_PLUS("currentAccountPlus"),
    JUNIOR_SAVER_ACCOUNT("juniorSaverAccount"),
    STUDENT_ACCOUNT("studentAccount"),
    SENIOR_ACCOUNT("seniorAccount"),
    DEBIT_CARD("debitCard"),
    CREDIT_CARD("creditCard"),
    GOLD_CREDIT_CARD("goldCreditCard");

    private final String productName;

    Product(String productName) {
        this.productName = productName;
    }

    @Override
    public String toString() {
        return productName;
    }
}
