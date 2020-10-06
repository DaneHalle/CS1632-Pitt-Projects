import static org.junit.Assert.*;

import org.junit.*; 
import org.mockito.*;
import static org.mockito.Mockito.*;

public class CoffeeMakerQuestTest {

	CoffeeMakerQuest cmq;
	Player player;
	Room room1;	// Small room
	Room room2;	// Funny room
	Room room3;	// Refinanced room
	Room room4;	// Dumb room
	Room room5;	// Bloodthirsty room
	Room room6;	// Rough room

	@Before
	public void setup()
	{
		// 0. Turn on bug injection for Player and Room.
		Config.setBuggyPlayer(true);
		Config.setBuggyRoom(true);
		
		// 1. Create the Coffee Maker Quest object and assign to cmq.
		cmq = CoffeeMakerQuest.createInstance();

		// TODO: 2. Create a mock Player and assign to player and call cmq.setPlayer(player). 
		// Player should not have any items (no coffee, no cream, no sugar)
		player = mock(Player.class); 
		when(player.checkCoffee()).thenReturn(false);
		when(player.checkCream()).thenReturn(false);
		when(player.checkSugar()).thenReturn(false);
		
		cmq.setPlayer(player);

		// TODO: 3. Create mock Rooms and assign to room1, room2, ..., room6.
		// Mimic the furnishings / adjectives / items of the rooms in the original Coffee Maker Quest.
		room1 = mock(Room.class);
		when(room1.getFurnishing()).thenReturn("Quaint sofa");
	  when(room1.getAdjective()).thenReturn("Small");
		when(room1.getItem()).thenReturn(Item.CREAM);
	  room1.setNorthDoor("Magenta");
		when(room1.getDescription()).thenReturn("You see a Small room.\nIt has a Quaint sofa\nA magenta door leads North.\n"); //Hard coded since dynamic string was not liked
			
		room2 = mock(Room.class);
		when(room2.getFurnishing()).thenReturn("Sad record player");
		when(room2.getAdjective()).thenReturn("Funny");
	  when(room2.getItem()).thenReturn(Item.NONE);
		when(room2.getDescription()).thenReturn("You see a Funny room.\nIt has a Sad record player\nA Dead door leads North.\nA Massive door leads South"); //Hard coded since dynamic string was not liked
			  
		room3 = mock(Room.class);
			when(room3.getFurnishing()).thenReturn("Tight pizza");
			when(room3.getAdjective()).thenReturn("Refinanced");
			when(room3.getItem()).thenReturn(Item.COFFEE);
			  
		room4 = mock(Room.class);
			when(room4.getFurnishing()).thenReturn("Flat energy drink");
			when(room4.getAdjective()).thenReturn("Dumb");
			when(room4.getItem()).thenReturn(Item.NONE);
			  
		room5 = mock(Room.class);
			when(room5.getFurnishing()).thenReturn("Beautiful bag of money");
			when(room5.getAdjective()).thenReturn("Bloodthirsty");
			when(room5.getItem()).thenReturn(Item.NONE);
			  
		room6 = mock(Room.class);
			when(room6.getFurnishing()).thenReturn("Pefect air hockey table");
			when(room6.getAdjective()).thenReturn("Rough");
			when(room6.getItem()).thenReturn(Item.SUGAR);
			when(room6.getDescription()).thenReturn("You see a Rough room.\nIt has a Pefect air hockey table\nA Minimalist door leads South.\n");
		
		// TODO: 4. Add the rooms created above to mimic the layout of the original Coffee Maker Quest.
		cmq.addFirstRoom(room1);
		cmq.addRoomAtNorth(room2, "Beige", "Massive");
		cmq.addRoomAtNorth(room3, "Dead", "Smart");
		cmq.addRoomAtNorth(room4, "Vivacious", "Slim");
		cmq.addRoomAtNorth(room5, "Purple", "Sandy");
		cmq.addRoomAtNorth(room6, "northDoor", "Minimalist");
	}

	@After
	public void tearDown() 
	{
		player = null;
		room1  = null;
		room2  = null;
		room3  = null;
		room4  = null;
		room5  = null;
		room6  = null;
		
		cmq.setPlayer(player);
	}
	
