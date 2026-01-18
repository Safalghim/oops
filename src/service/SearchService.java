package service;

import model.Staff;
import java.util.List;
import java.util.stream.Collectors;

public class SearchService {

    public List<Staff> searchByKeyword(List<Staff> data, String keyword) {

        if (data == null) return List.of();
        if (keyword == null || keyword.isBlank()) return data;

        final String lower = keyword.toLowerCase();  // ✔ final → lambda-safe

        return data.stream()
            .filter(s -> {
                if (s == null) return false;

                String name = s.getName() == null ? "" : s.getName();
                String email = s.getEmail() == null ? "" : s.getEmail();
                String pos = s.getPosition() == null ? "" : s.getPosition();

                return name.toLowerCase().contains(lower)
                        || email.toLowerCase().contains(lower)
                        || pos.toLowerCase().contains(lower);
            })
            .collect(Collectors.toList());
    }
}
