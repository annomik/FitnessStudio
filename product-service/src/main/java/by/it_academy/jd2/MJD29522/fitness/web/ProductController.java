package by.it_academy.jd2.MJD29522.fitness.web;

import by.it_academy.jd2.MJD29522.fitness.core.dto.PageDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductCreateDTO;
import by.it_academy.jd2.MJD29522.fitness.core.dto.food.ProductDTO;
import by.it_academy.jd2.MJD29522.fitness.service.api.IProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final IProductService productService;

    public ProductController(IProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> addNewUser(@RequestBody ProductCreateDTO product) {
        productService.addNewProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDTO<ProductDTO>> getPage(
            @RequestParam(name = "page", required = false, defaultValue = "0") int numberOfPage,
            @RequestParam(name = "size", required = false, defaultValue = "20") int size){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getPage(numberOfPage, size));
    }

    @RequestMapping(path = "/{uuid}/dt_update/{dt_update}", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@PathVariable("uuid") UUID uuid,
                                    @PathVariable("dt_update") LocalDateTime dtUpdate,
                                    @RequestBody ProductCreateDTO product) {
        productService.update(uuid, dtUpdate, product);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
