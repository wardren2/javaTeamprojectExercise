package totallab.model;

import totallab.exception.InsufficientStockException;

public class Product {
    private String productId;       // 상품 ID
    private String name;           // 상품명
    private int price;            // 가격
    private int stock;            // 재고
    private String category;      // 카테고리
    private double rating;        // 평점 (0.0 ~ 5.0)
    private int reviewCount;      // 리뷰 개수
    
    
    public Product(String productId, String name, int price, int stock, String category) {
		super();
		this.productId = productId;
		this.name = name;
		this.price = price;
		this.stock = stock;
		this.category = category;
	}
    
    
	// 생성자, getter 메서드들
    public void reduceStock(int quantity) throws InsufficientStockException{
    	if(stock>=quantity) {
    	stock -= quantity;
    	}
    }
    public void addStock(int quantity) {
    	stock += quantity;
    }
    
    public boolean isInStock() {
    	if(stock >0) {
    		return true;
    		} else {
    			return false;
    			}
    }
    
    public boolean isLowStock() {// 재고 5개 이하 체크 (요구사항)
    	if (stock<=5) {
    		return true;}
    	else {return false;}
	}
    

    @Override
	public String toString() {
		return 
				String.format("ID: %s, 상품명: %s, 가격: %,d원, 재고: %d개, 카테고리: %s, 평점: %.1f(%d리뷰), 상태: %s", 
						productId, name, price, stock, category, rating, reviewCount, isLowStock()? "재고부족":"재고충분");

				//				"Product [productId=" + productId + ", name=" + name + ", price=" + price + ", stock=" + stock
//				+ ", category=" + category + ", rating=" + rating + ", reviewCount=" + reviewCount + "]";
	}


	public void addReview(double newRating) {
    	
    }
	public String getProductId() {
		return productId;
	}
	public String getName() {
		return name;
	}
	public int getPrice() {
		return price;
	}
	public int getStock() {
		return stock;
	}
	public String getCategory() {
		return category;
	}
	public double getRating() {
		return rating;
	}
	public int getReviewCount() {
		return reviewCount;
	}
	
   

}
