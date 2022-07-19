package self.eshop.controller;

import com.alibaba.fastjson.JSONObject;
import org.apache.ibatis.annotations.Param;
import org.json.JSONString;
import org.json.JSONStringer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import self.eshop.domain.ProductCategory;
import self.eshop.services.IProductServices;

import java.util.List;

@RestController
@RequestMapping("/productCatory")
public class ProductCatoryController {
    private final Logger logger = LoggerFactory.getLogger(ProductCatoryController.class);

    @Autowired
    private IProductServices productServices;


    @RequestMapping("/nextPage/{pageNo}/{pageSize}")
    public List<ProductCategory> getProductCatories(@PathVariable Integer pageNo,@PathVariable Integer pageSize){
            List<ProductCategory> result =productServices.getAll(pageNo,pageSize,"数码");
            logger.info("in next page controller");
            return result;
    }

    @RequestMapping("greet")
    private String  getPlainGreet(){
       return "hello.html";
    }

    @RequestMapping("/checkPathVariable/{type}/{name}")
    public void testVariable(@PathVariable int type,@PathVariable("name")  String categoryName) {
        logger.info("type:{},categoryName:{}",type,categoryName);
    }

    @RequestMapping(value = "/checkParams",params = {"type","categoryName"})
    public void testParam(int type,String categoryName){
        logger.info("type:{},categoryName:{}",type,categoryName);
    }

    @RequestMapping(value = "/checkRequestParams")
    public void testRequestParam(int type,@RequestParam("name") String categoryName){
        logger.info("type:{},categoryName:{}",type,categoryName);
    }

    @PostMapping(value = "/addCategory",consumes = "application/json")
    public String addCategory(@RequestBody  ProductCategory productCategory){
            return  JSONObject.toJSONString(productCategory);
    }

    @PostMapping(value = "/modifyCategory")
    public String modifyCategory(ProductCategory productCategory){
        logger.info("content:{}",JSONObject.toJSONString(productCategory));
        return "success";
    }

    @RequestMapping("/delete")
    public String deleteByIds( List<Integer> ids){
        logger.info("ids:{}",ids);
        return "done!";
    }



}
