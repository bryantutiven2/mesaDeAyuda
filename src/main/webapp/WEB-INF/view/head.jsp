<%-- 
    Document   : head
    Created on : 10/04/2020, 19:15:40
    Author     : bryan
--%>

<meta charset="utf-8">
<title>Mesa de Ayuda</title>
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />
<link rel="stylesheet" href="https://netdna.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.css">
<link href="${pageContext.request.contextPath}/css/sidebar.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/datatables.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/css/loader.css" rel="stylesheet" type="text/css"/>
<c:if test="${viewMain == 'dashboardTecnico'}">
    <link href="${pageContext.request.contextPath}/css/dashboardTecnico.css" rel="stylesheet"/>
</c:if>