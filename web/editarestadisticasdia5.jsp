<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.sql.*"%>
<%@page import="java.util.*"%>
<%
    HttpSession objsesion = request.getSession(false);
    String id_usuario = (String)objsesion.getAttribute("id_usuario");
    String Descripcion_perfil = (String)objsesion.getAttribute("descripcion_perfil");
    if(id_usuario==null){
        response.sendRedirect("login.jsp");
    }else{
        if(Descripcion_perfil.equals("COORDINADOR")||
                Descripcion_perfil.equals("JEFE")){
            
        }else{
          response.sendRedirect("nomina.htm");  
        }
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Editar Día 5</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" />
        <script src="https://kit.fontawesome.com/a076d05399.js"></script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
        <script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@9"></script>

    </head>
    <body>
        <%
            Modelos.Estadisticas.clsEstadisticas obclsEstadisticas = new Modelos.Estadisticas.clsEstadisticas();
            
            if(request.getAttribute("obclsEstadisticas")!=null){
                obclsEstadisticas = (Modelos.Estadisticas.clsEstadisticas)request.getAttribute("obclsEstadisticas");
            }
            
            List<Modelos.Estadisticas.clsEstadisticas> lstclsEstadisticas = new ArrayList<Modelos.Estadisticas.clsEstadisticas>();
            
            if(request.getAttribute("lstclsEstadisticas")!=null){
                
                lstclsEstadisticas = (List<Modelos.Estadisticas.clsEstadisticas>)request.getAttribute("lstclsEstadisticas");
            }
            
            if(request.getAttribute("stMensaje")!=null && request.getAttribute("stTipo")!=null){
                
            
        
        %>
        <input type="text" hidden="" id="txtMensaje" value="<%=request.getAttribute("stMensaje")%>"/>
        <input type="text" hidden="" id="txtTipo" value="<%=request.getAttribute("stTipo")%>"/>
        <script>
            var mensaje = document.getElementById("txtMensaje").value;
            var tipo = document.getElementById("txtTipo").value;

            swal.fire("Mensaje",mensaje, tipo);
        </script>
        <%
            }
        %>
        <div>    
            <div class="card border-info ">
                <div class="card-header bg-info text-white">                    
                        <div class="form-group">
                            <div class="form-group">
                                <div class="col-12 text-center">                                    
                                    <h5 class="align-middle mt-1"> 
                                        <b><%=obclsEstadisticas.getObEmpleado().getNombreEmp()!= null ? obclsEstadisticas.getObEmpleado().getNombreEmp(): ""%></b>
                                    </h5>                                         
                                </div>
                            </div>
                        </div>
                </div>
                <div class="card-body">
                    <form action="estadisticas" method="POST">                           
                        <!--FILA 1-->
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-12">
                                    <label for="lblDia5"><b>Dia 5</b></label>
                                    <select class="form-control" name="ddlDia5">
                                        <%
                                            List<Modelos.Estadisticas.clsId_dia5> lstclsDia5 = new ArrayList<Modelos.Estadisticas.clsId_dia5>();

                                            if (request.getAttribute("lstclsDia5") != null) {
                                                lstclsDia5 = (List<Modelos.Estadisticas.clsId_dia5>) request.getAttribute("lstclsDia5");
                                            }

                                            for (Modelos.Estadisticas.clsId_dia5 elem : lstclsDia5) {
                                        %>
                                        <option value="<%=elem.getId_dia5()%>"
                                                <%=obclsEstadisticas.getObId_dia1()!= null ? obclsEstadisticas.getObId_dia5().getId_dia5()== elem.getId_dia5()? "selected" : "" : ""%>>
                                            <%=elem.getDescripcionDia5()%>(<%=elem.getAliasDia5()%>)
                                        </option>
                                        <%
                                            }
                                        %>
                                    </select>
                                </div>
                            </div>  
                        </div>                        
                        <!--FILA 11-->   
                        <div class="form-group">
                            <div class="form-row">
                                <div class="col-12">
                                    <input type="submit" value="Guardar" class="btn btn-info" name="btnEditarEstaDia5"/>
                                    <input type="text" name="IdModificar" id="IdModificar" value="<%=obclsEstadisticas.getId_estadistica()%>" hidden=""/>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
