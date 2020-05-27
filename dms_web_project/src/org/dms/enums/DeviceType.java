package org.dms.enums;

public enum DeviceType {
    //1手机、2卡、3蓝牙打印机、4地磁、5道闸、6高位视频
    PHONE("手机", 1),
    CARD("信号卡", 2),
    PRINTER("蓝牙打印机", 3),
    MAG_SENSOR("地磁传感器", 4),
    BARRIER("道闸", 5),
    HIGH_VIDEO("高位视频", 6);
    private String typeName;
    private Integer type;

    DeviceType(String typeName, Integer type) {
        this.typeName = typeName;
        this.type = type;
    }

    public static DeviceType getDeviceTypeByType(Integer type) {
        if (type == null)
            return null;
        for (DeviceType deviceType : DeviceType.values()) {
            if (deviceType.type == type)
                return deviceType;
        }
        return null;
    }

    public Integer getType() {
        return type;
    }

    public String getTypeName() {
        return typeName;
    }
}
