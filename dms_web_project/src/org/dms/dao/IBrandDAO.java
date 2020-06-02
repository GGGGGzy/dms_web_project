package org.dms.dao;

import org.dms.entity.Brand;

import java.util.List;

public interface IBrandDAO {
    /**
     * 新增品牌
     * @param brand 新增品牌名称
     * @return 新增成功条数
     */
    int insertBrand(Brand brand);

    /**
     * 查询所有品牌
     * @return 品牌列表
     */
    List<Brand> queryAllBrand();

    /**
     * 根据id删除品牌
     * @param id id
     * @return 成功删除条数
     */
    int deleteBrandById(Integer id);

    /**
     * 根据id查询品牌
     * @param id id
     * @return 品牌实体类
     */
    Brand queryBrandById(Integer id);

    /**
     * 根据id修改品牌
     * @param id id
     * @param brand 品牌实体类
     * @return 修改成功条数
     */
    int updateBrandById(Long id, Brand brand);
}
