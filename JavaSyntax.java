package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class JavaSyntax {

    public static String html = "<html>\n<head><title>Java Syntax Highlighter</title></head>\n<body style='font-size:x-large; font-family: monospace; font-weight: 700;'><code>\n%s\n</code>\n</body>\n</html>";

    public static ArrayList<String> keywords = new ArrayList<>(Arrays.asList("abstract", "assert", "boolean",
            "break", "byte", "case", "catch", "char", "class", "const",
            "continue", "default", "do", "double", "else", "enum",
            "extends", "for", "final", "finally", "float", "goto",
            "if", "implements", "import", "instanceof", "int",
            "interface", "long", "native", "new", "package", "private",
            "protected", "public", "return", "short", "static",
            "strictfp", "super", "switch", "synchronized", "this",
            "throw", "throws", "transient", "try", "void", "volatile",
            "while", "true", "false", "null"));

    // werdgfh ws rgfgd s sd public
    public static void main(String[] args) throws IOException {
        ArrayList<String> data = read(args[0]);
        String formatedData = "";
        for (String line: data) {
            formatedData += "<br>" + format(line);
        }
        write(args[0], String.format(html, formatedData));
    }

    public static String format(String line) {
        if(isComment(line)) {
            return String.format("<span style='color: green'>%s</span>", line);
        }
        ArrayList<String> items = new ArrayList<>(Arrays.asList(line.split(" ")));
        ArrayList<String> formatItems = new ArrayList<>();
        for (String item: items) {
            if(isKeyword(item)) {
                formatItems.add(String.format(" " + "<span style='color: blue'>%s</span>", item));
            } else {
                formatItems.add(" " + item);
            }
            // TODO add literals
        }
        String format = "";
        for(int i = 0; i < formatItems.size(); i++) {
            format += (formatItems.get(i).equals(" ") ? "&nbsp" : formatItems.get(i));
        }

        return format;
    }

    public static boolean isLiteral(String item) {
        return item.contains("\"");
    }
    public static String formatLiteral(String item) {
        return item;
    }

    public static boolean isKeyword(String item) {
        return keywords.contains(item);
    }

    public static boolean isComment(String line) {
        return line.startsWith("//");
    }

    public static ArrayList<String> read(String sourceCode) throws IOException {
        ArrayList<String> lines = new ArrayList<>();
        File file = new File(sourceCode);
        FileReader fileReader = new FileReader(file);
        try (BufferedReader input = new BufferedReader(fileReader)){
            String line;
            while ((line = input.readLine()) != null) {
                lines.add(line);
            }
        }
        return lines;
    }
    public static void write(String path, String sourceCode) throws IOException {
        try(DataOutputStream fileOutputStream = new DataOutputStream(new FileOutputStream(path.replace(".java", ".html")));){
            fileOutputStream.writeUTF(sourceCode);
        }
    }
}
