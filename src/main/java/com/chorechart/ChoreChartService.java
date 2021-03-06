package com.chorechart;

import com.chorechart.model.Chore;
import com.chorechart.model.ChoreLogEntry;
import com.chorechart.model.HouseholdMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoreChartService {

    @Autowired
    private ChoreChartRepository choreChartRepository;

    List<HouseholdMember> getAllHouseholdMembers() {
        return choreChartRepository.getAllHouseholdMembers();
    }

    List<Chore> getAllChores() {
        return choreChartRepository.getAllChores();
    }

    List<ChoreLogEntry> getAllChoreLogEntires() {
        return choreChartRepository.getAllChoreLogEntries();
    }

    public ChoreLogEntry addCompletedChore(int choreID, int memberID) {
        return choreChartRepository.addCompletedChore(choreID, memberID);
    }

    public ChoreLogEntry editCompletedChore(int entryID, int timesCompleted) {
        return choreChartRepository.editCompletedChore(entryID, timesCompleted);
    }
}
