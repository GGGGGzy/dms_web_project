package org.dms.enums;

public enum DeviceStatus {
    NEW("新增", 1),
    REPERTORY("库存", 2),
    RECEIVE("发放", 3),
    REPORT("待修", 4),
    REPAIR("维修", 5),
    LOSE("丢失报废", 6),
    REPLACE("更换", 7);

    private String statusName;
    private Integer status;

    DeviceStatus(String statusName, Integer status) {
        this.statusName = statusName;
        this.status = status;
    }

    public String getStatusName() {
        return statusName;
    }

    public Integer getStatus() {
        return status;
    }

    public static DeviceStatus getDeviceStatusByStatus(Integer status) {
        if (status == null)
            return null;
        for (DeviceStatus deviceStatusEnum : DeviceStatus.values()) {
            if (deviceStatusEnum.getStatus().equals(status))
                return deviceStatusEnum;
        }
        return null;
    }
}
