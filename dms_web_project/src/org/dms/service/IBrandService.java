package org.dms.service;

import org.dms.entity.Brand;

import java.util.List;

public interface IBrandService {
    /**
     * 新增品牌
     * @param brand 品牌实体类
     * @return 新增成功条数
     */
    int addBrand(Brand brand);

    /**
     * 获取所有品牌
     * @return 品牌列表
     */
    List<Brand> getAllBrand();

    /**
     * 通过id删除品牌
     * @param id id
     * @return 成功删除条数
     */
    int deleteBrandById(Integer id);

    /**
     * 根据id查询品牌
     * @param id id
     * @return 品牌实体类
     */
    Brand getBrandById(Integer id);

    /**
     * 更新品牌
     * @param brand 品牌实体类，id不能为null
     * @return 更新成功条数
     */
    int updateBrand(Brand brand);
}
