package tienda.persistencia;

import java.util.ArrayList;
import java.util.Collection;
import tienda.entidades.Fabricante;

/**
 *
 * @author jigcau89
 */
public final class FabricanteDAO extends DAO {

    public void guardarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricnte");
            }

            String sql = "INSERT INTO fabricante (nombre) VALUES ('"+ fabricante.getNombre() +"');";

            System.out.println("Fabricante"+ fabricante.getNombre()+" agregado correctamente" + sql);
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }

    }

    public void modificarFabricante(Fabricante fabricante) throws Exception {
        try {
            if (fabricante == null) {
                throw new Exception("Debe indicar el fabricante que desea modificar");
            }
            String sql = "UPDATE Fabricante SET "
                    + " codigo = '" + fabricante.getCodigo() + "' , nombre = '" + fabricante.getNombre()
                    + " WHERE id = '" + fabricante.getCodigo() + "'";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public void eliminarFabricante(Integer codigo) throws Exception {
        try {
            String sql = "DELETE FROM Fabricante WHERE codigo = " + codigo + "";
            insertarModificarEliminar(sql);
        } catch (Exception e) {
            throw e;
        } finally {
            desconectarBase();
        }
    }

    public static Fabricante buscarFabricantePorCodigo(Integer codigo) throws Exception {
        try {
            String sql = "SELECT * FROM Fabricante "
                    + " WHERE codigo = '" + codigo + "'";
            consultarBase(sql);
            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Fabricante buscarFabricantePorNombre(String nombre) throws Exception {
        try {

            String sql = "SELECT * FROM Usuario "
                    + " WHERE nombre = '" + nombre + "'";

            consultarBase(sql);

            Fabricante fabricante = null;
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));

            }
            desconectarBase();
            return fabricante;
        } catch (Exception e) {
            desconectarBase();
            throw e;
        }
    }

    public Collection<Fabricante> listarFabricantes() throws Exception {
        try {
            String sql = "SELECT codigo, nombre FROM Fabricante ";

            consultarBase(sql);

            Fabricante fabricante = null;
            Collection<Fabricante> fabricantes = new ArrayList();
            while (resultado.next()) {
                fabricante = new Fabricante();
                fabricante.setCodigo(resultado.getInt(1));
                fabricante.setNombre(resultado.getString(2));
                fabricantes.add(fabricante);
            }
            desconectarBase();
            return fabricantes;
        } catch (Exception e) {
            e.printStackTrace();
            desconectarBase();
            throw new Exception("Error de sistema");
        }
    }

}
