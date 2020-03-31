<%-- 
    Document   : menuUsuarioGeneral
    Created on : 05/03/2020, 12:27:39
    Author     : bryan
--%>
<div class="bg-light border-right" id="sidebar-wrapper" style="font-size: 0.95em;">
    <div class="sidebar-heading text-center">Menu</div>
    <div class="list-group list-group-flush">
        
        <c:if test="${rol == 'admin_sist'}">
            <span class="list-group-item list-group-item-action bg-light"></span>
            <a href="crearSolictudAdmin" class="list-group-item list-group-item-action bg-light"><i class="fas fa-file-alt"></i>&nbsp &nbsp Crear Solicitud</a>
            <a href="consultarSolicitud" class="list-group-item list-group-item-action bg-light"><i class="fas fa-tasks"></i>&nbsp &nbsp Consultar Solicitudes</a>

            <span class="list-group-item list-group-item-action bg-light"></span>

            <a href="nuevasSolicitudes" class="list-group-item list-group-item-action bg-light"><i class="far fa-plus-square"></i>&nbsp &nbsp Solicitudes Nuevas</a>
            <a href="#" class="list-group-item list-group-item-action bg-light"><i class="far fa-question-circle"></i>&nbsp &nbsp Estado de Ayudas</a>
        </c:if>
            
        <c:if test="${rol == 'general_acad'}">
            <span class="list-group-item list-group-item-action bg-light"></span>
            <a href="crearSolicitud" class="list-group-item list-group-item-action bg-light"><i class="fas fa-file-alt"></i>&nbsp &nbsp Crear Solicitud</a>
            <a href="consultarSolicitud" class="list-group-item list-group-item-action bg-light"><i class="fas fa-tasks"></i>&nbsp &nbsp Consultar Solicitudes</a>
        </c:if>
    </div>
</div>