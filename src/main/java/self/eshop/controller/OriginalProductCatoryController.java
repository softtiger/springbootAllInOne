package self.eshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import self.eshop.domain.ProductCategory;
import self.eshop.services.IProductServices;

import java.util.List;

@Controller
@RequestMapping("/originalProductCatory")
public class OriginalProductCatoryController {


        @RequestMapping("/greet")
        public String  getPlainGreet(){
                return "hello";
        }

    @RequestMapping("/greet2")
    public String  getPlainGreet2(){
        return "/hello";
    }
    @RequestMapping("/greet3")
    public String  getPlainGreet3(){
        return "/hello.html";
    }
}
