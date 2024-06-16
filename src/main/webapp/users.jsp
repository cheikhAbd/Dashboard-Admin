<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page
	import="com.Services.UsersServices,com.ServicesImpl.UserSerImpl,com.Beans.UsersBean,java.util.*,
jakarta.servlet.ServletOutputStream,java.io.*"%>

<%
    String userName = (String) session.getAttribute("userName");
    if(userName == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Liste des Utilisateurs</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- DataTables CSS -->
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.21/css/dataTables.bootstrap4.min.css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- DataTables JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/jquery.dataTables.js"></script>
    <!-- DataTables Bootstrap JS -->
    <script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.21/js/dataTables.bootstrap4.min.js"></script>
    
    <!-- Favicon-->
	<link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
	<!-- Font Awesome icons (free version)-->
	<script src="https://use.fontawesome.com/releases/v5.15.4/js/all.js"
	crossorigin="anonymous"></script>
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
    <div class="container sp">
        <h2>Liste des Utilisateurs</h2>
        <table id="usersTable" class="table table-bordered table-hover">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nom</th>
                    <th>Téléphone</th>
                    <th>Email</th>
                    <th>Actif</th>
                    <th>Code de Vérification</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${userList}" var="user">
                    <tr>
                        <td><c:out value="${user.customerId}" /></td>
                        <td><c:out value="${user.customerName}" /></td>
                        <td><c:out value="${user.customerPhone}" /></td>
                        <td><c:out value="${user.customerEmail}" /></td>
                        <td>
						    <c:choose>
						        <c:when test="${user.active}">
						            <span class="badge badge-success">Oui</span>
						        </c:when>
						        <c:otherwise>
						            <span class="badge badge-danger">Non</span>
						        </c:otherwise>
						    </c:choose>
						</td>
                        <td><c:out value="${user.verifyCode}" /></td>
                        <td>
                
                            <form action="users" method="post" style="display: inline; margin-right: 10px;">
                                <input type="hidden" name="userId" value="${user.customerId}">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit" class="btn btn-danger btn-sm" onclick="return confirm('Êtes-vous sûr de vouloir supprimer cet utilisateur ?')"><i class="bi bi-trash3"></i></button>
                            </form>
                            <form action="users" method="post" style="display: inline;">
                                <input type="hidden" name="userId" value="${user.customerId}">
                                <input type="hidden" name="action" value="activate">
                                <input type="hidden" name="active" value="${!user.active}">
                                <button type="submit" class="btn ${user.active ? 'btn-success' : 'btn-warning'} btn-sm">
                                    ${user.active ? '<i class="bi bi-person-check-fill"></i>' : '<i class="bi bi-person-x-fill"></i>'} 
                                </button>
                            </form><!-- <i class="bi bi-person-check-fill"></i> -->
                          
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <script>
        $(document).ready(function() {
            /* $('#usersTable').DataTable(); */
            $('#usersTable').DataTable({
                "language": {
                    "url": "assets/French.json"
                }
            });
        });
    </script>
</body>
</html>
