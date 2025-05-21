package com.example.evergreenevents;

public class ContsctUsData {
    public String fullName;
    public String email;
    public String phone;
    public String eventType;
    public String eventBudget;
    public String eventDate;
    public String eventLocation;
    public String numberOfGuests;
    public String hearAboutUs;

    public ContsctUsData() {
    }

    public ContsctUsData(String fullName, String email, String phone, String eventType, String eventBudget, String eventDate, String eventLocation, String numberOfGuests, String hearAboutUs) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
        this.eventType = eventType;
        this.eventBudget = eventBudget;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
        this.numberOfGuests = numberOfGuests;
        this.hearAboutUs = hearAboutUs;
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

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventBudget() {
        return eventBudget;
    }

    public void setEventBudget(String eventBudget) {
        this.eventBudget = eventBudget;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }

    public String getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(String numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public String getHearAboutUs() {
        return hearAboutUs;
    }

    public void setHearAboutUs(String hearAboutUs) {
        this.hearAboutUs = hearAboutUs;
    }
}
