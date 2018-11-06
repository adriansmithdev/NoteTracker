package model;

import java.util.Comparator;

/**
 * @author Adrian Smith
 * @author Kyle Johnson
 *
 * @version 1.0
 */
public class SortByDateTime implements Comparator<INote> {

    @Override
    public int compare(INote o1, INote o2)
    {
        return o2.getDateCreated().compareTo(o1.getDateCreated());
    }
}
