package totallab.main;
import java.util.ArrayList;
import java.util.Scanner;

import totallab.exception.CustomerNotFoundException;
import totallab.exception.ProductNotFoundException;
import totallab.exception.ShopException;
import totallab.model.Product;
import totallab.service.ShoppingMall;



public class ShoppingMallSystem {
	private Scanner scanner;		// 사용자 입력 
	private ShoppingMall mall;
	
	public ShoppingMallSystem() throws ShopException {
		
		this.scanner = new Scanner(System.in);
		this.mall = new ShoppingMall("자바쇼핑몰");
		initializeData();	//초기 데이터 설정 
	}
	
	private void initializeData() throws ShopException{
		 // 기본 상품 데이터
        mall.addProduct("P001", "갤럭시 스마트폰", 800000, 10, "전자제품");
        mall.addProduct("P002", "애플 노트북", 1500000, 5, "전자제품");
        mall.addProduct("P003", "무선 이어폰", 150000, 20, "전자제품");
        mall.addProduct("P004", "게이밍 키보드", 120000, 8, "컴퓨터");
        mall.addProduct("P005", "모니터", 300000, 3, "컴퓨터");
        mall.addProduct("P006", "운동화", 89000, 15, "의류");
        mall.addProduct("P007", "청바지", 65000, 12, "의류");
        mall.addProduct("P008", "백팩", 45000, 7, "가방");
        
        // 기본 고객 데이터
        mall.addCustomer("C001", "김철수", "kim@email.com");
        mall.addCustomer("C002", "이영희", "lee@email.com");
        mall.addCustomer("C003", "박민수", "park@email.com");
        
        // 기본 관리자 데이터
        mall.addManager("M001", "정관리", "admin@mall.com", "운영팀", 1001);
	}

	public static void main(String[] args) throws ShopException {
		ShoppingMallSystem system = new ShoppingMallSystem();
		system.run();
	}


	private void run() throws ShopException {
		
		while(true) {
			displayMainMenu();
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			//사용자 선택에 따른 메서드 호출
			switch(choice) {
				case 1:				//  상품 관리
					handleProductManagement();
					break;
				case 2:
					handleCustomerManagement();	// 고객 관리  
					break;
				case 3:
					handleCartManagement();	// 장바구니 관리
					break;
				case 4:
					handleOrderManagement();		// 주문 관리
					break;
				case 5:
					mall.displayMallStatistics();		//통계 정보
					break;
				case 0:
					System.out.println("쇼핑몰 관리 시스템을 종료합니다. 감사합니다.");
					scanner.close();
					return;
				default:
					System.out.println("잘못된 선택입니다. 다시 입력해주세요.");
					break;
			}
	}
}


