package org.dms.dao.impl;

import org.dms.dao.IDeviceDAO;
import org.dms.entity.Device;
import org.dms.util.DBUtil;
import org.dms.util.EntityUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeviceDAOImpl implements IDeviceDAO {
    @Override
    public int queryTotalCount() {
        int totalCount = 0;
        try {
            ResultSet rs = DBUtil.executeQuery("select count(1) from db_device", null);
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return totalCount;
    }

    @Override
    public List<Device> queryDevicesByPage(int currentPage, int pageSize) {
        List<Device> devices = new ArrayList<>();
        String sql = "select * from db_device order by code limit ?,?";
        Object[] params = {(currentPage - 1) * pageSize, pageSize};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            while (rs.next()) {
                Device device = new Device();
                EntityUtil.setPropertiesByResultSet(device, rs);
//                device.setCode(rs.getString("code"));
//                device.setParent(rs.getString("parent"));
//                device.setBatch(rs.getString("batch"));
//                device.setType(rs.getInt("type"));
//                device.setBrand(rs.getString("brand"));
//                device.setName(rs.getString("name"));
//                device.setModel(rs.getString("model"));
//                device.setImei1(rs.getString("imei1"));
//                device.setImei2(rs.getString("imei2"));
//                device.setMeid(rs.getString("meid"));
//                device.setSim(rs.getString("sim"));
//                device.setSn(rs.getString("sn"));
//                device.setStatus(rs.getInt("status"));
//                device.setNote(rs.getString("note"));
//                device.setCreateTime(rs.getTimestamp("create_time"));
//                device.setCreateOperator(rs.getString("create_operator"));
//                device.setUpdateTime(rs.getTimestamp("update_time"));
//                device.setUpdateOperator(rs.getString("update_operator"));
                devices.add(device);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return devices;
    }

    @Override
    public boolean isExist(String code) {
        return DBUtil.isExist("select count(1) from db_device where code=?", new Object[]{code});
    }

    @Override
    public int deleteDeviceByCode(String code) {
        Object[] params = {code};
        DBUtil.executeUpdate("delete from db_device_receive_detail where device_code=?", params);
        DBUtil.executeUpdate("delete from db_device_refund_detail where device_code=?", params);
        DBUtil.executeUpdate("delete from db_device_report_detail where device_code=?", params);
        DBUtil.executeUpdate("delete from db_device_repair_detail where device_code=?", params);
        DBUtil.executeUpdate("delete from db_device_lose_detail where device_code=?", params);
        return DBUtil.executeUpdate("delete from db_device where code=?", params);
    }

    @Override
    public String queryMaxCode() {
        String maxCode = null;
        ResultSet rs = DBUtil.executeQuery("select max(code) from db_device", null);
        try {
            if (rs.next()) {
                maxCode = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return maxCode;
    }

    @Override
    public int insertDevice(Device device) {
        String sql = "insert into db_device values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {
                device.getCode(),
                device.getParent(),
                device.getBatch(),
                device.getType(),
                device.getBrand(),
                device.getName(),
                device.getModel(),
                device.getImei1(),
                device.getImei2(),
                device.getMeid(),
                device.getSim(),
                device.getSn(),
                device.getStatus(),
                device.getNote(),
                device.getCreateTime(),
                device.getCreateOperator(),
                device.getUpdateTime(),
                device.getUpdateOperator()
        };
        return DBUtil.executeUpdate(sql, params);
    }

    @Override
    public Device queryDeviceByCode(String code) {
        Device device = null;
        String sql = "select * from db_device where code=?";
        Object[] params = {code};
        ResultSet rs = DBUtil.executeQuery(sql, params);
        try {
            if (rs.next()) {
                device = new Device();
                EntityUtil.setPropertiesByResultSet(device, rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DBUtil.closeAll();
        }
        return device;
    }

    @Override
    public int updateDevice(Device device) {
        String sql = "update db_device set parent=?, batch=?, type=?, brand=?, name=?, model=?, imei1=?, imei2=?, meid=?, sim=?, sn=?, note=?, update_time=?, update_operator=? where code=?";
        Object[] params = {device.getParent(), device.getBatch(), device.getType(), device.getBrand(), device.getName(), device.getModel(), device.getImei1(), device.getImei2(), device.getMeid(), device.getSim(), device.getSn(), device.getNote(), device.getUpdateTime(), device.getUpdateOperator(), device.getCode()};
        return DBUtil.executeUpdate(sql, params);
    }
}
