package com.GREENWORKS.controller;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class NewMarker {
    ScriptEngineManager sem = new ScriptEngineManager();
    ScriptEngine se = sem.getEngineByName("JavaScript");

    se.eval(Files.newBufferedReader(Paths.get("scripts/index.js"), StandardCharsets.UTF_8));

    Invocable inv = (Invocable) se;
    inv.invokeFunction("addMarkers", "{lat: 28.3827392697488, lng:-81.51192658984489}, icons[5], \"<h3><b>Hyatt Grand Cypress | Wall,  J-1772</b></h3><p>1 Grand Cypress Blvd 32836</p>\"");
}
