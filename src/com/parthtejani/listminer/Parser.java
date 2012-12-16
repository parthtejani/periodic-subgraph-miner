package com.parthtejani.listminer;

import java.util.*;

public class Parser {

    private final Set<String> tags = new HashSet<String>();
    private final Map<String, Option> options = new HashMap<String, Option>();
    private final Map<Option, String> values = new HashMap<Option, String>();

    /**
     * Main constructor
     * @param options Array of options available for parsing
     */
    public Parser(Option[] options) {
        for (Option o : options) {
            tags.add(o.tag);
            this.options.put(o.tag, o);
        }
    }

    public void parse(String[] tokens) {
        int i = 0;
        while (i < tokens.length) {
            //check if current token is a tag
            String token = tokens[i++];
            if (!tags.contains(token)) {
                throw new IllegalArgumentException("Unrecognized option: " + token);
            }

            //obtain which option is being set for this token
            Option option = options.get(token);

            //if the token is not a flag, obtain the next token, and put it in the values map
            //for retrieval later
            if (!option.flag) {
                if (i == tokens.length) {
                    throw new IllegalArgumentException("Missing value for option: " + option.name + ", " + option.tag);
                }
                token = tokens[i++];
                values.put(option, token);
            } else {
                //token was a flag, so just put a "true" to represent flag was present
                //Note: It actually just needs to be a non null value, so any String would be
                //fine
                values.put(option, "true");
            }

        }
    }

    public Map<Option, String> getValues() {
        return values;
    }
}
