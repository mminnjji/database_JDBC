
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        InsertData orderInsertion = new InsertData();
        SelectData orderQuery = new SelectData();
        UpdateData orderUpdate = new UpdateData();
        DeleteData orderDelete = new DeleteData();
        
        
        while (true) {
            System.out.println("메뉴를 선택하세요");
            System.out.println("1. 주문 전송");
            System.out.println("2. 주문 내역 및 정보 확인");
            System.out.println("3. 회원 정보 수정");
            System.out.println("4. 주문 내역 삭제");
            System.out.println("5. 종료");
            System.out.print("항목 입력: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // 개행 문자 소비
            
            if (choice == 1)
            {
            	 // 사용자로부터 새 주문 정보 입력 받기
                System.out.print("고객 ID를 입력하세요: ");
                int customerId = scanner.nextInt();
                scanner.nextLine(); // 개행 문자 소비

                // 현재 날짜와 시간을 포맷하여 문자열로 변환
                String orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

                // 제품 정보 입력 받기
                System.out.print("입력할 제품의 수를 입력하세요: ");
                int numberOfProducts = scanner.nextInt();
                int[] productIds = new int[numberOfProducts];
                int[] quantities = new int[numberOfProducts];

                for (int i = 0; i < numberOfProducts; i++) {
                    System.out.print("제품 ID를 입력하세요: ");
                    productIds[i] = scanner.nextInt();
                    System.out.print("수량을 입력하세요: ");
                    quantities[i] = scanner.nextInt();
                }

                // OrderInsertion 클래스를 사용하여 주문 삽입
                orderInsertion.insertOrder(customerId, orderDate, productIds, quantities);
            }
            else if (choice == 2) {
                // Select menu
                while (true) {
                    System.out.println("확인할 항목을 선택하세요");
                    System.out.println("1. 주문정보 확인");
                    System.out.println("2. 메뉴판매량 확인");
                    System.out.println("3. 메인메뉴로");
                    System.out.print("항목 입력: ");
                    int selectChoice = scanner.nextInt();
                    scanner.nextLine(); // 개행 문자 소비

                    if (selectChoice == 1) {
                        // Query with join and view
                        System.out.print("주문자 이름을 입력하세요: ");
                        String customerName = scanner.nextLine();
                        orderQuery.queryWithJoinAndView(customerName);
                    } else if (selectChoice == 2) {
                        // Query with aggregation and group by
                        System.out.print("메뉴 이름을 입력하세요: ");
                        String productName = scanner.nextLine();
                        orderQuery.queryWithAggregationAndGroupBy(productName);
                    } else if (selectChoice == 3) {
                        break;
                    } else {
                        System.out.println("잘못된 접근입니다. 다시 시도해주세요");
                    }
                }
            }
            else if (choice == 3) {
                // Update customer information
                System.out.print("수정할 사용자 아이디를 입력하세요: ");
                int customerId = scanner.nextInt();
                scanner.nextLine(); // consume newline character
                System.out.print("새로운 이름을 입력하세요: ");
                String newName = scanner.nextLine();
                System.out.print("새로운 전화번호를 입력하세요: ");
                String newPhone = scanner.nextLine();
                orderUpdate.updateCustomer(customerId, newName, newPhone);
            }
            else if (choice == 4) {
            	 System.out.print("삭제할 주문번호를 입력하세요: ");
                 int orderId = scanner.nextInt();
                 scanner.nextLine(); // consume newline character
                 orderDelete.deleteOrder(orderId);
            }
            else if (choice == 5) {
                break;
            }
            else {
            	System.out.println("잘못된 접근입니다. 다시 시도해주세요");
            }
        }

        scanner.close();
    }
}