	private void handleOrderManagement() {
		while(true) {
			System.out.println("\n=== 주문 관리 ===");
			System.out.println("1. 주문하기");
			System.out.println("2. 고객 주문 내역 보기");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");	
			System.out.println();
			System.out.println();
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
				case 1: // 1. 주문하기
					togoOrder();
					break;
				case 2: // 2. 고객 주문 내역 보기
					
					break;
				case 0:	//메인 메뉴로 돌아가기
					return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}
	}
	}

	private void togoOrder() {
		
		System.out.print("고객 ID를 입력하세요: ");
		mall.placeOrder(scanner.nextLine());
	}

	private void handleCartManagement() throws ShopException {
		while(true) {
			System.out.println("\n=== 고객 관리 ===");
			System.out.println("1. 장바구니에 상품 추가");
			System.out.println("2. 장바구니 보기");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");		
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
				case 1: // 1. 장바구니에 상품 추가
					putInCart();
					break;
				case 2: // 2. 장바구니 보기
					togoDisplayCart();
					break;
				case 0:	//메인 메뉴로 돌아가기
					return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}}
		
	}

	private void togoDisplayCart() throws CustomerNotFoundException, ShopException {
		System.out.print("고객 ID를 입력하세요: ");
		mall.displayCart(scanner.nextLine());
	}

	private void putInCart()  {
		System.out.print("고객 ID를 입력하세요: ");
		String customerId = scanner.nextLine().trim();

		System.out.print("상품 ID를 입력하세요: ");
		String productId = scanner.nextLine().trim();
		
		System.out.print("상품 수량을 입력하세요: ");
		int productAmount = Integer.parseInt(scanner.nextLine());

		try {
			mall.addToCart(customerId, productId, productAmount);
				
		} catch (CustomerNotFoundException e) {
			System.out.println("장바구니 추가 중 오류: 존재하지 않는 고객ID입니다: " + customerId);
		} catch (ProductNotFoundException e) {
			System.out.println("장바구니 추가 중 오류: 존재하지 않는 제품ID입니다: " + productId);
		}
		
		

		
		

	}

	private void handleCustomerManagement() {
		while(true) {
			System.out.println("\n=== 고객 관리 ===");
			System.out.println("1. 고객 등록");
			System.out.println("2. 관리자 등록");
			System.out.println("3. 고객 주문 내역 조회 ");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");		
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
				case 1: // 고객 등록
					
					break;
				case 2: // 관리자 등록
					
					break;
				case 3: // 고객 주문 내역 조회
					
					break;
				case 0:	//메인 메뉴로 돌아가기
					return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}}
	}

	private void handleProductManagement() {
		while(true) {
			System.out.println("\n=== 상품 관리 ===");
			System.out.println("1. 상품 추가");
			System.out.println("2. 전체 상품 보기");
			System.out.println("3. 구매 가능한 상품 보기 ");
			System.out.println("4. 상품 검색 (이름)");
			System.out.println("5. 카테고리별 상품 보기");
			System.out.println("6. 재고 부족 상품 보기 (5개 이하)");
			System.out.println("0. 메인 메뉴로 돌아가기");
			System.out.print("선택하세요: ");		
			
			int choice = Integer.parseInt(scanner.nextLine());
			
			switch(choice) {
				case 1: // 상품 추가
					addNewProduct();
					break;
				case 2: // 전체 상품 보기
					mall.displayAllProducts();
					break;
				case 3: // 구매 가능한 상품 보기  
					mall.displayAvailableProducts();
					break;
				case 4:	//상품 검색 (이름)
					System.out.print("상품 이름을 입력하세요: ");		
					String searchName = scanner.nextLine();
					ArrayList<Product> results = mall.searchProductsByName(searchName);
					displaySearchResults("상품",searchName,results);
					break;
				case 5: //카테고리별 상품 보기
					scanner.nextLine();
					System.out.print("카테고리명을 입력하세요: ");		
					String searchCategory = scanner.nextLine();
					ArrayList<Product> results2 = mall.searchProductsByCategory(searchCategory);
					displaySearchResults("카테고리",searchCategory,results2);
					break;
				case 6:	//재고 부족 상품 보기 (5개 이하)
					mall.getLowStockProducts();
					
					break;
				case 0:	//메인 메뉴로 돌아가기
					return;
				default:
					System.out.println("잘못된 선택입니다.");
					break;
			}
	}
	}

	private void displaySearchResults(String searchType, String keyword, ArrayList<Product> results) {
		System.out.println("\n=== " +searchType+
				" '"+keyword+"' 검색 결과 ===");
		if(results.isEmpty()) {
		System.out.println("검색 결과가 없습니다.");
		} else {
		// 검색된 모든 정보 출력
			for(Product product: results) {
				System.out.println(product.toString());
			}
		System.out.println("총 " +results.size()+ "개의 상품을 찾았습니다.");
		}
		System.out.println();	
		}
	

	private void addNewProduct() {
		System.out.print("상품 ID를 입력하세요: ");
		String addProductId = scanner.nextLine().trim();
		System.out.print("상품명을 입력하세요: ");
		String addProductName = scanner.nextLine().trim();
		System.out.print("가격을 입력하세요: ");
		String num1 = scanner.nextLine().trim();
		System.out.print("재고수량을 입력하세요: ");
		String num2 = scanner.nextLine().trim();
		System.out.print("카테고리를 입력하세요: ");
		String addProductCategory = scanner.nextLine().trim();
		
		// 입력값 유효성 검사
		if(addProductId.isEmpty() || addProductName.isEmpty() 
				|| num1.isEmpty() || num2.isEmpty()|| addProductCategory.isEmpty()) {
			System.out.println("모든 필드를 입력해주세요.");
			return;
		}
		
		int addProductPrice = Integer.parseInt(num1);
		int addProductAmount = Integer.parseInt(num2);

		
		Product product = new Product(addProductId, addProductName, addProductPrice, addProductAmount, addProductCategory);
		try {
			mall.addProduct(addProductId, addProductName, addProductPrice, addProductAmount, addProductCategory);
		} catch (ShopException e) {
			System.out.println("에러가 발생했습니다: "+e.getMessage());
			return;
		}
		
		System.out.println("\n상품이 추가되었습니다: "+addProductName);
		
				
		
	}

	private void displayMainMenu() {
		System.out.println("\n=== 메인 메뉴 ===");
		System.out.println("1. 상품 관리");
		System.out.println("2. 고객 관리 ");
		System.out.println("3. 장바구니 관리");
		System.out.println("4. 주문 관리");
		System.out.println("5. 통계 정보");
		System.out.println("0. 종료");
		System.out.print("메뉴를 선택하세요: ");
		
	}
}


// 