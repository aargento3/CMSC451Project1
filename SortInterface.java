/**
 * Created by aargento on 8/25/17.
 */

public interface SortInterface {

    void recursiveSort(int[] list) throws UnsortedException;
    void iterativeSort(int[] list) throws UnsortedException;
    int getCount();
    long getTime();

}//end SortInterface
