package com.hackerrank.github.util;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ActorStreakCalculation {

    public static Actor setMaximumStreakAndLatestEventDate(Actor actor){
        if(actor != null){
            if(actor.getEvents() != null && !actor.getEvents().isEmpty()){
                List<Event> listOfEvents = actor.
                                            getEvents().
                                            stream().
                                            sorted((o1, o2) -> o2.getCreatedAt().compareTo(o1.getCreatedAt())).
                                            collect(Collectors.toList());

                setMaximumStrakAndDate(actor, listOfEvents);
            }
        }
        return actor;
    }

    private static void setMaximumStrakAndDate(Actor actor, List<Event> events){
        Integer maximumEventStreak = 0;
        LocalDateTime lastComparableDate = null;

        if(events != null){
            Integer eventStreakCounter = 0;
            List<Event> listOfEventsSorted = events.stream().sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt())).collect(Collectors.toList());

            for (Event event : listOfEventsSorted) {
                if(lastComparableDate == null){
                    lastComparableDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());
                    eventStreakCounter++;
                }else{
                    LocalDateTime lastComparableDatePlus1Day = lastComparableDate.plusDays(1);
                    LocalDateTime currentDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());

                    if((lastComparableDate.getYear() == currentDate.getYear()) &&
                       (lastComparableDate.getMonth() == currentDate.getMonth()) &&
                       (lastComparableDatePlus1Day.getDayOfMonth() == currentDate.getDayOfMonth())){

                        eventStreakCounter++;
                    }else {
                        if(maximumEventStreak.compareTo(eventStreakCounter)<0){
                            maximumEventStreak = eventStreakCounter;
                        }

                        actor.setLatestEvent(convertToDateViaInstant(lastComparableDate));
                        eventStreakCounter = 1;
                    }
                    lastComparableDate = currentDate;
                }
            }
            maximumEventStreak = maximumEventStreak.compareTo(eventStreakCounter) > 0 ? maximumEventStreak : eventStreakCounter;
        }
        actor.setMaximumStreak(maximumEventStreak);
        actor.setLatestEvent(convertToDateViaInstant(lastComparableDate));
    }

    private static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    public static Date convertToDateViaInstant(LocalDateTime localDate) {
        Date date1 = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }
}