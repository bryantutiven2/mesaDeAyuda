
$( document ).ready(function() {
    $(".nav li").on("click", function(){
        $(".nav").find(".activar").removeClass("activar");
        var seleccionSidebar = localStorage.getItem("activarSeleccion");
        if(seleccionSidebar==null){
                var divR = $(this).children(); 
                divR.each(function (i) {
                    var valo = $(this).find('.eleccion').text();
                    localStorage.setItem('activarSeleccion',valo);
                });
        }
        $(this).addClass("activar");
        
        /*resetear localStorage*/
        var lsGrupo = localStorage.getItem("grupo");
        var lsEstado = localStorage.getItem("estado");
        if(lsGrupo != null && lsEstado == null){
            localStorage.removeItem('grupo');
        }
        else if(lsEstado != null && lsGrupo == null){
            localStorage.removeItem('estado');
        }
        else if(lsEstado == null && lsGrupo == null){
            localStorage.removeItem('grupo');
            localStorage.removeItem('estado');
            
        }
        });
  });

$( document ).ready(function() {
    var seleccionSidebar = localStorage.getItem("activarSeleccion");
    var aseleccion = document.getElementsByClassName('eleccion');
     
    if(seleccionSidebar!=null){
        for(var i = 0; i<aseleccion.length; i++){
            if(aseleccion[i].innerHTML == seleccionSidebar){
                $(".nav").find(".activar").removeClass("activar");
                var pa = aseleccion[i].parentNode
                var paA = pa.parentNode;
                $(paA).addClass("activar");
                localStorage.removeItem('activarSeleccion');
            }
        }
    }
});

$('#logoutU').click(function(){
    var lsGrupo = localStorage.getItem("grupo");
    var lsEstado = localStorage.getItem("estado");
    if(lsGrupo != null && lsEstado == null){
        localStorage.removeItem('grupo');
    }
    else if(lsEstado != null && lsGrupo == null){
        localStorage.removeItem('estado');
    }
    else if(lsEstado == null && lsGrupo == null){
        localStorage.removeItem('grupo');
        localStorage.removeItem('estado');

    }
});