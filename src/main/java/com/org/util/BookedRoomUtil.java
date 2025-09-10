package com.org.util;

import com.org.entity.BookedRoom;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class BookedRoomUtil {
    private BookedRoomUtil() {
    }

    public static Set<Integer> collectIds(List<BookedRoom> bookedRooms) {
        return bookedRooms.stream()
                .map(br -> br.getRoom().getId())
                .collect(Collectors.toSet());
    }
}
