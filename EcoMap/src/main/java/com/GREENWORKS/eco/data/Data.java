package com.GREENWORKS.eco.data;

import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/***
 * The purpose that the Data class fulfills is to provide the front-end of the application with all of the
 * necessary information. If the front-end requires data then this is the class that will take the data to 
 * the front-end. 
 */
public class Data {

    private List<SubPillar> subPillarList;
    private List<Pillar> pillarList;
    private List<Pin> pinList;
    private HashMap<Pillar, ArrayList<SubPillar>> pillarHashMap; 

    /***
     * The zero parameter constructor will use the SessionAssistant to populate all of the
     * instance variables. 
     */
    public Data() {
        SessionAssistant sessionAssistant = new SessionAssistant();
        subPillarList = sessionAssistant.getAllSubPillars();
        pillarList = sessionAssistant.getAllPillars();
        pinList = sessionAssistant.getAllPinsList();
        pillarHashMap = new HashMap<>(); 
        createHashMap();
    }

    /***
     * The way the object-relational mapping is configured, the parent tables do not know about 
     * their child tables. As a result, ORM maps from lowest to highest: Pin -> SubPillar -> Pillar
     * This is a necessary configuration to avoid an infinite recursive callstack that results in an
     * overflow. 
     * As a result, this method was designed to provide a lightweight downward facing association of:
     * Pillar -> SubPillar.
     * Because the generated HashMap is constructed using the pillarList instance variable, the pillarList
     * can be used as the key list for the HashMap. 
     */
    public void createHashMap(){
        for(SubPillar subPillar : subPillarList) {
            if(!pillarHashMap.containsKey(subPillar.getPillar())){
                pillarHashMap.put(subPillar.getPillar(), new ArrayList<SubPillar>());
            }
            ArrayList<SubPillar> spList = pillarHashMap.get(subPillar.getPillar());
            spList.add(subPillar);
            pillarHashMap.put(subPillar.getPillar(), spList);
        }
    }

	/***
	 * Accessor method for the subPillarList. 
	 * @return Returns the collection.
	 */
    public List<SubPillar> getSubPillarList() {
        return subPillarList;
    }

	/***
	 * Mutator method for assigning a List<SubPillar> to the subPillarList. 
	 * @param subPillarList The collection to be assigned. 
	 */
    public void setSubPillarList(List<SubPillar> subPillarList) {
        this.subPillarList = subPillarList;
    }

	/***
	 * Accessor method for the pillarList. 
	 * @return Returns the collection.
	 */
    public List<Pillar> getPillarList() {
        return pillarList;
    }

	/***
	 * Mutator method for assigning a List<Pillar> to the pillarList. 
	 * @param pillarList The collection to be assigned. 
	 */
    public void setPillarList(List<Pillar> pillarList) {
        this.pillarList = pillarList;
    }

	/***
	 * Accessor method for the pinList variable. 
	 * @return Returns the collection.
	 */
    public List<Pin> getPinList() {
        return pinList;
    }

	/***
	 * Mutator method for assigning a List<Pin> to the pinList. 
	 * @param pinList The collection to be assigned. 
	 */
    public void setPinList(List<Pin> pinList) {
        this.pinList = pinList;
    }

	/***
	 * Accessor method for the pillarHashMap. 
	 * @return Returns the collection.
	 */
    public HashMap<Pillar, ArrayList<SubPillar>> getPillarHashMap() {
        return pillarHashMap;
    }

	/***
	 * Mutator method for assigning a HashMap<Pillar, ArrayList<SubPillar>> to the pillarHashMap. 
	 * @param pillarHashMap The collection to be assigned. 
	 */
    public void setPillarHashMap(HashMap<Pillar, ArrayList<SubPillar>> pillarHashMap) {
        this.pillarHashMap = pillarHashMap;
    }

}
