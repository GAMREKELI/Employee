package net.neket.SpringMVC.DAO;

import net.neket.SpringMVC.entity.Persone;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonDAO {
    private List<Persone> listpersone;
    private static int ID;

    {
        listpersone = new ArrayList<>();

        listpersone.add(new Persone("Никита", "Гамрекели", 21, ++ID));
        listpersone.add(new Persone("Иван", "Иванов", 21, ++ID));
        listpersone.add(new Persone("Артём", "Сидоров", 21, ++ID));
    }

    public List<Persone> getAll() {
        return listpersone;
    }

    public void addPersone(Persone persone) {
        persone.setID(++ID);
        listpersone.add(persone);
    }

    public Persone getPersone(int id) {
        for (int i = 0; i < listpersone.size(); i ++) {
            if (i == (id - 1))
                return listpersone.get(i);
        }
        return null;
    }

    public int deletePersone(int id) {
        for (int i = 0; i < listpersone.size(); i ++) {
            if (i == (id - 1)) {
                listpersone.remove(id - 1);
                return 1;
            }
        }
        return -1;

    }
}
