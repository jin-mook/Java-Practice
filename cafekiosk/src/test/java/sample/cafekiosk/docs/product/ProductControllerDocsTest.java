package sample.cafekiosk.docs.product;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation;
import org.springframework.restdocs.operation.preprocess.Preprocessors;
import org.springframework.restdocs.payload.JsonFieldType;
import org.springframework.restdocs.payload.PayloadDocumentation;
import org.springframework.test.web.servlet.ResultActions;
import sample.cafekiosk.controller.product.ProductController;
import sample.cafekiosk.controller.product.dto.request.ProductCreateRequest;
import sample.cafekiosk.docs.RestDocsSupport;
import sample.cafekiosk.domain.product.ProductSellingStatus;
import sample.cafekiosk.domain.product.ProductType;
import sample.cafekiosk.service.product.ProductService;
import sample.cafekiosk.service.product.request.ProductCreateServiceRequest;
import sample.cafekiosk.service.product.response.ProductResponse;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProductControllerDocsTest extends RestDocsSupport {

    private final ProductService productService = mock(ProductService.class);

    @Override
    protected Object initController() {
        return new ProductController(productService);
    }

    @DisplayName("신규 상품을 등록하는 API")
    @Test
    void createProduct() throws Exception {
        // given
        ProductCreateRequest request = ProductCreateRequest.builder()
                .type(ProductType.HANDMADE)
                .sellingStatus(ProductSellingStatus.SELLING)
                .name("아메리카노")
                .price(4000)
                .build();

        doReturn(
                ProductResponse.builder()
                        .id(1L)
                        .productNumber("001")
                        .type(ProductType.HANDMADE)
                        .sellingStatus(ProductSellingStatus.SELLING)
                        .name("아메리카노")
                        .build()
        ).when(productService).createProduct(any(ProductCreateServiceRequest.class));


        // when
        ResultActions resultActions = mockMvc.perform(post("/api/v1/products/new")
                .content(objectMapper.writeValueAsString(request))
                .contentType(MediaType.APPLICATION_JSON));

        // then
        resultActions.andExpect(status().isOk())
                .andDo(print())
                .andDo(MockMvcRestDocumentation.document("product-create",
                        Preprocessors.preprocessRequest(Preprocessors.prettyPrint()),
                        Preprocessors.preprocessResponse(Preprocessors.prettyPrint()),
                        PayloadDocumentation.requestFields(
                                PayloadDocumentation.fieldWithPath("type").type(JsonFieldType.STRING)
                                        .optional()
                                        .description("상품 타입"),
                                PayloadDocumentation.fieldWithPath("sellingStatus").type(JsonFieldType.STRING)
                                        .description("상품 판매상태"),
                                PayloadDocumentation.fieldWithPath("name").type(JsonFieldType.STRING)
                                        .description("상품 이름"),
                                PayloadDocumentation.fieldWithPath("price").type(JsonFieldType.NUMBER)
                                        .description("상품 가격")
                        ),
                        PayloadDocumentation.responseFields(
                                PayloadDocumentation.fieldWithPath("code").type(JsonFieldType.NUMBER)
                                        .description("코드"),
                                PayloadDocumentation.fieldWithPath("status").type(JsonFieldType.STRING)
                                        .description("상태"),
                                PayloadDocumentation.fieldWithPath("message").type(JsonFieldType.STRING)
                                        .description("메시지"),
                                PayloadDocumentation.fieldWithPath("data").type(JsonFieldType.OBJECT)
                                        .description("응답 데이터"),
                                PayloadDocumentation.fieldWithPath("data.id").type(JsonFieldType.NUMBER)
                                        .description("상품 아이디"),
                                PayloadDocumentation.fieldWithPath("data.productNumber").type(JsonFieldType.STRING)
                                        .description("상품 번호"),
                                PayloadDocumentation.fieldWithPath("data.type").type(JsonFieldType.STRING)
                                        .description("상품 타입"),
                                PayloadDocumentation.fieldWithPath("data.sellingStatus").type(JsonFieldType.STRING)
                                        .description("상품 판매상태"),
                                PayloadDocumentation.fieldWithPath("data.name").type(JsonFieldType.STRING)
                                        .description("상품 이름"),
                                PayloadDocumentation.fieldWithPath("data.price").type(JsonFieldType.NUMBER)
                                        .description("상품 가격")
                        )
                ));
    }
}
