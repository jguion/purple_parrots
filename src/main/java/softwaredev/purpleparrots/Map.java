package softwaredev.purpleparrots;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Map {
	
	public ArrayList<String> ORANGE = new ArrayList<String>();
	public ArrayList<String> BLUE = new ArrayList<String>();
	public ArrayList<String> RED = new ArrayList<String>();
	public Line orangeLine;
	public Line redLine;
	public Line blueLine;
	public HashMap<String, ArrayList<String>> stationToLine;
	
	
	
	public Map(){
		ORANGE.add("Orange");
		BLUE.add("Blue");
		RED.add("Red");
		
		this.stationToLine = new HashMap<String, ArrayList<String>>();

		
		createMap();
	}
	
	public void createMap(){
		
		ArrayList<String> orangeLineStations = new ArrayList<String>();	
		
		Station oakGrove = new Station("Oak Grove", ORANGE);	
		orangeLineStations.add(oakGrove.getName());
		Station maldenCenter = new Station("Malden Center", ORANGE);
		orangeLineStations.add(maldenCenter.getName());
		Station wellington = new Station("Wellington", ORANGE);	
		orangeLineStations.add(wellington.getName());
		Station sullivanSquare = new Station("Sullivan", ORANGE);	
		orangeLineStations.add(sullivanSquare.getName());
		Station communityCollege = new Station("Community College", ORANGE);
		orangeLineStations.add(communityCollege.getName());
		Station northStation = new Station("North Station", ORANGE);
		orangeLineStations.add(northStation.getName());
		Station haymarket = new Station("Haymarket", ORANGE);
		orangeLineStations.add(haymarket.getName());
		List<String> state = ORANGE;
		state.add("Blue");
		Station stateSt = new Station("State Street", ORANGE);	
		orangeLineStations.add(stateSt.getName());
		List<String> dtnCrsing = ORANGE;
		dtnCrsing.add("Red");
		Station downtownCrossing = new Station("Downtown Crossing", dtnCrsing);	
		orangeLineStations.add(downtownCrossing.getName());
		Station chinatown = new Station("Chinatown", ORANGE);	
		orangeLineStations.add(chinatown.getName());
		Station tufts = new Station("Tufts Medical", ORANGE);	
		orangeLineStations.add(tufts.getName());
		Station backBay = new Station("Back Bay", ORANGE);
		orangeLineStations.add(backBay.getName());
		Station massAve = new Station("Mass Ave", ORANGE);
		orangeLineStations.add(massAve.getName());
		Station ruggles = new Station("Ruggles", ORANGE);
		orangeLineStations.add(ruggles.getName());
		Station roxburyCrossing = new Station("Roxbury Crossing", ORANGE);	
		orangeLineStations.add(roxburyCrossing.getName());
		Station jacksonSquare = new Station("Jackson Square", ORANGE);
		orangeLineStations.add(jacksonSquare.getName());
		Station stonyBrook = new Station("Stony Brook", ORANGE);
		orangeLineStations.add(stonyBrook.getName());
		Station greenSt = new Station("Green Street", ORANGE);
		orangeLineStations.add(greenSt.getName());
		Station forestHills = new Station("Forest Hills", ORANGE);
		orangeLineStations.add(forestHills.getName());
		
		this.orangeLine = new Line("Orange", orangeLineStations);
		
		ArrayList<String> redLineStations = new ArrayList<String>();		
		
		Station alewife = new Station("Alewife", RED);
		redLineStations.add(alewife.getName());
        Station davis = new Station("Davis", RED);
        redLineStations.add(davis.getName());
        Station porter = new Station("Porter Square", RED);
        redLineStations.add(porter.getName());
        Station harvard = new Station("Harvard Square", RED);
        redLineStations.add(harvard.getName());
        Station central = new Station("Central Square", RED);
        redLineStations.add(central.getName());
        Station kendall = new Station("Kendall/MIT", RED);
        redLineStations.add(kendall.getName());
        Station charles = new Station("Charles/MGH", RED);
        redLineStations.add(charles.getName());
        Station parkSt = new Station("Park Street", RED);
        redLineStations.add(parkSt.getName());
        redLineStations.add(downtownCrossing.getName());
        Station southStation = new Station("South Station", RED);
        redLineStations.add(southStation.getName());
        Station broadway = new Station("Broadway", RED);
        redLineStations.add(broadway.getName());
        Station andrew = new Station("Andrew", RED);
        redLineStations.add(andrew.getName());
        Station jfk = new Station("JFK/UMass", RED);
        redLineStations.add(jfk.getName());
        Station savinHill = new Station("Savin Hill", RED);
        redLineStations.add(savinHill.getName());
        Station fieldsCorner = new Station("Fields Corner", RED);
        redLineStations.add(fieldsCorner.getName());
        Station shawmut = new Station("Shawmut", RED);
        redLineStations.add(shawmut.getName());
        Station ashmont = new Station("Ashmont", RED);
        redLineStations.add(ashmont.getName());
        Station northQuincy = new Station("North Quincy", RED);
        redLineStations.add(northQuincy.getName());
        Station wollaston = new Station("Wollaston", RED);
        redLineStations.add(wollaston.getName());
        Station quincyCenter = new Station("Quincy Center", RED);
        redLineStations.add(quincyCenter.getName());
        Station quincyAdams = new Station("Quincy Adams", RED);
        redLineStations.add(quincyAdams.getName());
        Station braintree = new Station("Braintree", RED);
        redLineStations.add(braintree.getName());
        
        this.redLine = new Line("Red", redLineStations);
		
		ArrayList<String> blueLineStations = new ArrayList<String>();	
		
		Station bowdoin = new Station("Bowdoin", BLUE);
		blueLineStations.add(bowdoin.getName());
        Station governmentCenter = new Station("Government Center", BLUE);
        blueLineStations.add(governmentCenter.getName());
        blueLineStations.add(stateSt.getName());
        Station aquarium = new Station("Aquarium", BLUE);
        blueLineStations.add(aquarium.getName());
        Station maverick = new Station("Maverick", BLUE);
        blueLineStations.add(maverick.getName());
        Station airport = new Station("Airport", BLUE);
        blueLineStations.add(airport.getName());
        Station woodIsland = new Station("Wood Island", BLUE);
        blueLineStations.add(woodIsland.getName());
        Station orientHeight = new Station("Orient Height", BLUE);
        blueLineStations.add(orientHeight.getName());
        Station suffolkDowns = new Station("Suffolk Downs", BLUE);
        blueLineStations.add(suffolkDowns.getName());
        Station beachmont = new Station("Beachmont", BLUE);
        blueLineStations.add(beachmont.getName());
        Station revereBeach = new Station("Revere Beach", BLUE);
        blueLineStations.add(revereBeach.getName());
        Station wonderland = new Station("Wonderland", BLUE);
        blueLineStations.add(wonderland.getName());
        
        this.blueLine = new Line("Blue", blueLineStations);
        
        updateStationToLineMap(orangeLineStations, "Orange");
        updateStationToLineMap(redLineStations, "Red");
        updateStationToLineMap(blueLineStations, "Blue");
            
	}

    private void updateStationToLineMap(ArrayList<String> lineStations, String lineColor) {
        for(int i = 0; i < lineStations.size(); i++){
            String stationName = lineStations.get(i);
            ArrayList<String> lines;
            if(this.stationToLine.get(stationName) == null){
                lines = new ArrayList<String>();
            }else{
                lines = this.stationToLine.get(stationName);
            }
            
            lines.add(lineColor);
            this.stationToLine.put(stationName, lines);
        }
    }
	
	
	
	public Line getLine(String color){
		if(color.equals("Orange")){
			return orangeLine;
		}
		else if(color.equals("Red")){
			return redLine;
		}
		else return blueLine;
	}
	
	public HashMap<String, ArrayList<String>> getStationToLineMap(){
	    return this.stationToLine;
	}
}
