package lt.project.CoronaTracker.models;

public class CountryCases {

    private String province;
    private String country;
    private int latestTotal;

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

    @Override
    public String toString() {
        return "CountryCases{" +
                "province='" + province + '\'' +
                ", country='" + country + '\'' +
                ", latestTotal=" + latestTotal +
                '}';
    }
}
