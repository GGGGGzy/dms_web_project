package org.dms.service.impl;

import org.dms.dao.IDeviceDAO;
import org.dms.dao.impl.DeviceDAOImpl;
import org.dms.entity.Device;
import org.dms.entity.Pagination;
import org.dms.service.IDeviceService;
import org.dms.util.StringUtil;

import java.util.List;

public class DeviceServiceImpl implements IDeviceService {
    @Override
    public Pagination<Device> getPagination(int currentPage, int pageSize) {
        IDeviceDAO deviceDAO = new DeviceDAOImpl();
        int totalCount = deviceDAO.queryTotalCount();
        List<Device> data = deviceDAO.queryDevicesByPage(currentPage, pageSize);
        return new Pagination<>(currentPage, pageSize, totalCount, data);
    }

    @Override
    public int deleteDeviceByCode(String code) {
        IDeviceDAO deviceDAO = new DeviceDAOImpl();
        if (deviceDAO.isExist(code)) {
            return deviceDAO.deleteDeviceByCode(code);
        }
        return 0;
    }

    @Override
    public int addDevice(Device device) {
        IDeviceDAO deviceDAO = new DeviceDAOImpl();
        String maxCode = deviceDAO.queryMaxCode();
        device.setCode(StringUtil.generateNextCode(maxCode == null ? "DP" : maxCode, 10, 1));
        return deviceDAO.insertDevice(device);
    }

    @Override
    public Device getDeviceByCode(String code) {
        IDeviceDAO deviceDAO = new DeviceDAOImpl();
        if (!deviceDAO.isExist(code))
            return null;
        return deviceDAO.queryDeviceByCode(code);
    }

    @Override
    public int updateDevice(Device device) {
        IDeviceDAO deviceDAO = new DeviceDAOImpl();
        if (!deviceDAO.isExist(device.getCode()))
            return 0;
        return deviceDAO.updateDevice(device);
    }
}
