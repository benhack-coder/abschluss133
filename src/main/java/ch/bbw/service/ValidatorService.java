package ch.bbw.service;

import ch.bbw.entities.Person;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Service
public class ValidatorService {

    public Map<String, String> validate(Person person) {
        Map<String, String> res = new HashMap<>();
        if (person.getFirstname().length() < 2 || person.getFirstname().length() > 30 || person.getLastname().length() < 2 || person.getLastname().length() > 30) {
            res.put("valid", "false");
            res.put("msg", "invalid name length. The first and last name must be between 2 and 30 characters");
            return res;
        }
        try {
            Date date = person.getBirthday();
            if (date.getYear() >= 105) {
                res.put("valid", "false");
                res.put("msg", "invalid date, must be before 2005");
                return res;
            }
        } catch (Exception e) {
            res.put("valid", "false");
            res.put("msg", "invalid date, must be before 2005");
            return res;
        }
        if (!person.getGender().toLowerCase(Locale.ROOT).equals("m") && !person.getGender().toLowerCase(Locale.ROOT).equals("w")) {
            res.put("valid", "false");
            res.put("msg", "invalid gender. there are only 2 genders (m or w)");
            return res;
        }
        if (!person.getEmail().contains("@")) {
            res.put("valid", "false");
            res.put("msg", "invalid email");
            return res;
        }
        res.put("valid", "true");
        res.put("msg", "");
        return res;
    }
}
