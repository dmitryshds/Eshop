package biz.bagira.shds.eshop.entity;

/**
 * Created by Dmitriy on 31.10.2016.
 */
public enum Payment {


    CASH("cash"), CREDITCARDS("creditcards");

    private String payment;



    Payment(String payment) {
        this.payment = payment;
    }




    public static String getPayment(Payment payment) {
        switch (payment) {
            case CASH:  return "cash";
            case CREDITCARDS: return "creditcards";
            default: return "unknown";
        }
    }
    public static Payment getPayment(String payment)
    {
        if (payment.equals("cash"))
        {
            return Payment.CASH;
        }
        else if (payment.equals("creditcards")){
            return Payment.CREDITCARDS;
        }
        else {
            return null;
        }

    }
}


