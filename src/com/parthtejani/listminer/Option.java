package com.parthtejani.listminer;

/**
 * Enum for the possible options for the program.
 * 
 * <p>Each option is represented with a name, the command line tag, and a boolean indicating whether
 * the option is used as a standalone flag or takes in an input value.
 */
public enum Option {
    INPUT_FILE("Input file", "-i", false),
    OUTPUT_FILE("Output file", "-o", false),
    MIN_SUPPORT("Min support", "-s", false),
    MIN_PERIOD("Min period", "-Pmin", false),
    MAX_PERIOD("Max period", "-Pmax", false),
    SMOOTH_STEP("Smooth step", "-smooth", false),
    SUBSUME("Subsume Off", "-f", true),
    PRINT_GRAPHS("Print graphs", "-d", true);

    public final String name;
    public final String tag;
    public final boolean flag;

    Option(String name, String tag, boolean flag) {
        this.name = name;
        this.tag = tag;
        this.flag = flag;
    }
}
