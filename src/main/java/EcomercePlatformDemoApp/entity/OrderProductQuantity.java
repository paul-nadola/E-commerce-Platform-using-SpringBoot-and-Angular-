package EcomercePlatformDemoApp.entity;

import javax.persistence.Entity;

public class OrderProductQuantity {


    private Integer productId;
    private Integer productQuantity;

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public Integer getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(Integer productQuantity) {
        this.productQuantity = productQuantity;
    }
}
