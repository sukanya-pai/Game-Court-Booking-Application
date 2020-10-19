package dev.sukanya.gamecourtbooking.dto.court;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BadmintonCourtDTO extends CourtDTO {
    private boolean isOpen;
    public BadmintonCourtDTO(){
        super();
        this.isOpen = true;
    }

    public BadmintonCourtDTO(int specialCourtChargePerHour){
        super();
        this.isOpen = true;
        super.setSpecialCourtChargePerHour(specialCourtChargePerHour);
    }
}
