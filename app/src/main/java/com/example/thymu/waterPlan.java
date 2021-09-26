package com.example.thymu;

public class waterPlan {

    private String weight;
    private String workoutHrs;
    private CharSequence wakeupTime;
    private CharSequence bedTime;
    private double drinkTarget;

    public waterPlan() {

    }

    public String getWeight(){ return weight;
    }
    public void setWeight(String weight){ this.weight = weight;
    }
    public  String getWorkoutHrs(){ return workoutHrs;
    }
    public void setWorkoutHrs(String workoutHrs){ this.workoutHrs = workoutHrs;
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