	/**
	 * Test case for String getInstructionsString().
	 * Preconditions: None.
	 * Execution steps: Call cmq.getInstructionsString().
	 * Postconditions: Return value is " INSTRUCTIONS (N,S,L,I,D,H) > ".
	 */
	@Test
	public void testGetInstructionsString() 
	{
		assertEquals("Getting instructions string",cmq.getInstructionsString(), " INSTRUCTIONS (N,S,L,I,D,H) > " );
	}
	
	/**
	 * Test case for boolean addFirstRoom(Room room).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock room and assign to myRoom.
	 * Execution steps: Call cmq.addFirstRoom(myRoom).
	 * Postconditions: Return value is false.
	 */
	@Test
	public void testAddFirstRoom() 
	{
		Room myRoom = mock(Room.class);
		
		assertEquals("Try adding a first room when there is already a first room", cmq.addFirstRoom(myRoom), false);
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Fake bed" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is true.
	 *                 room6.setNorthDoor("North") is called.
	 *                 myRoom.setSouthDoor("South") is called.
	 */
	@Test
	public void testAddRoomAtNorthUnique() 
	{
		Room myRoom = mock(Room.class);
		
		when(myRoom.getAdjective()).thenReturn("Fake");
		when(myRoom.getFurnishing()).thenReturn("Fake bed");
		
		assertEquals("Add a new room at north", cmq.addRoomAtNorth(myRoom, "North", "South"), true);
		verify(myRoom, times(1)).setSouthDoor("South");
		verify(room6, times(1)).setNorthDoor("North");
	}
	
	/**
	 * Test case for boolean addRoomAtNorth(Room room, String northDoor, String southDoor).
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                Create a mock "Fake" room with "Flat energy drink" furnishing with no item, and assign to myRoom.
	 * Execution steps: Call cmq.addRoomAtNorth(myRoom, "North", "South").
	 * Postconditions: Return value is false.
	 *                 room6.setNorthDoor("North") is not called.
	 *                 myRoom.setSouthDoor("South") is not called.
	 */
	@Test
	public void testAddRoomAtNorthDuplicate() 
	{
		Room myRoom = mock(Room.class);
		
		when(myRoom.getAdjective()).thenReturn("Fake");
		when(myRoom.getFurnishing()).thenReturn("Flat energy drink");
		
		assertEquals("Add a new room at north that has the same furnishing", cmq.addRoomAtNorth(myRoom, "North", "South"), false);
		verify(myRoom, times(0)).setSouthDoor("South");
		verify(room6, times(0)).setNorthDoor("North");
	}
	
	/**
	 * Test case for Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room) has not yet been called.
	 * Execution steps: Call cmq.getCurrentRoom().
	 * Postconditions: Return value is null.
	 */
	@Test
	public void testGetCurrentRoom() 
	{
		assertEquals("Trying to get the current room when the current room does not exist", cmq.getCurrentRoom(), null);
	}
	
	/**
	 * Test case for void setCurrentRoom(Room room) and Room getCurrentRoom().
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(Room room) has not yet been called.
	 * Execution steps: Call cmq.setCurrentRoom(room3).
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.setCurrentRoom(room3) is true. 
	 *                 Return value of cmq.getCurrentRoom() is room3.
	 */
	@Test
	public void testSetCurrentRoom() 
	{
		assertEquals("Trying to set current room to room3", true, cmq.setCurrentRoom(room3));
		assertEquals("Trying to get the current room after setting it to room3", cmq.getCurrentRoom(), room3);
	}
	
	/**
	 * Test case for String processCommand("I").
	 * Preconditions: None.
	 * Execution steps: Call cmq.processCommand("I").
	 * Postconditions: Return value is "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n".
	 */
	@Test
	public void testProcessCommandI() 
	{
		when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
		
		assertEquals("Checking inventory when it is empty",cmq.processCommand("I"), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\n");
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some creamy cream!\n".
	 *                 player.addItem(Item.CREAM) is called.
	 */
	@Test
	public void testProcessCommandLCream() 
	{
		cmq.setCurrentRoom(room1);
		
		assertEquals("When in room with cream, we find cream with the l command",cmq.processCommand("l"), "There might be something here...\nYou found some creamy cream!\n");
		verify(player, times(1)).addItem(Item.CREAM);
	}
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room3) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some bitter coffee!\n".
	 *                 player.addItem(Item.COFFEE) is called.
	 */
	@Test
	public void testProcessCommandLCoffee() 
	{
		cmq.setCurrentRoom(room3);
		
		assertEquals("When in room with coffee, we find coffee with the l command",  cmq.processCommand("l"), "There might be something here...\nYou found some caffeinated coffee!\n");
		verify(player, times(1)).addItem(Item.COFFEE);
	}
	
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room6) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "There might be something here...\nYou found some sweet sugar!\n".
	 *                 player.addItem(Item.SUGAR) is called.
	 */
	@Test
	public void testProcessCommandLSugar() 
	{
		cmq.setCurrentRoom(room6);
		
		assertEquals("When in room with sugar, we find sugar with the l command",cmq.processCommand("l"), "There might be something here...\nYou found some sweet sugar!\n");
		verify(player, times(1)).addItem(Item.SUGAR);
	}
	
	
	/**
	 * Test case for String processCommand("l").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room2) has been called.
	 * Execution steps: Call cmq.processCommand("l").
	 * Postconditions: Return value is "You don't see anything out of the ordinary.\n".
	 */
	@Test
	public void testProcessCommandLNothing() 
	{
		cmq.setCurrentRoom(room2);
		
		assertEquals("When in room with nothing, find nothing",cmq.processCommand("l"), "You don't see anything out of the ordinary.\n");
	}
	
	/**
	 * Test case for String processCommand("n").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room4) has been called.
	 * Execution steps: Call cmq.processCommand("n").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("n") is "".
	 *                 Return value of cmq.getCurrentRoom() is room5.
	 */
	@Test
	public void testProcessCommandN() 
	{
		cmq.setCurrentRoom(room4);
		
		assertEquals("When hitting n, go n",cmq.processCommand("n"), "");
		assertEquals("current room is room5", room5, cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for String processCommand("s").
	 * Preconditions: room1 ~ room6 have been added to cmq.
	 *                cmq.setCurrentRoom(room1) has been called.
	 * Execution steps: Call cmq.processCommand("s").
	 *                  Call cmq.getCurrentRoom().
	 * Postconditions: Return value of cmq.processCommand("s") is "A door in that direction does not exist.\n".
	 *                 Return value of cmq.getCurrentRoom() is room1.
	 */
	@Test
	public void testProcessCommandS() 
	{
		cmq.setCurrentRoom(room1);
		
		assertEquals("When hitting s and s does not exist, throw error",cmq.processCommand("s"), "A door in that direction does not exist.\n" );
		assertEquals("current room is room1", room1, cmq.getCurrentRoom());
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has no items.
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n"
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDLose() 
	{
	  when(player.checkSugar()).thenReturn(false);
    when(player.checkCream()).thenReturn(false);
    when(player.checkCoffee()).thenReturn(false);
    when(player.getInventoryString()).thenReturn("YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!");
	  
		assertEquals("When drinking with nothing, respond no", cmq.processCommand("D"), "YOU HAVE NO COFFEE!\nYOU HAVE NO CREAM!\nYOU HAVE NO SUGAR!\nYou drink the air, as you have no coffee, sugar, or cream.\nThe air is invigorating, but not invigorating enough. You cannot study.\nYou lose!\n");
		assertEquals("game over is true", true, cmq.isGameOver());
	}
	
	/**
	 * Test case for String processCommand("D").
	 * Preconditions: Player has all 3 items (coffee, cream, sugar).
	 * Execution steps: Call cmq.processCommand("D").
	 *                  Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\n"
	 *                 Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testProcessCommandDWin() 
	{
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCream()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(true);
		when(player.getInventoryString()).thenReturn("You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.");
		
		assertEquals("When drinking with all 3 items, win game",cmq.processCommand("D") ,"You have a cup of delicious coffee.\nYou have some fresh cream.\nYou have some tasty sugar.\nYou drink the beverage and are ready to study!\nYou win!\n");
		assertEquals("game over is true", true, cmq.isGameOver());
	}
	
	// TODO: Put in more unit tests of your own making to improve coverage!
	
	/**
	 * Test case for checkMissing() when no room to the south. To do this since method is private,
	 *  utilize processCommand("H")
	 * Preconditions: Player starts in a room that has a room to the north of it and no room to the south of it.
	 * Execution steps: Call cmq.processCommand("H").
	 * Postconditions: Return value of cmq.processCommand("H") is "N - Go north\n" + 
        "L - Look and collect any items in the room\n" + 
        "I - Show inventory of items collected\n" + 
        "D - Drink coffee made from items in inventory\n"
	 */
	@Test
	public void testCheckMissingSouth()
	{
		assertEquals(true, cmq.setCurrentRoom(room1));
		assertEquals("When you can only go north",cmq.processCommand("H"), "N - Go north\n" + 
        "L - Look and collect any items in the room\n" + 
        "I - Show inventory of items collected\n" + 
        "D - Drink coffee made from items in inventory\n");
	}
	
	/**
	 * Test case for checkMissing() when no room to the north. To do this since method is private,
	 *  utilize processCommand("H")
	 * Preconditions: Player starts in a room that has a room to the south of it but no room to the north of it.
	 * Execution steps: Call cmq.processCommand("H").
	 * Postconditions: Return value of cmq.processCommand("H") is "S - Go south\n" + 
        "L - Look and collect any items in the room\n" + 
        "I - Show inventory of items collected\n" + 
        "D - Drink coffee made from items in inventory\n"
	 */
	@Test
	public void testCheckMissingNorth()
	{
		assertEquals(true, cmq.setCurrentRoom(room6));
		assertEquals("When you can only go south", cmq.processCommand("H"), "S - Go south\n" + 
		    "L - Look and collect any items in the room\n" + 
		    "I - Show inventory of items collected\n" + 
		    "D - Drink coffee made from items in inventory\n");
	}
	
	/**
	 * Test case for checkMissing() when there is a room to the north and south. To do this since method is private,
	 *  utilize processCommand("H")
	 * Preconditions: Player starts in a room that has a room to the south and north of it.
	 * Execution steps: Call cmq.processCommand("H").
	 * Postconditions: Return value of cmq.processCommand("H") is ""N - Go north\n" +  "S - Go south\n" +  "L - Look and collect any items in the room\n" +  "I - Show inventory of items collected\n" +  "D - Drink coffee made from items in inventory\n"
	 */
	@Test
	public void testCheckMissingNone()
	{
		assertEquals(true, cmq.setCurrentRoom(room2));
		assertEquals("When you can go north and south", cmq.processCommand("H"), "N - Go north\n" +  "S - Go south\n" +  "L - Look and collect any items in the room\n" +  "I - Show inventory of items collected\n" +  "D - Drink coffee made from items in inventory\n");
	}
	
	/**
	 * Test case for processCommand("bad") or when a bad command is processed
	 * Preconditions: Player starts at the entrance of the house.
	 * Execution steps: Call cmq.processCommand("bad").
	 * Postconditions: Return value of cmq.processCommand("H") is "What?"
	 */
	@Test
	public void testBadCommand()
	{
		assertEquals(true, cmq.setCurrentRoom(room2));
		assertEquals("When you process a bad command",cmq.processCommand("bad"), "What?");
	}
	
	/**
	 * Test case for processCommand("D") when user only has Sugar
	 * Preconditions: Player has Sugar but no Cream and Coffee.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "You drink the sweet sugar hoping for the bitter coffee and smooth cream, however, it never comes. You Lose."
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlySugar()
	{
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCream()).thenReturn(false);
		when(player.checkCoffee()).thenReturn(false);
		
		assertEquals("Checking Drink command only Sugar", cmq.processCommand("D"), "\nYou eat the sugar, but without caffeine, you cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}

	/**
	 * Test case for processCommand("D") when user only has Cream
	 * Preconditions: Player has Cream but no Sugar and Coffee.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n"
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlyCream()
	{
		when(player.checkSugar()).thenReturn(false);
		when(player.checkCream()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(false);
		
		
		assertEquals("Checking Drink command only Cream", cmq.processCommand("D"), "\nYou drink the cream, but without caffeine, you cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}

	/**
	 * Test case for processCommand("D") when user only has Coffee
	 * Preconditions: Player has Coffee but no Cream and Sugar.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n"
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlyCoffee()
	{
		when(player.checkSugar()).thenReturn(false);
		when(player.checkCream()).thenReturn(false);
		when(player.checkCoffee()).thenReturn(true);
		
		assertEquals("Checking Drink command only Coffee", cmq.processCommand("D"), "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}

	/**
	 * Test case for processCommand("D") when user only has Sugar and Cream
	 * Preconditions: Player has Sugar and Cream but no Coffee.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n"
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlySugarCream()
	{
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCream()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(false);
		
		assertEquals("Checking Drink command Sugar and Cream", cmq.processCommand("D"), "\nYou drink the sweetened cream, but without caffeine you cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}

	/**
	 * Test case for processCommand("D") when user only has Sugar and Coffee
	 * Preconditions: Player has Sugar and Coffee but no Cream.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n"
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlySugarCoffee()
	{
		when(player.checkSugar()).thenReturn(true);
		when(player.checkCream()).thenReturn(false);
		when(player.checkCoffee()).thenReturn(true);
		
		assertEquals("Checking Drink command Sugar and Coffee", cmq.processCommand("D"), "\nWithout cream, you get an ulcer and cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}

	/**
	 * Test case for processCommand("D") when user only has Cream and Coffee
	 * Preconditions: Player has Cream and Coffee but no Sugar.
	 * Execution steps: Call cmq.processCommand("D").
	 * 					Call cmq.isGameOver().
	 * Postconditions: Return value of cmq.processCommand("D") is "\nWithout sugar, the coffee is too bitter. You cannot study.\nYou lose!\n"
	 * 				   Return value of cmq.isGameOver() is true.
	 */
	@Test
	public void testDrinkOnlyCreamCoffee()
	{
		when(player.checkSugar()).thenReturn(false);
		when(player.checkCream()).thenReturn(true);
		when(player.checkCoffee()).thenReturn(true);
		
		assertEquals("Checking Drink command Cream and Coffee", cmq.processCommand("D"), "\nWithout sugar, the coffee is too bitter. You cannot study.\nYou lose!\n");
		assertEquals(cmq.isGameOver(), true);
	}
	
	/**
	 * Test case for processCommand("S") when user can move south into another room
	 * Preconditions: Player is in a room that has a room to the south of it.
	 * Execution steps: Call cmq.processCommand("S").
	 * Postconditions: Return value of cmq.processCommand("S") is ""
	 */
	@Test
	public void testMoveSouth()
	{
		cmq.setCurrentRoom(room2);
		assertEquals("Checking player can move south in house when possible", cmq.processCommand("S"), "");
	}

	/**
	 * Test case for setCurrentRoom() when user can move south into another room
	 * Preconditions: An amount of rooms have been added to the house
	 * Execution steps:  Create a new Room testRoom using mockito.
	 * 					 Call cmq.setCurrentRoom(testRoom).
	 * Postconditions: Return value of cmq.processCommand(testRoom) is false
	 */
	@Test
	public void testSetCurrentRoomNotInHouse()
	{
		Room testRoom = mock(Room.class);
		assertEquals("Checking setting current room when not in house", cmq.setCurrentRoom(testRoom), false);
	}

	/**
	 * Test case for addRoomAtNorth() when a door was null
	 * Preconditions: At least the first room has been added to the house
	 * Execution steps:  Create a new Room testRoom using mockito.
	 * 					 Call cmq.addRoomAtNorth(testRoom, null, null).
	 * 					 Call cmq.addRoomAtNorth(testRoom, "test", null).
	 * 					 Call cmq.addRoomAtNorth(testRoom, null, "test").
	 * Postconditions: Return value of cmq.addRoomAtNorth(testRoom, null, null) is false.
	 * 				   Return value of cmq.addRoomAtNorth(testRoom, "test", null) is false.
	 * 				   Return value of cmq.addRoomAtNorth(testRoom, null, "test") is false.
	 */
	@Test
	public void testRoomWithNullDoor()
	{
		Room testRoom = mock(Room.class);
		assertEquals("Checking adding a room north with a null door", cmq.addRoomAtNorth(testRoom, null, null), false);
		assertEquals("Checking adding a room north with a null door", cmq.addRoomAtNorth(testRoom, "test", null), false);
		assertEquals("Checking adding a room north with a null door", cmq.addRoomAtNorth(testRoom, null, "test"), false);
	}
}
