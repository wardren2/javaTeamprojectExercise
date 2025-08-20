package totallab.service;

import java.util.ArrayList;
import java.util.HashMap;


import totallab.exception.CustomerNotFoundException;
import totallab.exception.InsufficientStockException;
import totallab.exception.ProductNotFoundException;
import totallab.exception.ShopException;
import totallab.model.CartItem;
import totallab.model.Customer;
import totallab.model.Manager;
import totallab.model.Order;
import totallab.model.Product;

public class ShoppingMall {
    private HashMap<String, Product> products;      // 상품 관리
    private HashMap<String, Customer> customers;    // 고객 관리  
    private HashMap<String, Manager> managers;      // 관리자 관리
    private ArrayList<Order> orders;               // 주문 내역
    private HashMap<String, ArrayList<CartItem>> carts; // 고객별 장바구니
    private String mallName;
    private int orderCounter  ; // 주문 번호 생성용
    private int totalRevenue;
    
 public ShoppingMall(String string) {
		this.mallName = string;
		this.products = new HashMap<>();
		this.customers = new HashMap<>();
		this.managers = new HashMap<>();
		this.carts = new HashMap<>();
		this.orders = new ArrayList<>();
		
	}



	// 기본 CRUD
    public void addProduct(String productId, String name, int price, int stock, String category) throws ShopException{
    	
    	if(products.containsKey(productId)) {
    		throw new ShopException("이미 존재하는 제품입니다.: " + productId);
    		
    	} else {
    	
    	Product product = new Product(productId, name, price, stock, category);
    	products.put(productId, product);
    	System.out.println("상품이 추가되었습니다.");
    	}
    }
    
    
    
    public void displayAllProducts() {
    	
    	if(products.isEmpty()) {
    		System.out.println("등록된 상품이 없습니다.");
    		return;
    	}
    	
    	System.out.println("=== 전체 상품 목록 ===");
		for(Product product : products.values()) {
			System.out.println(product.toString());
		}
		System.out.println("총 " +products.size()+ "종류의 상품이 등록되어 있습니다.\n");
	}
    
    
    
    public void displayAvailableProducts() {// 재고 있는 상품만
    	
    	System.out.println("=== 재고 있는 상품 목록 ===");
		
		long availableCount = products.values().stream()
					.filter(Product::isInStock)
					.peek(System.out::println)
					.count();
		
		if(availableCount == 0) {
			System.out.println("현재 재고 있는 상품이 없습니다.");
		}
		
		System.out.println();
    }

    // 검색 기능 - 이름별 조회
    public ArrayList<Product> searchProductsByName(String name){
    	ArrayList<Product> results = new ArrayList<>();
    	
    	for(Product product : products.values()) {
    		if(product.getName().toLowerCase().contains(name.toLowerCase())) {
    			results.add(product) ;
    		}
    	}
    	
    	
    	return results;
    			
    		
    	
    	
    }


    // 검색 기능 - 카테고리별 조회
    public ArrayList<Product> searchProductsByCategory(String category){  
    	ArrayList<Product> results = new ArrayList<>();
    	
    	for(Product product : products.values()) {
    		if(product.getCategory().toLowerCase().contains(category.toLowerCase())) {
    			results.add(product);
    		}
    	}
    	
    	
    	return results;
    }
    
    // 재고 부족 상품 (5개 이하)
    public ArrayList<Product> getLowStockProducts(){
    	System.out.println("=== 재고 부족 상품 목록 (5개 이하) ===");
		
    	ArrayList<Product> results = new ArrayList<>();
    	
    	for(Product product : products.values()) {
    		if(product.isLowStock()) {
    			results.add(product);
    		}
    	}
    	
    	for(Product result: results) {
			System.out.println(result.toString());
		}
    	return results;
    }
  
    
    
