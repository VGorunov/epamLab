package by.gsu.epamlab.controllers;

import by.gsu.epamlab.Constants;
import by.gsu.epamlab.model.exception.DAOException;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.interfaces.ITaskDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

@WebServlet("/todo/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class FileUploadServlet extends AbstractServlet {
    @Override
    protected void performTask(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uploadUuid = UUID.randomUUID();
        String id = req.getParameter("taskId");
        Part filePart = req.getPart("file");

        //return the absolute disk file system path
        String uploadPath = req.getServletContext().getRealPath("") + File.separator + Constants.UPLOAD_DIR;
        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        System.out.println(fileName);
        String actualFileName = uploadUuid.toString() + "__" + fileName;
        System.out.println(actualFileName);
        String uploadFilePath = uploadPath + File.separator + actualFileName;
        System.out.println(uploadPath);
        System.out.println(uploadFilePath);
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        filePart.write(uploadFilePath);

        try {
            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            taskDAO.addFileName(fileName, Constants.UPLOAD_DIR + File.separator + actualFileName, id);
            resp.getWriter().println("OK");
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("taskId");
        try {
            ITaskDAO taskDAO = TaskFactory.getClassFromFactory();
            String actualFileName = taskDAO.getActualFileName(id);
            taskDAO.deleteFile(id);

            String fullActualFileName = req.getServletContext().getRealPath("") + File.separator + actualFileName;
            File uploadedFile = new File(fullActualFileName);

            if (!uploadedFile.exists()) {
                resp.getWriter().println("OK: File isn't exists");
                return;
            }
            resp.getWriter().println("OK: " + uploadedFile.delete());
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
