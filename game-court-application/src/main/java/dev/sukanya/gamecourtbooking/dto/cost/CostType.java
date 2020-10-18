package dev.sukanya.gamecourtbooking.dto.cost;

import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;

public interface CostType {

    long costCalculationAlgorithm(long specialCourtChargePerHour, TimeSlotDTO selectedTimeSlot);
}
