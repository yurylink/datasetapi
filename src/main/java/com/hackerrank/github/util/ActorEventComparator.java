package com.hackerrank.github.util;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

import java.util.Comparator;

public class ActorEventComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor o1, Actor o2) {
        if(o1.getEvents().size() == o2.getEvents().size()){
            Event event1 = o1.getEvents().stream().max((e1, e2) -> new EventComprator().compare(e1,e2)).orElse(null);
            Event event2 = o2.getEvents().stream().max((e1, e2) -> new EventComprator().compare(e1,e2)).orElse(null);

            int compareEventsResult = new EventComprator().compare(event1, event2);
            if (compareEventsResult == 0)
                return o1.getLogin().compareTo(o2.getLogin());
            return compareEventsResult;
        }else{
            return o2.getEvents().size() - o1.getEvents().size();
        }
    }
}
