package by.bytechs.util;

/**
 * @author Romanovich Andrei
 */
public class UsCIMStatus {
    public static int WFS_CIM_STATCUOK = 1;
    public static int WFS_CIM_STATCUFULL = 2;
    public static int WFS_CIM_STATCUHIGH = 3;
    public static int WFS_CIM_STATCUEMPTY = 4;
    public static int WFS_CIM_STATCUINOP = 5;
    public static int WFS_CIM_STATCUMISSING = 6;
    public static int WFS_CIM_STATCUNOVAL = 7;
    public static int WFS_CIM_STATCUNOREF = 8;
    public static int WFS_CIM_STATCUMANIP = 9;

    public static int getTypeByString(String s) {
        if ("WFS_CIM_STATCUOK".equals(s))
            return 1;
        if ("WFS_CIM_STATCUFULL".equals(s))
            return 2;
        if ("WFS_CIM_STATCUHIGH".equals(s))
            return 3;
        if ("WFS_CIM_STATCUEMPTY".equals(s))
            return 4;
        if ("WFS_CIM_STATCUINOP".equals(s))
            return 5;
        if ("WFS_CIM_STATCUMISSING".equals(s))
            return 6;
        if ("WFS_CIM_STATCUNOVAL".equals(s))
            return 7;
        if ("WFS_CIM_STATCUNOREF".equals(s))
            return 8;
        if ("WFS_CIM_STATCUMANIP".equals(s))
            return 9;

        return -1;
    }

    public static String getDescriptionById(int id) {
        switch (id) {
            case 1:
                return "Кассета в порядке";
            case 2:
                return "Кассета полная";
            case 3:
                return "Кассета почти полная";
            case 4:
                return "Кассета пустая";
            case 5:
                return "Кассета в неоперабельном состоянии";
            case 6:
                return "Кассета отсутсвует";
            case 7:
                return "Кассета не откалибрована";
            case 8:
                return "Кассета не имеет параметров";
            case 9:
                return "Кассета извлекалась вне режима инкасации";
            default:
                return "";
        }
    }
}
