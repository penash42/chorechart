package com.chorechart;

import com.chorechart.model.Chore;
import com.chorechart.model.ChoreLogEntry;
import com.chorechart.model.HouseholdMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.PUT;

@RestController
public class ChoreChartController {
    @Autowired
    private ChoreChartService choreChartService;

    @RequestMapping(value="/hello-world", method=GET)
    @ResponseBody
    public String helloworld() {
        return "Hello World!";
    }

    @RequestMapping(value="/all-chores", method=GET)
    @ResponseBody
    public List<Chore> getAllChores() {
        return choreChartService.getAllChores();
    }

    @RequestMapping(value="/household-members", method=GET)
    @ResponseBody
    public List<HouseholdMember> getHouseholdMembers() {
        return choreChartService.getAllHouseholdMembers();
    }

    @RequestMapping(value="/completed-chores", method=GET)
    @ResponseBody
    public List<ChoreLogEntry> getCompletedChores() {
        return choreChartService.getAllChoreLogEntires();
    }

    @RequestMapping(value="/add-completed-chore", method=PUT)
    @ResponseBody
    public ChoreLogEntry addCompletedChore(@RequestParam int choreID,
                                           @RequestParam int memberID) {
        return choreChartService.addCompletedChore(choreID, memberID);
    }

    @RequestMapping(value="/edit-completed-chore/{id}", method=PUT)
    @ResponseBody
    public ChoreLogEntry editCompletedChore(@PathVariable int id,
                                            @RequestParam int timesCompleted) {
        return choreChartService.editCompletedChore(id, timesCompleted);
    }

}