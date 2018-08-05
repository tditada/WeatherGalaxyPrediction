package model.Weather;

public enum WeatherType {

    DROUGHT("drought"),
    OPTIMAL("optimal"),
    RAIN("rain"),
    NORMAL("normal");

    private final String name;

    WeatherType(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
