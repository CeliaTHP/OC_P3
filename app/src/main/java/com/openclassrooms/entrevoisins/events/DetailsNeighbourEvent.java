package com.openclassrooms.entrevoisins.events;

import com.openclassrooms.entrevoisins.model.Neighbour;

/**
 * Event fired when a user selects a Neighbour
 */
public class DetailsNeighbourEvent {

    /**
     * Neighbour to select
     */
    public Neighbour neighbour;

    /**
     * Constructor.
     *
     * @param neighbour
     */
    public DetailsNeighbourEvent(Neighbour neighbour) {
        this.neighbour = neighbour;
    }
}
