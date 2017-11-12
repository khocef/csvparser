package com.hardisgroup.parser;

import com.hardisgroup.common.model.Error;
import com.hardisgroup.common.model.Reference;
import com.hardisgroup.common.model.Report;
import com.hardisgroup.common.utils.Constants;
import com.hardisgroup.common.utils.InputsValidators;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.collections.CollectionUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Parser {

    public Report parse(final String inputFile) throws UnsupportedEncodingException, FileNotFoundException {

        CsvParserSettings settings = new CsvParserSettings();
        settings.getFormat().setDelimiter(Constants.DELIMITER);
        CsvParser csvParser = new CsvParser(settings);

        Report report = new Report();
        report.setInputFile(inputFile);

        List<String[]> lines = csvParser.parseAll(getReader(inputFile));

        if (CollectionUtils.isNotEmpty(lines)) {
            List<Reference> references = new ArrayList<>();
            List<Error> errors = new ArrayList<>();

            lines.forEach((line) -> {
                if (this.isValidLine(line)) {
                    Reference reference = new Reference(line[0], line[1], line[2], line[3]);
                    references.add(reference);
                } else {
                    Error error = this.constructErrorMessage(line, lines.indexOf(line));
                    errors.add(error);
                }
            });

            report.setReferences(references);
            report.setErrors(errors);
        }

        return report;
    }

    /**
     * Methode permettant de verifier une ligne de données.
     *
     * @param line la ligne à vérifier.
     * @return true si valide, false sinon
     */
    private boolean isValidLine(String[] line) {
        return line != null &&
                line.length == 4 &&
                InputsValidators.isValidReferenceNumber(line[0]) &&
                InputsValidators.isValidColor(line[1]);
    }

    /**
     * Methode permettant de construire un objet {@Error} pour une ligne erronée.
     *
     * @param line  la ligne en erreur
     * @param index l'index de la ligne en erreur
     * @return une Error
     */
    private Error constructErrorMessage(String[] line, int index) {
        Error error = new Error();
        // set the line index
        error.setLine(index);

        // set the line value
        String lineString = Arrays.toString(line);
        lineString = lineString.substring(1, lineString.length() - 1);
        error.setValue(lineString);

        // set the error message
        String message = "";
        if (line.length != 4) {
            message = Constants.INCORRECT_ARGS;
        } else if (!InputsValidators.isValidReferenceNumber(line[0])) {
            message = Constants.INCORRECT_NUM_REF;
        } else if (!InputsValidators.isValidColor(line[1])) {
            message = Constants.INCORRECT_COLOR;
        }
        error.setMessage(message);
        return error;
    }

    /**
     * https://github.com/uniVocity/univocity-parsers
     *
     * @param relativePath file path.
     * @return a new reader.
     * @throws UnsupportedEncodingException exception.
     */
    private Reader getReader(String relativePath) throws UnsupportedEncodingException, FileNotFoundException {
        return new InputStreamReader(new FileInputStream(relativePath));
    }
}
