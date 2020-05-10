<%-- 
    Document   : scripts
    Created on : 10/04/2020, 19:18:11
    Author     : bryan
--%>

<script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo=" crossorigin="anonymous"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
<script src="${pageContext.request.contextPath}/script/sidebar.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/notificacionEncuesta.js" type="text/javascript"></script>

<c:if test="${viewMain == 'crearSolicitud'}">
    <script src="${pageContext.request.contextPath}/script/crearSolicitud.js"></script>
</c:if>
<c:if test="${viewMain == 'consultaSolicitudes'}">
    <script src="${pageContext.request.contextPath}/script/consultarSolicitudes.js"></script>
    <script src="${pageContext.request.contextPath}/script/observacion.js"></script>
</c:if>

<!-- main contenido usuario Admin-->
<c:if test="${viewMain == 'crearSolicitudAdmin'}">
    <script src="${pageContext.request.contextPath}/script/crearSolicitudAdmin.js"></script>
</c:if>
<c:if test="${viewMain == 'consultaSolicitudesAdmin'}">
    <script src="${pageContext.request.contextPath}/script/consultarSolicitudesAdmin.js"></script>
    <script src="${pageContext.request.contextPath}/script/observacion.js"></script>
</c:if>
<c:if test="${viewMain == 'solicitudesNuevasAdmin'}">
    <script src="${pageContext.request.contextPath}/script/solicitudesNuevasAdmin.js"></script>
    <script src="${pageContext.request.contextPath}/script/observacion.js"></script>
</c:if>
<c:if test="${viewMain == 'dashboardTecnico'}">
    <script src="${pageContext.request.contextPath}/script/dashboardTecnico.js"></script>
</c:if>
<c:if test="${viewMain == 'dashboardEncuesta'}">
    <script src="${pageContext.request.contextPath}/script/dashboardEncuesta.js"></script>
</c:if>
<c:if test="${viewMain == 'dashboardUsuario'}">
    <script src="${pageContext.request.contextPath}/script/dashboardUsuario.js"></script>
</c:if>


<!-- main contenido usuario Tecnico-->
<c:if test="${viewMain == 'cargarSolicitudesTecnico'}">
    <script src="${pageContext.request.contextPath}/script/cargarSolicitudTecnico.js"></script>
    <script src="${pageContext.request.contextPath}/script/observacion.js"></script>
</c:if>
<c:if test="${viewMain == 'consultaSolicitudesTecnico'}">
    <script src="${pageContext.request.contextPath}/script/consultarSolicitudesTecnico.js"></script>
    <script src="${pageContext.request.contextPath}/script/observacion.js"></script>
</c:if>

<script src="${pageContext.request.contextPath}/script/datatables/datatables.js"></script>
<script src="${pageContext.request.contextPath}/script/datatables/dataTables.buttons.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/datatables/buttons.print.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/datatables/jszip.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/datatables/pdfmake.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/datatables/vfs_fonts.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/script/datatables/buttons.html5.min.js" type="text/javascript"></script>
