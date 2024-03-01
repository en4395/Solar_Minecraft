package solarminecraft.application.client;

// All of this HUD rendering code is from Matt Czyr's Explorer's Compass. Thanks Matt! :)
public enum OverlaySide {
	LEFT, RIGHT;

	public static OverlaySide fromString(String str) {
		if (str.equals("RIGHT")) {
			return RIGHT;
		}
		return LEFT;
	}

}
