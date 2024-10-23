package org.xml.xmlprocessorapi.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class XmlProcessorService {

    /**
     * Procesa el XML de entrada y añade el tag RegistrationAddress copiando PhysicalLocation.
     *
     * @param xmlData El XML en formato de bytes
     * @return El XML modificado en formato de bytes
     */
    public byte[] processXml(byte[] xmlData) {
        try {

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xmlData));


            NodeList partyTaxSchemeNodes = document.getElementsByTagName("cac:PartyTaxScheme");
            Node partyTaxSchemeNode = partyTaxSchemeNodes.item(0);


            NodeList physicalLocationNodes = document.getElementsByTagName("cac:PhysicalLocation");
            Node physicalLocationNode = physicalLocationNodes.item(0);

            if (partyTaxSchemeNode != null && physicalLocationNode != null) {

                Node clonedPhysicalLocation = physicalLocationNode.cloneNode(true);


                Element registrationAddressElement = document.createElement("cac:RegistrationAddress");
                registrationAddressElement.appendChild(clonedPhysicalLocation.getFirstChild());


                NodeList taxSchemeNodes = document.getElementsByTagName("cac:TaxScheme");
                Node taxSchemeNode = taxSchemeNodes.item(0);
                partyTaxSchemeNode.insertBefore(registrationAddressElement, taxSchemeNode);


                partyTaxSchemeNode.appendChild(taxSchemeNode);


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(document);
                ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                StreamResult result = new StreamResult(outputStream);
                transformer.transform(source, result);

                return outputStream.toByteArray();
            } else {
                throw new RuntimeException("No se encontró el nodo PartyTaxScheme o PhysicalLocation");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error procesando el XML", e);
        }
    }
}
