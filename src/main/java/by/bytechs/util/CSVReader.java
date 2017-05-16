package by.bytechs.util;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Romanovich Andrei
 */
public class CSVReader {

    public static void main(String[] args) {
        String line;
        String cvsSplitBy = ";";
        BufferedReader br = null;
        try {

            File fileDir = new File("d:\\xml\\111-old.csv");
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "cp1251"));
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
            while ((line = br.readLine()) != null) {
                String[] mas = line.split(cvsSplitBy);
                for (int i = 0; i < mas.length; i++) {
                    String terminalID = mas[0].replace("\"", "");
                    Date date = dateFormat.parse(mas[2].replace("\"", ""));
                    String numberCard = mas[3].replace("\"", "");
                    String reasonDesc= mas[4].replace("\"", "");
                    saveXmlFile(date, numberCard, terminalID, reasonDesc);
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveXmlFile(Date rejectDate, String numberCard, String termId, String reasonDesc) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-dd-M--HH-mm-ss");
        String date = formatter.format(rejectDate);
        String lastName = "";
        String firstName = "";

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();

            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("cards");

            Element card = doc.createElement("card");
            card.appendChild(doc.createTextNode(numberCard));

            Element firstNameElement = doc.createElement("FirstName");
            firstNameElement.appendChild(doc.createTextNode(firstName));

            Element lastNameElement = doc.createElement("LastName");
            lastNameElement.appendChild(doc.createTextNode(lastName));

            Element dateExpireElement = doc.createElement("DateTo");
            dateExpireElement.appendChild(doc.createTextNode(""));

            Element atmElement = doc.createElement("atm");
            atmElement.appendChild(doc.createTextNode(termId));

            Element capturetime = doc.createElement("capturetime");
            capturetime.appendChild(doc.createTextNode(date));

            Element reason = doc.createElement("reason");
            reason.appendChild(doc.createTextNode(reasonDesc));

            Element withdrawal = doc.createElement("withdrawal");
            withdrawal.appendChild(doc.createTextNode(""));


            rootElement.appendChild(card);
            rootElement.appendChild(firstNameElement);
            rootElement.appendChild(lastNameElement);
            rootElement.appendChild(dateExpireElement);
            rootElement.appendChild(atmElement);
            rootElement.appendChild(capturetime);
            rootElement.appendChild(reason);
            rootElement.appendChild(withdrawal);

            doc.appendChild(rootElement);

            builder = factory.newDocumentBuilder();
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            StreamResult result = new StreamResult(new File("d:\\xml\\result\\Capture CARD_" + termId + "_" + date + ".xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
