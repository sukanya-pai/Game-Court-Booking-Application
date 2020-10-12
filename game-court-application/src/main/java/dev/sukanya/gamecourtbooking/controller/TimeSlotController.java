package dev.sukanya.gamecourtbooking.controller;

import dev.sukanya.gamecourtbooking.dto.ResponseDTO;
import dev.sukanya.gamecourtbooking.dto.timeslot.TimeSlotDTO;
import dev.sukanya.gamecourtbooking.model.courts.TimeSlot;
import dev.sukanya.gamecourtbooking.service.interfaces.TimeSlotService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;

    @PostMapping("/timeSlot/addTimeSlot")
    public ResponseDTO<?> addTimeSlot(@RequestBody @Valid TimeSlotDTO timeSlotDTO, BindingResult result){
        log.info("Received Request for adding new court" );
        List<String> errorMessages = new ArrayList<String>();
        if(checkForErrors(result, errorMessages)){
            return new ResponseDTO<List<String>>(HttpStatus.BAD_REQUEST, errorMessages);
        }
        try{
            TimeSlot timeSlot= timeSlotService.addTimeSlot(timeSlotDTO);
            return new ResponseDTO<String>(HttpStatus.OK, "Time slot added successfully!");
        }catch( Exception e){
            return new ResponseDTO<String>(HttpStatus.BAD_REQUEST, e.getMessage());
        }

    }


    public boolean checkForErrors(BindingResult result, List<String> errorMessages){
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();

            for (ObjectError error:
                    errors) {
                errorMessages.add(error.getDefaultMessage());
            }
            return true;
        }
        return false;
    }
}
