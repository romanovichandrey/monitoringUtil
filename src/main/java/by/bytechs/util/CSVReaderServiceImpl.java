package by.bytechs.util;

import by.bytechs.util.interfaces.CSVReaderService;
import org.springframework.stereotype.Service;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
@Service
public class CSVReaderServiceImpl implements CSVReaderService {

    @Override
    public boolean saveXmlWuthDrawalCards(File selectedFile, File selectedPath) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
        List<String[]> arrayList = getMasParamCsvFile(selectedFile.getAbsolutePath());
        for (String[] mas : arrayList) {
            try {
                for (int i = 0; i < mas.length; i++) {
                    String terminalID = mas[0].replace("\"", "");
                    Date date = dateFormat.parse(mas[2].replace("\"", ""));
                    String numberCard = mas[3].replace("\"", "");
                    String reasonDesc = mas[4].replace("\"", "");
                    saveXmlFile(date, numberCard, terminalID, reasonDesc, selectedPath.getAbsolutePath());
                }
            } catch (ParseException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }

    @Override
    public List<String[]> getMasParamCsvFile(String path) {
        String line;
        String cvsSplitBy = ";";
        BufferedReader br = null;
        List<String[]> arrayList = new ArrayList<>();
        try {
            File fileDir = new File(path);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(fileDir), "cp1251"));

            while ((line = br.readLine()) != null) {
                String[] mas = line.split(cvsSplitBy);
                arrayList.add(mas);
            }
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
        return arrayList;
    }

    private void saveXmlFile(Date rejectDate, String numberCard, String termId, String reasonDesc, String savePath) {
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

            StreamResult result = new StreamResult(new File(savePath + "Capture CARD_" + termId + "_" + date + ".xml"));
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
