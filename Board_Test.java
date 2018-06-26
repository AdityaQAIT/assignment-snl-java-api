package com.qainfotech.automation.Snl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONObject;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import junit.framework.Assert;

public class Board_Test {
	
	Board board;
	
	 
	public Board_Test() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		
		board=new Board();
	}
	
	public void RegisterFivePlayers(String user1,String user2,String user3,String user4,String user5) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException  {
		
		board.registerPlayer(user1);
		board.registerPlayer(user2);
		board.registerPlayer(user3);
		board.registerPlayer(user4);
		board.registerPlayer(user5);
		Assert.assertTrue(true);
		
	}
	
	
	public void RegisterSamePlayerTwice(String user1,String user2,String user3) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {

		board.registerPlayer(user1);
		board.registerPlayer(user2);
		board.registerPlayer(user3);
		Assert.assertTrue(true);
	}
	
	public void RegisterFourPlayers(String user1,String user2,String user3,String user4) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException
	{

		board.registerPlayer(user1);
		board.registerPlayer(user2);
		board.registerPlayer(user3);
		board.registerPlayer(user4);
		Assert.assertTrue(true);
	}

	public void DeletePlayerIfNotPresent() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {

		UUID id = UUID.randomUUID();
		board.deletePlayer(id);
		Assert.assertTrue(true);
	
	}
	
	public void ValidateIfRegisteredPlayerDeletedSuccessfully(String user1,String user2,String user3,String user4) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {
		RegisterFourPlayers(user1, user2, user3, user4);
		JSONObject data = board.getData();
		JSONObject Player = data.getJSONArray("players").getJSONObject(0);
		UUID id = (UUID)Player.get("uuid");
//		System.out.println(Player);
		board.deletePlayer(id);
		data=board.getData();
		boolean status=true;
		for (int i=0;i<data.getJSONArray("players").length();i++) {
			Player = data.getJSONArray("players").getJSONObject(i);
			String name = Player.getString("name");
			if(name.equals(user1))
				status=false;
			
		}
		Assert.assertTrue(status);
		
	}

	public void ValidateInvalidPlayerCannotRollDice(String user1,String user2,String user3,String user4) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		RegisterFourPlayers(user1, user2, user3, user4);
		JSONObject data = board.getData();
		JSONObject player = data.getJSONArray("players").getJSONObject(1);
		UUID player_Id = (UUID)player.get("uuid");
		board.rollDice(player_Id);
		Assert.assertTrue(true);


		
	}

	public void RegisterPlayerWhileGAmeInProgress(String Current_user1, String current_user2, String current_user3, String new_user) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		board.registerPlayer(current_user3);
		board.registerPlayer(current_user2);
		board.registerPlayer(Current_user1);
		JSONObject data = board.getData();
		JSONObject player = data.getJSONArray("players").getJSONObject(0);
		UUID player_Id = (UUID)player.get("uuid");
		board.rollDice(player_Id);
		board.registerPlayer(new_user);
		
	}

	public void ValidatePositionChange(String string) throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
	    board.registerPlayer(string);
	    JSONObject data = board.getData();
		JSONObject player = data.getJSONArray("players").getJSONObject(0);
		UUID player_Id = (UUID)player.get("uuid");
		int current_posotion = player.getInt("position");
		board.rollDice(player_Id);
		int ne_position= player.getInt("position");
	    Assert.assertTrue(ne_position!=current_posotion);
		
		
	    
		
	}
	
	
	
	
	

}
