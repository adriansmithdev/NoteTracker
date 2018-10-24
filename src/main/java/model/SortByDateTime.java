package model;

import java.util.Comparator;

public class SortByDateTime implements Comparator<INote> {

    @Override
    public int compare(INote o1, INote o2)
    {
        return o1.getDateCreated().compareTo(o2.getDateCreated());
    }
}
