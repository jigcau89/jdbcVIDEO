package tienda.servicios;

import java.util.Collection;
import tienda.entidades.Fabricante;
import tienda.persistencia.FabricanteDAO;
/**
 * 
 * @author jigcau89
 */
public class FabricanteServicios {

    private FabricanteDAO daof;

    public FabricanteServicios() {
        this.daof = new FabricanteDAO();
    }

    public void crearFabricante(String nombre) throws Exception {
        try {
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar nombre del fabricante");
            }
           if (buscarFabricantePorNombre(nombre) != null) {
                throw new Exception("Ya existe un fabricante con ese codigo"+ nombre);
            }
            Fabricante fabricante = new Fabricante();
        
            fabricante.setNombre(nombre);
       
            daof.guardarFabricante(fabricante);

        } catch (Exception e) {
            throw e;
        }
    }

   public void modificarFabricante(Integer codigo, String nombre) throws Exception {
        try {
            //Validamos
            if (codigo == null || codigo.toString().isEmpty()) {
                throw new Exception("Debe indicar el codigo");
            }
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar nombre del fabricante");
            }
            //Buscamos
            Fabricante fabricante = buscarFabricantePorCodigo(codigo);
            //Validamos

            //Modificamos
            fabricante.setCodigo(codigo);
            fabricante.setNombre(nombre);

            daof.modificarFabricante(fabricante);
        } catch (Exception e) {
            throw e;
        }
    }

    public void eliminarFabricante(Integer codigo) throws Exception {

        try {

            //Validamos 
            if (codigo == null || codigo.toString().trim().isEmpty()) {
                throw new Exception("Debe indicar el codigo");
            }

            daof.eliminarFabricante(codigo);
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {
            //Validamos
            if (nombre == null || nombre.trim().isEmpty()) {
                throw new Exception("Debe indicar el nombre del fabricante");
            }
            Fabricante fabricante = daof.buscarFabricantePorNombre(nombre);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {

        try {

            //Validamos
            if (codigo == null) {
                throw new Exception("Debe indicar el codigo");
            }

            Fabricante fabricante = daof.buscarFabricantePorCodigo(codigo);

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricantes() throws Exception {

        try {

            Collection<Fabricante> fabricante = daof.listarFabricantes();

            return fabricante;
        } catch (Exception e) {
            throw e;
        }
    }

    public void imprimirFabricantes() throws Exception {

        try {

            //Listamos los fabricante
            Collection<Fabricante> fabricante = listarFabricantes();

            //Imprimimos los fabricantes
            if (fabricante.isEmpty()) {
                throw new Exception("No existen fabricantes para imprimir");
            } else {
                for (Fabricante f : fabricante) {
                    System.out.println(f);
                }
            }
        } catch (Exception e) {
            throw e;
        }
    }

    public Fabricante buscarProductoPorCodigo(Integer codigo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Fabricante buscarProductoPorNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
