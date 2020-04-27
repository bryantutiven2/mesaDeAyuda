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
                    <c:if test="${viewMain == 'dashboardTecnico'}">
                        <%@ include file="dashboardTecnico.jsp" %>
                    </c:if>
                    <c:if test="${viewMain == 'dashboardEncuesta'}">
                        <%@ include file="dashboardEncuesta.jsp" %>
                    </c:if>
                    
                    <!-- main contenido usuario Tecnico-->
                    <c:if test="${viewMain == 'cargarSolicitudesTecnico'}">
                        <%@ include file="cargarSolicitudesTecnico.jsp" %>
                    </c:if>
                    <c:if test="${viewMain == 'consultaSolicitudesTecnico'}">
                        <%@ include file="consultarSolicitudesTecnico.jsp" %>
                    </c:if>
                    
                    <!-- modal para cargar Encuesta -->
                    <div class="modal fade  bd-example-modal-lg" id="modalEncuesta" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog modal-lg" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <div class="text-center" style="width: 100%;"> <h4 class="titulo">Encuesta</h4> </div>

                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                                </div>
                                <div class="modal-body " id="mensajeEncuesta">

                                </div>
                                <div class="modal-footer">
                                    <button type="button" class="btn-cambiar-color btn btn-primary" id="aceptarET">Confirmar</button>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- modal para Confirmar ecnuesta -->
                    <div class="modal fade" id="modalConfirmar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <form id="formCR" autocomplete="off">
                                    <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                    </div>
                                    <div class="modal-body " id="mensajeConfirmar">
                                        <div class="form-group">
                                            <label><h6>Ingrese código de registro:</h6></label>
                                        </div>
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="idRegistroSol" id="idRegistroSol" required>
                                        </div>
                                    </div>

                                    <div class="modal-footer">
                                        <button type="submit" class="btn-cambiar-color btn btn-primary" id="aceptarEncuestaT">Aceptar</button>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancelar</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <!-- modal para cargar mensaje -->
                    <div class="modal fade" id="modalMensajeNotificacion" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body " id="mensajeModalNotificacion">

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    <%@ include file="scripts.jsp" %>
    </body
</html>
