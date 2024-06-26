package EcomercePlatformDemoApp.service;

import EcomercePlatformDemoApp.configuration.JwtRequestFilter;
import EcomercePlatformDemoApp.dao.OrderDetailDao;
import EcomercePlatformDemoApp.dao.ProductDao;
import EcomercePlatformDemoApp.dao.UserDao;
import EcomercePlatformDemoApp.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {

    private static final String ORDER_PLACED = "Placed";
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    public void placeOrder (OrderInput orderInput) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList) {

            Product product = productDao.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userDao.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductActualPrice() * o.getProductQuantity(),
                    product,
                    user

            );

            orderDetailDao.save(orderDetail);

        }

    }

}
