package com.hardisgroup;


import com.hardisgroup.common.model.Report;
import com.hardisgroup.common.utils.Constants;
import com.hardisgroup.common.utils.InputsValidators;
import com.hardisgroup.mapper.Mapper;
import com.hardisgroup.parser.Parser;
import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import java.io.File;
import java.util.UUID;

@EnableAutoConfiguration
public class Main {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
        if (!InputsValidators.validateInputs(args)) {
            System.err.println(Constants.MISSING_PARAMETER_MSG);
            System.err.println(Constants.HOW_TO_RUN_APPLICATION_MSG);
        } else {
            parseAndMapFile(args);
        }
    }

    private static void parseAndMapFile(String[] args) {
        final String inputFile = args[0];
        final String outputFormat = args[1];
        final String outputLocation = args[2];

        try {
            String outputFileName = outputLocation + UUID.randomUUID() + Constants.DOT + outputFormat.toLowerCase();
            System.out.println(outputFileName);
            File outputFile = new File(outputFileName);

            // parse the input file.
            Parser parser = new Parser();
            Report report = parser.parse(inputFile);

            // Map the input file to the desired format
            Mapper mapper = new Mapper();
            FileUtils.writeStringToFile(outputFile, mapper.map(report, outputFormat));

        } catch (Exception e) {
            System.err.print(e.getMessage());
        }
    }
}
