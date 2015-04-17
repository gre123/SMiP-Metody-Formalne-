package factory;

import org.apache.commons.collections15.Factory;

/**
 * Najprostsza fabtyka krawędzi będących INTami. Zwraca koeljne numerki, żeby
 * się nie powtarzały ID, EDIT: ale się powtarzają przy kążdej nowej instancji
 * klasy, więc myszka się sypie
 *
 * @author Epifaniusz
 */
public class EdgeFactory implements Factory {

    private static int e = 0;
    
    public static void zeruj() {
        e = 0;
    }

    @Override
    public Integer create() {
        return (e++);
    }
}
