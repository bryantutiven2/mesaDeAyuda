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
        <%@ include file="head.jsp" %>
    </head>

    <body>
        <div class="container-fluid">
            <div class="row">
                <!-- Sidebar -->
                <%@ include file="sidebarUsuario.jsp" %>

                <div class="main col pt-2">
                    
                    <!--Navbar-->
                    <%@ include file="nav.jsp" %>

                    <!-- main contenido usuario General-->
                    
                    
                    <c:if test="${viewMain == 'crearSolicitud'}">
                        <%@ include file="crearSolicitud.jsp" %>
                    </c:if>
                    <c:if test="${viewMain == 'consultaSolicitudes'}">
                        <%@ include file="consultarSolicitudes.jsp" %>
                    </c:if>
                    
                    <!-- main contenido usuario Admin-->
                    <c:if test="${viewMain == 'crearSolicitudAdmin'}">
                        <%@ include file="crearSolicitudAdmin.jsp" %>
                    </c:if>
                    <c:if test="${viewMain == 'consultaSolicitudesAdmin'}">
                        <%@ include file="consultarSolicitudesAdmin.jsp" %>
                    </c:if>
                    <c:if test="${viewMain == 'solicitudesNuevasAdmin'}">
                        <%@ include file="solicitudesNuevasAdmin.jsp" %>
                    </c:if>
                </div>
            </div>
        </div>
    <%@ include file="scripts.jsp" %>
    </body
</html>
