package com.hardisgroup.parser;

import com.hardisgroup.common.model.Report;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.File;

@RunWith(MockitoJUnitRunner.class)
public class ParserTest {

    private static final String INPUT_CSV = "input.csv";
    private static final String INPUT_OK_CSV = "input_ok.csv";

    private final File ERROR_FILE = new File(this.getClass().getClassLoader().getResource(INPUT_CSV).getFile());
    private final File CORRECT_FILE = new File(this.getClass().getClassLoader().getResource(INPUT_OK_CSV).getFile());

    @InjectMocks
    Parser parser;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void parseKO() throws Exception {

        Report report = parser.parse(ERROR_FILE.getAbsolutePath());
        Assert.assertNotNull(report);
        Assert.assertEquals(1, report.getErrors().size());
        Assert.assertEquals("1462100403, A, 100.1, 9", report.getErrors().get(0).getValue());
        Assert.assertEquals("Incorrect value for color", report.getErrors().get(0).getMessage());
        Assert.assertEquals(4, report.getReferences().size());

    }

    @Test
    public void parseOK() throws Exception {

        Report report = parser.parse(CORRECT_FILE.getAbsolutePath());
        Assert.assertNotNull(report);
        Assert.assertEquals(5, report.getReferences().size());
        Assert.assertEquals(0, report.getErrors().size());

    }

}