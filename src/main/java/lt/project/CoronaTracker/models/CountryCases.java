package lt.project.CoronaTracker.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "totalCases")
public class CountryCases implements Serializable {

    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "province")
    private String province;
    @Column(name = "country")
    private String country;
    @Column(name = "latestTotal")
    private int latestTotal;
    @Column(name = "deltaFromPrevDay")
    private int deltaFromPrevDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getLatestTotal() {
        return latestTotal;
    }

    public void setLatestTotal(int latestTotal) {
        this.latestTotal = latestTotal;
    }

    public int getDeltaFromPrevDay() {
        return deltaFromPrevDay;
    }

    public void setDeltaFromPrevDay(int deltaFromPrevDay) {
        this.deltaFromPrevDay = deltaFromPrevDay;
    }

    @Override
    public String toString() {
        return "CountryCases{" +
                "province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", latestTotal=" + latestTotal +
                '}';
    }
}
