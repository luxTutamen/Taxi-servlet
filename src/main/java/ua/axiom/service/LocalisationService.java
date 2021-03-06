package ua.axiom.service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Provides localised text for html pages, localised regexe's for input check
 */
public class LocalisationService {
    private static Map<Locale, Map<String, String>> localeToDictionaryMap;

    public LocalisationService() {
        localeToDictionaryMap = new HashMap<>();
        try {
            loadProperties("src/main/resources/properties/pagecontent_eng.properties", Locale.ENGLISH);
            loadProperties("src/main/resources/properties/pagecontent_ukr.properties", new Locale("UA"));
        } catch (IOException ioe) {
            ioe.printStackTrace();
            System.exit(2);
        }
    }

    public void setLocalisedMessages(Map<String, Object> model, Locale locale, String... messages) {
        Arrays
                .stream(messages)
                .forEach(m -> model.put(m, getLocalisedMessage(m, locale)));
    }

    public String getLocalisedMessage(Locale locale, String msg) {
        return localeToDictionaryMap.get(locale).get(msg);
    }

    public String getRegex(String key, Locale locale) {
        return localeToDictionaryMap.get(locale).get("regex." + key);
    }

    private String getLocalisedMessage(String messageKey, Locale locale) {
        Map<String, String> dictionary = localeToDictionaryMap.get(locale);

        if(dictionary == null) {
            throw new IllegalStateException("Locale <" + locale + "> not present in the " + this.getClass());
        }

        String result = dictionary.get(messageKey);

        if(result == null) {
            return "Cannot find entry for key " + messageKey + ", for locale " + locale;
        } else {
            return result;
        }
    }

    private Map<String, String> getDictionary(Locale locale) {
        Optional<Map<String, String>> dictionaryOptional = Optional.of(localeToDictionaryMap.get(locale));

        return dictionaryOptional.orElseThrow(() ->  new IllegalStateException("Locale " + locale + " is not present in " + this.getClass()));
    }

    private void addLocale(Locale locale) {
        if(localeToDictionaryMap.containsKey(locale)) {
            throw new IllegalArgumentException("Locale " + locale + "is already present in " + this.getClass());
        }

        localeToDictionaryMap.put(locale, new HashMap<>());
    }

    private void loadProperties(String filePath, Locale toLoad) throws IOException {
        Properties properties = new Properties();

        Path root = Paths.get(new File("").getAbsolutePath()).getParent().getParent().getParent();
        filePath = root.toString() + "/" + filePath;


        try {
            properties.load(new InputStreamReader(new FileInputStream(filePath), StandardCharsets.UTF_8));


        } catch (FileNotFoundException fnfe) {
            new File(filePath + "placeproperties.here").createNewFile();
            throw new IOException();
        }

        Enumeration<Object> keys = properties.keys();
        if(!localeToDictionaryMap.containsKey(toLoad)) {
            addLocale(toLoad);
        }

        Map<String, String> localDictionary = localeToDictionaryMap.get(toLoad);
        while (keys.hasMoreElements()) {
            String key = (String)keys.nextElement();
            localDictionary.put(key, properties.getProperty(key));
        }
    }
}
