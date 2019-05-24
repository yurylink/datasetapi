package com.hackerrank.github.util;

import com.hackerrank.github.model.Actor;
import com.hackerrank.github.model.Event;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Receive an Actor and calculate the maximum of consecutive days of events and the lastest event.
 *
 * Returns the same Actor object but with the fields maximumStreak and latestEvent filled
 */
public class ActorStreakCalculation {

    public static Actor setMaximumStreakAndLatestEventDate(Actor actor){
        if(actor != null){
            if(actor.getEvents() != null && !actor.getEvents().isEmpty()){

                /**
                 * Sort events in decrescent order to iterate over a time direction list
                 * so i can be sure that every next day will be bigger than the previus one and dont
                 * misscount the streak days
                 */
                List<Event> listOfEventsSorted = actor.getEvents().stream().sorted((o1, o2) -> o1.getCreatedAt().compareTo(o2.getCreatedAt())).collect(Collectors.toList());

                Integer maximumEventStreak = 0;
                LocalDateTime lastComparableDate = null;
                Integer eventStreakCounter = 0;

                for (Event event : listOfEventsSorted) {
                    //fist iteration
                    if(lastComparableDate == null){
                        lastComparableDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());
                        eventStreakCounter++;
                    }else{
                        LocalDateTime lastComparableDatePlus1Day = lastComparableDate.plusDays(1);
                        LocalDateTime currentDate = convertToLocalDateTimeViaInstant(event.getCreatedAt());

                        /**
                         * If the last day + 1 equals the next day on the list increase the counter in one
                         */
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
                /**
                 * this validation is necessary in caso of the list is a conunious streak of days, if the count is never broke the maimumEventStrak will be 0
                 */
                maximumEventStreak = maximumEventStreak.compareTo(eventStreakCounter) > 0 ? maximumEventStreak : eventStreakCounter;
                actor.setMaximumStreak(maximumEventStreak);
                actor.setLatestEvent(convertToDateViaInstant(lastComparableDate));
            }
        }
        return actor;
    }

    /**
     * Converion a java Util Date in LocalDateTime to use the methos plusDay
     * @param dateToConvert
     * @return
     */
    private static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    private static Date convertToDateViaInstant(LocalDateTime localDate) {
        Date date1 = Date.from(localDate.atZone(ZoneId.systemDefault()).toInstant());
        return date1;
    }
}