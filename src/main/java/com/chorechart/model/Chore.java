package com.chorechart.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Chore {
    private int ID;
    private String Name;
    private String Description;
    private int BasePoints;
    private int IncentivePoints;
    private boolean IsRecurring;
    private Date LastCompleted;
}
