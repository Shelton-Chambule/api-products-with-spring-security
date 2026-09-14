package project.product.spring.security.controller;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import project.product.spring.security.dto.product.ProductRequest;
import project.product.spring.security.dto.product.ProductResponse;
import project.product.spring.security.services.ProductServices;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductServices productServices;

    public ProductController(ProductServices productServices) {
        this.productServices = productServices;
    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> saveProduct(@RequestBody @Valid ProductRequest productRequest) {
        ProductResponse product = productServices.saveProduct(productRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    @GetMapping("/find/all")
    public ResponseEntity<List<ProductResponse>> findAll(){
        List<ProductResponse> products = productServices.findAll();
        return ResponseEntity.ok().body(products);
    }

    @PutMapping("/{productId}")
    public  ResponseEntity<ProductResponse> update(@PathVariable  Long productId,  @ Valid @RequestBody ProductRequest productRequest){
        ProductResponse product = productServices.update(productId,productRequest);
        return ResponseEntity.ok().body(product);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<Void> delete(@PathVariable Long productId){
        productServices.delete(productId);
        return ResponseEntity.noContent().build();
    }
}
