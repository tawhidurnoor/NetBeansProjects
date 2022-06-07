/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.math.BigInteger;

/**
 *
 * @author User
 */
public class TimeTracker {
    public final BigInteger timeTrackerId;

    public TimeTracker(BigInteger tuimeTrackerId) {
        this.timeTrackerId = tuimeTrackerId;
    }

    public BigInteger getTuimeTrackerId() {
        return timeTrackerId;
    }
    
}
