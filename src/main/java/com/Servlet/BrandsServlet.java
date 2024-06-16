package com.Servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import com.Beans.BrandsBean;
import com.Services.BrandServices;
import com.ServicesImpl.BrandSerImp;

@WebServlet("/brands")
public class BrandsServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(BrandsServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private BrandServices brandService;

    public BrandsServlet() {
        super();
    }
    
    public void init() throws ServletException {
    	brandService = new BrandSerImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userName") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<BrandsBean> brandList = brandService.getAllBrands();
        if (brandList != null) {
            logger.info("Brand list size: " + brandList.size());
        } else {
            logger.warning("Brand list is null");
        }
        request.setAttribute("brandList", brandList);
        request.getRequestDispatcher("/brands.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String brandName = request.getParameter("brandName");
            BrandsBean brand = new BrandsBean();
            brand.setBrandName(brandName);
            brandService.createBrand(brand);
        } else if ("delete".equals(action)) {
            Long brandId = Long.parseLong(request.getParameter("brandId"));
            brandService.deleteBrand(brandId);
        } else if ("edit".equals(action)) {
            Long brandId = Long.parseLong(request.getParameter("brandId"));
            String brandName = request.getParameter("brandName");
            BrandsBean brand = brandService.getBrandById(brandId);
            if (brand != null) {
                brand.setBrandName(brandName);
                brandService.editBrand(brand);
            }
        }

        response.sendRedirect("brands");
    }
}
