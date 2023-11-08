package refactoring.problema3.model;

public class Product {
    private int itemId;
    private String item;
    private int quantity;

    public Product(int itemId, String item, int quantity) {
        this.itemId = itemId;
        this.item = item;
        this.quantity = quantity;
    }

    public int getItemId() {
        return itemId;
    }

    public String getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
