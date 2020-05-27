package org.dms.dao;

import org.dms.entity.Device;

import java.sql.SQLException;
import java.util.List;

public interface IDeviceDAO {
    /**
     * 查询总设备数
     * @return 总设备数
     */
    int queryTotalCount();

    /**
     * 根据分页查找设备
     * @param currentPage 当前页码
     * @param pageSize 页面大小
     * @return 分页设备
     */
    List<Device> queryDevicesByPage(int currentPage, int pageSize);

    /**
     * 根据编号查询设备是否存在
     * @param code 设备编号
     * @return 存在为真，否则假
     */
    boolean isExist(String code);

    /**
     * 根据编号删除设备
     * @param code 设备编号
     * @return 删除条数
     */
    int deleteDeviceByCode(String code);

    /**
     * 查询最大编号
     * @return 最大编号
     */
    String queryMaxCode();

    /**
     * 新增设备
     * @param device 设备实体类
     * @return 插入成功条数
     */
    int insertDevice(Device device);

    /**
     * 根据编号查询设备
     * @param code 编号
     * @return 设备实体类
     */
    Device queryDeviceByCode(String code);

    /**
     * 更新设备信息
     * @param device 设备实体类
     * @return 更新成功记录数
     */
    int updateDevice(Device device);
}
