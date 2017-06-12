package by.bytechs.services.interfaces;

import java.io.File;

/**
 * @author Romanovich Andrei
 */
public interface DateChangeTerminalParametersService {

    boolean saveParametersOrSqlScript(File selectCsvFile, File selectSqlFile, boolean operation);

}
