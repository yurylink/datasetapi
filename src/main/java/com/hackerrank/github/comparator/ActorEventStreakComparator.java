package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

/**
 * Sort Actors by :
 *  1º Maximum of consecutive days of events
 *  2º The latest event
 *  3º The Login in Alphabetical order
 */
public class ActorEventStreakComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor o1, Actor o2) {
        Integer compareMaximumSteak = o2.getMaximumStreak().compareTo(o1.getMaximumStreak());

        if (compareMaximumSteak == 0) {
            Integer latestEventdate = o2.getLatestEvent().compareTo(o1.getLatestEvent());
            if (latestEventdate == 0) {
                return compareStrings(o1.getLogin(), o2.getLogin());
            }
            return latestEventdate;
        } else {
            return compareMaximumSteak;
        }
    }


    private static Integer compareStrings(String login1, String login2){
        Collator collator = Collator.getInstance(Locale.US);
        collator.setStrength(Collator.TERTIARY);
        return collator.compare(login1, login2);
    }
}