package io.finbook.invoicehandler.box.helpers;

import io.finbook.datahub.events.Invoice;
import io.finbook.datahub.events.ProcessedInvoice;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Locale;

public class XMLHelper {

    private static final String VOUCHER = "cfdi:Voucher";
    private static final String TRANSMITTER = "cfdi:Issuer";
    private static final String RECEIVER = "cfdi:Receiver";
    private static  final String DATE_FORMAT = "E MMM dd HH:mm:ss zzz yyyy";

    private String xml;
    private Invoice event;

    public XMLHelper(Invoice event, String xml) {
        this.xml = xml;
        this.event = event;
    }

    public ProcessedInvoice getProcessedInvoice() {
        File xmlFile = createFileFrom(xml);

        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            xmlFile.delete();

            ProcessedInvoice processedInvoices = new ProcessedInvoice()
                    .ts(event.ts())
                    .ss(event.ss());
            handleNodes(document.getChildNodes(), processedInvoices);
            return processedInvoices;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            xmlFile.delete();
            return null;
        }
    }

    private void handleNodes(NodeList childNodes, ProcessedInvoice processedInvoice) {
        processedInvoice.xml(xml);

        for (int i = 0; i < childNodes.getLength(); i++) {
            Node node = childNodes.item(i);
            Element element = (Element) node;
            if(node.getNodeName().equalsIgnoreCase(VOUCHER)) {
                processedInvoice
                        .uUID(element.getAttribute("Uuid"))
                        .date(getInstantFrom(element.getAttribute("Date")))
                        .pC(Integer.parseInt(element.getAttribute("Location")))
                        .type(element.getAttribute("Type"))
                        .use(element.getAttribute("Use"))
                        .concept(element.getAttribute("Concept"))
                        .subtotal(Double.parseDouble(element.getAttribute("SubTotal")))
                        .taxRate(Double.parseDouble(element.getAttribute("TaxRate")))
                        .total(Double.parseDouble(element.getAttribute("Total")))
                        .currency(element.getAttribute("Currency"));
                handleNodes(node.getChildNodes(), processedInvoice);
            } else if(node.getNodeName().equalsIgnoreCase(TRANSMITTER)) {
                processedInvoice.issuerName(element.getAttribute("IssuerName"))
                        .issuerRFC(element.getAttribute("IssuerRFC"));
            } else if(node.getNodeName().equalsIgnoreCase(RECEIVER)) {
                processedInvoice.receiverName(element.getAttribute("ReceiverName"))
                        .receiverRFC(element.getAttribute("ReceiverRFC"));
            }
        }
    }

    private Instant getInstantFrom(String date) {
        DateFormat formato = new SimpleDateFormat(DATE_FORMAT, Locale.ENGLISH);
        try {
            return formato.parse(date).toInstant();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private File createFileFrom(String xml) {
        File file = new File("xmlTemp.xml");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
            bw.write(xml);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
