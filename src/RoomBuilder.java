/*
 * @author Ethan Saum (2304185) @saumethan272 
 * 19/11/2024
 */

import java.util.ArrayList;

public abstract class RoomBuilder {

    // Variables 
    protected String description;
    protected ArrayList<ICHallenge> challenges;

    public RoomBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public abstract Room build();
}
