package org.dms.servlet;

import org.dms.entity.Device;
import org.dms.entity.Pagination;
import org.dms.entity.User;
import org.dms.enums.DeviceStatus;
import org.dms.service.IDeviceService;
import org.dms.service.impl.BrandServiceImpl;
import org.dms.service.impl.DeviceServiceImpl;
import org.dms.service.IBrandService;
import org.dms.util.EntityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeviceServlet extends HttpServlet {

    private IDeviceService deviceService = new DeviceServiceImpl();
    private IBrandService brandService = new BrandServiceImpl();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        String method = request.getParameter("method");
        if (method != null) {
            if ("delete".equals(method)) {
                delete(request);
            }
            else if ("add".equals(method)) {
                add(request, user);
            }
            else if ("detail".equals(method)) {
                detail(request);
                request.getRequestDispatcher("detail/device.jsp").forward(request, response);
            }
            else if ("update".equals(method)) {
                update(request, user);
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

    private void update(HttpServletRequest request, User user) {
        Device device = new Device();
        EntityUtil.setPropertiesByRequest(device, request);
        EntityUtil.setUpdateInformation(device, user.getUsername());
        deviceService.updateDevice(device);
    }

    private void detail(HttpServletRequest request) {
        String code = request.getParameter("code");
        request.setAttribute("brands", brandService.getAllBrand());
        request.setAttribute("device", deviceService.getDeviceByCode(code));
    }

    private void add(HttpServletRequest request, User user) {
        Device device = new Device();
        EntityUtil.setPropertiesByRequest(device, request);
        device.setStatus(DeviceStatus.NEW.getStatus());
        EntityUtil.setCreateInformation(device, user.getUsername());
        deviceService.addDevice(device);
    }

    private void delete(HttpServletRequest request) {
        String code = request.getParameter("code");
        deviceService.deleteDeviceByCode(code);
    }
}
