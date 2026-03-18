package PTIT_CNTT4_IT203B_Session09.miniProject.smartsim.traffic;

public interface TrafficLightState {
    String getName();

    long getDurationMillis();

    TrafficLightState next();
}
