package self.eshop.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import self.eshop.domain.ProductCategory;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    List<ProductCategory> findAll(@Param("categoryName") String name);
}