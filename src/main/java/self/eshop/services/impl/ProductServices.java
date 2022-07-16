package self.eshop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;
import self.eshop.dao.ProductCategoryMapper;
import self.eshop.services.IProductServices;
import self.eshop.domain.ProductCategory;

import java.time.Duration;
import java.util.List;


@Service
public class ProductServices implements IProductServices {


    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private ProductCategoryMapper productCategoryDao;


    private static final  String PRODUCT_CATEGORY_KEY="productCategory_list";
    private static final  long PRODUCT_CATEGORY_TIMEOUT=5000;


    @Override
    public void addProductCategory(ProductCategory productCategory) {
        productCategoryDao.insert(productCategory);
    }

    @Override
    public List<ProductCategory> getAll() {
        List<ProductCategory> result =  (List<ProductCategory>)redisTemplate.opsForValue().get(PRODUCT_CATEGORY_KEY);

        if  (null == result){
              result = productCategoryDao.findAll();

              redisTemplate.opsForValue().set(PRODUCT_CATEGORY_KEY,result, Duration.ofSeconds(PRODUCT_CATEGORY_TIMEOUT));

              redisTemplate.opsForValue().set(PRODUCT_CATEGORY_KEY+"_STRING","use StringRedisTemplate");
        }
        return result;

    }
}
