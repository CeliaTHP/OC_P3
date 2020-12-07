package com.openclassrooms.entrevoisins.service;

import com.openclassrooms.entrevoisins.di.DI;
import com.openclassrooms.entrevoisins.model.Neighbour;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Unit test on Neighbour service
 */
@RunWith(JUnit4.class)
public class NeighbourServiceTest {

    private NeighbourApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }

    /**
     * Get the neighbour list
     */
    @Test
    public void getNeighboursWithSuccess() {
        List<Neighbour> neighbours = service.getNeighbours();
        List<Neighbour> expectedNeighbours = DummyNeighbourGenerator.DUMMY_NEIGHBOURS;
        //assert that our list contains all neighbours
        assertThat(neighbours, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedNeighbours.toArray()));
    }

    /**
     * Delete a neighbour
     */
    @Test
    public void deleteNeighbourWithSuccess() {
        Neighbour neighbourToDelete = service.getNeighbours().get(0);
        service.deleteNeighbour(neighbourToDelete);
        //assert that our neighbour has been deleted from our list
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Create a neighbour
     */
    @Test
    public void createNeighbour() {
        Neighbour neighbour = new Neighbour(19, "Zoe", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        assertFalse(service.getNeighbours().contains(neighbour));
        service.createNeighbour(neighbour);
        //assert that our neighbour has been added to the neighbour list
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    /**
     * Get the favorite neighbour list
     */
    @Test
    public void getFavoriteNeighboursWithSuccess() {
       Neighbour neighbour = service.getNeighbours().get(5);
        assertFalse(service.getFavoriteNeighbours().contains(neighbour));
        neighbour.setFavorite(true);
        //assert that our favorite list contains all favorite neighbours
        assertTrue(service.getFavoriteNeighbours().contains(neighbour));
    }

    /**
     * Add a neighbour to favorite list
     */
    @Test
    public void addNeighbourFavorite() {
        int favSize = service.getFavoriteNeighbours().size();
       Neighbour neighbour = service.getNeighbours().get(8);
        service.toggleFavorite(neighbour);
        //assert that our favorite list size counts 1 item
        assertEquals(favSize + 1, service.getFavoriteNeighbours().size());
    }

    /**
     * Delete a favorite neighbour
     */
    @Test
    public void deleteNeighbourFavorite() {
        Neighbour favNeighbourToDelete = service.getFavoriteNeighbours().get(0);
        assertTrue(service.getFavoriteNeighbours().contains(favNeighbourToDelete));
        service.deleteNeighbour(favNeighbourToDelete);
        //assert that our favorite neighbour has been deleted from our favorite list
        assertFalse(service.getFavoriteNeighbours().contains(favNeighbourToDelete));
    }
}
