package com.shoes101.service;

import com.shoes101.pojo.Addshoes;
import com.shoes101.pojo.Shoessku;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GoodsMService {

    public String addShoesInformationAjax();

    public String shoesCatalogAjax(Integer parentId);

    public String uploadShoespic(HttpServletRequest request,Integer shoesid);

    public String shoesPropertyAjax(Integer propertyId);

    public String addShoesForm(Addshoes addshoes, HttpServletRequest request);

    public String uploadColorpic(HttpServletRequest request, List<List<Shoessku>> shoessku,Integer shoesid);

    public String shoesShowAjax();

    public String editQuantitAjax(Integer shoesid);

    public String setQuantitAjax(List<Integer> quantity,List<Integer> skuid);

}
