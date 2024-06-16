<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.Services.BrandServices,com.ServicesImpl.BrandSerImp,com.Beans.BrandsBean,java.util.*,
jakarta.servlet.ServletOutputStream,java.io.*"%>

<%
    String userName = (String) session.getAttribute("userName");
    System.out.println("Session UserName in brands.jsp: " + userName);
    if(userName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Marques</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap JS -->
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.bundle.min.js"></script>
    <!-- DataTables JS -->
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    
    <!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
		crossorigin="anonymous"></script>
	<!-- Google fonts-->
	<link href="https://fonts.googleapis.com/css?family=Montserrat:400,700"
		rel="stylesheet" type="text/css" />
	<link
		href="https://fonts.googleapis.com/css?family=Lato:400,700,400italic,700italic"
		rel="stylesheet" type="text/css" />
	<!-- Core theme CSS (includes Bootstrap)-->
	<link href="css/index-styles.css" rel="stylesheet" />
	<!-- Icon CDN -->
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
	
	
	<style type="text/css">
		.sp{
		margin-top: 120px !important;
		}
	</style>
</head>
<body>
	<!-- navbar -->
	<%@include file="header.jsp"%>
    <div class="container sp mt-4">
        <h2>Liste des Marques</h2>
        <!-- Button to Open the Modal -->
        <button type="button" class="btn btn-success mb-2" data-toggle="modal" data-target="#addBrandModal">
            Ajouter une marque
        </button>

        <table id="brandsTable" class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${brandList}" var="brand">
                    <tr>
                        <td>${brand.brandId}</td>
                        <td>${brand.brandName}</td>
                        <td>
                            <!-- Delete Form -->
                            <form action="brands" method="post" style="display: inline; margin-right: 10px;">
                                <input type="hidden" name="brandId" value="${brand.brandId}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cette marque ?')"><i class="bi bi-trash3"></i></button>
                            </form>
                            <!-- Edit Button -->
                            <button type="button" class="btn btn-primary btn-sm" data-toggle="modal" data-target="#editBrandModal" 
                                    onclick="setEditModalValues('${brand.brandId}', '${brand.brandName}')"><i class="bi bi-pencil-square"></i></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <!-- Add Brand Modal -->
    <div class="modal" id="addBrandModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Ajouter une nouvelle marque</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <form id="addBrandForm" action="brands" method="post">
                        <input type="hidden" name="action" value="create">
                        <div class="form-group">
                            <label for="brandName">Nom de la marque:</label>
                            <input type="text" class="form-control" id="brandName" name="brandName" required>
                        </div>
                        <button type="submit" class="btn btn-success">Ajouter</button>
                    </form>
                </div>
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Edit Brand Modal -->
    <div class="modal" id="editBrandModal">
        <div class="modal-dialog">
            <div class="modal-content">
                <!-- Modal Header -->
                <div class="modal-header">
                    <h4 class="modal-title">Modifier la marque</h4>
                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                </div>
                <!-- Modal Body -->
                <div class="modal-body">
                    <form id="editBrandForm" action="brands" method="post">
                        <input type="hidden" name="action" value="edit">
                        <input type="hidden" id="editBrandId" name="brandId">
                        <div class="form-group">
                            <label for="editBrandName">Nom de la marque:</label>
                            <input type="text" class="form-control" id="editBrandName" name="brandName" required>
                        </div>
                        <button type="submit" class="btn btn-primary">Modifier</button>
                    </form>
                </div>
                <!-- Modal Footer -->
                <div class="modal-footer">
                    <button type="button" class="btn btn-danger" data-dismiss="modal">Fermer</button>
                </div>
            </div>
        </div>
    </div>

    <script>
        function setEditModalValues(brandId, brandName) {
            $('#editBrandId').val(brandId);
            $('#editBrandName').val(brandName);
        }

        $(document).ready(function(){
            $('#brandsTable').DataTable({
                "language": {
                    "url": "assets/French.json"
                }
            });

            $('#addBrandModal').on('hidden.bs.modal', function () {
                $(this).find('form').trigger('reset');
            });

            $('#editBrandModal').on('hidden.bs.modal', function () {
                $(this).find('form').trigger('reset');
            });
        });
    </script>
</body>
</html>
