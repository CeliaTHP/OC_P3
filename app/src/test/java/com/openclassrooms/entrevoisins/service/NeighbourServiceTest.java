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
/*● Phase 3 : Création d’un test unitaire pour chaque fonctionnalité. OK */

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
        //assert that our neighbour has been delete from our list
        assertFalse(service.getNeighbours().contains(neighbourToDelete));
    }

    /**
     * Create a neighbour
     */
    @Test
    public void createNeighbour() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.createNeighbour(neighbour);
        //assert that our neighbour has been added to the neighbour list
        assertTrue(service.getNeighbours().contains(neighbour));
    }

    /**
     * Get the favorite neighbour list
     */
    @Test
    public void getFavoriteNeighboursWithSuccess() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.toggleFavorite(neighbour);
        //doesn't work with toggle favorite
        //assert that our favorite list contains all favorite neighbours
        assertFalse(service.getFavoriteNeighbours().isEmpty());
    }

    /**
     * Add a neighbour to favorite list
     */
    @Test
    public void addNeighbourFavorite() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.toggleFavorite(neighbour);
        //assert that our favorite list size counts 1 item
        assertEquals(1, service.getFavoriteNeighbours().size());
    }

    /**
     * Delete a favorite neighbour
     */
    @Test
    public void deleteNeighbourFavorite() {
        Neighbour neighbour = new Neighbour(1, "Caroline", "https://i.pravatar.cc/350?u=a042581f4e29026704d", "lyon ; 5km",
                "+33 6 86 57 90 14", "Bonjour !Je souhaiterais faire de la marche nordique. Pas initiée, je recherche une ou plusieurs personnes susceptibles de m'accompagner !J'aime les jeux de cartes tels la belote et le tarot..");
        service.toggleFavorite(neighbour);
        Neighbour favNeighbourToDelete = service.getFavoriteNeighbours().get(0);
        service.deleteNeighbour(favNeighbourToDelete);
        //assert that our favorite neighbour has been deleted from our favorite list
        assertFalse(service.getFavoriteNeighbours().contains(favNeighbourToDelete));
    }
}
