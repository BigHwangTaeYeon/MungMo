package mungmo.adminService.admin.com.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import mungmo.adminService.admin.otherDomain.room.dto.MeetupRoomParticipantDTO;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ObjectConverter {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    private static Object jsonBody(ResponseEntity<?> res) {
        return ((LinkedHashMap<String, Object>) res.getBody()).get("body");
    }

    public static <T> T operating(ResponseEntity<?> res, Class<T> toValueType) {
        return MAPPER.convertValue(jsonBody(res), toValueType);
    }

    public static <T> ArrayList<T> operatingList(ResponseEntity<?> res, Class<T> toValueType) {
        List<?> list = (List<?>) ObjectConverter.jsonBody(res);
        return list.stream()
                .map(body -> MAPPER.convertValue(body, toValueType))
                .collect(Collectors.toCollection(ArrayList::new));
    }
}
