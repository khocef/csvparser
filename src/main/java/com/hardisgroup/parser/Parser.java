package com.hardisgroup.parser;

import com.hardisgroup.common.model.Error;
import com.hardisgroup.common.model.Reference;
import com.hardisgroup.common.model.Report;
import com.hardisgroup.common.utils.Constants;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

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
                if (isValidLine(line)) {
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
     * Methode permettant de verifier une couleur.
     *
     * @param color le code couleur.
     * @return true si code couleur est valide, false sinon
     */
    private boolean isValidColor(String color) {
        String[] colors = new String[]{"R", "G", "B"};
        return Arrays.asList(colors).contains(color);
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
                StringUtils.isNumeric(line[0]) &&
                isValidColor(line[1]);
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
        error.setLine(index);
        error.setValue(Arrays.toString(line));

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
