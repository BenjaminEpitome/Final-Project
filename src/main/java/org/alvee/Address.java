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

    public Address(Integer streetNo, String street, String city, Province province, String postalCode) {
        if (isPostalCodeValid(postalCode)) {
            this.streetNo = streetNo;
            this.street = street;
            this.city = city;
            this.province = province;
            this.postalCode = postalCode.toUpperCase();
        } else {
            this.streetNo = null;
            this.street = null;
            this.city = null;
            this.province = null;
            this.postalCode = null;
        }
        // toString

    }
    @Override
    public String toString() {
        return "Address{" +
                "streetNo=" + streetNo +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", province=" + province +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }

    // equals and hashcode
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Address)) return false;

        Address other = (Address) obj;

        return java.util.Objects.equals(streetNo, other.streetNo) &&
                java.util.Objects.equals(street, other.street) &&
                java.util.Objects.equals(city, other.city) &&
                province == other.province &&
                java.util.Objects.equals(postalCode, other.postalCode);
    }

    @Override
    public int hashCode() {
        return java.util.Objects.hash(streetNo, street, city, province, postalCode);
    }

}
