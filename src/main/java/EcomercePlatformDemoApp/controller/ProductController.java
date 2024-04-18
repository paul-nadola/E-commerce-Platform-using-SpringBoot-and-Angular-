package EcomercePlatformDemoApp.controller;


import EcomercePlatformDemoApp.entity.ImageModel;
import EcomercePlatformDemoApp.entity.Product;
import EcomercePlatformDemoApp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    //make it a multipart request
    @PreAuthorize("hasRole('admin')")
    @PostMapping(value = {"/addNewProduct"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 //can upload multiple images at the same time
                                 @RequestPart("imageFile") MultipartFile[] file) {
        //return productService.addNewProduct(product);

        try {
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFile) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFile) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }
        return imageModels;
    }

    @GetMapping({"/getAllProducts"})
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping({"/deleteProductDetails/{productId}"})
    public void deleteProductDetails (@PathVariable("productId") Integer productId) {
        productService.deleteProductDetails(productId);
    }

}
