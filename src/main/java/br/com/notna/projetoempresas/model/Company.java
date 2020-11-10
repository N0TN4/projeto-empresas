package br.com.notna.projetoempresas.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Company {
	 @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String tradeName;
    private String corporateName;
    private long country;
    private long state;
    private long city;
    private long neighbourhood;
    private String address;
    private String phone;
    private String federalTaxNumber;

    @JsonProperty("id")
    public long getID() { return id; }
    @JsonProperty("id")
    public void setID(long value) { this.id = value; }

    @JsonProperty("tradeName")
    public String getTradeName() { return tradeName; }
    @JsonProperty("tradeName")
    public void setTradeName(String value) { this.tradeName = value; }

    @JsonProperty("corporateName")
    public String getCorporateName() { return corporateName; }
    @JsonProperty("corporateName")
    public void setCorporateName(String value) { this.corporateName = value; }

    @JsonProperty("country")
    public long getCountry() { return country; }
    @JsonProperty("country")
    public void setCountry(long value) { this.country = value; }

    @JsonProperty("state")
    public long getState() { return state; }
    @JsonProperty("state")
    public void setState(long value) { this.state = value; }

    @JsonProperty("city")
    public long getCity() { return city; }
    @JsonProperty("city")
    public void setCity(long value) { this.city = value; }

    @JsonProperty("neighbourhood")
    public long getNeighbourhood() { return neighbourhood; }
    @JsonProperty("neighbourhood")
    public void setNeighbourhood(long value) { this.neighbourhood = value; }

    @JsonProperty("address")
    public String getAddress() { return address; }
    @JsonProperty("address")
    public void setAddress(String value) { this.address = value; }

    @JsonProperty("phone")
    public String getPhone() { return phone; }
    @JsonProperty("phone")
    public void setPhone(String value) { this.phone = value; }

    @JsonProperty("federalTaxNumber")
    public String getFederalTaxNumber() { return federalTaxNumber; }
    @JsonProperty("federalTaxNumber")
    public void setFederalTaxNumber(String value) { this.federalTaxNumber = value; }
}
