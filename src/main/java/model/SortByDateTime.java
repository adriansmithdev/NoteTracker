package model;

import java.util.Comparator;

public class SortByDateTime implements Comparator<INote> {

    @Override
    public int compare(INote o1, INote o2)
    {
        return o2.getDateCreated().compareTo(o1.getDateCreated());
    }
}
