package com.hackerrank.github.comparator;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;
import com.hackerrank.github.util.EventComprator;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Sort Actors by :
 *  1º The number of events
 *  2º The latest event
 *  3º The Login name in alphabetical order
 */
public class ActorEventQuantityComparator implements Comparator<Actor> {

    @Override
    public int compare(Actor o1, Actor o2) {
        if(o1.getEvents() == null && o2.getEvents() == null){
            return 0;
        }

        if(o1.getEvents() == null && o2.getEvents() != null){
            return -1;
        }

        if(o1.getEvents() != null && o2.getEvents() == null ){
            return 0;
        }

        if(o1.getEvents().size() == o2.getEvents().size()){
            List<Event> eventList1 = o1.getEvents().stream().sorted(new EventComprator()).collect(Collectors.toList());
            List<Event> eventList2 = o2.getEvents().stream().sorted(new EventComprator()).collect(Collectors.toList());

            int compareEventsResult = new EventComprator().compare(eventList2.get(0), eventList1.get(0));
            if (compareEventsResult == 0)
                return o1.getLogin().compareTo(o2.getLogin());
            return compareEventsResult;
        }else{
            return o2.getEvents().size() - o1.getEvents().size();
        }
    }
}
