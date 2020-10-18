package dev.sukanya.gamecourtbooking.dto.cost;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class RegularCost implements CostType{
    private final long REGULAR_COST_PER_HOUR = 2000;

    @Override
    public long costCalculationAlgorithm(long courtCharge, TimeSlotDTO selectedTimeSlot) {

        Duration duration  = Duration.between(selectedTimeSlot.getStartDate().toLocalTime(),selectedTimeSlot.getEndDate().toLocalTime());
        long durationInHours = duration.toHours();

        long finalCost = (REGULAR_COST_PER_HOUR+courtCharge)*durationInHours ;


        return finalCost;
    }
}
