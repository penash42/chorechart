package com.chorechart;

import com.chorechart.model.Chore;
import com.chorechart.model.ChoreLogEntry;
import com.chorechart.model.HouseholdMember;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Repository
public class ChoreChartRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    List<HouseholdMember> getAllHouseholdMembers() {
        // Fetch members from datastore
        String sql = "SELECT * FROM household_members";
        var members = jdbcTemplate.query(sql, new BeanPropertyRowMapper(HouseholdMember.class));

        return members;
    }

    List<Chore> getAllChores() {
        // Fetch chores from datastore
        String sql = "SELECT * FROM chores";
        var chores = jdbcTemplate.query(sql, new BeanPropertyRowMapper(Chore.class));

        return chores;
    }

    List<ChoreLogEntry> getAllChoreLogEntries() {
        // Fetch log entries from datastore
        String sql = "SELECT * FROM chore_completion_log";
        var logEntries = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChoreLogEntry.class));

        return logEntries;
    }

    public ChoreLogEntry addCompletedChore(int choreID, int memberID) {
        // Insert new completed entry into datastore

        var entry = new ChoreLogEntry(0, choreID, memberID, 1, new Date());
        String sql = "INSERT INTO chore_completion_log (chore_id, completed_by, times_completed, completed_date)" +
                " VALUES (" + entry.getChoreId() + "," + entry.getCompletedBy() + "," + entry.getTimesCompleted()
                + ",\'" + entry.getCompletedDate() + "\')";
        var id = insertEntryReturnID(sql);
        entry.setID(id);
        return entry;
    }

    public ChoreLogEntry editCompletedChore(int entryID, int timesCompleted) {
        String sql = "UPDATE chore_completion_log SET times_completed = " + timesCompleted + ", completed_date = \'" +
                new Date() + "\' where id = " + entryID;

        var rowsUpdated = jdbcTemplate.update(sql);

        if (rowsUpdated > 0) {
            return getChoreLogEntry(entryID);
        }

        return null;
    }

    private ChoreLogEntry getChoreLogEntry(int entryID) {
        String sql = "SELECT * FROM chore_completion_log where id = " + entryID;
        var entry = jdbcTemplate.query(sql, new BeanPropertyRowMapper(ChoreLogEntry.class));
        return (ChoreLogEntry) entry.get(0);
    }

    private int insertEntryReturnID(String sql) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(sql, new String[] {"id"});
                        return ps;
                    }
                },
                keyHolder);
        return (int) keyHolder.getKey();
    }
}
