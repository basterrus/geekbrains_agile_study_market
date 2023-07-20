package com.geekbrains.shop.dtos.order;

import com.geekbrains.shop.dtos.product.ProductDto;

import java.math.BigDecimal;
import java.util.List;

public class OrderDto {

    private Long id;
    private String uuid;
    private BigDecimal price;
    private List<ProductDto> productDtos;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ProductDto> getProductDtos() {
        return productDtos;
    }

    public void setProductDtos(List<ProductDto> productDtos) {
        this.productDtos = productDtos;
    }
}
