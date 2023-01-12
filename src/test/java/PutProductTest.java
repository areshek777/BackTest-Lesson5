import com.github.javafaker.Faker;
import api.ProductService;
import dto.Product;
import utils.RetrofitUtils;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import retrofit2.Response;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;


public class PutProductTest {

    static ProductService productService;
    Product product = null;
    Faker faker = new Faker();
    int id;

    @BeforeAll
    static void beforeAll() {
        productService = RetrofitUtils.getRetrofit()
                .create(ProductService.class);
    }

    @BeforeEach
    void setUp() {
        product = new Product()
                .withId(4)
                .withTitle(faker.food().ingredient())
                .withCategoryTitle("Food")
                .withPrice((int) (Math.random() * 110));
    }

    @Test
    void createsProductInFoodCategoryTest() throws IOException {
        Response<Product> response1 = productService.modifyProduct(product)
                .execute();
        id =  response1.body().getId();
        assertThat(response1.isSuccessful(), CoreMatchers.is(true));
    }


}