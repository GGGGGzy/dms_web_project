package org.dms.service.impl;

import org.dms.dao.IBrandDAO;
import org.dms.dao.impl.BrandDAOImpl;
import org.dms.entity.Brand;
import org.dms.service.IBrandService;

import java.util.List;

public class BrandServiceImpl implements IBrandService {

    private IBrandDAO brandDAO = new BrandDAOImpl();

    @Override
    public int addBrand(Brand brand) {
        return brandDAO.insertBrand(brand);
    }

    @Override
    public List<Brand> getAllBrand() {
        return brandDAO.queryAllBrand();
    }

    @Override
    public int deleteBrandById(Integer id) {
        return brandDAO.deleteBrandById(id);
    }

    @Override
    public Brand getBrandById(Integer id) {
        return brandDAO.queryBrandById(id);
    }

    @Override
    public int updateBrand(Brand brand) {
        return brandDAO.updateBrandById(brand.getId(), brand);
    }
}
