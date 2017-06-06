package by.bytechs.util.interfaces;

import java.io.File;
import java.util.List;

/**
 * @author Romanovich Andrei
 */
public interface CSVReaderService {

    boolean saveXmlWuthDrawalCards(File selectedFile, File selectedPath);

    List<String[]> getMasParamCsvFile(String path);
}
