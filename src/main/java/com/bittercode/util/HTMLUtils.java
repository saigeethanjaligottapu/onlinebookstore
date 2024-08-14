package com.bittercode.util;

public class HTMLUtils {
    public static String escapeHtml(String input) {
        if (input == null) {
            return null;
        }
        return input.replaceAll("&", "&amp;")
                    .replaceAll("<", "&lt;")
                    .replaceAll(">", "&gt;")
                    .replaceAll("\"", "&quot;")
                    .replaceAll("'", "&#x27;")
                    .replaceAll("/", "&#x2F;");
    }
}
