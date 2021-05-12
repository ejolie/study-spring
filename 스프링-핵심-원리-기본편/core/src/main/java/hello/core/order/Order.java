package hello.core.order;

public class Order {

    private Long memberId;
    private String itemNmae;
    private int itemPrice;
    private int discountPrice;

    public Order(Long memberId, String itemNmae, int itemPrice, int discountPrice) {
        this.memberId = memberId;
        this.itemNmae = itemNmae;
        this.itemPrice = itemPrice;
        this.discountPrice = discountPrice;
    }

    public int caculatePrice() {
        return itemPrice - discountPrice;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getItemNmae() {
        return itemNmae;
    }

    public void setItemNmae(String itemNmae) {
        this.itemNmae = itemNmae;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(int discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "memberId=" + memberId +
                ", itemNmae='" + itemNmae + '\'' +
                ", itemPrice=" + itemPrice +
                ", discountPrice=" + discountPrice +
                '}';
    }
}
