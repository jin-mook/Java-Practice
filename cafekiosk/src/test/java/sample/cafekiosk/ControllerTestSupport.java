package sample.cafekiosk;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import sample.cafekiosk.controller.order.OrderController;
import sample.cafekiosk.controller.product.ProductController;
import sample.cafekiosk.service.order.OrderService;
import sample.cafekiosk.service.product.ProductService;

@WebMvcTest(controllers = {
        OrderController.class,
        ProductController.class
})
public abstract class ControllerTestSupport {

    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected OrderService orderService;

    @Autowired
    protected ObjectMapper objectMapper;

    @MockBean
    protected ProductService productService;

}
