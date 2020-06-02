package org.dms.dao.impl;

import org.dms.dao.IBrandDAO;
import org.dms.entity.Brand;
import org.dms.util.DBUtil;
import org.dms.util.EntityUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAOImpl implements IBrandDAO {
    @Override
    public int insertBrand(Brand brand) {
        String sql = "insert into db_device_brand (name, create_time, create_operator, update_time, update_operator) values(?,?,?,?,?)";
        Object[] params = {brand.getName(), brand.getCreateTime(), brand.getCreateOperator(), brand.getUpdateTime(), brand.getUpdateOperator()};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public List<Brand> queryAllBrand() {
        List<Brand> brands = new ArrayList<>();
        ResultSet rs = DBUtil.executeQuery("select * from db_device_brand", null);
        try {
            while (rs.next()) {
                Brand brand = new Brand();
                EntityUtil.setPropertiesByResultSet(brand, rs);
                brands.add(brand);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return brands;
    }

    @Override
    public int deleteBrandById(Integer id) {
        String sql = "delete from db_device_brand where id=?";
        Object[] params = {id};
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public Brand queryBrandById(Integer id) {
        String sql = "select * from db_device_brand where id=?";
        Object[] params = {id};
        Brand brand = null;
        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            if (rs.next()) {
                brand = new Brand();
                EntityUtil.setPropertiesByResultSet(brand, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return brand;
    }

    @Override
    public int updateBrandById(Long id, Brand brand) {
        String sql = "update db_device_brand set name=?, update_time=?, update_operator=? where id=?";
        Object[] params = {brand.getName(), brand.getUpdateTime(), brand.getUpdateOperator(), id};
        return DBUtil.executeUpdate(sql, params);
    }
}
