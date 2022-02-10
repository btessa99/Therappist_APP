package it.unipi.dii.dsmt.therappist.jstlUtility;

import it.unipi.dii.dsmt.therappist.dto.TherapistDTO;
import it.unipi.dii.dsmt.therappist.service.SearchService;

import java.util.ArrayList;

public class JstlUtility {

    public static ArrayList<TherapistDTO> getAvailableTherapists(String issue){
        return new SearchService().getAvailableTherapists(issue);
    }
}
