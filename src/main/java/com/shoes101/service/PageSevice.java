package com.shoes101.service;

import com.shoes101.vo.pageBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PageSevice {
    @Autowired
    pageBean pb;
    public pageBean<?> setTopageBean(Integer PageCode, Integer pageSize, List<?> list,Integer count) {
        pb.setBeanList(list);
        pb.setTotalCount(count);
        pb.setPageCode(PageCode);
        pb.setPageSize(pageSize);
        return pb;
    }
}
