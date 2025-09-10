package com.org.controller.room;

import com.org.dao.BookedRoomDAO;
import com.org.dao.BranchDAO;
import com.org.entity.BookedRoom;
import com.org.entity.Branch;
import com.org.entity.Room;
import com.org.util.BookedRoomUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@WebServlet("/customer/branch")
public class RoomsByBranchServlet extends HttpServlet {
    BranchDAO branchDAO = BranchDAO
            .getInstance();

    BookedRoomDAO bookedRoomDAO = BookedRoomDAO
            .getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String branchIdRaw = req
                .getParameter("id");
        if (branchIdRaw == null) {
            // UNABLE TO GET ID
            return;
        }
        Integer branchId = Integer
                .valueOf(branchIdRaw);
        Optional<Branch> optionalBranch = branchDAO
                .findById(branchId);

        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch
                    .get();

            List<Room> rooms = branch
                    .getRooms();

            req
                    .setAttribute("branch", branch);
            req
                    .setAttribute("rooms", rooms);

            // GOT ALL ROOMS BY BRANCH
        } else {
            // UNABLE TO FIND BRANCH
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String checkInRaw = req
                .getParameter("checkIn");
        String checkOutRaw = req
                .getParameter("checkOut");

        if (checkInRaw == null || checkOutRaw == null) {
            // UNABLE TO GET PARAMETERS
            return;
        }

        LocalDate checkIn = LocalDate
                .parse(checkInRaw);
        LocalDate checkOut = LocalDate
                .parse(checkOutRaw);

        String branchIdRaw = req
                .getParameter("id");
        if (branchIdRaw == null) {
            // UNABLE TO GET BRANCH ID
            return;
        }
        Integer branchId = Integer
                .valueOf(branchIdRaw);
        Optional<Branch> optionalBranch = branchDAO
                .findById(branchId);

        if (optionalBranch.isPresent()) {
            Branch branch = optionalBranch
                    .get();

            List<BookedRoom> overlapping = bookedRoomDAO
                    .findOverlapping(checkIn, checkOut);

            Set<Integer> blockedRoomIds = BookedRoomUtil
                    .collectIds(overlapping);

            List<Room> availableRooms = branch
                    .getRooms().stream()
                    .filter(r -> !blockedRoomIds.contains(r.getId()))
                    .toList();

            req
                    .setAttribute("branch", branch);
            req
                    .setAttribute("rooms", availableRooms);

            // GOT ALL FILTERED ROOMS
        } else {
            // UNABLE TO FIND BRANCH
        }
    }
}
