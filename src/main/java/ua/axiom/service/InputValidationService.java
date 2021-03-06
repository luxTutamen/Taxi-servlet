package ua.axiom.service;

import ua.axiom.core.annotations.Autowired;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Pattern;

public class InputValidationService {
    public enum InputType{
        username,
        password,
        location,
    }

    @Autowired
    private LocalisationService localisationService;
    private Map<InputType, Pattern> patternCache = new HashMap<>();

    public boolean isValid(String input, InputType type, Locale locale) {
        Pattern pattern = patternCache.get(type);

        if(pattern == null) {
            pattern = Pattern.compile(localisationService.getRegex(type.toString(), locale));
            patternCache.put(type, pattern);
        }

        return pattern.matcher(input).matches();
    }
}
