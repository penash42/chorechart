package com.chorechart;

import com.chorechart.model.Chore;
import com.chorechart.model.ChoreLogEntry;
import com.chorechart.model.HouseholdMember;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ChoreChartApplicationTests {

	@Autowired
	private ChoreChartService choreChartService;

	@Test
	void FetchAllHouseholdMembers() {
		List<HouseholdMember> members = choreChartService.getAllHouseholdMembers();

		assertFalse(members.isEmpty());
	}

	@Test
	void FetchAllChores() {
		List<Chore> chores = choreChartService.getAllChores();
		assertFalse(chores.isEmpty());
	}

	@Test
	void FetchAllCompletionLogs_NoneAdded() {
//		List<ChoreLogEntry> entries = choreChartService.getAllChoreLogEntires();
//		assertTrue(entries.isEmpty());
	}

	@Test
	void FetchAllCompletionLogs_OneAdded() {
//		List<ChoreLogEntry> entries = choreChartService.getAllChoreLogEntires();
//		assertTrue(entries.isEmpty());

		ChoreLogEntry entry = choreChartService.addCompletedChore(1, 2);
		assertNotNull(entry);
		assertNotEquals(entry.getID(), 0);
	}

}
