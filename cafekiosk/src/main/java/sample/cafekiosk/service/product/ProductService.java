package sample.cafekiosk.service.product;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sample.cafekiosk.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.domain.product.Product;
import sample.cafekiosk.domain.product.ProductRepository;
import sample.cafekiosk.domain.product.ProductSellingStatus;
import sample.cafekiosk.domain.product.ProductType;
import sample.cafekiosk.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.service.product.response.ProductResponse;

import java.util.List;
import java.util.stream.Collectors;

/**
 * readOnly = true : 읽기 전용
 * CRUD 에서 CUD 동작 X / only Read
 * JPA : CUD 스탭샷 저장, 변경감지 X (성능 향상)
 *
 * CQRS - Command(CUD) / Query 분리 -> 이에 대한 첫 시작으로 readOnly 를 분리하는게 중요하다고 생각한다.
 * Read 서비스만 따로 만들고, Command 서비스만 따로 만들 수 있다.
 * 각 서비스 별로 dataSource도 분리 가능
 * 일반적으로는 Read 로직이 훨씬 빈도수가 높다
 */
@Transactional(readOnly = true)
@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductNumberFactory productNumberFactory;

    // 동시성 이슈 -> 빈도수가 낮다면 상품번호에 unique를 달고 재시도 로직을 작성해도 된다.
    // 정책을 001의 값이 아닌 uuid 같은 값을 이용하는 방법도 있다.
    @Transactional
    public ProductResponse createProduct(ProductCreateServiceRequest request) {
        // productNumber
        // 001 002 003 004
        // DB에서 마지막 저장된 Product의 상품 번호를 읽어와서 +1
        // 009 -> 010
        String nextProductNumber = productNumberFactory.createNextProductNumber();

        Product product = request.toEntity(nextProductNumber);
        Product savedProduct = productRepository.save(product);

        return ProductResponse.of(savedProduct);
    }


    public List<ProductResponse> getSellingProducts() {
        List<Product> products = productRepository.findAllBySellingStatusIn(ProductSellingStatus.forDisplay());

        return products.stream()
                .map(ProductResponse::of)
                .collect(Collectors.toList());
    }


//    private String createNextProductNumber() {
//        String latestProductNumber = productRepository.findLatestProductNumber();
//        if (latestProductNumber == null) {
//            return "001";
//        }
//        int latestProductNumberInt = Integer.parseInt(latestProductNumber);
//        int nextProductNumberInt = latestProductNumberInt + 1;
//
//        return String.format("%03d", nextProductNumberInt);
//    }

}
