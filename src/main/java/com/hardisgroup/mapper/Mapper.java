package com.hardisgroup.mapper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.hardisgroup.common.model.Report;
import com.hardisgroup.common.utils.Constants;

import java.io.IOException;

public class Mapper {

    public String map(final Report report, final String format) throws IOException {
        if (format.equalsIgnoreCase(Constants.JSON)) {
            return this.mapToJson(report);
        } else {
            return this.mapToXml(report);
        }
    }

    private String mapToJson(final Report report) throws IOException {
        return new ObjectMapper().writeValueAsString(report);
    }

    private String mapToXml(final Report report) throws JsonProcessingException {
        XmlMapper xmlMapper = new XmlMapper();
        xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);

        return xmlMapper.writeValueAsString(report);
    }

}
