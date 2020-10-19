package dev.sukanya.gamecourtbooking.dto.cost;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;

public interface CostType {

    int costCalculationAlgorithm(int specialCourtChargePerHour, TimeSlotDTO selectedTimeSlot);
}
