package model;

import java.util.Comparator;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 * @version 1.0
 */
public class SortByDateTime implements Comparator<INote> {

    @Override
    public int compare(INote object1, INote object2) {
        return object2.getDateCreated().compareTo(object1.getDateCreated());
    }
}