    // 고객 관리 메서드 (예외 처리 포함)
    
    
    public void addCustomer(String id, String name, String email) throws ShopException{
    	if(customers.containsKey(id)) {
    		throw new ShopException("이미 존재하는 고객 ID입니다.: " + id);
    		
    	} else {
    	
    	Customer customer = new Customer(id, name, email);
    	customers.put(id, customer);
    	System.out.println("고객이 추가되었습니다.");
    	}
    }
    
    
    public void addManager(String id, String name, String email, String department, int employeeId) throws ShopException{
    	if(managers.containsKey(id)) {
    		throw new ShopException("이미 존재하는 매니저 ID입니다.: " + id);
    		
    	} else {
    	
	    	Manager manager = new Manager(id, name, email, department, employeeId);
	    	managers.put(id, manager);
	    	System.out.println("매니저가 추가되었습니다.");
    	}
    }
    
    
    
    public void displayCustomerOrders(String customerId) throws CustomerNotFoundException{
    	if(!customers.containsKey(customerId)) {
    		throw new CustomerNotFoundException("존재하지 않는 고객입니다: "+customerId);
    	} else {
    		
        	for(Order order : orders) {
        		System.out.println("아이디:"+order.getOrderId());
        		System.out.println("이름: "+order.getCustomer());
        		System.out.println("상태: "+order.getStatus());
        		System.out.println("주문일: "+order.getOrderDate());
        		System.out.println("총 금액: "+order.getTotalAmount());
        		}
        		
        	}
        	
        	
        	
    	}
    
    
    public void addToCart(String customerId, String productId, int quantity) 
    	    throws CustomerNotFoundException, ProductNotFoundException{
    	
    	
    	if(!customers.containsKey(customerId)) {
    		throw new CustomerNotFoundException("장바구니 추가 중 오류: 존재하지 않는 고객ID입니다: " + customerId);}
    	if(!products.containsKey(productId)) {
    		throw new ProductNotFoundException("장바구니 추가 중 오류: 존재하지 않는 제품ID입니다: " + productId);}
    	
    	Customer customer = customers.get(customerId);
    	Product product = products.get(productId);
    	

        
        if (!carts.containsKey(customerId)) {
            carts.put(customerId, new ArrayList<CartItem>());
        }
        
        
        ArrayList<CartItem> customerCart = carts.get(customerId);
        
        
        boolean found = false;
        for (CartItem item : customerCart) {
            if (item.getProduct().getProductId().equals(productId)) {
                
                item.addQuantity(quantity);//이미잇음 수량만증가
                found = true;
                break;
            }
        }
        
        if (!found) {
            CartItem newItem = new CartItem(product, quantity);
            customerCart.add(newItem);
            carts.put(customerId, customerCart);
        }
        
        
        	System.out.println("장바구니에 상품이 추가되었습니다: "+product.getName() +" ("+quantity+"개)");
        	
    	}
    
	
    
	

    
    
    
    
    	public void displayCart(String customerId) throws CustomerNotFoundException, ShopException{
    		if(!customers.containsKey(customerId)) {
        		throw new CustomerNotFoundException("존재하지 않는 고객ID입니다: " + customerId);}
    		
    		
        	Customer customer = customers.get(customerId);
            ArrayList<CartItem> customerCart = carts.get(customerId);

            
            if(customerCart ==null) {
            	throw new ShopException("카트가 비어있습니다: "+customerId);
            }
            
            
            System.out.println("==="+customer.getName()+"님의 장바구니 ===");
    		for(CartItem cart : customerCart) {
//                System.out.println("- "+cart.getProduct().getName() +" x "+cart.getQuantity() +" = "+cart.getTotalPrice() +"원");
                System.out.printf("- %s x %d개 = %,d원",cart.getProduct().getName(),cart.getQuantity(), cart.getTotalPrice());
                
    		}
    		
    		int totalAmount = 0;
    		for(CartItem item:customerCart) {
	    		totalAmount += item.getTotalPrice();
	    	}
    		
    		System.out.printf("\n 장바구니 합계: %,d원\n",totalAmount);
    		

    	}



       	// 핵심 처리 로직:
    	// 1. 고객 존재 확인 → CustomerNotFoundException
    	// 2. 장바구니 비어있는지 확인 → ShopException  
    	// 3. 재고 확인 및 차감 → InsufficientStockException
    	// 4. 주문 생성 및 저장
    	// 5. 장바구니 비우기
    	
