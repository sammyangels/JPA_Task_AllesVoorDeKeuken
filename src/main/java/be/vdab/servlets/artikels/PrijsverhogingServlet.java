package be.vdab.servlets.artikels;

import be.vdab.entities.Artikel;
import be.vdab.services.ArtikelService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/artikels/prijsverhoging.htm", name = "PrijsverhogingServlet")
public class PrijsverhogingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String VIEW = "/WEB-INF/JSP/artikels/prijsverhoging.jsp";
    private final transient ArtikelService artikelService = new ArtikelService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> fouten = new HashMap<>();
        BigDecimal percentage = null;
        try {
            percentage = new BigDecimal(request.getParameter("percentage"));
            if (percentage.compareTo(BigDecimal.ZERO) <= 0) {
                fouten.put("percentage", "tik een positief getal");
            }
        } catch (NumberFormatException ex) {
            fouten.put("percentage", "tik een positief getal");
        }
        if (fouten.isEmpty()) {
            artikelService.prijsverhoging(percentage);
            response.sendRedirect(request.getContextPath());
        } else {
            request.setAttribute("fouten", fouten);
            request.getRequestDispatcher(VIEW).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(VIEW).forward(request, response);
    }
}
