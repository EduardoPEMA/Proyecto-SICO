package utils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Eduardo
 */
public final class Utils {

    public static boolean isStringOnlyAlphabet(String str) {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^[a-zA-Z]*$")));
    }
    
    public static boolean isPhoneNumber(String str) {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("^\\s*(?:\\+?(\\d{1,3}))?[-. (]*(\\d{3})[-. )]*(\\d{3})[-. ]*(\\d{4})(?: *x(\\d+))?\\s*$")));
    }
    
    public static boolean isNumber(String str) {
        return ((!str.equals(""))
                && (str != null)
                && (str.matches("\\d{1,10}")));
    }
    
}
