package org.xml.xmlprocessorapi.api;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.xml.xmlprocessorapi.models.response.XmlResponse;
import org.xml.xmlprocessorapi.service.XmlProcessorService;

@RestController
@RequestMapping("/api/xml")
public class XmlProcessorApi {

    private final XmlProcessorService xmlProcessorService;

    public XmlProcessorApi(XmlProcessorService xmlProcessorService) {
        this.xmlProcessorService = xmlProcessorService;
    }

    /**
     * Procesa el XML de entrada
     *
     * @param xmlData El XML en formato de bytes
     * @return El XML modificado como respuesta en formato de bytes
     */
    @PostMapping(value = "/process", consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
    public XmlResponse processXml(@RequestBody byte[] xmlData) {
        byte[] result = xmlProcessorService.processXml(xmlData);
        return new XmlResponse(result);
    }
}
