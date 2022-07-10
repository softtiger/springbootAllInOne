package self.eshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.eshop.dao.ProductCategoryMapper;
import self.eshop.services.IProductServices;
import self.eshop.domain.ProductCategory;

import java.util.List;

@Service
public class ProductServices implements IProductServices {

    @Autowired
    private ProductCategoryMapper productCategoryDao;


    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryDao.insert(productCategory);
    }

    @Override
    public List<ProductCategory> getAll() {
        return productCategoryDao.findAll();
    }
}
