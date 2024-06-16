<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.Services.CategorieServices,com.ServicesImpl.CatagorieSerImpl,com.Beans.CategoriesBean,java.util.*,
jakarta.servlet.ServletOutputStream,java.io.*"%>

<%
    String userName = (String) session.getAttribute("userName");
    System.out.println("Session UserName in categorie.jsp: " + userName);
    if(userName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Catégories</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <!-- DataTables JavaScript -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <!-- DataTables Bootstrap JavaScript -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>

    <!-- Favicon-->
    <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
    <!-- Font Awesome icons (free version)-->
    <script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js" crossorigin="anonymous"></script>
    <!-- Google fonts-->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic" rel="stylesheet" type="text/css" />
    <!-- Core theme CSS (includes Bootstrap)-->
    <link href="css/index-styles.css" rel="stylesheet" />
	<!-- Icon CDN -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	
    <style type="text/css">
        .sp {
            margin-top: 120px !important;
        }
    </style>
</head>
<body>
    <!-- navbar -->
    <%@include file="header.jsp"%>

    <div class="container sp mt-4">
        <h2>Liste des Catégories</h2>
        <!-- Button to Open the Modal -->
        <button type="button" class="btn btn-success mb-2" data-toggle="modal" data-target="#addCategorieModal">
            Ajouter une catégorie
        </button>

        <table id="categoriesTable" class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categorieList}" var="categorie">
                    <tr>
                        <td>${categorie.categorieId}</td>
                        <td>${categorie.categorieName}</td>
                        <td>
                            <!-- Delete Form -->
                            <form action="categories" method="post" style="display: inline;">
                                <input type="hidden" name="categorieId" value="${categorie.categorieId}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette catégorie ?')"><i class="bi bi-trash3"></i></button>
                            </form>
                            <!-- Edit Button -->
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editCategorieModal" 
                                    onclick="setEditModalValues('${categorie.categorieId}', '${categorie.categorieName}')"><i class="bi bi-pencil-square"></i></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Add Categorie Modal -->
    <div class="modal" id="addCategorieModal">
        <!-- Modal content here -->
    </div>

    <!-- Edit Categorie Modal -->
    <div class="modal" id="editCategorieModal">
        <!-- Modal content here -->
    </div>

    <!-- JavaScript to reset modals -->
    <script>
        function setEditModalValues(categorieId, categorieName) {
            $('#editCategorieId').val(categorieId);
            $('#editCategorieName').val(categorieName);
        }

        $(document).ready(function() {
            $('#addCategorieModal').on('hidden.bs.modal', function () {
                $(this).find('form').trigger('reset');
            });

            $('#editCategorieModal').on('hidden.bs.modal', function () {
                $(this).find('form').trigger('reset');
            });

            $('#categoriesTable').DataTable(); // Initialize DataTables
        });
    </script>
</body>
</html>
