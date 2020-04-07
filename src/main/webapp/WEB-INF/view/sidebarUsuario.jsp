<%-- 
    Document   : menuUsuarioGeneral
    Created on : 05/03/2020, 12:27:39
    Author     : bryan
--%>
<div class="sidebar col-2 collapse width show px-0 bg-light" id ="sidebar" style="font-size: 0.95em;">
    <div class="position-fixed h-100" style="width: 225px;">
        <ul class="list-group">
            <li class="list-group-item text-center" style="padding: 0px; padding-top: 10%; border-radius: 0px">
                <div class=" form-group text-center" style="background-color: white;width: 100%; height: 55px;">
                    <img src="${pageContext.request.contextPath}/images/logo.png" style="width: 100%"/>
                </div>
            </li>
            <!-- Separator with title -->
            <li class="list-group-item text-muted text-center bg-light">
                <h5>Menu</h5>
            </li>
            <c:if test="${rol == 'admin_sist'}">
                <a href="${pageContext.request.contextPath}/admin/crearSolictud" aria-expanded="false" class="bg-light list-group-item list-group-item-action flex-column align-items-start">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="fas fa-file-alt"></i>
                        <span style="padding-left: 10px">Crear Solicitud</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/admin/consultarSolicitud" class="list-group-item list-group-item-action bg-light">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="fas fa-tasks"></i>
                        <span style="padding-left: 10px">Consultar Solicitudes</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/admin/nuevasSolicitudes" class="list-group-item list-group-item-action bg-light">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="far fa-plus-square"></i>
                        <span style="padding-left: 10px">Solicitudes Nuevas</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
                <a href="#" class="list-group-item list-group-item-action bg-light" style="border-radius: 0px">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="far fa-question-circle"></i>
                        <span style="padding-left: 10px">Estado de Ayudas</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
                
            </c:if>
            <c:if test="${rol == 'general_acad'}">
                <span class="list-group-item list-group-item-action bg-light"></span>
                <a href="${pageContext.request.contextPath}/usuario/crearSolicitud" class="list-group-item list-group-item-action bg-light">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="fas fa-file-alt"></i>
                        <span style="padding-left: 10px">Crear Solicitud</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
                <a href="${pageContext.request.contextPath}/usuario/consultarSolicitud" class="list-group-item list-group-item-action bg-light" style="border-radius: 0px">
                    <div class="d-flex w-100 justify-content-start align-items-center">
                        <i class="fas fa-tasks"></i>
                        <span style="padding-left: 10px">Consultar Solicitudes</span>
                        <span class="submenu-icon ml-auto"></span>
                    </div>
                </a>
            </c:if>
        </ul>
       
    </div>
</div>