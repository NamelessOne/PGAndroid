package ru.sigil.libgdxexperimentalproject;

public abstract class GlobalConstants {
    //STRINGS
    public static final class StringConstants
    {

    }

    public static final class MySQLErrorCodes {  //MYSQL ERRORS
        private static final int DB_CREATE_EXISTS = 1007;
        private static final int SYNTAX_ERROR = 1149;
        private static final int PARSE_ERROR = 1064;
        private static final int NO_SUCH_TABLE = 1146;
        private static final int WRONG_DB_NAME = 1102;
        private static final int WRONG_TABLE_NAME = 1103;
        private static final int FIELD_SPECIFIED_TWICE = 1110;
        private static final int INVALID_GROUP_FUNC_USE = 1111;
        private static final int UNSUPPORTED_EXTENSION = 1112;
        private static final int TABLE_MUST_HAVE_COLUMNS = 1113;
        private static final int CANT_DO_THIS_DURING_AN_TRANSACTION = 1179;
        private static final int WARN_DATA_TRUNCATED = 1265;
        private static final int WARN_NULL_TO_NOTNULL = 1263;
        private static final int WARN_DATA_OUT_OF_RANGE = 1264;
        private static final int NO_DEFAULT = 1230;
        private static final int PRIMARY_CANT_HAVE_NULL = 1171;
        private static final int DATA_TOO_LONG = 1406;
        private static final int DATETIME_FUNCTION_OVERFLOW = 1441;
        private static final int DUP_ENTRY = 1062;
        private static final int NO_REFERENCED_ROW = 1216;
        private static final int NO_REFERENCED_ROW_2 = 1452;
        private static final int ROW_IS_REFERENCED = 1217;
        private static final int ROW_IS_REFERENCED_2 = 1451;
        private static final int CANNOT_ADD_FOREIGN = 1215;
        private static final int WARNING_NOT_COMPLETE_ROLLBACK = 1196;
        private static final int NOT_SUPPORTED_YET = 1235;
        private static final int FEATURE_DISABLED = 1289;
        private static final int UNKNOWN_STORAGE_ENGINE = 1286;
    }
}
