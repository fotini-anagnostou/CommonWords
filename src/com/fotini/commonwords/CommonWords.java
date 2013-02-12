package com.fotini.commonwords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class CommonWords {

    private static final Logger logger = Logger.getLogger(CommonWords.class);

    public static void main(String[] args) throws IOException {

        if (args.length < 3) {
            logger.error(" Usage is: com.fotini.commonwords.CommonWords inputPathA inputPathB outptuPath");
            return;
        }

        work(args[0], args[1], args[2]);
    }

    private static void work(String inputA, String inputB, String output)
            throws IOException {

        Set<String> inputFileALines = new HashSet<String>();
        Set<String> outputFileLines = new HashSet<String>();

        InputStream fis = null;
        BufferedReader br = null;
        try {
            fis = new FileInputStream(inputA);
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

            String line;
            while ((line = br.readLine()) != null) {
                inputFileALines.add(line);
            }
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }
                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                logger.error("Error closing file or stream: " + e.getMessage());
            }
        }

        try {
            fis = new FileInputStream(inputB);
            br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

            String line;
            while ((line = br.readLine()) != null) {
                if (inputFileALines.contains(line)) {
                    outputFileLines.add(line);
                }
            }
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                }

                if (br != null) {
                    br.close();
                }
            } catch (Exception e) {
                logger.error("Error closing file: " + e.getMessage());
            }
        }

        writeToFile(output, outputFileLines);
    }

    private static void writeToFile(String output, Set<String> outputFileLines)
            throws IOException {

        List<String> sortedLines = sortLines(outputFileLines);

        FileWriter writer = null;
        try {
            writer = new FileWriter(output);
            for (String line : sortedLines) {
                writer.write(line + "\n");
            }
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                logger.error("Error closing file: " + e.getMessage());
            }
        }
    }

    private static List<String> sortLines(Set<String> outputFileLines) {
        List<String> sortedLines = new ArrayList<String>(outputFileLines);
        Collections.sort(sortedLines);
        return sortedLines;
    }
}
