package Testing;

/**
 * This class is used to test each package individually.
 * It only tests primitive functionality because most, if not all, 
 * of the functionality has to be tested visually.
 * 
 * @author Greg
 *
 */

public class TestHarness {
	private Test_StartMain test_startmain;
	private Test_Objects test_objects;
	private Test_NPC test_npc;
	private Test_HUD test_hud;
	private Test_GameState test_gamestate;
	private Test_Entities test_entities;
	private Test_Clue test_clue;
	private Test_Characters test_characters;
	
	
	public TestHarness() {
		this.test_characters = new Test_Characters();
		this.test_clue = new Test_Clue();
		this.test_entities = new Test_Entities();
		this.test_gamestate = new Test_GameState();
		this.test_hud = new Test_HUD();
		this.test_npc = new Test_NPC();
		this.test_objects = new Test_Objects();
		this.test_startmain = new Test_StartMain();
	}
	
	public boolean runtests() {
		boolean test_failed = false;
		test_failed = this.test_characters.run();
		test_failed = this.test_clue.run();
		test_failed = this.test_entities.run();
		test_failed = this.test_gamestate.run();
		test_failed = this.test_hud.run();
		test_failed = this.test_npc.run();
		test_failed = this.test_objects.run();
		test_failed = this.test_startmain.run();
		return test_failed;
	}
	
}
