package hello.core.singleton;

public class StatefulService {
    private int price;

    public void order(String name, int price) {
        System.out.println("name = " + name + " price = " + price);
        this.price = price;  // 여기가 문제!!!
    }

    // 공유 필드 price로 인해 테스트에서 문제가 생겼다
    // 따라서 이를 해결하기 위해 price를 상태로 생성하지 않고 위 함수에서 반환하도록 바꿀 수 있다.
    public int order2(String name, int price) {
        return price;
    }

    public int getPrice() {
        return this.price;
    }
}
