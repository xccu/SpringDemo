package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper extends BaseMapper<Product> {
}