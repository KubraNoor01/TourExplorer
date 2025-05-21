package com.example.evergreenevents;

public class VenueBookingData {
    private String fullName;
    private String email;
    private String phone;
    private String venueName;
    private String eventType;
    private String address;
    private String city;
    private String province;
    private String postalCode;
    private String eventDate;
    private String time;
    private String amPm;
    private String time2;
    private String amPm2;
    private String aTime;
    private String amPm3;
    private String eTime;
    private String amPm4;
    private String maxGuests;
    private String caterer;
    private String additionalInfo;

    public VenueBookingData() {
    }

    public VenueBookingData(String fullName, String email, String venueName, String phone, String eventType,
                            String address, String city, String province, String postalCode, String eventDate,
                            String time, String amPm, String time2, String amPm2, String aTime, String amPm3,
                            String eTime, String amPm4, String maxGuests, String caterer, String additionalInfo) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.venueName = venueName;
        this.eventType = eventType;
        this.address = address;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
        this.eventDate = eventDate;
        this.time = time;
        this.amPm = amPm;
        this.time2 = time2;
        this.amPm2 = amPm2;
        this.aTime = aTime;
        this.amPm3 = amPm3;
        this.eTime = eTime;
        this.amPm4 = amPm4;
        this.maxGuests = maxGuests;
        this.caterer = caterer;
        this.additionalInfo = additionalInfo;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAmPm() {
        return amPm;
    }

    public void setAmPm(String amPm) {
        this.amPm = amPm;
    }

    public String getTime2() {
        return time2;
    }

    public void setTime2(String time2) {
        this.time2 = time2;
    }

    public String getAmPm2() {
        return amPm2;
    }

    public void setAmPm2(String amPm2) {
        this.amPm2 = amPm2;
    }

    public String getaTime() {
        return aTime;
    }

    public void setaTime(String aTime) {
        this.aTime = aTime;
    }

    public String getAmPm3() {
        return amPm3;
    }

    public void setAmPm3(String amPm3) {
        this.amPm3 = amPm3;
    }

    public String geteTime() {
        return eTime;
    }

    public void seteTime(String eTime) {
        this.eTime = eTime;
    }

    public String getAmPm4() {
        return amPm4;
    }

    public void setAmPm4(String amPm4) {
        this.amPm4 = amPm4;
    }

    public String getMaxGuests() {
        return maxGuests;
    }

    public void setMaxGuests(String maxGuests) {
        this.maxGuests = maxGuests;
    }

    public String getCaterer() {
        return caterer;
    }

    public void setCaterer(String caterer) {
        this.caterer = caterer;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }
}
