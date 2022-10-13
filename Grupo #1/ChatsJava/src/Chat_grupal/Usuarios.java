package Chat_grupal;

import java.util.LinkedList;

public class Usuarios {

    public String Direccion;

    private LinkedList<String> Group;

    public Usuarios(String Grupo, String Direccion) {
        this.Direccion = Direccion;
        Group = new LinkedList<String>();
        Group.add(Grupo);
    }


    public String ConsultaGrupo(String Grupos)
    {
        if(knowsTo(Grupos))
        {
           return Direccion;
        }
        return Grupos;
    }

    public boolean knowsTo(String Groups)
    {
        return Group.contains(Groups);
    }
}
