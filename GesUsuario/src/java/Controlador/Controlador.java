package Controlador;

import Modelo.categorias;
import ModeloDAO.PersonaDAO;
import Controlador.Consultas;
import ModeloVO.CUsuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Controlador extends HttpServlet {

    String listar = "vistas/listar.jsp";
    String add = "vistas/listar.jsp";
    String edit = "vistas/edit.jsp";
    String empleado = "vistas/registroEmple.jsp";
    categorias p = new categorias();
    PersonaDAO dao = new PersonaDAO();
    Consultas con = new Consultas();
    CUsuario use = new CUsuario();
    int id;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String acceso = "";
        String action = request.getParameter("accion");
        if (action.equalsIgnoreCase("listarr")) {
            acceso = listar;
        } else if (action.equalsIgnoreCase("add")) {
            acceso = add;
        } else if (action.equalsIgnoreCase("empleado")) {
            acceso = empleado;
        } else if (action.equalsIgnoreCase("Agregar")) {

            String nom = request.getParameter("txtNom");
            p.setCategorias(nom);

            dao.add(p);
            acceso = listar;
        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idper", request.getParameter("id"));
            acceso = edit;
        } else if (action.equalsIgnoreCase("Actualizar")) {
            id = Integer.parseInt(request.getParameter("txtid"));
            String nom = request.getParameter("txtNom");
            p.setId(id);
            p.setCategorias(nom);

            dao.edit(p);
            acceso = listar;
        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            p.setId(id);
            dao.eliminar(id);
            acceso = listar;
        } else if (action.equalsIgnoreCase("cambios")) {
            String correo = request.getParameter("correo");
            String contraseña = request.getParameter("password");
            String nombre = request.getParameter("nombre");
            String apellido = request.getParameter("apellido");

            use.setCorreo(correo);
            use.setContraseña(contraseña);
            use.setNombre(nombre);
            use.setApellido(apellido);
            
                if (action.equalsIgnoreCase("cambios")) {
                    con.registrarEmp(use);
                acceso = empleado;
                }
        }
        RequestDispatcher vista = request.getRequestDispatcher(acceso);
        vista.forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }                                                                    

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