    	public void placeOrder(String customerId){
    		 	
            ArrayList<CartItem> customerCart = carts.get(customerId);

            if(carts.isEmpty()) {
            	System.out.println("주문 처리 중 오류: 카트가 비었습니다: " + customerId);
            	return;}
            
    	
            
            try {
            	displayCart(customerId);
			} catch (ShopException e) {
				System.out.println("주문 처리 중 오류: 존재하지 않는 ID입니다: "+customerId);
				return;
			}
            
            
            
            
    	   for(CartItem item : customerCart) {
    	        Product product = item.getProduct();
    	        int requestedQuantity = item.getQuantity();
    	        
    	        if(product.getStock() < requestedQuantity) {
    	        	System.out.println("주문 처리 중 오류: 주문 실패: 재고 부족: " + product.getName() + 
    	                                  " (요청: " + requestedQuantity + ", 현재: " + product.getStock() + ")");
    	        	return;
    	        }
    	   }
    	   
    	   //주문 
    	   
    	   int totalMoney = 0;
    	   
    	   for(CartItem item : customerCart) {
    		   Product product = item.getProduct();
    		   int requestedQuantity = item.getQuantity();
    		   
    		   
    		   totalMoney += product.getPrice() * requestedQuantity;
    		   
    		   //줄이기
    	        try {
					product.reduceStock(requestedQuantity);
				} catch (InsufficientStockException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    	   }
    	   
    	   totalRevenue += totalMoney;

    	   orderCounter ++;
    	   
    	    System.out.println("\n=== 주문이 완료되었습니다! ===");
    	    
    	    System.out.printf("주문번호: ORD%03d%n",orderCounter);
    	    System.out.printf("결제금액: %,d원",totalMoney);
     	   
    	    
    	    
    	    
    	
    	}

    	
 
    
    	
    	
    	
    	public void displayMallStatistics() {
    		System.out.println("\n=== 통계 정보 ===");
    		
    		//통계 정보
	    		//총 상품 수
	    		int totalProducts = products.size();	
	    		//구매 가능한 상품 수
	    		long availableProducts = products.values().stream()	
	    								.filter(Product::isInStock).count(); 
	    		//재고 부족 상품 수
	    		long lowAmountProducts = products.values().stream()	
										.filter(Product::isLowStock).count(); 
	    		
	    		//등록 고객 수
	    		int totalCustomer = customers.size();
	    		
	    		
	    		//카테고리별 상품 수
	    		
	    		
	    		System.out.println("\n=== "+mallName+" 통계 정보 ===");
	    		System.out.println("총 상품 수: "+totalProducts+"개");
	    		System.out.println("구매 가능한 상품: "+availableProducts+"개");
	    		System.out.println("재고 부족 상품: "+lowAmountProducts+"개 (5개 이하)");
	    		System.out.println("등록 고객 수: "+totalCustomer+"명");
	    		System.out.println("총 주문 수: "+orderCounter+"건");
	    		System.out.printf("총 매출: %,d원",totalRevenue);
	    		
	    		System.out.println();
	    		System.out.println();
	    		
	    		System.out.println("카테고리별 상품 수:");
	    		
	    		HashMap<String, Integer> categoryCount = new HashMap<>();
	    		
	    		for(Product product : products.values()) {
					String categoryName = product.getCategory();
					if(categoryCount.containsKey(categoryName)) {
						categoryCount.put(categoryName, categoryCount.get(categoryName)+1);// 있으면 value만+하기
					} else {
						categoryCount.put(categoryName, 1);  //없으면 1 넣기
					}
					
	    		}
	    		
	    		for(String category : categoryCount.keySet()) {
	    			System.out.println("  "+category+": "+categoryCount.get(category)+"개");
	    		}
	    		
    	}
    	// 출력 내용:
    	// - 총 상품 수, 구매 가능한 상품 수, 재고 부족 상품 수 (5개 이하)
    	// - 등록 고객 수, 총 주문 수, 총 매출
    	// - 카테고리별 상품 수
	    		
//	    		카테고리별 상품 수:
//	    			  전자제품: 4개
//	    			  컴퓨터: 2개
//	    			  의류: 2개
//	    			  가방: 1개
    }
    

    
    












