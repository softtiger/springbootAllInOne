package self.eshop.services;

import self.eshop.domain.ProductCategory;

import java.util.List;

public interface IProductServices {

     public void addProductCategory(ProductCategory productCategory);


     public List<ProductCategory> getAll(int pageNo,int pageSize,String categoryName);
}
