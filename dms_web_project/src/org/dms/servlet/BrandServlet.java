package org.dms.servlet;

import org.dms.entity.Brand;
import org.dms.entity.User;
import org.dms.service.IBrandService;
import org.dms.service.impl.BrandServiceImpl;
import org.dms.util.EntityUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BrandServlet extends HttpServlet {
    private IBrandService brandService = new BrandServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getParameter("method");
         if ("detail".equals(method)) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            request.setAttribute("brand", brandService.getBrandById(id));
            request.getRequestDispatcher("/detail/brand.jsp").forward(request, response);
            return;
        }
        if ("add".equals(method)) {
            Brand brand = new Brand();
            EntityUtil.setPropertiesByRequest(brand, request);
            brandService.addBrand(brand);
        } else if ("delete".equals(method)) {
            Integer id = Integer.valueOf(request.getParameter("id"));
            brandService.deleteBrandById(id);
        } else if ("update".equals(method)) {
            Brand brand = new Brand();
            EntityUtil.setPropertiesByRequest(brand, request);
            EntityUtil.setUpdateInformation(brand, ((User)request.getSession().getAttribute("user")).getUsername());
            brandService.updateBrand(brand);
        }
        request.setAttribute("brands", brandService.getAllBrand());
        request.getRequestDispatcher("/brand.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
