package com.hardisgroup.common.model;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "report")
public class Report {

    /**
     * Input file.
     */
    String inputFile;

    /**
     * list of references.
     */
    List<Reference> references;

    /**
     * List of errors.
     */
    List<Error> errors;

    /**
     * Default constructor.
     */
    public Report() {
    }

    /**
     * Constructor.
     *
     * @param inputFile  input file.
     * @param references list of references.
     * @param errors     list of errors.
     */
    public Report(String inputFile, List<Reference> references, List<Error> errors) {
        this.inputFile = inputFile;
        this.references = references;
        this.errors = errors;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public List<Reference> getReferences() {
        return references;
    }

    public void setReferences(List<Reference> references) {
        this.references = references;
    }

    public List<Error> getErrors() {
        return errors;
    }

    public void setErrors(List<Error> errors) {
        this.errors = errors;
    }
}
