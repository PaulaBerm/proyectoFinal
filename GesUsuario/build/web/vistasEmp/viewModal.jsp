<%-- 
    Document   : viewModal
    Created on : 31/08/2020, 04:29:12 PM
    Author     : Paula
--%>

<%@page import="Controlador.EmpleadoC"%>
<%@page import="ModeloVO.CUsuario"%>
<%@page import="Modelo.descripcionPedido"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Modelo.infoPedidoFila"%>
<%@page import="Controlador.PedidoFilaEmpC"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    EmpleadoC emp = new EmpleadoC();
    HttpSession objsesion = request.getSession(true);
    String correo = (String) objsesion.getAttribute("usuario");

    String nombre = emp.buscarNombre(correo);
    if (correo.equals("")) {
        out.print(correo);
        out.print(nombre);
    }
%>

<!DOCTYPE html>


<html> 
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Descripcin</title>
    </head>
    <body>

        <%
            PedidoFilaEmpC ped = new PedidoFilaEmpC();

            List<infoPedidoFila> list = ped.listar();
            Iterator<infoPedidoFila> iter = list.iterator();
            infoPedidoFila infP = null;
            while (iter.hasNext()) {
                infP = iter.next();
                CUsuario user = emp.list(correo);
                int id = infP.getNumeroPedido();
                // ArrayList<descripcionPedido> descripcion = ped.descripcionPedido();
                //infoPedidoFila p = ped.buscarPedido(infP.getNumeroPedido());
                ArrayList<descripcionPedido> descripcion = ped.descripcionPedido(id);
                for (int i = 0; i < descripcion.size(); i++) {
        %>

        <div class="modal fade" id="view<%= descripcion.get(i).getNumeroPedido()%>" tabindex="-1" role="dialog" aria-labelledby="edit" aria-hidden="true">

            <div class="modal-dialog">
                <%
                    //infoPedidoFila fila = new infoPedidoFila();

                    //fila =(infoPedidoFila) ped.buscarPedido(id);

                %>
                <form  action="${pageContext.request.contextPath}/ControladorEmp" method="post">
                    <div class="modal-content">
                        <div class="modal-header">
                            <button type="button" class="close" data-dismiss="modal" aria-hidden="true"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></button>
                            <h4 class="modal-title custom_align" id="Heading">Descripcion del pedido...</h4>
                        </div>
                        <div class="modal-body" id="contenido">
                            <div class="card-body w-100">
                                <%
                                %> 
                                <input type="hidden" name="id" value="<%= descripcion.get(i).getNumeroPedido()%> ">
                                <div class="row">
                                    <div class="col-md-6 pr-1">
                                        <div class="form-group">
                                            <label>Pedido:</label>
                                            <label name="cliente"><%= descripcion.get(0).getNumeroPedido()%> </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 pr-1">
                                        <div class="form-group">
                                            <label>Cliente:</label>
                                            <label name="cliente"><%= descripcion.get(0).getCliente()%> </label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 pr-2">
                                        <div class="form-group">
                                            <label># de Mesa:</label>
                                            <label name="mesa"><%= descripcion.get(0).getNumeroMesa()%>  </label>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6 pr-1">
                                        <div class="form-group">
                                            <label>Producto:</label>
                                            <label name="producto"><%=  descripcion.get(i).getProducto()%>  </label>
                                        </div>
                                    </div>
                                    <div class="col-md-6 pr-2">
                                        <div class="form-group">
                                            <label>Cantidad:</label>
                                            <label name="cantidad"><%= descripcion.get(i).getCantidad()%>  </label>
                                        </div>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="modal-footer ">
                            <div class="row">
                                <div class="col-md-6 pr-1">
                                    <input type="hidden" name="txtPedido" value="<%= descripcion.get(0).getNumeroPedido()%>">
                                    <input type="hidden" name="txtEmp" value="<%= user.getId_empleado()%>">                                    
                                    <input class="btn btn-success btn-lg" type="submit" name="accion" value="confirmar">
                                </div>
                                <div class="col-md-6">
                                    <button type="button" class="btn btn-danger btn-lg" style="width: 100%;" data-dismiss="modal" aria-label="Close"><span class="glyphicon glyphicon-ok-sign"></span> Cancelar</button>
                                </div>                       
                            </div>
                        </div>
                    </div>
                    <!-- /.modal-content --> 
                </form>

            </div>
            <!-- /.modal-dialog -->
        </div>
        <%}%>
        <%}%> 
    </body>
</html>
