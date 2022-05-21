public class SimplePID {
    private double kp;
    private double ki;
    private double kd;
    private double targetValue; // aka setpoint but I like target value better
    private double cumulativeI = 0;
    
    public SimplePID(double inputkp, double inputki, double inputkd, double inputTargetValue) {
        this.kp = inputkp;
        this.ki = inputki;
        this.kd = inputkd;
        this.targetValue = inputTargetValue;
        this.cumulativeI = 0;
    }
    
    /*
     * You're meant to have the next two methods running inside a loop that needs PID control
     */
    
    public double getOutput(double input, double lastInput) {
        // it might be input - targetValue in some cases, solution is to have negative kp
        double error = this.targetValue - input; 
        double lastError = this.targetValue - lastInput;
        double p = this.kp * error;
        this.cumulativeI += error;
        double i = this.cumulativeI * ki;
        double d = this.kd * ( error - lastError );
        return p + i + d;
    }
    
    public double getOutput(double input, double lastInput, double inputTargetValue) {
        // changes the targetValue to the new targetValue so be careful not to accidentally mess something up
        this.targetValue = inputTargetValue;
        // it might be input - targetValue in some cases, solution is to have negative kp
        double error = this.targetValue - input; 
        double lastError = this.targetValue - lastInput;
        double p = this.kp * error;
        this.cumulativeI += error;
        double i = this.cumulativeI * ki;
        double d = this.kd * ( error - lastError );
        return p + i + d;
    }
    
    public void reset() {
        this.cumulativeI = 0;
    }
    
    public static void main(String[] args) {
        SimplePID PIDTest = new SimplePID(10, 0, 0, 50);
        while ( true ) {
            
            System.out.println(PIDTest.getOutput(51, 0));
        }
    }
}
