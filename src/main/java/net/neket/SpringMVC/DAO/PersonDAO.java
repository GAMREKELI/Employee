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
        return listpersone.stream().filter(p -> p.getID() == id).findAny().orElse(null);
    }

    public void deletePersone(int id) {
        listpersone.removeIf(p -> p.getID() == id);
    }

    public void update(Persone persone, int id) {
        listpersone.stream()
                .filter(p -> p.getID() == id)
                .forEach(p -> {
                    p.setFirstName(persone.getFirstName());
                    p.setLastName(persone.getLastName());
                    p.setAge(persone.getAge());
                });
    }
}
