package org.xml.xmlprocessorapi.models.response;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

@JacksonXmlRootElement(localName = "response")
public class XmlResponse {
    private byte[] xmlData;

    public XmlResponse() {
        // Constructor vacío necesario para Jackson
    }

    public XmlResponse(byte[] xmlData) {
        this.xmlData = xmlData;
    }

    @JacksonXmlProperty(localName = "data")
    public byte[] getXmlData() {
        return xmlData;
    }

    public void setXmlData(byte[] xmlData) {
        this.xmlData = xmlData;
    }
}
