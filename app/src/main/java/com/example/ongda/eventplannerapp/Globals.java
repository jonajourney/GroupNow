package com.example.ongda.eventplannerapp;

import java.util.ArrayList;

/**
 * Created by Ongda on 4/12/17.
 */

public class Globals {

    private static ArrayList<Event> events = new ArrayList<Event>();
    private static boolean eventAdded;

    public static ArrayList<Event> getEvents() {
        return events;
    }
    // Initial Events
    public static void initalize() {
        Event event = new Event();
        event.setName("Pick-Up Bball");
        event.setDescription("5-5 game, need 4 more people");
        event.setLocation("UMN Rec Center");
        event.setType("Sports");
        event.setStartDate("Jun. 12 2017");
        event.setEndDate("Jun. 12 2017");
        event.setStartTime("10:00 AM");
        event.setEndTime("1:00 PM");

        Event event2 = new Event();
        event2.setName("LAN Party");
        event2.setDescription("Come Join us for Food, Fun, and Games");
        event2.setLocation("Keller Hall");
        event2.setType("Social");
        event2.setStartDate("July. 4 2017");
        event2.setEndDate("Jun. 4 2017");
        event2.setStartTime("8:00 PM");
        event2.setEndTime("11:00 PM");

        Event event3 = new Event();
        event3.setName("Soccer Match in Minneapolis");
        event3.setDescription("Casual game. Come Play! Need lots of people! ");
        event3.setLocation("Fridley Soccer Field");
        event3.setType("Sports");
        event3.setStartDate("Apr. 28 2017");
        event3.setEndDate("Apr. 28 2017");
        event3.setStartTime("10:00 AM");
        event3.setEndTime("1:00 PM");

        events.add(event);
        events.add(event2);
        events.add(event3);

    }

    public static boolean isEventAdded() {
        return eventAdded;
    }

    public static void setEventAdded(boolean eventAdded) {
        Globals.eventAdded = eventAdded;
    }
}

