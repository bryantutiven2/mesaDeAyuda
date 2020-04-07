<%-- 
    Document   : menuUsuarioGeneral
    Created on : 05/03/2020, 12:27:39
    Author     : examen
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
        <link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css">
        <link href="${pageContext.request.contextPath}/css/simple-sidebar.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/datatables.css" rel="stylesheet">
        
    </head>

    <body>
            
             
            
            <div class="container-fluid">
                <div class="row">
                    <!-- Sidebar -->
                    <%@ include file="sidebarUsuario.jsp" %>
                    
                    <div class="main col pt-2">
                        <!--Navbar-->
                        <%@ include file="nav.jsp" %>
                        
                        <!-- main contenido -->
                        <c:if test="${viewMain == 'crearSolicitudAdmin'}">
                            <%@ include file="crearSolicitudAdmin.jsp" %>
                        </c:if>
                        <c:if test="${viewMain == 'crearSolicitud'}">
                            <%@ include file="crearSolicitud.jsp" %>
                        </c:if>
                        <c:if test="${viewMain == 'consultaSolicitudes'}">
                            <%@ include file="consultarSolicitudes.jsp" %>
                        </c:if>
                        <c:if test="${viewMain == 'solicitudesNuevasAdmin'}">
                            <%@ include file="solicitudesNuevasAdmin.jsp" %>
                        </c:if>
                    </div>
                </div>
            </div>
            
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n" crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <script src="${pageContext.request.contextPath}/script/crearSolicitudAdmin.js"></script>
        <script src="${pageContext.request.contextPath}/script/toggle.js"></script>
        <script src="${pageContext.request.contextPath}/script/modal.js"></script>
        <script src="${pageContext.request.contextPath}/script/nuevaSolicitud.js"></script>
        <script src="${pageContext.request.contextPath}/script/datatables.js"></script>
    </body
</html>
