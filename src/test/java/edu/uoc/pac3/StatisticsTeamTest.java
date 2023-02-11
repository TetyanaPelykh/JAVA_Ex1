package edu.uoc.pac3;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class StatisticsTeamTest {

    StatisticsTeam statisticsTeam;

    @BeforeAll
    void init() {
        try{
            statisticsTeam = new StatisticsTeam();
        }catch(Exception e) {
            fail("constructor failed");
        }
    }

    @Test
    @Order(1)
    void testStatisticsTeamInitialValues() {
        try{
            assertEquals(0, statisticsTeam.getWon());
            assertEquals(0, statisticsTeam.getDrawn());
            assertEquals(0, statisticsTeam.getLost());
            assertEquals(0, statisticsTeam.getGoalsFor());
            assertEquals(0, statisticsTeam.getGoalsAgainst());

        }catch(Exception e) {
            fail("failed");
        }
    }

    @Test
    @Order(2)
    void testWon() {
        try{
            statisticsTeam.setWon(5);
            assertEquals(5, statisticsTeam.getWon());
        }catch(Exception e) {
            fail("setWon failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	statisticsTeam.setWon(-1));
        assertEquals("[ERROR] StatisticsTeam's won must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    @Order(3)
    void testDrawn() {
        try{
            statisticsTeam.setDrawn(7);
            statisticsTeam.setDrawn(statisticsTeam.getDrawn() + 2);
            assertEquals(9, statisticsTeam.getDrawn());
        }catch(Exception e) {
            fail("setDrawn failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	statisticsTeam.setDrawn(-2));
        assertEquals("[ERROR] StatisticsTeam's drawn must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    @Order(4)
    void testLost() {
        try{
            statisticsTeam.setLost(3);
            assertEquals(3, statisticsTeam.getLost());
        }catch(Exception e) {
            fail("setLost failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	statisticsTeam.setLost(-2));
        assertEquals("[ERROR] StatisticsTeam's lost must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    @Order(5)
    void testGoalsFor() {
        try{
            statisticsTeam.incGoalsFor(6);
            assertEquals(6, statisticsTeam.getGoalsFor());
            statisticsTeam.incGoalsFor(3);
            statisticsTeam.incGoalsFor(8);
            assertEquals(17, statisticsTeam.getGoalsFor());

        }catch(Exception e) {
            fail("setGoalsFor failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	statisticsTeam.incGoalsFor(-2));
        assertEquals("[ERROR] The number of goalsFor that you want to add must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    @Order(6)
    void testGoalsAgainst() {
        try{
            statisticsTeam.incGoalsAgainst(3);
            assertEquals(3, statisticsTeam.getGoalsAgainst());
            statisticsTeam.incGoalsAgainst(6);
            assertEquals(9, statisticsTeam.getGoalsAgainst());

        }catch(Exception e) {
            fail("setGoalsAgainst failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	statisticsTeam.incGoalsAgainst(-1));
        assertEquals("[ERROR] The number of goalsAgainst that you want to add must be greater than or equal to 0", ex.getMessage());
    }

    @Test
    @Order(7)
    void testGamesPlayed() {
        try{
            assertEquals(17, statisticsTeam.getGamesPlayed());
            statisticsTeam.setWon(10);
            assertEquals(22, statisticsTeam.getGamesPlayed());

        }catch(Exception e) {
            fail("GamesPlayed failed");
        }
    }

    @Test
    @Order(8)
    void testGetGoalsDifference() {
        try{
            assertEquals(8, statisticsTeam.getGoalsDifference());
            statisticsTeam.incGoalsAgainst(15);
            assertEquals(-7, statisticsTeam.getGoalsDifference());

        }catch(Exception e) {
            fail("GetGoalsDifference failed");
        }
    }

    @Test
    @Order(9)
    void testPoints() {
        try{
            assertEquals(39, statisticsTeam.getPoints());
            statisticsTeam.setWon(2);
            statisticsTeam.setLost(3);
            statisticsTeam.setDrawn(2);
            assertEquals(8, statisticsTeam.getPoints());

        }catch(Exception e) {
            fail("Points failed");
        }
    }

    @Test
    @Order(9)
    void testGetStatisticsTeam() {
        try{
            assertEquals(10, statisticsTeam.getWon());
            assertEquals(9, statisticsTeam.getDrawn());
            assertEquals(3, statisticsTeam.getLost());
            assertEquals(17, statisticsTeam.getGoalsFor());
            assertEquals(24, statisticsTeam.getGoalsAgainst());
            assertEquals(22, statisticsTeam.getGamesPlayed());
            assertEquals(-7, statisticsTeam.getGoalsDifference());
            assertEquals(39, statisticsTeam.getPoints());
        }catch(Exception e) {
            fail("Points failed");
        }
    }
}