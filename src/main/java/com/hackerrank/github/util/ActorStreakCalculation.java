package com.hackerrank.github.util;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class ActorStreakCalculation {

    public static Actor setMaximumStreakAndLatestEventDate(Actor actor){
        if(actor != null){
            Integer maximumStreak = 0;
            Date lastEvent = null;

            if(actor.getEvents() != null && !actor.getEvents().isEmpty()){
                List<Event> listOfEvents = actor.
                                            getEvents().
                                            stream().
                                            sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).
                                            collect(Collectors.toList());

                lastEvent = listOfEvents.get(0).getCreatedAt();
                maximumStreak = getMaximumStreak(listOfEvents);
            }
            actor.setMaximumStreak(maximumStreak);
            actor.setLatestEvent(lastEvent);
        }
        return actor;
    }

    private static Integer getMaximumStreak(List<Event> events){
        if(events != null){
            Integer maximumEventStreak = 0;
            Integer eventStreak = 0;
            LocalDate lastComparableDate = null;
            List<Event> listOfEventsSorted = events.stream().sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt())).collect(Collectors.toList());

            for (Event event : listOfEventsSorted) {
                if(lastComparableDate == null){
                    lastComparableDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());
                    eventStreak++;
                }else{
                    LocalDate lastComparableDatePlus1Day = lastComparableDate.plusDays(1);
                    LocalDate currentDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());

                    if(lastComparableDatePlus1Day.getDayOfMonth() == currentDate.getDayOfMonth()){
                        eventStreak++;
                        lastComparableDate = currentDate;
                    }else {
                        if(maximumEventStreak.compareTo(eventStreak)<0){
                            maximumEventStreak = eventStreak;
                            lastComparableDate = null;
                        }
                        eventStreak = 0;
                    }
                }
            }
            return maximumEventStreak;
        }
        return 0;
    }

    private static LocalDate convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}