package totallab.model;

public class CartItem {
    private Product product;  // 상품
    private int quantity;    // 수량
    
	public CartItem(Product product, int quantity) {
		super();
		this.product = product;
		this.quantity = quantity;
	}
	
	
    public void addQuantity(int amount) {// 수량 증가
    		this.quantity += amount;
	}
    
    public int getTotalPrice() {		// 총 가격 계산
    	return product.getPrice() * quantity;
	}
    		
    		
    // toString()으로 상품정보와 총액 표시
    @Override
	public String toString() {
		return "CartItem [product=" + product + ", quantity=" + quantity + ", total price=" + getTotalPrice() + "]";
	}

	



	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Product getProduct() {
		return product;
	}

    
    
}