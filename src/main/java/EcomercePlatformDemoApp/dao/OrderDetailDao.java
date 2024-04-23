package EcomercePlatformDemoApp.dao;

import EcomercePlatformDemoApp.entity.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailDao extends CrudRepository<OrderDetail, Integer> {

}
