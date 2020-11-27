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
        if(favorites.contains(neighbour))
            favorites.remove(neighbour);
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

    /**
     * check if neighbour is favorite
     */
    @Override
    public boolean isFavorite(Neighbour neighbour) {
        return getFavoriteNeighbours().contains(neighbour);
    }

    /**
     * add neighbour to favorite list
     */
    @Override
    public void addFavorite(Neighbour neighbour) {
        if (!favorites.contains(neighbour)) {
            favorites.add(neighbour);
        }
    }

    /**
     * remove neighbour from favorite list
     */
    @Override
    public void removeFavorite(Neighbour neighbour) {
        if (favorites.contains(neighbour)) {
            favorites.remove(neighbour);
        }
    }

    /**
     * get favorite neighbours list
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        return favorites;
    }

}
