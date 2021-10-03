package com.example.thymu;

public class waterPlan {

    private String weight;
    private String workoutMins;
    private String wakeupTime;
    private String bedTime;
    private Double drinkTarget;

    public waterPlan() {

    }

    public String getWeight(){ return weight;
    }
    public void setWeight(String weight){ this.weight = weight;
    }
    public  String getWorkoutMins(){ return workoutMins;
    }
    public void setWorkoutMins(String workoutMins){ this.workoutMins = workoutMins;
    }
    public String getWakeupTime(){

        return wakeupTime;
    }
    public void setWakeupTime(String wakeupTime){

        this.wakeupTime = wakeupTime;
    }
    public String getBedTime(){

        return bedTime;
    }
    public void setBedTime(String bedTime){

        this.bedTime = bedTime;
    }

    public double getDrinkTarget(){
        return drinkTarget;
    }

    public  void setDrinkTarget(double drinkTarget){
        this.drinkTarget = drinkTarget;
    }

}
