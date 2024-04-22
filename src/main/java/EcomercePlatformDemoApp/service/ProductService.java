package EcomercePlatformDemoApp.service;

import EcomercePlatformDemoApp.dao.ProductDao;
import EcomercePlatformDemoApp.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductDao productDao;

    //checks if primary key is present, if present it performs an update functionality, if not it perfroms a create functionality
    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts() {
        //cast it into a list
        return (List<Product>) productDao.findAll();
    }

    public Product getProductDetailsById (Integer productId) {
        return productDao.findById(productId).get();
    }

    public void deleteProductDetails(Integer productId) {
        productDao.deleteById(productId);
    }
}
