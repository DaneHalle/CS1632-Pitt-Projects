import java.util.*;

enum Item {
	NONE,
	COFFEE,
	CREAM,
	SUGAR
}

public class CoffeeMakerQuestImpl implements CoffeeMakerQuest {

	private Player currentPlayer;
	private ArrayList<Room> roomMap;
	private int currentRoomIndex;
	private boolean gameOver;
	
	CoffeeMakerQuestImpl() {
		currentPlayer=null;
		roomMap=null;
		currentRoomIndex=-1;
		gameOver=false;	
		// TODO
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean isGameOver() {
		// TODO
		
		return gameOver;
	}

	/**
	 * Set the player to p.
	 * 
	 * @param p the player
	 */
	public void setPlayer(Player p) {
		// TODO
		currentPlayer=p;
	}
	
	/**
	 * Add the first room in the game. If room is null or if this not the first room
	 * (there are pre-exiting rooms), the room is not added and false is returned.
	 * 
	 * @param room the room to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFirstRoom(Room room) {
		// TODO
		if(roomMap==null) {
			roomMap=new ArrayList<Room>();
			roomMap.add(room);
			return true;
		}
		return false;
	}

	/**
	 * Attach room to the northern-most room. If either room, northDoor, or
	 * southDoor are null, the room is not added. If there are no pre-exiting rooms,
	 * the room is not added. If room is not a unique room (a pre-exiting room has
	 * the same adjective or furnishing), the room is not added. If all these tests
	 * pass, the room is added. Also, the north door of the northern-most room is
	 * labeled northDoor and the south door of the added room is labeled southDoor.
	 * Of course, the north door of the new room is still null because there is
	 * no room to the north of the new room.
	 * 
	 * @param room      the room to add
	 * @param northDoor string to label the north door of the current northern-most room
	 * @param southDoor string to label the south door of the newly added room
	 * @return true if successful, false otherwise
	 */
	public boolean addRoomAtNorth(Room room, String northDoor, String southDoor) {
		// TODO
		if(roomMap!=null && northDoor!=null && southDoor!=null) {
			roomMap.add(room);
			roomMap.get(roomMap.size()-1).setSouthDoor(southDoor);;
			roomMap.get(roomMap.size()-2).setNorthDoor(northDoor);;
		} 
		return false;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */ 
	public Room getCurrentRoom() {
		// TODO
		if(currentRoomIndex!=-1) {
			return roomMap.get(currentRoomIndex);
		}
		return null;
	}
	
	/**
	 * Set the current location of the player. If room does not exist in the game,
	 * then the location of the player does not change and false is returned.
	 * 
	 * @param room the room to set as the player location
	 * @return true if successful, false otherwise
	 */
	public boolean setCurrentRoom(Room room) {
		// TODO
		int isRoom=roomMap.indexOf(room);
		if(isRoom!=-1) {
			currentRoomIndex=isRoom;
			return true;
		}
		return false;
	}
	
	/**
	 * Get the instructions string command prompt. It returns the following prompt:
	 * " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 * 
	 * @return command prompt string
	 */
	public String getInstructionsString() {
		// TODO
		return "Instructions (N,S,L,I,D,H) > ";
	}
	
	/**
	 * Processes the user command given in String cmd and returns the response
	 * string. For the list of commands, please see the Coffee Maker Quest
	 * requirements documentation (note that commands can be both upper-case and
	 * lower-case). For the response strings, observe the response strings printed
	 * by coffeemaker.jar. The "N" and "S" commands potentially change the location
	 * of the player. The "L" command potentially adds an item to the player
	 * inventory. The "D" command drinks the coffee and ends the game. Make
     * sure you use Player.getInventoryString() whenever you need to display
     * the inventory.
	 * 
	 * @param cmd the user command
	 * @return response string for the command
	 */
	public String processCommand(String cmd) {
		String out="";
		switch(cmd.toUpperCase()) {
			case "N":
				if (currentRoomIndex+1!=roomMap.size()) {
					out="";
					currentRoomIndex++;
				}
				return out;
			case "S":
				if(currentRoomIndex!=0) {
					currentRoomIndex--;
				}
				return out;
			case "L":
				Item thisRoomItem=roomMap.get(currentRoomIndex).getItem();
				out="There might be something here...\n";
				if(thisRoomItem==Item.NONE) {
					out="You don't see anything out of the ordinary.";
				} else if (thisRoomItem==Item.COFFEE) {
					out+="You found some bitter coffee!";
					currentPlayer.addItem(Item.COFFEE);
				} else if (thisRoomItem==Item.CREAM) {
					out+="You found some creamy cream!";
					currentPlayer.addItem(Item.CREAM);
				} else if (thisRoomItem==Item.SUGAR) {
					out+="You found some sweet sugar!";
					currentPlayer.addItem(Item.SUGAR);
				}
				break;
			case "I":
				out=currentPlayer.getInventoryString();
				break;
			case "D":
				boolean checkSugar=currentPlayer.checkSugar();
				boolean checkCream=currentPlayer.checkCream();
				boolean checkCoffee=currentPlayer.checkCoffee();
				if(checkSugar && !checkCream && !checkCoffee) {
					out="You drink the sweet sugar hoping for the bitter coffee and smooth cream, however, it never comes. You Lose.";
				} else if(!checkSugar && checkCream && !checkCoffee) {
					out="You drink the smnooth cream hoping for the bitter coffee and sweet sugar, however, it never comes. You Lose.";
				} else if(!checkSugar && !checkCream && checkCoffee) {
					out="You drink the bitter coffee hoping for the smooth cream and sweet sugar, however, it never comes. You Lose.";
				} else if(checkSugar && checkCream && !checkCoffee) {
					out="You drink the sweet sugar mixed with the smooth cream hoping for the bitter coffee, however, it never comes. You Lose.";
				} else if(checkSugar && !checkCream && checkCoffee) {
					out="You drink the sweet sugar mixed with the bitter coffee hoping for the smooth cream, however, it never comes. You Lose.";
				} else if(!checkSugar && checkCream && checkCoffee) {
					out="You drink the bitter coffe mixed with the smooth cream hoping for the esweet sugar, however, it never comes. You Lose.";
				} else if(checkSugar && checkCream && checkCoffee) {
					out="You drink the bitter coffee mixed with the smooth cream and sweet sugar. It tastes wonderful. You win.";
				} else {
					out="You tilt the glass back expecting bitter coffee, sweet sugar, and smooth cream, yet all that comes out is dissapointment in the form of air. You Lose.";
				}
				gameOver=true;
				break;
			case "H":
				String missing=checkMissing();
				if(missing==null) {
					out="N - Move to the northern room if possible\n";
					out+="S - Move to the sothern room if possible\n";
				} else if (missing=="N") {
					out="S - Move to the sothern room if possible\n";
				} else if (missing=="S") {
					out="N - Move to the northern room if possible\n";
				}
				out+="L - Collect any item that may be in the room\n";
				out+="I - Shows any items you may have or have not collected\n";
				out+="D - Will drink items. If you have all items, you win, if you are missing all or any, you lose\n";
				out+="H - Shows the Help menu which shows possible commands and descriptions for said commands\n";
				break;
			default:
				out="What?";
				break;
		}
		// TODO
		out+="\n";
		return out;
	}
	
	private String checkMissing() {
		if(!roomMap.get(currentRoomIndex).getDescription().contains("North")) {
			return "N";
		} else if(!roomMap.get(currentRoomIndex).getDescription().contains("South")) {
			return "S";
		}
		return null;
	}
	
	
}
