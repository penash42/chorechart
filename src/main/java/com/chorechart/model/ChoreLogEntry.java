package com.chorechart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ChoreLogEntry {
    private int ID;
    private int ChoreId;
    private int CompletedBy;
    private int TimesCompleted;
    private Date CompletedDate;
}
