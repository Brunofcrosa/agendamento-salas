package controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import model.Sala;
import service.SalaService;

import java.io.IOException;
import java.util.List;

@WebServlet("/sala")
public class SalaServlet extends HttpServlet {

    private SalaService service = new SalaService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String acao = req.getParameter("acao");
        String tela = req.getParameter("tela");
        try {
            if ("editar".equals(acao)) {
                int id = Integer.parseInt(req.getParameter("id"));
                Sala sala = service.buscarPorId(id);
                req.setAttribute("sala", sala);
                tela = "novo";
            }

            if ("excluir".equals(acao)) {
                int id = Integer.parseInt(req.getParameter("id"));
                service.excluir(id);
                resp.sendRedirect("sala?tela=listar&msg=excluido");
                return;
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("erro", e.getMessage());
        }

        if (tela == null || tela.isEmpty()) {
            tela = "listar";
        }

        List<Sala> lista = service.listar();
        req.setAttribute("salas", lista);
        req.setAttribute("tela", tela);

        RequestDispatcher rd = req.getRequestDispatcher("WEB-INF/pages/salas.jsp");
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("usuario") == null) {
            resp.sendRedirect("index.jsp");
            return;
        }

        String idParam = req.getParameter("id");
        String nome = req.getParameter("nome");
        String bloco = req.getParameter("bloco");
        try {
            String recursos = req.getParameter("recursos");
            int capacidade = Integer.parseInt(req.getParameter("capacidade"));
            boolean ativa = "on".equals(req.getParameter("ativa"));
            Sala sala = new Sala(nome, bloco, capacidade, recursos, ativa);

            if (idParam != null && !idParam.isEmpty()) {
                sala.setId(Integer.parseInt(idParam));
                service.atualizar(sala);
                resp.sendRedirect("sala?tela=listar&msg=editado");
            } else {
                service.inserir(sala);
                resp.sendRedirect("sala?tela=listar&msg=salvo");
            }
        } catch (IllegalArgumentException e) {
            req.setAttribute("erro", e.getMessage());
            req.setAttribute("salas", service.listar());
            req.setAttribute("tela", "novo");
            req.getRequestDispatcher("WEB-INF/pages/salas.jsp").forward(req, resp);
        }
    }
}
