package totallab.model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Order {
    private String orderId;              // 주문 ID
    private Customer customer;           // 주문 고객
    private ArrayList<CartItem> items;   // 주문 상품들
    private LocalDateTime orderDate;     // 주문일시
    private int totalAmount;            // 총 주문금액
    private String status;              // 주문 상태
    
    


	// 생성자에서 총 금액 자동 계산
    public void updateStatus(String newStatus) {
    	for(CartItem item:items) {
    		totalAmount += item.getTotalPrice();
    	}
    }

    
    
    
	public String getOrderId() {
		return orderId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public ArrayList<CartItem> getItems() {
		return items;
	}

	public LocalDateTime getOrderDate() {
		return orderDate;
	}

	public int getTotalAmount() {
		return totalAmount;
	}

	public String getStatus() {
		return status;
	}
    
    
}