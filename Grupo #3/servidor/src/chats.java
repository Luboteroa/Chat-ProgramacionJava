import java.util.LinkedList;

public class chats {
    private String nombre;
    private LinkedList<String> usuariosdentrodelchat=new LinkedList<>();
    public  chats(String nombre,String ipusuario){
        this.nombre=nombre;
        this.usuariosdentrodelchat.add(ipusuario);
    }
    //agregamos un nuevo usuario a la lista
    public void agregarUsuario(String nombreUsuaro){
        usuariosdentrodelchat.add(nombreUsuaro);
    }
    //devolvemos el nombre del chat
    public String getNombre(){
        return this.nombre;
    }
    //comprobamos si el usuario esta en el chat
    public boolean estaenelchat(String ipusuario){
        for(String index:usuariosdentrodelchat){
            if (index.equals(ipusuario)){
                return true;
            }
        }
        return false;
    }
    //entramos a un chat
    public void entrarchat(String ipusuario){
        if(!estaenelchat(ipusuario)){
            usuariosdentrodelchat.add(ipusuario);
        }
    }
    //salimos del chat
    public void salirchat(String ipusuario){
        for(int i=0;i<usuariosdentrodelchat.size();i++){
            if(usuariosdentrodelchat.get(i).equals(ipusuario)){
                usuariosdentrodelchat.remove(i);
            }
        }
    }
    //enviar a todos los que esten en el grupo
    public void send(String mensage,String ipusuario,LinkedList<conexiones> listaconexones ){
        for (conexiones index:listaconexones){
            if (estaenelchat(index.getDireccionip()) && !index.getDireccionip().equals(ipusuario)){
                index.envarMensaje(mensage);
                //System.out.println(index.getDireccionip());
            }
        }
    }
    public void mostrarusuarios(){
        for (String index : usuariosdentrodelchat) {
            System.out.println(index);
        }
    }
}
