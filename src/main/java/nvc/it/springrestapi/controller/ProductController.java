package nvc.it.springrestapi.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nvc.it.springrestapi.models.Product;
import nvc.it.springrestapi.models.Review;
import nvc.it.springrestapi.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping("")
    public ResponseEntity<Object> getAllProduct(){
        List<Product> products = productService.getProducts();
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK.!");
        map.put("data", products);
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK.!");
        map.put("data", productService.findById(id));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getProductByName(@PathVariable String name){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Search OK.!");
        map.put("data", productService.findByProductName(name));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Object> addProduct(@RequestBody Product product){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Add OK.!");
        map.put("data", productService.addProduct(product));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Product product){
        Product currentProduct = productService.findById(id).get();

        if(product.getName()!=null) 
            currentProduct.setName(product.getName());

        if(product.getPrice()!=null) 
            currentProduct.setPrice(product.getPrice());

        if(product.getUnit_in_stock()!=null) 
            currentProduct.setUnit_in_stock(product.getUnit_in_stock());

        if(product.getCreatedAt()!=null) 
            currentProduct.setCreatedAt(product.getCreatedAt());

        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Update OK.!");
        map.put("data", productService.updateProduct(id, currentProduct));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable String id, @RequestBody Review review){
        HashMap<String, Object> map = new HashMap<>();
        map.put("msg", "Reviews OK.!");
        map.put("data", productService.addReview(id, review));
        return new ResponseEntity<Object>(map, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable String id){
        HashMap<String, Object> map = new HashMap<>();
        if(!productService.deleteProduct(id)){
            map.put("msg", "Delete Error.! at id :" + id);
            return new ResponseEntity<Object>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        else{
            map.put("msg", "Delete Ok.! at id :" + id);
            return new ResponseEntity<Object>(map, HttpStatus.OK);
        }
        
    }
}
