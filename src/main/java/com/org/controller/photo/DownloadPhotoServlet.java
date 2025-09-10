package com.org.controller.photo;

import com.org.dao.PhotoDAO;
import com.org.entity.Photo;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Optional;

@WebServlet("/room/download/photo")
public class DownloadPhotoServlet extends HttpServlet {
    PhotoDAO photoDAO = PhotoDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String photoIdRaw = req
                .getParameter("id");
        if (photoIdRaw == null) {
            // UNABLE TO DOWNLOAD
            return;
        }
        Integer photoId = Integer
                .valueOf(photoIdRaw);
        Optional<Photo> optionalPhoto = photoDAO
                .findById(photoId);

        if (optionalPhoto.isPresent()) {
            Photo photo = optionalPhoto
                    .get();

            resp.setContentType(
                    Files.probeContentType(
                            Paths.get(
                                    photo.getFileLocation()
                            )
                    )
            );

            resp.setHeader(
                    "Content-Disposition",
                    "inline; filename=\"" + photo.getFileName() + "\""
            );

            Files.copy(
                    Paths.get(
                            photo.getFileLocation()
                    ),
                    resp.getOutputStream()
            );
        } else {
            throw new ServletException("Nothing found");
        }
    }
}
