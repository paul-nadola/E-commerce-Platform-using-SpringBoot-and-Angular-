package EcomercePlatformDemoApp.service;

import EcomercePlatformDemoApp.dao.ProductDao;
import EcomercePlatformDemoApp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }
}
