package by.bytechs.util.interfaces;

import java.io.InputStream;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
public interface CSVReaderService {

    void saveXmlWuthDrawalCards(InputStream inputStream);

    List<String[]> getMasParamCsvFile(String path);
}
