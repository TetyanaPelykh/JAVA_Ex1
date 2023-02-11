package edu.uoc.pac3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import java.lang.reflect.Field;

@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(OrderAnnotation.class)
class TeamTest {

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

    }

    @Test
    @Order(3)
    void testGetId() {
        assertEquals(0,team.getId());
    }

    @Test
    @Order(4)
    void testGetNextId() {
        assertEquals(2,Team.getNextId());
    }

    @Test
    @Order(5)
    void testGetName() {
        assertEquals("Futbol Club Barcelona", team.getName());
    }

    @Test
    @Order(6)
    void testSetName() {
        try{
            team.setName("FCB");
            assertEquals("FCB", team.getName());
        }catch(Exception e) {
            fail("setName failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	team.setName("Lorem ipsum dolor sit amet, consectetur vestibulum."));
        assertEquals("[ERROR] Team's name cannot be longer than 50 characters", ex.getMessage());
    }

    @Test
    @Order(7)
    void testGetFoundationYear() {
        assertEquals("1899", team.getFoundationYear());
    }

    @Test
    @Order(8)
    void testSetFoundationYear() {
        team.setFoundationYear("2000");
        assertEquals("2000",team.getFoundationYear());
    }


    @Test
    @Order(9)
    void testGetNif() {
        assertEquals("G08266298", team.getNif());
    }

    @Test
    @Order(10)
    void testSetNif() {
        try{
            team.setNif("B12345678");
            assertEquals("B12345678", team.getNif());

            team.setNif("A12345678");
            assertEquals("A12345678", team.getNif());
        }catch(Exception e) {
            fail("setNif failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	team.setNif("a12345678"));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () ->	team.setNif("C12345678"));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () ->	team.setNif("A1234567"));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () ->	team.setNif("B123456789"));
        assertEquals("[ERROR] Team's NIF pattern is incorrect", ex.getMessage());
    }

    @Test
    @Order(11)
    void testGetEmail() {
        assertEquals("sac@fcbarcelona.com", team.getEmail());
    }

    @Test
    @Order(12)
    void testSetEmail() {
        try{
            team.setEmail("fran@fcb.es");
            assertEquals("fran@fcb.es", team.getEmail());


        }catch(Exception e) {
            fail("setEmail failed");
        }
        Exception ex = assertThrows(Exception.class, () ->	team.setEmail("fmanez@uoc.edu"));
        assertEquals("[ERROR] Team's email pattern is incorrect", ex.getMessage());

        ex = assertThrows(Exception.class, () ->	team.setEmail("fmanezuoc.com"));
        assertEquals("[ERROR] Team's email pattern is incorrect", ex.getMessage());
    }

    @Test
    @Order(15)
    void testGetCapacity() {
        assertEquals(25,team.getCapacity());
    }

    @Test
    @Order(16)
    void testSetCapacity() {
        try{
            team.setCapacity(21);
            assertEquals(21,team.getCapacity());
        }catch(Exception e) {
            fail("setCapacity failed");
        }

        Exception ex = assertThrows(Exception.class, () ->	team.setCapacity(0));
        assertEquals("[ERROR] Team's capacity must be greater than 0", ex.getMessage());

        ex = assertThrows(Exception.class, () -> team.setCapacity(-10));
        assertEquals("[ERROR] Team's capacity must be greater than 0", ex.getMessage());
    }

    @Test
    @Order(17)
    void testGetInfo() {
        assertEquals("Name: FCB - Foundation year: 2000 - NIF: A12345678", team.getInfo());
    }
}