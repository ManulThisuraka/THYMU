package com.example.thymu;

public class waterPlan {

    private Integer weight;
    private Integer workoutHrs;
    private CharSequence wakeupTime;
    private CharSequence bedTime;

    public waterPlan() {

    }

    public Integer getWeight(){

        return weight;
    }
    public void setWeight(Integer weight){

        this.weight = weight;
    }
    public  Integer getWorkoutHrs(){

        return workoutHrs;
    }
    public void setWorkoutHrs(Integer workoutHrs){

        this.workoutHrs = workoutHrs;
    }
    public CharSequence getWakeupTime(){

        return wakeupTime;
    }
    public void setWakeupTime(CharSequence wakeupTime){

        this.wakeupTime = wakeupTime;
    }
    public CharSequence getBedTime(){

        return bedTime;
    }
    public void setBedTime(CharSequence bedTime){

        this.bedTime = bedTime;
    }
}
