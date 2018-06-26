package com.qainfotech.automation.Snl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SnlTest {
	
	Board_Test boardtest;
	
	
	@BeforeMethod()
	public void initialise() throws FileNotFoundException, UnsupportedEncodingException, IOException {
		boardtest = new Board_Test();
	}
	
	@Test(expectedExceptions=PlayerExistsException.class)
	public void Step_1_EnterPlayerWithSameName() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardtest.RegisterSamePlayerTwice("Aditya","Anmol","Aditya");
	}
	
	@Test(expectedExceptions=MaxPlayersReachedExeption.class)
	public void EnterFiveplayesrPlayers() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
           boardtest.RegisterFivePlayers("Aditya","Anmol","Gaurav","Prince","Shivam");
		
	}
	
	@Test
	public void EnterValidNumberOfPlayers() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException {
		boardtest.RegisterFourPlayers("Aditya","Anmol","Gaurav","Prince");
	}
	
	@Test(expectedExceptions=NoUserWithSuchUUIDException.class)
	public void DeletePlayerWhoIsnotRegistered() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {
		boardtest.DeletePlayerIfNotPresent();		
	}
	
	@Test 
	public void DeleteRegisteredPalyed() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException {
		boardtest.ValidateIfRegisteredPlayerDeletedSuccessfully("Aditya","Anmol","Gaurav","Prince");
	}
	
	@Test(expectedExceptions=InvalidTurnException.class)
	public void InvalidPlayerRollTheDice() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		boardtest.ValidateInvalidPlayerCannotRollDice("Aditya","Anmol","Gaurav","Prince");
	}
	
	@Test(expectedExceptions=GameInProgressException.class)
	public void PlayerCannotRegisterWhileGameInProgress() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		boardtest.RegisterPlayerWhileGAmeInProgress("Aditya","Anmol","Gaurav","Prince");
	}
	
	@Test
	public void PositionChangeAfterCallingRollDiceFunction() throws FileNotFoundException, UnsupportedEncodingException, PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException, InvalidTurnException {
		boardtest.ValidatePositionChange("Aditya");
	}
	
	
	
	
	
	
	

}
