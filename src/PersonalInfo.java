public class PersonalInfo {
   private String name;
   private String address;
   private String country;
   private String gender;
   private String degree;

    public PersonalInfo(String name, String address, String country, String gender, String degree) {
        this.name = name;
        this.address = address;
        this.country = country;
        this.gender = gender;
        this.degree = degree;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getGender() {
        return gender;
    }

    public String getDegree() {
        return degree;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    @Override
    public String toString() {
        return name + address + country + gender +degree;
    }
}
