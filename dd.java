import java.io.*;
import java.util.*;

class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date startTime;
    private Date endTime;
    private double chargeAmount;
    private double cost;

    public Order(Date startTime, Date endTime, double chargeAmount, double cost) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.chargeAmount = chargeAmount;
        this.cost = cost;
    }

    // Getters and Setters
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
   import java.io.*;
import java.util.*;

class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date startTime;
    private Date endTime;
    private double chargeAmount;
    private double cost;

    public Order(Date startTime, Date endTime, double chargeAmount, double cost) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.chargeAmount = chargeAmount;
        this.cost = cost;
    }

    // Getters and Setters
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public double getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
   

