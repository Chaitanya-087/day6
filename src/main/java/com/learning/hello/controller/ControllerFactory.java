package com.learning.hello.controller;

import java.util.HashMap;
import java.util.Map;

public class ControllerFactory {
	private static Map<String, IController> controllers;
	static {
		controllers = new HashMap<>();
		controllers.put("/odometer", new OdometerController());
		controllers.put("/mangatha", new MangathaController());
		controllers.put("/notices", new NoticeBoardController());
		controllers.put("/game2584",new Game2584Controller());
	}
	
	public static IController get(String path) {
		return controllers.get(path);
	}
}
