package com.GREENWORKS.eco.data;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Data {

    private List<SubPillar> subPillarList;
    private List<Pillar> pillarList;
    private List<Pin> pinList;
    private HashMap<Pillar, ArrayList<SubPillar>> pillarHashMap; 

    public Data() {
        SessionAssistant sessionAssistant = new SessionAssistant();
        subPillarList = sessionAssistant.getAllSubPillars();
        pillarList = sessionAssistant.getAllPillars();
        pinList = sessionAssistant.getAllPinsList();
        pillarHashMap = new HashMap<>(); 
        for(SubPillar subPillar : subPillarList) {
            if(!pillarHashMap.containsKey(subPillar.getPillar())){
                pillarHashMap.put(subPillar.getPillar(), new ArrayList<SubPillar>());
            }
            ArrayList<SubPillar> spList = pillarHashMap.get(subPillar.getPillar());
            spList.add(subPillar);
            pillarHashMap.put(subPillar.getPillar(), spList);
        }
    }

    public List<SubPillar> getSubPillarList() {
        return subPillarList;
    }

    public void setSubPillarList(List<SubPillar> subPillarList) {
        this.subPillarList = subPillarList;
    }

    public List<Pillar> getPillarList() {
        return pillarList;
    }

    public void setPillarList(List<Pillar> pillarList) {
        this.pillarList = pillarList;
    }

    public List<Pin> getPinList() {
        return pinList;
    }

    public void setPinList(List<Pin> pinList) {
        this.pinList = pinList;
    }

    public HashMap<Pillar, ArrayList<SubPillar>> getPillarHashMap() {
        return pillarHashMap;
    }

    public void setPillarHashMap(HashMap<Pillar, ArrayList<SubPillar>> pillarHashMap) {
        this.pillarHashMap = pillarHashMap;
    }

}
