package com.abc.ecom.mapper;

import org.mapstruct.Mapper;

import com.abc.ecom.dto.ProductDTO;
import com.abc.ecom.model.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {

	ProductDTO mapProduct(Product product);
}
