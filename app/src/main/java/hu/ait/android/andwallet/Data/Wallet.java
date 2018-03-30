package hu.ait.android.andwallet.Data;

/**
 * Created by johnc on 10/19/2017.
 */

public class Wallet {

    private String expenseTitle;
    private String amount;
    private int value;
    private int imageResource;


    public Wallet(String expenseTitle, String amount, int value, int imageResource) {
        this.expenseTitle = expenseTitle;
        this.amount = amount;
        this.value = value;
        this.imageResource = imageResource;
    }

    public String getExpenseTitle() {
        return expenseTitle;
    }

    public void setExpenseTitle(String expenseTitle) {
        this.expenseTitle = expenseTitle;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setIV(int ImageResource) {
        this.imageResource = imageResource;
    }
}
