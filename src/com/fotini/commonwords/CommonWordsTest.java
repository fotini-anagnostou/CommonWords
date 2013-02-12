package com.fotini.commonwords;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

public class CommonWordsTest {

    @Test
    public void testCommonWords() throws IOException {
        String[] args = {
                "D:/Data/Workspaces/eclipse/idocsNG/CommonWords/resources/input/inputFileA.txt",
                "D:/Data/Workspaces/eclipse/idocsNG/CommonWords/resources/input/inputFileB.txt",
                "D:/Data/Workspaces/eclipse/idocsNG/CommonWords/resources/output/outputFile.txt" };
        
        CommonWords.main(args);

        makeAssertions(args[2]);
    }

    private void makeAssertions(String outputFile) throws FileNotFoundException, IOException {
        List<String> outputFileLines = new ArrayList<String>();

        InputStream fis = new FileInputStream(outputFile);
        BufferedReader br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));

        String line;
        while ((line = br.readLine()) != null) {
            outputFileLines.add(line);
        }
        br.close();
        
        String[] expected = {"Apples", "Bananas", "Bread", "Milk", "Oranges", "Soap"};
        
        Assert.assertEquals(outputFileLines.size(), expected.length);
        
        for (int i=0; i<outputFileLines.size(); i++) {
            Assert.assertEquals(outputFileLines.get(i), expected[i]);
        }
    }
}
