package by.bytechs.services;

import by.bytechs.entity.CashUnitInfo;
import by.bytechs.util.UsCIMStatus;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * @author Romanovich Andrei
 */
public class CashStatusHandler extends DefaultHandler {

    private CashUnitInfo cashUnitInfo;
    private CashUnitInfoServiceImpl cashUnitInfoService;
    private String thisElement = "";
    private String currency = "";
    private boolean noteNumberListStatus = false;

    public CashStatusHandler(CashUnitInfo cashUnitInfo, CashUnitInfoServiceImpl cashUnitInfoService) {
        this.cashUnitInfo = cashUnitInfo;
        this.cashUnitInfoService = cashUnitInfoService;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        thisElement = qName;
        if (thisElement.equalsIgnoreCase("command_result")) {
            cashUnitInfo.setDeviceName(attributes.getValue("param"));
        } else if (thisElement.equalsIgnoreCase("NoteNumberList")) {
            noteNumberListStatus = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        thisElement = "";
    }

    @Override
    public void endDocument() throws SAXException {
        if (cashUnitInfo != null) {
            if (cashUnitInfo.getSumCurrencyInfo() != null && cashUnitInfo.getSumCurrencyInfo().length() > 1) {
                cashUnitInfo.setSumCurrencyInfo(cashUnitInfo.getSumCurrencyInfo().substring(0,
                        cashUnitInfo.getSumCurrencyInfo().length() - 1));
            }
        }
        cashUnitInfoService.saveCashUnitInfo(cashUnitInfo);
        currency = null;
        thisElement = null;
        cashUnitInfo = null;
        cashUnitInfoService = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (thisElement.equalsIgnoreCase("ulCount") && !noteNumberListStatus) {
            cashUnitInfo.setCountBanknotes(new Integer(new String(ch, start, length)));
        } else if (thisElement.equalsIgnoreCase("usStatus")) {
            cashUnitInfo.setDeviceStatus(UsCIMStatus.getTypeByString(new String(ch, start, length)));
        } else if (thisElement.equalsIgnoreCase("ulCount") && noteNumberListStatus) {
            currency = new String(ch, start, length);
        } else if (thisElement.equalsIgnoreCase("NoteType")) {
            cashUnitInfo.setSumCurrencyInfo((cashUnitInfo.getSumCurrencyInfo() != null ?
                    cashUnitInfo.getSumCurrencyInfo() : "") + new String(ch, start, length) + "*" + currency + "#");
        }
    }


}
