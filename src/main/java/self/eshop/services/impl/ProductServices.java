package self.eshop.services.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import self.eshop.dao.ProductCategoryMapper;
import self.eshop.services.IProductServices;
import self.eshop.domain.ProductCategory;

import java.util.HashMap;
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
    public List<ProductCategory> getAll(int pageNo, int pageSize, String categoryName) {
        PageHelper.startPage(pageNo,pageSize);
        List<ProductCategory> productCategoryList = productCategoryDao.findAll(categoryName);
        PageInfo<ProductCategory> pageInfo = new PageInfo<>(productCategoryList);
        System.out.printf("total page:%d,total number:%d", pageInfo.getPages(),pageInfo.getTotal());
        return productCategoryList;
    }
}
