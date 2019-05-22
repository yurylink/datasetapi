package com.hackerrank.github.util;

import com.hackerrank.github.model.Event;

import java.util.Comparator;

public class EventComprator implements Comparator<Event> {

    @Override
    public int compare(Event o1, Event o2) {
        if(o1.getCreatedAt() == null && o2.getCreatedAt() == null ){
            return 0;
        } else if (o1.getCreatedAt() == null && o2.getCreatedAt() != null){
            return -1;
        }else if (o1.getCreatedAt() != null && o2.getCreatedAt() == null){
            return 1;
        }else{
            return o1.getCreatedAt().compareTo(o2.getCreatedAt());
        }
    }
}
