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

import com.Beans.CategoriesBean;
import com.Services.CategorieServices;
import com.ServicesImpl.CatagorieSerImpl;

@WebServlet("/categories")
public class CategoriesServlet extends HttpServlet {
    private static final Logger logger = Logger.getLogger(CategoriesServlet.class.getName());
    private static final long serialVersionUID = 1L;
    private CategorieServices categorieService;

    public CategoriesServlet() {
    	super();
    }
    
    public void init() throws ServletException {
    	categorieService = new CatagorieSerImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userName") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        List<CategoriesBean> categorieList = categorieService.getAllCategorie();
        if (categorieList != null) {
            logger.info("Categorie list size: " + categorieList.size());
        } else {
            logger.warning("Categorie list is null");
        }
        request.setAttribute("categorieList", categorieList);
        request.getRequestDispatcher("/categories.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("userName") == null) {
            response.sendRedirect("login.jsp");
            return;
        }

        String action = request.getParameter("action");

        if ("create".equals(action)) {
            String categorieName = request.getParameter("categorieName");
            CategoriesBean newCategorie = new CategoriesBean();
            newCategorie.setCategorieName(categorieName);
            categorieService.createCat(newCategorie);
        } else if ("delete".equals(action)) {
            Long categorieId = Long.parseLong(request.getParameter("categorieId"));
            categorieService.deleteCategorie(categorieId);
        } else if ("edit".equals(action)) {
            Long categorieId = Long.parseLong(request.getParameter("categorieId"));
            String categorieName = request.getParameter("categorieName");
            CategoriesBean categorie = categorieService.getCatById(categorieId);
            if (categorie != null) {
                categorie.setCategorieName(categorieName);
                categorieService.editCatgorie(categorie);
            }
        }

        response.sendRedirect("categories");
    }
}
