package org.alvee;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Address {

    private Integer streetNo;
    private String street;
    private String city;
    private Province province;
    private String postalCode;

    //Static helper
    public static boolean isPostalCodeValid(String postalCode) {
        if (postalCode == null || postalCode.length() != 6) {
            return false;
        }

        for (int i = 0; i < 6; i++) {
            char c = postalCode.charAt(i);

            if (i % 2 == 0) { // letter positions
                if (!Character.isLetter(c)) {
                    return false;
                }
            } else { // digit positions
                if (!Character.isDigit(c)) {
                    return false;
                }
            }
        }
        return true;
    }
    // args cons
}
