package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;

import java.util.Comparator;

public class ActorEventStreakComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor o1, Actor o2) {
        Integer compareMaximumSteak = o1.getMaximumStreak().compareTo(o2.getMaximumStreak());

        if (compareMaximumSteak==0){
            Integer latestEventdate = o2.getLatestEvent().compareTo(o1.getLatestEvent());
            if(latestEventdate == 0){
                return o1.getLogin().compareTo(o2.getLogin());
            }
            return latestEventdate;
        }else{
            return compareMaximumSteak;
        }
    }
}