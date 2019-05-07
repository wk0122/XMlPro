package tech.aistar.pojo;

import java.io.Serializable;

/**
 * @author success
 * @version 1.0
 * @description:本类用来演示:
 * @date 2019/4/22 0022
 */
public class Address implements Serializable{
    private String province;

    private String city;

    public Address(){

    }

    public Address(String province, String city) {
        this.province = province;
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Address{");
        sb.append("province='").append(province).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
