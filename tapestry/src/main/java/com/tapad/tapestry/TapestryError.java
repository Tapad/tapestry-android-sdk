package com.tapad.tapestry;

import com.tapad.util.Logging;

public class TapestryError {
    public static int UNEXPECTED_EXCEPTION_ERROR = 0;
    public static int JSON_PARSE_ERROR = 1;
    public static int BAD_REFERRER_ERROR = 2;
    public static int NO_PARTNER_ID_ERROR = 3;
    public static int OPTED_OUT = 4;
    public static int NO_PERMISSIONS = 5;
    public static int UNRECOGNIZED_PARAMETER = 6;
    public static int CANNOT_IDENTIFY_DEVICE = 7;
    public static int CLIENT_REQUEST_ERROR = 8;

    public int type;
    public String name;
    public String message;

    public static TapestryError fromJSON(String error) {
        try {
            String[] split = error.split("\\|");
            String message = split.length > 2 ? split[2] : "";
            return new TapestryError(Integer.parseInt(split[0]), split[1], message);
        } catch (Exception e) {
            Logging.error(TapestryError.class, "Could not parse error message " + error, e);
            return new TapestryError(0, "UnexpectedExceptionError", e.getMessage());
        }
    }

    public TapestryError(int type, String name, String message) {
        this.type = type;
        this.name = name;
        this.message = message;
    }

    @Override
    public String toString() {
        return type + "|" + name + (message.isEmpty() ? "" : "|" + message);
    }

    @Override
    public boolean equals(Object o) {
        return toString().equals(o.toString());
    }

    @Override
    public int hashCode() {
        return toString().hashCode();
    }
}