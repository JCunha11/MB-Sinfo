package app;



import app.beans.Booking;
import app.beans.Dealer;
import app.logic.DataLoader;
import java.util.ArrayList;

import java.util.List;

public class TestDrive {
    static List<Booking> bookings;
    static List<Dealer> dealers;

    public static void main(String[] args) {
        if (bookings==null && dealers==null) {
            bookings= new ArrayList<>();
            dealers = new ArrayList<>();
            DataLoader.loadData(bookings, dealers);
        }
    }
}