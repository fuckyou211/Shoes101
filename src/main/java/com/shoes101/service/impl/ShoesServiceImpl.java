package com.shoes101.service.impl;


import com.shoes101.mapper.ShoesMapper;
import com.shoes101.service.ShoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoesServiceImpl implements ShoesService {

    @Autowired
    private ShoesMapper shoesMapper;

    public List getAllShoes()
    {
        return shoesMapper.getAllShoes();
    }
}
