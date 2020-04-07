<%-- 
    Document   : nav
    Created on : 13/03/2020, 11:54:50
    Author     : bryan
--%>

<nav class="navbar navbar-expand-lg navbar-light bg-light" style="margin-top: 10px">
    <button class="btn sticky-top mr-1" data-toggle="collapse" href="#sidebar" role="button">
        <span class="navbar-toggler-icon"></span>
    </button>
    <button class="navbar-toggler ml-auto" type="button" data-toggle="collapse" data-target="#navbar1">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav ml-auto mt-2 mt-lg-0"></ul>
        <div class="dropdown" style="margin-right: 17px">
            <button class="btn btn-light dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              <span>&nbsp;&nbsp;&nbsp;&nbsp;${username}&nbsp;&nbsp;&nbsp;&nbsp;</span>
            </button>
            <div class="dropdown-menu text-center">    
                <span class="dropdown-item-text"><i class="fas fa-user"></i>&nbsp;${usuario}</span>
                <span class="dropdown-item-text">${correo}</span>
                <div class="dropdown-divider"></div>
                <a class="dropdown-item" href="${pageContext.request.contextPath}/login">Cerrar Sesión &nbsp;<i class="fas fa-key"></i></a>
            </div>
        </div>
    </div>
</nav>
