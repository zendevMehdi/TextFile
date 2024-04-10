package org.zendev.lib.text;

import org.zendev.lib.text.options.CountOption;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class TextFile {
    private String path;

    public TextFile(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> readAllLines() throws IOException {
        return Arrays.asList(Files.readString(Path.of(path)).split("\n"));
    }

    public String read() throws IOException {
        return Files.readString(Path.of(path));
    }

    public boolean write(String text, boolean append) throws IOException {
        FileWriter writer = new FileWriter(path);
        if (append) {
            writer.append(text);
        } else {
            writer.write(text);
        }

        writer.close();
        return true;
    }

    public boolean write(List<String> lines, boolean append) throws IOException {
        FileWriter writer = new FileWriter(path);
        for (String line : lines) {
            if (append) {
                writer.append(line);
            } else {
                writer.write(line);
            }
        }

        writer.close();
        return true;
    }

    public boolean search(String keyword, boolean caseSensitive, boolean wholeWord) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(path));
        while (scan.hasNext()) {
            String line = scan.nextLine();

            if (!caseSensitive) {
                keyword = keyword.toLowerCase();
                line = line.toLowerCase();
            }

            if (wholeWord) {
                if (Arrays.asList(line.split("\\s+")).contains(keyword)) {
                    return true;
                }
            } else {
                if (line.contains(keyword)) {
                    return true;
                }
            }
        }

        scan.close();
        return false;
    }

    public long count(CountOption option) throws IOException {
        switch (option) {
            case CHARACTERS -> {
                return Files.readString(Path.of(path)).length();
            }

            case WORDS -> {
                return Files.readString(Path.of(path)).split("\\s+").length;
            }

            case LINES -> {
                return Files.readString(Path.of(path)).split("\n").length;
            }

            case EMPTY_LINES -> {
                String[] lines = Files.readString(Path.of(path)).split("\\s+");
                long emptyLines = 0;

                for (String line : lines) {
                    if (line.isEmpty() || line.isBlank()) {
                        emptyLines++;
                    }
                }

                return emptyLines;
            }

            case SENTENCES -> {
                return Files.readString(Path.of(path)).split("\\.").length;
            }
        }

        return -1;
    }

    public boolean replace(String keyword, String newWord) throws IOException {
        String content = Files.readString(Path.of(path))
                .replace(keyword, newWord);

        Files.writeString(Path.of(path), content);
        return true;
    }

    public boolean clear() throws IOException {
        Files.writeString(Path.of(path), "");
        return true;
    }
}
