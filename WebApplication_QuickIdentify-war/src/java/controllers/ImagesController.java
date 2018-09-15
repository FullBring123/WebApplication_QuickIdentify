/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.file.Files;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author PC
 */
public class ImagesController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private String imagePath;
    
    //Initialisation
    @Override
    public void init() throws ServletException {
        // Définit le chemin de base en quelque sorte. Vous pouvez le définir comme init-param du servlet..
        this.imagePath = "C:\\Users\\PC\\Desktop\\Système de collecte d'informations QRCODE\\Photos";

        // Dans un environnement Windows avec le serveur d'applications s'exécutant sur le volume 7
        // c:, le chemin ci-dessus est exactement le même que "c: \ var \ webapp \ images". 
        // Sous Linux / Mac / UNIX, c'est simplement "/ var / webapp / images".
    }
    
    // Actions ------------------------------------------------------------------------------------

      @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Obtenir l'image demandée par les informations de chemin.
        String requestedImage = request.getPathInfo();

        // Vérifiez si le nom du fichier est réellement fourni à l'URI de la demande.
        if (requestedImage == null) {
             // Faites votre truc si l'image n'est pas fournie à l'URI de la requête. 
            // Lancer une exception, ou envoyer 404, ou afficher une image par défaut / avertissement, ou simplement l'ignorer.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Décode le nom du fichier (peut contenir des espaces et activé) et prépare l’objet du fichier.
        File image = new File(imagePath, URLDecoder.decode(requestedImage, "UTF-8"));

        // Vérifiez si le fichier existe réellement dans le système de fichiers. 
        if (!image.exists()) {
             // Faites votre truc si le fichier semble ne pas exister. 
            // Lancer une exception, ou envoyer 404, ou afficher une image par défaut / avertissement, ou simplement l'ignorer.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Récupère le type de contenu par nom de fichier.
        String contentType = getServletContext().getMimeType(image.getName());

         // Vérifiez si le fichier est réellement une image (évitez le téléchargement d'autres fichiers par des pirates!). 
        // Pour tous les types de contenu, voir: http://www.w3schools.com/media/media_mimeref.asp 
        if (contentType == null || !contentType.startsWith("image")) {
            // Faites votre truc si le fichier ne semble pas être une image réelle. 
            // Lancer une exception, ou envoyer 404, ou afficher une image par défaut / avertissement, ou simplement l'ignorer.
            response.sendError(HttpServletResponse.SC_NOT_FOUND); // 404.
            return;
        }

        // Initie la réponse du servlet.
        response.reset();
        response.setContentType(contentType);
        response.setHeader("Content-Length", String.valueOf(image.length()));

        // Écrivez le contenu de l'image dans la réponse.
        Files.copy(image.toPath(), response.getOutputStream());
    }
    
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet ImagesController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet ImagesController at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
