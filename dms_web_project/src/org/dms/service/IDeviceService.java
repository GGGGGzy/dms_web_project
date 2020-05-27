package org.dms.service;

import org.dms.entity.Device;
import org.dms.entity.Pagination;

public interface IDeviceService {
    /**
     * 根据参数获得分页数据
     * @param currentPage 当前页
     * @param pageSize 页面大小
     * @return 分页数据
     */
    Pagination<Device> getPagination(int currentPage, int pageSize);

    /**
     * 根据编号删除设备
     * @param code 设备编号
     * @return 执行成功条数
     */
    int deleteDeviceByCode(String code);

    /**
     * 新增设备
     * @param device 设备实体类
     * @return 新增成功条数
     */
    int addDevice(Device device);

    /**
     * 根据编号获取设备
     * @param code 编号
     * @return 设备实体类
     */
    Device getDeviceByCode(String code);

    /**
     * 更新设备信息
     * @param device 设备实体类
     * @return 更新成功记录条数
     */
    int updateDevice(Device device);
}
