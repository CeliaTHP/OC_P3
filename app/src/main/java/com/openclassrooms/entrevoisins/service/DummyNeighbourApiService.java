package com.openclassrooms.entrevoisins.service;

import android.content.Intent;
import android.util.Log;

import com.openclassrooms.entrevoisins.model.Neighbour;
import com.openclassrooms.entrevoisins.ui.neighbour_list.NeighbourDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Dummy mock for the Api
 */
public class DummyNeighbourApiService implements NeighbourApiService {

    private List<Neighbour> neighbours = DummyNeighbourGenerator.generateNeighbours();
    private List<Neighbour> favorites = new ArrayList<>();


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Neighbour> getNeighbours() {
        return neighbours;
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteNeighbour(Neighbour neighbour) {
        neighbours.remove(neighbour);
    }

    /**
     * {@inheritDoc}
     *
     * @param neighbour
     */
    @Override
    public void createNeighbour(Neighbour neighbour) {
        neighbours.add(neighbour);
    }


    @Override
    public void addFavorite(Neighbour neighbour) {
        //on ajoute le voisin dans la liste des favoris
        if (!favorites.contains(neighbour)) {
            favorites.add(neighbour);
        }
    }

    @Override
    public void removeFavorite(Neighbour neighbour) {
        //on supprime le voisin dans la liste des favoris
        if (favorites.contains(neighbour)) {
            favorites.remove(neighbour);
        }
    }

    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favorites;
    }

    @Override
    public String checkIfNeighbourIsFav(Neighbour neighbour) {
        if (favorites.contains(neighbour))
           return (neighbour.getName().toString() + " is favorite");
        else
            return (neighbour.getName().toString() + " is not favorite");
    }


}
