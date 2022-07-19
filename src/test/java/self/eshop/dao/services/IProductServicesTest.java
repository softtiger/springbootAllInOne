package self.eshop.dao.services;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import self.eshop.domain.ProductCategory;
import self.eshop.services.IProductServices;

import java.sql.Date;
import java.util.List;

@SpringBootTest
class IProductServicesTest {

    @Autowired
    IProductServices productServices;

    @Test
    void addProductCategory() {
        ProductCategory productCategory = new ProductCategory();
      //  productCategory.setCategoryId(2);
        productCategory.setCategoryName("数码");
        productCategory.setCategoryType(Short.valueOf("3"));
        productCategory.setCreateTime(new Date(System.currentTimeMillis()));
        productServices.addProductCategory(productCategory);

    }

    @Test
    void getAll() {
        List<ProductCategory> productCategoryList = productServices.getAll(1,5 ,"家居");
        productServices.getAll(0,5 ,"家居");
        productServices.getAll(0,5 ,"家居");
        System.out.printf(" total result is %d",productCategoryList.size());
    }
}