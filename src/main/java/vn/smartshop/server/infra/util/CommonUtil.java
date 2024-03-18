package vn.smartshop.server.infra.util;

public class CommonUtil {
    public static Boolean isNull(Object data){
        return data ==null || data.equals("null") || data.equals("");
    }

    public static Boolean isNonNull(Object data){
        return  data !=null;
    }
}
