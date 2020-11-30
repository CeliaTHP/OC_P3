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
    public void toggleFavorite(Neighbour neighbour) {
        int position = neighbours.indexOf(neighbour);
        neighbours.get(position).setFavorite(!neighbour.isFavorite());
    }

    /**
     * get favorite neighbours list
     */
    @Override
    public List<Neighbour> getFavoriteNeighbours() {
        List<Neighbour> favoriteList = new ArrayList<>();
        for (Neighbour neighbour : neighbours) {
            if (neighbour.isFavorite()) {
                favoriteList.add(neighbour);
            }
        }
        return favoriteList;
    }
}
