package com.hardisgroup.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Classe utilitaire.
 */
public class InputsValidators {

    /**
     * Methode permettant de valider les entrées de l'application.
     *
     * @param args arguments a validés
     * @return true si valides, false sinon
     */
    public static boolean validateInputs(String[] args) {
        return args.length == 3 && isValideInputFile(args[0])
                && isValidFormat(args[1]) && isValideOutputLocation(args[2]);
    }

    /**
     * Methode permettant de valider le format du fichier en sortie.
     *
     * @param format le format
     * @return true si format valide, faux sinon
     */
    public static boolean isValidFormat(String format) {
        final boolean isValidFormat = (StringUtils.isNotBlank(format) &&
                format.equalsIgnoreCase(Constants.XML) || format.equalsIgnoreCase(Constants.JSON));

        if (!isValidFormat) {
            System.out.println(String.format(Constants.OUTPUT_FORMAT_INCORRECT_MSG, 2, format.toUpperCase()));
        }
        return isValidFormat;
    }

    /**
     * Methode permettant de verifier le fichier à transformer.
     *
     * @param inputFile fichier a transformer.
     * @return true si valide, false sinon
     */
    public static boolean isValideInputFile(String inputFile) {
        final boolean isFileExists = Files.exists(Paths.get(inputFile));
        if (!isFileExists) {
            System.out.println(String.format(Constants.FILE_OR_DIRECTORY_NOT_FOUND, 3, inputFile));
        }
        return isFileExists;
    }

    /**
     * Methode permettant de verifier le répertoire du fichier generé.
     *
     * @param location le chemin vers le repertoire
     * @return true si valide, false sinon
     */
    public static boolean isValideOutputLocation(String location) {
        final boolean isLocationExists = Files.exists(Paths.get(location)) && Files.isDirectory(Paths.get(location));
        if (!isLocationExists) {
            System.out.println(String.format(Constants.FILE_OR_DIRECTORY_NOT_FOUND, 3, location));
        }
        return isLocationExists;
    }
}
