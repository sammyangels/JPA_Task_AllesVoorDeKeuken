package be.vdab.servlets.artikels;

import be.vdab.services.ArtikelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

@WebServlet(urlPatterns = "/artikels/zoekenopnaam.htm", name = "ZoekenOpNaamServlet")
public class ZoekenOpNaamServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/artikels/zoekenopnaam.jsp";
    private final transient ArtikelService artikelService = new ArtikelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String woord = request.getParameter("woord");
        if (woord != null) {
            if (woord.isEmpty()) {
                request.setAttribute("fouten", Collections.singletonMap("woord", "verplicht"));
            } else {
                request.setAttribute("artikels", artikelService.findByNaamContains(woord));
            }
        }
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
