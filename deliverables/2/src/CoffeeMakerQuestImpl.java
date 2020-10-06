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
	
	CoffeeMakerQuestImpl() 
	{
		currentPlayer    = null;
		roomMap          = null;
		currentRoomIndex = -1;
		gameOver         = false;
	}

	/**
	 * Whether the game is over. The game ends when the player drinks the coffee.
	 * 
	 * @return true if successful, false otherwise
	 */
	public boolean isGameOver() 
	{		
		return gameOver;
	}

	/**
	 * Set the player to p.
	 * 
	 * @param p the player
	 */
	public void setPlayer(Player p) 
	{
		currentPlayer=p;
	}
	
	/**
	 * Add the first room in the game. If room is null or if this not the first room
	 * (there are pre-exiting rooms), the room is not added and false is returned.
	 * 
	 * @param room the room to add
	 * @return true if successful, false otherwise
	 */
	public boolean addFirstRoom(Room room) 
	{
		if(roomMap == null)
		{
			roomMap = new ArrayList<Room>();
			
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
	public boolean addRoomAtNorth(Room room, String northDoor, String southDoor) 
	{
		if(roomMap != null && northDoor != null && southDoor != null)
		{
		  for(Room pRoom: roomMap)
		  {
		    if(pRoom.getAdjective() == room.getAdjective() || pRoom.getFurnishing() == room.getFurnishing())
		    {
		      return false;
		    }
		  }
			roomMap.add(room);
			roomMap.get(roomMap.size()-1).setSouthDoor(southDoor);
			roomMap.get(roomMap.size()-2).setNorthDoor(northDoor);
			
			return true;
		} 
		
		return false;
	}

	/**
	 * Returns the room the player is currently in. If location of player has not
	 * yet been initialized with setCurrentRoom, returns null.
	 * 
	 * @return room player is in, or null if not yet initialized
	 */ 
	public Room getCurrentRoom() 
	{
		if(currentRoomIndex!=-1) 
		{
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
	public boolean setCurrentRoom(Room room) 
	{
		int isRoom=roomMap.indexOf(room);
		
		if(isRoom!=-1) 
		{
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
	public String getInstructionsString() 
	{
		return " INSTRUCTIONS (N,S,L,I,D,H) > ";
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
	public String processCommand(String cmd) 
	{
		String out = "";
		
		switch(cmd.toUpperCase()) 
		{
			case "N":
				if (currentRoomIndex+1 != roomMap.size()) 
				{
					out="";
					currentRoomIndex++;
				}
				return out;
			case "S":
				if(currentRoomIndex != 0) 
				{
					currentRoomIndex--;
				}
				else
				{
				  out = "A door in that direction does not exist.\n";
				}
				return out;
			case "L":
				Item thisRoomItem = roomMap.get(currentRoomIndex).getItem();
				out="There might be something here...\n";
				
				if(thisRoomItem == Item.NONE) 
				{
					out = "You don't see anything out of the ordinary.\n";
				} 
				else if (thisRoomItem == Item.COFFEE) 
				{
					out += "You found some caffeinated coffee!\n";
					currentPlayer.addItem(Item.COFFEE);
				} 
				else if (thisRoomItem == Item.CREAM) 
				{
					out += "You found some creamy cream!\n";
					currentPlayer.addItem(Item.CREAM);
				}
				else if (thisRoomItem == Item.SUGAR)
				{
					out += "You found some sweet sugar!\n";
					currentPlayer.addItem(Item.SUGAR);
				}
				break;
			case "I":
				out = currentPlayer.getInventoryString();
				break;
			case "D":
				boolean checkSugar  = currentPlayer.checkSugar();
				boolean checkCream  = currentPlayer.checkCream();
				boolean checkCoffee = currentPlayer.checkCoffee();
				
				if(checkSugar && !checkCream && !checkCoffee) 
				{
					out = "\nYou eat the sugar, but without caffeine, you cannot study.\nYou lose!\n";
				} 
				else if(!checkSugar && checkCream && !checkCoffee) 
				{
					out = "\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n";
				} 
				else if(!checkSugar && !checkCream && checkCoffee) 
				{
					out = "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n";
				} 
				else if(checkSugar && checkCream && !checkCoffee) 
				{
					out = "\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n";
				} 
				else if(checkSugar && !checkCream && checkCoffee) 
				{
					out = "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n";
				} 
				else if(!checkSugar && checkCream && checkCoffee) 
				{
				  out = "\nWithout sugar, the coffee is too bitter. You cannot study.\nYou lose!\n";
				} 
				else if(checkSugar && checkCream && checkCoffee) 
				{
					out = currentPlayer.getInventoryString() + "\nYou drink the beverage and are ready to study!\nYou win!\n";
				} 
				else 
				{
					out = currentPlayer.getInventoryString() + "\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n";
				}
				gameOver = true;
				break;
			case "H":
				String missing = checkMissing();
				
				if(missing == null) 
				{
					out="N - Go north\n";
					out+="S - Go south\n";
				} 
				else if (missing == "N")
				{
					out="S - Go south\n";
				} 
				else if (missing == "S") 
				{
					out="N - Go north\n";
				}
				
				out += "L - Look and collect any items in the room\n";
				out += "I - Show inventory of items collected\n";
				out += "D - Drink coffee made from items in inventory\n";
				break;
			default:
				out = "What?";
				break;
		}
		return out;
	}
	
	private String checkMissing() 
	{
		if(roomMap.size()-1 == currentRoomIndex) 
		{ 
			return "N";
		} 
		else if (currentRoomIndex == 0) 
		{
			return "S";
		}
		return null;
	}
	
	
}
