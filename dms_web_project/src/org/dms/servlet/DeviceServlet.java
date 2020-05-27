package org.dms.servlet;

import org.dms.entity.Device;
import org.dms.entity.Pagination;
import org.dms.entity.User;
import org.dms.enums.DeviceStatus;
import org.dms.service.IDeviceService;
import org.dms.service.impl.DeviceServiceImpl;
import org.dms.util.EntityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;

public class DeviceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        IDeviceService deviceService = new DeviceServiceImpl();

        String method = request.getParameter("method");
        if (method != null) {
            if ("delete".equals(method)) {
                String code = request.getParameter("code");
                deviceService.deleteDeviceByCode(code);
            }
            else if ("addDevice".equals(method)) {
                Device device = new Device();
                EntityUtil.setPropertiesByRequest(device, request);
                User user = (User) request.getSession().getAttribute("user");
                device.setStatus(DeviceStatus.NEW.getStatus());
                device.setCreateOperator(user.getUsername());
                device.setUpdateOperator(user.getUsername());
                device.setCreateTime(new Timestamp(System.currentTimeMillis()));
                device.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                deviceService.addDevice(device);
            }
            else if ("detail".equals(method)) {
                String code = request.getParameter("code");
                request.setAttribute("device", deviceService.getDeviceByCode(code));
                request.getRequestDispatcher("detail/device.jsp").forward(request, response);
            }
            else if ("update".equals(method)) {
                Device device = new Device();
                EntityUtil.setPropertiesByRequest(device, request);
                User user = (User) request.getSession().getAttribute("user");
                device.setUpdateOperator(user.getUsername());
                device.setUpdateTime(new Timestamp(System.currentTimeMillis()));
                deviceService.updateDevice(device);
            }
        }
        int currentPage = 1, pageSize = 5;
        String cPage = request.getParameter("currentPage");
        String pSize = request.getParameter("pageSize");
        if (cPage != null) {
            currentPage = Integer.parseInt(cPage);
        }
        if (pSize != null) {
            pageSize = Integer.parseInt(pSize);
        }
        Pagination<Device> pagination = deviceService.getPagination(currentPage, pageSize);
        request.setAttribute("pagination", pagination);
        request.getRequestDispatcher("device.jsp").forward(request, response);
    }
}
