/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package hanoi;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author zalila
 */
public class HanoiServlet extends HttpServlet {

    /**
     * Ensemble de 15 couleurs
     */
    static String[] couleurs = {"black",
        "blue",
        "green",
        "cyan",
        "red",
        "darkmagenta",
        "brown",
        "lightgray",
        "darkgray",
        "lightblue",
        "lightgreen",
        "lightcyan",
        "orange",
        "magenta",
        "yellow"};

    /**
     * Nombre maximal de tours supportées pour ne pas écrouler le serveur
     */
    static final int maxTours = 10;

    private int ntours;
    private int [] depart;
    private int [] intermed;
    private int [] arrivee;
    private int etape;

    /**
     * Emet le nombre d'espaces indiqué avec la couleur d'arrière plan
     * indiquée et un alignement centré.
     * @param out
     * @param nspaces
     * @param htmlColor
     */
    protected void printColoredSpaces(PrintWriter out, int nspaces) {
        out.write("<p align='center'>");
        out.write("<span style='background:" + couleurs[nspaces - 1] + "'>");
        for (int i = 0; i < 3 * nspaces; i++) {
            out.write("&nbsp;");
        }
        out.write("</span>");
        out.write("</p>");
    }

    /**
     * Place le disque de diamètre i dans la pile de tours p
     * @param d
     * @param p
     */
    protected void placer(int d, int[] p) {
        int i;

        // On insère le disque d au dessus des disques déjà présents

        for (i = ntours - 1; i >= 0; i--) {
            /* Dès qu'on trouve un disque dans p, on insère d au dessus et on
             * arrète la boucle. */

            if (p[i] != 0) {
                p[i + 1] = d;
                break;
            }
        }

        /* Si on n'a pas trouvé de disque, cela veur dire que la place était
         * vide. On place notre disque à la position 0. */

        if (p[0] == 0) {
            p[0] = d;
        }
    }

    /**
     * Enlève le disque le plus haut de la pile de tours p et retourne sa valeur
     * @param p
     * @return
     */
    int enlever(int[] p) {
        int i, r;

        /* Enlève le disque le plus haut (celui ayant le plus petit
         * diamètre) de la place p. */

        for (i = ntours - 1; i >= 0; i--) {
            if (p[i] != 0) {
                r = p[i];
                p[i] = 0;
                return r;
            }
        }

        return 0; // Ce cas ne devrait jamais arriver
    }

    /**
     * Résoud récursivement le problème des tours de Hanoi d'ordre n
     * @param n
     * @param depart
     * @param intermed
     * @param arrivee
     * @param out
     */
    protected void resoudreTH(int n,
            int[] depart,
            int[] intermed,
            int[] arrivee,
            PrintWriter out) {
        if (n == 1) {
            placer(enlever(depart), arrivee);
            afficherPlaces(out);
        } else {
            /* 1 - On déplace N - 1 disque à partir de la position 'depart'
             * vers la position 'intermed'. On utilise la position 'arrivee'
             * comme relais. */

            resoudreTH(n - 1, depart, arrivee, intermed, out);

            /* 2 - On déplace le disque le plus grand vers la position
             * 'arrivee'. */

            placer(enlever(depart), arrivee);
            afficherPlaces(out);

            /* 3 - On déplace N - 1 disques à partir de la position 'intermed'
             * vers la position 'arrivee' en utilisant la position 'depart'
             * comme relais. */

            resoudreTH(n - 1, intermed, depart, arrivee, out);
        }
    }

    /**
     * Affiche un tableau HTML décrivant l'état courant des différentes tours
     * de Hanoï.
     * @param out
     */
    protected void afficherPlaces(PrintWriter out) {
        /* On n'affiche que les disques : les nombres non nuls. On part du
         * haut vers le bas. */

        out.println("<h5>Étape " + ++etape + "</h5>");
        out.println("<table border=0 style=\"border:1px blue solid\">");

        for (int i = ntours - 1; i >= 0; i--) {
            if ((depart[i] != 0 || arrivee[i] != 0) || intermed[i] != 0) {
                out.println("  <tr>");
                out.println("    <td width=\"200\">");
                if (depart[i] != 0) {
                    printColoredSpaces(out, depart[i]);
                    // out.println (depart [i]);
                }
                out.println("    </td>");
                out.println("    <td width=\"200\">");
                if (intermed[i] != 0) {
                    printColoredSpaces(out, intermed[i]);
                    // out.println (intermed [i]);
                }
                out.println("    </td>");
                out.println("    <td width=\"200\">");
                if (arrivee[i] != 0) {
                    printColoredSpaces(out, arrivee[i]);
                    // out.println (arrivee[i]);
                }
                out.println("    </td>");
                out.println("  </tr>");
            }
        }
        out.println("</table>");
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        ntours = Integer.valueOf(request.getParameter("ntours"));
        // Nombre de tours de Hanoï dans le probleme

        if (ntours > maxTours) {
            out.println("<html>");
            out.println("<head>");
            out.println("  <title>Servlet HanoiServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1>Servlet HanoiServlet at " + request.getContextPath() + "</h1>");
            out.println("  <p></p>");
            out.println("  <h2>Nombre de tours : <b>" + ntours + "</h2></b><br><br>");
            out.println("  Nombre de tours supérieurs à "+ maxTours + " non supporté");
            out.println("</body>");
            out.println("</html>");
            return;
        }

        depart = new int[ntours];
        intermed = new int[ntours];
        arrivee = new int[ntours];
        etape = 0;

        // Initialisation des Tableaux
        for (int i = 0; i < ntours; i++) {
            depart[i] = intermed[i] = arrivee[i] = 0;
        }

        // Construction de la tour initiale
        for (int i = 0; i < ntours; i++) {
            depart[i] = ntours - i;
        }

        try {
            // Affichage de l'entête de la réponse Web
            out.println("<html>");
            out.println("<head>");
            out.println("  <title>Servlet HanoiServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("  <h1>Servlet HanoiServlet at " + request.getContextPath() + "</h1>");
            out.println("  <p></p>");
            out.println("  <h2>Nombre de tours : <b>" + ntours + "</b></h2>");
            out.println("  <p></p>");
            afficherPlaces(out);
            resoudreTH(ntours, depart, intermed, arrivee, out);
            out.println("</body>");
            out.println("</html>");

        } finally {
            out.close();

            // Pour accelerer le comportement du ramasse miette
            depart = null;
            intermed = null;
            arrivee = null;

        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
