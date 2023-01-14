package com.example.foodwastageprevention;

public class StatusHelper {

    String quantity, type_of_food, slot_time, time_format, address, landmark;

    public StatusHelper(String quantity, String type_of_food, String slot_time, String time_format, String address, String landmark) {
        this.quantity = quantity;
        this.type_of_food = type_of_food;
        this.slot_time = slot_time;
        this.time_format = time_format;
        this.address = address;
        this.landmark = landmark;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getType_of_food() {
        return type_of_food;
    }

    public void setType_of_food(String type_of_food) {
        this.type_of_food = type_of_food;
    }

    public String getSlot_time() {
        return slot_time;
    }

    public void setSlot_time(String slot_time) {
        this.slot_time = slot_time;
    }

    public String getTime_format() {
        return time_format;
    }

    public void setTime_format(String time_format) {
        this.time_format = time_format;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLandmark() {
        return landmark;
    }

    public void setLandmark(String landmark) {
        this.landmark = landmark;
    }
}
