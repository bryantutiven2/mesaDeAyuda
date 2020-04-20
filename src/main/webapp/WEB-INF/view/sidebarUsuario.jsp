<%-- 
    Document   : menuUsuarioGeneral
    Created on : 05/03/2020, 12:27:39
    Author     : bryan
--%>
<div class="sidebar col-2 collapse width show px-0" id ="sidebar" style="font-size: 0.95em;">
    <nav class="navbar-default navbar-static-side position-fixed" role="navigation" style="width: 225px;">
        <ul class="nav metismenu list-group">
            <li class="text-center" style="background-color: #e6eaef; padding: 0px; padding-top: 10%; border-radius: 0px; height: 125px;">
                <div class="form-group text-center" style=" background-color: #e6eaef;width: 100%; height: 50px;">
                    <span>
                        <img src="${pageContext.request.contextPath}/images/logo.png" style="width: 100%"/>
                    </span>
                    <i style="font-style: italic; font-weight: 700; font-size: 14pt; color: #032345;">ANAI</i>
                </div>
            </li>
            <!-- Separator with title -->
            <li class="referenciaA list-group-item align-middle text-center" style="height: 60px;">
                <h5>Menú</h5>
            </li>
            <!--
                Seccion Administrador
            -->
            <c:if test="${rol == 'admin_sist'}">
                <li>
                    <a href="${pageContext.request.contextPath}/admin/consultarDashboardTecnico" class="activar referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-tachometer-alt"></i>
                            <span class="eleccion" style="padding-left: 10px">Dashboard Técnico</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/crearSolictud" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-file-alt"></i>
                            <span class="eleccion" style="padding-left: 10px">Crear Solicitud</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/consultarSolicitud" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-tasks"></i>
                            <span class="eleccion" style="padding-left: 10px">Consultar Solicitudes</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/admin/nuevasSolicitudes" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="far fa-plus-square"></i>
                            <span class="eleccion" style="padding-left: 10px">Solicitudes Nuevas</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tecnico/gestionarSolicitudes" id="cargarSTecnico" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-cogs"></i>
                            <span class="eleccion" style="padding-left: 10px">Gestionar Solicitudes</span>
                        </div>
                    </a>
                </li>
            </c:if>
            <!--
                Seccion Usuario general
            -->
            <c:if test="${rol == 'general_acad'}">
                <li>
                    <a href="${pageContext.request.contextPath}/usuario/crearSolicitud" class="activar referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-file-alt"></i>
                            <span class="eleccion" style="padding-left: 10px">Crear Solicitud</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/usuario/consultarSolicitud" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-tasks"></i>
                            <span class="eleccion" style="padding-left: 10px">Consultar Solicitudes</span>
                        </div>
                    </a>
                </li>
            </c:if>
            <!--
                Seccion Usuario tecnico sistema
            -->
            <c:if test="${rol == 'tecnico_sist'}">
                <li>
                    <a href="${pageContext.request.contextPath}/usuario/crearSolicitud" class="activar referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-file-alt"></i>
                            <span class="eleccion" style="padding-left: 10px">Crear Solicitud</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tecnico/consultarSolicitud" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-tasks"></i>
                            <span class="eleccion" style="padding-left: 10px">Consultar Solicitudes</span>
                        </div>
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/tecnico/gestionarSolicitudes" id="cargarSTecnico" class="referenciaA list-group-item">
                        <div class="d-flex w-100 justify-content-start align-items-center">
                            <i class="fas fa-cogs"></i>
                            <span class="eleccion" style="padding-left: 10px">Gestionar Solicitudes</span>
                        </div>
                    </a>
                </li>
            </c:if>
        </ul>
    </nav>
</div>