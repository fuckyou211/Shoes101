package com.shoes101.service;

import com.shoes101.vo.pageBean;
import java.util.List;

public interface PageSevice {

    public pageBean<?> setTopageBean(Integer PageCode, Integer pageSize, List<?> list,Integer count);
}
