/*resetear select de buscar por grupo y estado */

function resetSelect(){
    var selectGrupo = document.getElementById("buscarGrupo");
    var selectEstado = document.getElementById("buscarEstado");
    selectGrupo.selectedIndex = 0;
    selectEstado.selectedIndex = 0;
}


function cargarSelect(){
    var inputGrupo, inputEstado, filterGrupo, filterEstado, table, tr, td, i, j, visible;
    inputGrupo = document.getElementById("buscarGrupo");
    inputEstado = document.getElementById("buscarEstado");
    filterGrupo = inputGrupo.value;
    filterEstado = inputEstado.value;
    table = document.getElementById("dtBasicExample");
    tr = table.getElementsByTagName("tr");
    console.log(filterGrupo);
    console.log(filterEstado);
    for (i = 0; i < tr.length; i++) {
        visible = false;
        /* Obtenemos todas las celdas de la fila, no sólo la primera */
        td = tr[i].getElementsByTagName("td");
        if((filterGrupo === "sist" || filterGrupo === "mant" ) && filterEstado === ""){
            if (td[2] && td[2].innerHTML.toUpperCase() === filterGrupo.toUpperCase()) {
                visible = true;
            }
            if (visible === true) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
        else if(filterGrupo === "" && (filterEstado === "pendiente" || filterEstado === "asignada" || filterEstado === "finalizada")){
            if (td[7] && td[7].innerHTML.toUpperCase() === filterEstado.toUpperCase()) {
                visible = true;
            }
            if (visible === true) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}