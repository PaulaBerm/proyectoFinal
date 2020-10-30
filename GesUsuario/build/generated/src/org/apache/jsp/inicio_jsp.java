package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class inicio_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("    <head>\r\n");
      out.write("        <link rel=\"icon\" type=\"image/png\" href=\"assets/img/LogoInvertido.png\">\r\n");
      out.write("        <meta charset=\"utf-8\">\r\n");
      out.write("        <title>Login</title>\r\n");
      out.write("        <link href=\"assets/csslogin.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <link href=\"assets/fontslogin.css\" rel=\"stylesheet\" type=\"text/css\"/>\r\n");
      out.write("        <link href=\"https://fonts.googleapis.com/css?family=Josefin+Sans|Montez|Pathway+Gothic+One\" rel=\"stylesheet\">\r\n");
      out.write("    </head>\r\n");
      out.write("    <body>\r\n");
      out.write("        <div class=\"contenedor\">\r\n");
      out.write("\r\n");
      out.write("            <header>\r\n");
      out.write("                <h1 class=\"title\">INBAR</h1>\r\n");
      out.write("                <a href=\"index.html\"> Inicio </a>\r\n");
      out.write("                <a href=\"registro.jsp\"> Registrarme </a>\r\n");
      out.write("                            </header>\r\n");
      out.write("            <script src=\"assets/js/mainB.js\" type=\"text/javascript\"></script>\r\n");
      out.write("            \r\n");
      out.write("                \r\n");
      out.write("                <div class=\"login\">\r\n");
      out.write("                    <article class=\"fondo\">\r\n");
      out.write("                        <img src=\"assets/img/LogoInvertido.png\" alt=\"\"/>\r\n");
      out.write("\r\n");
      out.write("                        <h3>Inicio de Sesión</h3>\r\n");
      out.write("                        <form class=\"Iniciar\" action=\"index.html\" method=\"post\" id=\"forminicio\">\r\n");
      out.write("                            <span class=\"icon-user\"></span><input class=\"inp\" type=\"email\" name=\"usuario\" id=\"txtCorreo\" placeholder=\"Ingrese correo eléctronico\"/><br>\r\n");
      out.write("                            <span class=\"icon-key\"></span><input class=\"inp\" type=\"password\" name=\"pass\" id=\"txtPass\" placeholder=\"Ingrese Contraseña\" /><br>\r\n");
      out.write("                            <input class=\"boton\" type=\"submit\" name=\"accion\" value=\"Ingresar\" id=\"btnIniciar\" />\r\n");
      out.write("\r\n");
      out.write("                        </form>\r\n");
      out.write("                    </article>\r\n");
      out.write("                </div>\r\n");
      out.write("\r\n");
      out.write("        </div>\r\n");
      out.write("        \r\n");
      out.write("\r\n");
      out.write("    </div>\r\n");
      out.write("    <script src=\"assets/js/mainB.js\" type=\"text/javascript\"></script>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
