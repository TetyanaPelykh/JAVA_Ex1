package edu.uoc.pac3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class IntegrationTest {

    Team team;

    @BeforeAll
    void init() {
        try{
            Field field = Team.class.getDeclaredField("nextId");
            field.setAccessible(true);
            field.set(null, 0);
            team = new Team("Futbol Club Barcelona", "1899", "G08266298", "sac@fcbarcelona.com", 25);
        }catch(Exception e) {
            fail("Parameterized constructor failed");
        }
    }

    @Test
    @Order(1)
    void testTeamDefault() {
        try{
            Team t = new Team();
            assertEquals(1, t.getId());
            assertEquals("Lorem club", t.getName());
            assertEquals("2000", t.getFoundationYear());
            assertEquals("G12345678", t.getNif());
            assertEquals("info@yourmail.com", t.getEmail());
            assertEquals(21, t.getCapacity());
            assertNotNull(t.getStatisticsTeam());
            assertEquals(0, team.getStatisticsTeam().getWon());
            assertEquals(0, team.getStatisticsTeam().getDrawn());
            assertEquals(0, team.getStatisticsTeam().getLost());
            assertEquals(0, team.getStatisticsTeam().getGoalsFor());
            assertEquals(0, team.getStatisticsTeam().getGoalsAgainst());
            assertEquals(0, team.getStatisticsTeam().getGamesPlayed());
            assertEquals(0, team.getStatisticsTeam().getGoalsDifference());
            assertEquals(0, team.getStatisticsTeam().getPoints());
        }catch(Exception e) {
            fail("Default constructor failed");
        }
    }

    @Test
    @Order(2)
    void testTeam() {
        Exception ex = assertThrows(Exception.class, () -> new Team("Lorem ipsum dolor sit amet, consectetur erat curae.", "1899", "G08266298", "sac@fcbarcelona.com", 25));
        assertEquals("[ERROR] Team's name cannot be longer than 50 characters", ex.getMessage());

        ex = assertThrows(Exception.class, () -> new Team("Futbol Club Barcelona", "1899", "C08266298", "sac@fcbarcelona.com", 25));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () -> new Team("Futbol Club Barcelona", "1899", "08266298", "sac@fcbarcelona.com", 25));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () -> new Team("Futbol Club Barcelona", "1899", "08266298", "sacfcbarcelona.com", 25));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () -> new Team("Futbol Club Barcelona", "1899", "G08266298", "sac@fcbarcelona.com", 0));
        assertEquals("[ERROR] Team's capacity must be greater than 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.getStatisticsTeam().setWon(-9));
        assertEquals("[ERROR] StatisticsTeam's won must be greater than or equal to 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.getStatisticsTeam().setDrawn(-9));
        assertEquals("[ERROR] StatisticsTeam's drawn must be greater than or equal to 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.getStatisticsTeam().setLost(-9));
        assertEquals("[ERROR] StatisticsTeam's lost must be greater than or equal to 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.getStatisticsTeam().incGoalsFor(-9));
        assertEquals("[ERROR] The number of goalsFor that you want to add must be greater than or equal to 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.getStatisticsTeam().incGoalsAgainst(-9));
        assertEquals("[ERROR] The number of goalsAgainst that you want to add must be greater than or equal to 0", ex.getMessage());


    }

    @Test
    @Order(3)
    void testGetStatisticsTeam() {
        assertNotNull(team.getStatisticsTeam());
        assertEquals(0, team.getStatisticsTeam().getWon());
        assertEquals(0, team.getStatisticsTeam().getDrawn());
        assertEquals(0, team.getStatisticsTeam().getLost());
        assertEquals(0, team.getStatisticsTeam().getGoalsFor());
        assertEquals(0, team.getStatisticsTeam().getGoalsAgainst());
    }

    @Test
    @Order(4)
    void testSetGetStatisticsTeam() {

        try{
            team.getStatisticsTeam().setWon(5);
            team.getStatisticsTeam().setLost(1);
            team.getStatisticsTeam().setDrawn(7);
            team.getStatisticsTeam().incGoalsFor(16);
            team.getStatisticsTeam().incGoalsAgainst(11);

            assertEquals(5, team.getStatisticsTeam().getWon());
            assertEquals(1, team.getStatisticsTeam().getLost());
            assertEquals(7, team.getStatisticsTeam().getDrawn());
            assertEquals(16, team.getStatisticsTeam().getGoalsFor());
            assertEquals(11, team.getStatisticsTeam().getGoalsAgainst());
            assertEquals(13, team.getStatisticsTeam().getGamesPlayed());
            assertEquals(5, team.getStatisticsTeam().getGoalsDifference());
            assertEquals(22, team.getStatisticsTeam().getPoints());

        }catch(Exception e) {
            fail("setGetStatisticsTeam failed");
        }
    }


    @Test
    @Order(5)
    void testResetStatisticsTeam() {
        try{
            team.resetStatistics();
            assertNotNull(team.getStatisticsTeam());
            assertEquals(0, team.getStatisticsTeam().getWon());
            assertEquals(0, team.getStatisticsTeam().getDrawn());
            assertEquals(0, team.getStatisticsTeam().getLost());
            assertEquals(0, team.getStatisticsTeam().getGoalsFor());
            assertEquals(0, team.getStatisticsTeam().getGoalsAgainst());
            assertEquals(0, team.getStatisticsTeam().getGamesPlayed());
            assertEquals(0, team.getStatisticsTeam().getGoalsDifference());
            assertEquals(0, team.getStatisticsTeam().getPoints());
        }catch(Exception e) {
            fail("reset failed");
        }
    }
}