package com.Services;

import java.util.List;

import com.Beans.BrandsBean;



public interface BrandServices {
	public String createBrand(BrandsBean brand);
	public List<BrandsBean> getAllBrands();
	public String deleteBrand(Long id);
	public BrandsBean editBrand(BrandsBean brand);
	public BrandsBean getBrandById(Long id);
}
