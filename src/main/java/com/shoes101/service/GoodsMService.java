package com.shoes101.service;

import javax.servlet.http.HttpServletRequest;

public interface GoodsMService {

    public String addShoesInformationAjax();

    public String shoesCatalogAjax(Integer parentId);

    public String upload(HttpServletRequest request);

    public String shoesPropertyAjax(Integer propertyId);

}
