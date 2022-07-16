package self.eshop.services.impl;

import org.junit.jupiter.api.Test;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import self.eshop.domain.ProductCategory;
import self.eshop.services.IProductServices;

import java.sql.Date;
import java.util.List;

@SpringBootTest
class ProductServicesTest {

    @Autowired
    IProductServices productServices;

    @Test
    void addProductCategory() {
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(2);
        productCategory.setCategoryName("电器");
        productCategory.setCategoryType(Short.valueOf("1"));
        productCategory.setCreateTime(new Date(System.currentTimeMillis()));
        productServices.addProductCategory(productCategory);

    }

    @Test
    void getAll() {
        List<ProductCategory> productCategoryList = productServices.getAll();
        ProductCategory productCategory = productCategoryList.get(1);
        LoggerFactory.getLogger(this.getClass()).info(" productCategoryName:{}",productCategory.getCategoryName());
    }
}